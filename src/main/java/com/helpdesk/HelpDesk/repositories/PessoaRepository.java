package com.helpdesk.HelpDesk.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpdesk.HelpDesk.domains.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	Optional<Pessoa> findByCpf(String cpf);
	
	Optional<Pessoa> findByEmail(String email);

}
