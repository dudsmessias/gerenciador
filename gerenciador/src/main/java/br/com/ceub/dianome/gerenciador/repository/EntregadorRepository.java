package br.com.ceub.dianome.gerenciador.repository;

import br.com.ceub.dianome.gerenciador.entity.Entregador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador, Integer> {
    Optional<Entregador> findByCpf(String cpf);
}
