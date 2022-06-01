package com.example.viacep.endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    //Create a find endereco by cep function
    @Query("SELECT e FROM Endereco e WHERE e.cep = ?1")
    Optional<Endereco> findEnderecoByCep(String cep);
}

