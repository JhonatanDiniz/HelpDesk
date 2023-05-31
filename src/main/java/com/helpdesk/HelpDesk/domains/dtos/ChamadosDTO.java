package com.helpdesk.HelpDesk.domains.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.HelpDesk.domains.Chamado;

import jakarta.validation.constraints.NotNull;

public class ChamadosDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtAbertura = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtFechamento;

	@NotNull(message = "O campo PRIORIDADE é obrigatório!")
	private Integer prioridade;
	
	@NotNull(message = "O campo STATUS é obrigatório!")
	private Integer status;
	
	@NotNull(message = "O campo TITULO é obrigatório!")
	private String titulo;
	
	@NotNull(message = "O campo OBSERVAÇÕES é obrigatório!")
	private String observacoes;
	
	@NotNull(message = "O campo CLIENTE é obrigatório!")
	private Long cliente;
	
	@NotNull(message = "O campo TECNICO é obrigatório!")
	private Long tecnico;
	private String nomeCliente;
	private String nomeTecnico;

	public ChamadosDTO() {
		super();
	}

	public ChamadosDTO(Chamado obj) {
		id = obj.getId();
		dtAbertura = obj.getDtAbertura();
		dtFechamento = obj.getDtFechamento();
		prioridade = obj.getPrioridade().getCodigo();
		status = obj.getStatus().getCodigo();
		titulo = obj.getTitulo();
		observacoes = obj.getObservacoes();
		cliente = obj.getCliente().getId();
		tecnico = obj.getTecnico().getId();
		nomeCliente = obj.getCliente().getNome();
		nomeTecnico = obj.getTecnico().getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDtAbertura() {
		return dtAbertura;
	}

	public void setDtAbertura(LocalDate dtAbertura) {
		this.dtAbertura = dtAbertura;
	}

	public LocalDate getDtFechamento() {
		return dtFechamento;
	}

	public void setDtFechamento(LocalDate dtFechamento) {
		this.dtFechamento = dtFechamento;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Long getCliente() {
		return cliente;
	}

	public void setCliente(Long cliente) {
		this.cliente = cliente;
	}

	public Long getTecnico() {
		return tecnico;
	}

	public void setTecnico(Long tecnico) {
		this.tecnico = tecnico;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeTecnico() {
		return nomeTecnico;
	}

	public void setNomeTecnico(String nomeTecnico) {
		this.nomeTecnico = nomeTecnico;
	}

}
