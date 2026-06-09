package furb.web2.config;

import furb.web2.model.Usuario;
import furb.web2.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (repository.findByLogin("admin").isEmpty()) {
            Usuario admin = new Usuario("Administrador", "admin", passwordEncoder.encode("admin123"));
            repository.save(admin);
            System.out.println("Usuário admin criado com senha: admin123");
        }
    }
}
