package com.helpdesk.HelpDesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpdesk.HelpDesk.domains.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

}
