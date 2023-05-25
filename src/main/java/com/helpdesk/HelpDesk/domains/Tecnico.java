package com.helpdesk.HelpDesk.domains;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.helpdesk.HelpDesk.domains.dtos.TecnicoDTO;
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
	
	public Tecnico(TecnicoDTO obj) {
		super();
		id = obj.getId();
		nome = obj.getNome();
		cpf = obj.getCpf();
		email = obj.getEmail();
		password = obj.getPassword();
		perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		dtCadastro = obj.getDtCadastro();
		addPerfil(Perfil.TECNICO);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}

}
