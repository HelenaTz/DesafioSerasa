package com.serasa.basea.repository;

import com.serasa.basea.usuario.UsuarioBaseA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioBaseARepositorio extends JpaRepository<UsuarioBaseA, Long> {

    @Query(value = "SELECT cpf FROM usuario_base_a WHERE cpf = :cpf", nativeQuery = true)
    Optional<UsuarioBaseA> findByCpf(String cpf);

}