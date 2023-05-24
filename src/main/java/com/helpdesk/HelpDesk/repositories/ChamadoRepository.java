package com.helpdesk.HelpDesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpdesk.HelpDesk.domains.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

}
