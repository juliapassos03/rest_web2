package furb.web2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categorias")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(nullable = false, unique = true)

    private String nome;

    @JsonIgnore
    @ManyToMany(mappedBy = "categorias")

    private List<Jogo> jogos;
}