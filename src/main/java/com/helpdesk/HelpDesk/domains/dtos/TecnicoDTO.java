package com.helpdesk.HelpDesk.domains.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.HelpDesk.domains.Tecnico;
import com.helpdesk.HelpDesk.domains.enums.Perfil;

public class TecnicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Long id;
	protected String nome;
	protected String cpf;
	protected String email;
	protected String password;
	protected Set<Integer> perfis = new HashSet<>();

	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dtCadastro = LocalDate.now();

	public TecnicoDTO() {
		super();
		addPerfis(Perfil.TECNICO);
	}

	public TecnicoDTO(Tecnico obj) {
		super();
		id = obj.getId();
		nome = obj.getNome();
		cpf = obj.getCpf();
		email = obj.getEmail();
		password = obj.getPassword();
		perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		dtCadastro = obj.getDtCadastro();
		addPerfis(Perfil.TECNICO);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfis(Perfil perfis) {
		this.perfis.add(perfis.getCodigo());
	}

	public LocalDate getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(LocalDate dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

}
