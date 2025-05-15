package com.serasa.baseb.repository;

import com.serasa.baseb.usuario.UsuarioBaseB;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UsuarioBaseBRepositorio extends MongoRepository<UsuarioBaseB, String> {
    Optional<UsuarioBaseB> findByCpf(String cpf);
}