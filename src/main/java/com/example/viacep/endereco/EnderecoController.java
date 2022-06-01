package com.example.viacep.endereco;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    private final EnderecoService enderecoService;
    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public List<Endereco> getAllCep() {
        return enderecoService.getAllCep();
    }

    @GetMapping("/viacep/{cep}")
    public Endereco getCep(@PathVariable String cep) {
        final String uri = String.format("https://viacep.com.br/ws/%s/json/", cep);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        assert result != null;
        Endereco endereco = Endereco.fromJson(result);
        enderecoService.addNewCep(endereco);
        return endereco;
    }

    @GetMapping("/viacep2/{cep}")
    public Endereco getCep2(@PathVariable String cep) {
        String url = String.format("https://viacep.com.br/ws/%s/json/", cep);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        Gson gson = new Gson();
        return gson.fromJson(result, Endereco.class);
    }

    @PostMapping
    public void addNewCep(@RequestBody Endereco endereco) {
        enderecoService.addNewCep(endereco);
    }

    @GetMapping("/viacep2/addCep")
    @ResponseBody
    public Endereco addNewCep2(@RequestParam String cep){
        Endereco addCep = getCep2(cep);
        return enderecoService.addNewCep(addCep);
    }
}
