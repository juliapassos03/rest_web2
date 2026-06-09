package furb.web2.service;

import furb.web2.exception.ParametroObrigatorioException;
import furb.web2.exception.RegistroNaoEncontradoException;
import furb.web2.model.Usuario;
import furb.web2.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = repository.findByLogin(login)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + login));

        return User.builder()
            .username(usuario.getLogin())
            .password(usuario.getSenha())
            .roles("USER")
            .build();
    }

    public Usuario salvar(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isBlank())
            throw new ParametroObrigatorioException("nome");
        if (usuario.getLogin() == null || usuario.getLogin().isBlank())
            throw new ParametroObrigatorioException("login");
        if (usuario.getSenha() == null || usuario.getSenha().isBlank())
            throw new ParametroObrigatorioException("senha");

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RegistroNaoEncontradoException("Usuário", id));
    }

    public Usuario atualizar(Long id, Usuario dados) {
        Usuario usuario = buscarPorId(id);

        if (dados.getNome() != null && !dados.getNome().isBlank())
            usuario.setNome(dados.getNome());
        if (dados.getLogin() != null && !dados.getLogin().isBlank())
            usuario.setLogin(dados.getLogin());
        if (dados.getSenha() != null && !dados.getSenha().isBlank())
            usuario.setSenha(passwordEncoder.encode(dados.getSenha()));

        return repository.save(usuario);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
