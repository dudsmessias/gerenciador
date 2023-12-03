package br.com.ceub.dianome.gerenciador.service;

import br.com.ceub.dianome.gerenciador.dto.EntregadorDTO;
import br.com.ceub.dianome.gerenciador.dto.EntregadorUpdateDTO;
import br.com.ceub.dianome.gerenciador.entity.Entregador;
import br.com.ceub.dianome.gerenciador.repository.EntregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class EntregadorService {

    @Autowired
    private EntregadorRepository entregadorRepository;

    public EntregadorDTO cadastrar(EntregadorDTO dto) {
        try {
            Entregador entity = new Entregador();
            entity.setCpf(dto.cpf());
            entity.setNome(dto.nome());
            entity.setCapacidadeDeCargo(dto.capacidadeDeCargo());

            Entregador saved = entregadorRepository.save(entity);

            return new EntregadorDTO(saved.getNome(), saved.getCpf(), saved.getCapacidadeDeCargo());

        } catch (Exception exception) {
            throw new RuntimeException("Erro ao cadastrar entregador");
        }
    }

    public List<EntregadorDTO> listar() {
        List<Entregador> entregadores = entregadorRepository.findAll();
        return entregadores
                .stream()
                .map(ent -> new EntregadorDTO(ent.getNome(), ent.getCpf(), ent.getCapacidadeDeCargo()))
                .toList();
    }

    public EntregadorDTO detalhar(String cpf) {
        Entregador entregador = entregadorRepository.findByCpf(cpf)
                .orElseThrow();
        return new EntregadorDTO(entregador.getNome(), entregador.getCpf(), entregador.getCapacidadeDeCargo());
    }

    public void alterar(String cpf, EntregadorUpdateDTO dto) {
        Entregador entregador = entregadorRepository.findByCpf(cpf)
                .orElseThrow();
        if (nonNull(dto.nome())) {
            entregador.setNome(dto.nome());
        }
        if (nonNull(dto.capacidadeDeCarga())) {
            entregador.setCapacidadeDeCargo(dto.capacidadeDeCarga());
        }
        entregadorRepository.save(entregador);
    }

    public void remover(String cpf) {
        Entregador entregador = entregadorRepository.findByCpf(cpf)
                .orElseThrow();
        entregadorRepository.delete(entregador);
    }
}
