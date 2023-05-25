package com.helpdesk.HelpDesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.HelpDesk.domains.Tecnico;
import com.helpdesk.HelpDesk.repositories.TecnicoRepository;
import com.helpdesk.HelpDesk.services.exceptions.ObjectNotFoudException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;

	public Tecnico findById(Long id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoudException("Objeto não encontrado! id: " + id));
	}
	
	public List<Tecnico> findAll(){
		return repository.findAll();
	}

}
