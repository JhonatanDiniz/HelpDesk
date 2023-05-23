package com.helpdesk.HelpDesk.domains;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.helpdesk.HelpDesk.domains.enums.Prioridade;
import com.helpdesk.HelpDesk.domains.enums.Status;

public class Chamado implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private LocalDate dtAbertura = LocalDate.now();
	private LocalDate dtFechamento;
	private Prioridade prioridade;
	private Status status;
	private String titulo;
	private String observacoes;

	private Cliente cliente;
	private Tecnico tecnico;

	public Chamado() {
		super();
	}

	public Chamado(Long id, LocalDate dtFechamento, Prioridade prioridade, Status status, String titulo,
			String observacoes, Cliente cliente, Tecnico tecnico) {
		super();
		this.id = id;
		this.dtFechamento = dtFechamento;
		this.prioridade = prioridade;
		this.status = status;
		this.titulo = titulo;
		this.observacoes = observacoes;
		this.cliente = cliente;
		this.tecnico = tecnico;
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

	public LocalDate getDtFechamento() {
		return dtFechamento;
	}

	public void setDtFechamento(LocalDate dtFechamento) {
		this.dtFechamento = dtFechamento;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chamado other = (Chamado) obj;
		return Objects.equals(id, other.id);
	}

}
