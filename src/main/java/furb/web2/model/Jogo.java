package furb.web2.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "jogos")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(nullable = false)

    private String nome;

    private String descricao;

    @ManyToMany

    @JoinTable(
            name = "jogo_categoria",

            joinColumns =
            @JoinColumn(name = "jogo_id"),

            inverseJoinColumns =
            @JoinColumn(name = "categoria_id")
    )

    private List<Categoria> categorias;
}