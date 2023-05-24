package com.helpdesk.HelpDesk.domains;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.HelpDesk.domains.enums.Prioridade;
import com.helpdesk.HelpDesk.domains.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Chamado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtAbertura = LocalDate.now();
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dtFechamento;
	
	private Prioridade prioridade;
	
	private Status status;
	
	private String titulo;
	
	private String observacoes;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "tecnico_id")
	private Tecnico tecnico;

	public Chamado() {
		super();
	}

	public Chamado(Long id, Prioridade prioridade, Status status, String titulo,
			String observacoes, Tecnico tecnico, Cliente cliente) {
		super();
		this.id = id;
		this.prioridade = prioridade;
		this.status = status;
		this.titulo = titulo;
		this.observacoes = observacoes;
		this.tecnico = tecnico;
		this.cliente = cliente;
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
