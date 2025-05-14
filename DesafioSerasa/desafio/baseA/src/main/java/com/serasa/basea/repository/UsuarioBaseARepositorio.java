package com.serasa.basea.repository; // Pacote correto

import com.serasa.basea.usuario.UsuarioBaseA; // Importação correta da entidade
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioBaseARepositorio extends JpaRepository<UsuarioBaseA, Long> {

    @Query("SELECT u FROM UserDataA u WHERE u.cpf = ?1")
    Optional<UsuarioBaseA> encontraCpf(String cpf);

    @Query(value = "SELECT cpf FROM user_data_a WHERE id = :id", nativeQuery = true)
    String getCpfPeloId(Long id);
}