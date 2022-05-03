package com.algaworks.msr.algalogapi.api.controller;

//Importações:
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.algaworks.msr.algalogapi.api.assembler.EntregaAssembler;
import com.algaworks.msr.algalogapi.api.model.dto.EntregaDTO;
import com.algaworks.msr.algalogapi.api.model.input.EntregaInput;
import com.algaworks.msr.algalogapi.domain.model.Entrega;
import com.algaworks.msr.algalogapi.domain.repository.EntregaRepository;
import com.algaworks.msr.algalogapi.domain.service.usecase.FinalizacaoEntregaService;
import com.algaworks.msr.algalogapi.domain.service.usecase.SolicitacaoEntregaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
    //Atributos:
    private SolicitacaoEntregaService solicitacaoEntregaService;
    private FinalizacaoEntregaService finalizacaoEntregaService;
    private EntregaRepository entregaRepository;
    private EntregaAssembler entregaAssembler;

    //Métodos:
    @PostMapping
    public ResponseEntity<EntregaDTO> solicitar(@Valid @RequestBody EntregaInput entregaInputRequisicao) {
        Entrega entregaRequisicao = entregaAssembler.toEntity(entregaInputRequisicao);
        Entrega entregaBanco = solicitacaoEntregaService.solicitar(entregaRequisicao);
        EntregaDTO entregaBancoDTO = entregaAssembler.toDTO(entregaBanco);

        return ResponseEntity.status(HttpStatus.CREATED).body(entregaBancoDTO);
    }

    @GetMapping
    public List<EntregaDTO> buscar() {
        return entregaAssembler.toDTO(entregaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaDTO> buscar(@PathVariable Long id){
        Optional<Entrega> entregaBanco = entregaRepository.findById(id);

        if(entregaBanco.isPresent()){
            EntregaDTO entregaBancoDTO = entregaAssembler.toDTO(entregaBanco.get());
            return ResponseEntity.status(HttpStatus.OK).body(entregaBancoDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}/finalizacao")
    public ResponseEntity<Void> finalizar(@PathVariable Long id){
        finalizacaoEntregaService.finalizar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } 
}