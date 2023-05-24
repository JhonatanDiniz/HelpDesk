package com.helpdesk.HelpDesk.domains;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.helpdesk.HelpDesk.domains.enums.Perfil;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Tecnico extends Pessoa {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "tecnico")
	private List<Chamado> chamados = new ArrayList<>();

	public Tecnico() {
		super();
		addPerfil(Perfil.TECNICO);
	}

	public Tecnico(Long id, String nome, String cpf, String email, String password) {
		super(id, nome, cpf, email, password);
		addPerfil(Perfil.TECNICO);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

}
