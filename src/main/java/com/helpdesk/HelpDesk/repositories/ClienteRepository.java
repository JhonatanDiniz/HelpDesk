package com.helpdesk.HelpDesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helpdesk.HelpDesk.domains.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
