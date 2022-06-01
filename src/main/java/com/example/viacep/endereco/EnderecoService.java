package com.example.viacep.endereco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<Endereco> getAllCep() {
        return enderecoRepository.findAll();
    }

    public Endereco addNewCep(Endereco endereco) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findEnderecoByCep(endereco.getCep());
        return enderecoOptional.orElseGet(() -> enderecoRepository.save(endereco));
    }
}
