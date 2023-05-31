package com.helpdesk.HelpDesk.domains.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.helpdesk.HelpDesk.domains.Cliente;
import com.helpdesk.HelpDesk.domains.enums.Perfil;

import jakarta.validation.constraints.NotNull;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Long id;

	@NotNull(message = "O campo NOME é obrigatório!")
	protected String nome;

	@NotNull(message = "O campo CPF é obrigatório!")
	@CPF
	protected String cpf;

	@NotNull(message = "O campo EMAIL é obrigatório!")
	protected String email;

	@NotNull(message = "O campo PASSWORD é obrigatório!")
	protected String password;

	protected Set<Integer> perfis = new HashSet<>();

	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dtCadastro = LocalDate.now();

	public ClienteDTO() {
		super();
		addPerfis(Perfil.CLIENTE);
	}

	public ClienteDTO(Cliente obj) {
		super();
		id = obj.getId();
		nome = obj.getNome();
		cpf = obj.getCpf();
		email = obj.getEmail();
		password = obj.getPassword();
		addPerfis(Perfil.CLIENTE);
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

	public void addPerfis(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}

	public LocalDate getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(LocalDate dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

}
