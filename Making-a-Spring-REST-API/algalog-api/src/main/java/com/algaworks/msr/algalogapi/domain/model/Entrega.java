package com.algaworks.msr.algalogapi.domain.model;

//Importações:
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algaworks.msr.algalogapi.domain.exception.NegocioException;
import com.algaworks.msr.algalogapi.domain.model.ValidationsGroups.ClienteId;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "entregas")
public class Entrega {
    //Atributos:
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @ConvertGroup(from = Default.class, to = ClienteId.class)
    @NotNull
    @Valid
    private Cliente cliente;

    @Embedded
    @NotNull 
    @Valid
    private Destinatario destinatario;

    @NotNull
    private BigDecimal taxa;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();

    @Enumerated(EnumType.STRING)  
    private Situacao situacao;

    private OffsetDateTime dataPedido;
    
    private OffsetDateTime dataFinalizacao;

    //Métodos:
    public Ocorrencia registrarOcorrencia(String descricao) {
        Ocorrencia novaOcorrencia = new Ocorrencia();

        novaOcorrencia.setDescricao(descricao);
        novaOcorrencia.setDataOcorrencia(OffsetDateTime.now());
        novaOcorrencia.setEntrega(this);

        this.getOcorrencias().add(novaOcorrencia);

        return novaOcorrencia;
    }

    //Métodos:
    public void finalizar() {
        if(!this.podeFinalizar()){
            throw new NegocioException("Essa entrega não pode ser finalizada");
        }

        this.setSituacao(Situacao.FINALIZADA);
        this.setDataFinalizacao(OffsetDateTime.now());
    }
 
    public boolean podeFinalizar(){
        return this.getSituacao().equals(Situacao.PENDENTE);
    }
}