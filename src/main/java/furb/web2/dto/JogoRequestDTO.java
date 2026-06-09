package furb.web2.dto;

import java.util.List;

/**
 * DTO para receber dados de criação/atualização de Jogo.
 * O cliente envia apenas os IDs das categorias, não o objeto completo.
 */
public class JogoRequestDTO {

    private String nome;
    private String descricao;
    private List<Long> categoriaIds;

    public JogoRequestDTO() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public List<Long> getCategoriaIds() { return categoriaIds; }
    public void setCategoriaIds(List<Long> categoriaIds) { this.categoriaIds = categoriaIds; }
}
