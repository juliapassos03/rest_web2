package furb.web2.repository;

import furb.web2.model.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository
        extends JpaRepository<Categoria, Long> {
}