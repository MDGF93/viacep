package com.example.viacep.endereco;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnderecoConfig {

    @Bean
    CommandLineRunner commandLineRunner(EnderecoRepository enderecoRepository) {
        return args -> {
            Endereco endereco1 = new Endereco("58102-051", "Avenida Mar Negro", "", "Intermares", "Cabedelo", "PB", "2503209", "", "83", "1965");
            enderecoRepository.save(endereco1);
        };
    }
}
