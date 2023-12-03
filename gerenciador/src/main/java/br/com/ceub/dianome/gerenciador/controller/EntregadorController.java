package br.com.ceub.dianome.gerenciador.controller;

import br.com.ceub.dianome.gerenciador.dto.EntregadorDTO;
import br.com.ceub.dianome.gerenciador.dto.EntregadorUpdateDTO;
import br.com.ceub.dianome.gerenciador.service.EntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("entregadores")
public class EntregadorController {

    @Autowired
    private EntregadorService service;


    @PostMapping
    public EntregadorDTO cadastrar(@RequestBody EntregadorDTO dto){
        return service.cadastrar(dto);
    }

    @GetMapping
    public List<EntregadorDTO> listar(){
        return service.listar();
    }

    @GetMapping("{cpf}")
    public EntregadorDTO detalhar(@PathVariable String cpf){
        return service.detalhar(cpf);
    }

    @PatchMapping("{cpf}")
    public void alterar(@PathVariable String cpf, @RequestBody EntregadorUpdateDTO dto){
        service.alterar(cpf, dto);
    }

    @DeleteMapping("{cpf}")
    public void deletar(@PathVariable String cpf){
        service.remover(cpf);
    }

}
