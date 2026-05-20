package furb.web2.repository;

import furb.web2.model.Jogo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository
        extends JpaRepository<Jogo, Long> {
}