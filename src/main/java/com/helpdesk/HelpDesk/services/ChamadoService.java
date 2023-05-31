package com.helpdesk.HelpDesk.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.HelpDesk.domains.Chamado;
import com.helpdesk.HelpDesk.domains.Cliente;
import com.helpdesk.HelpDesk.domains.Tecnico;
import com.helpdesk.HelpDesk.domains.dtos.ChamadosDTO;
import com.helpdesk.HelpDesk.domains.enums.Prioridade;
import com.helpdesk.HelpDesk.domains.enums.Status;
import com.helpdesk.HelpDesk.repositories.ChamadoRepository;
import com.helpdesk.HelpDesk.services.exceptions.ObjectNotFoudException;

import jakarta.validation.Valid;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private TecnicoService tecnicoService;

	public Chamado findById(Long id) {
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoudException("Objeto não encontrado! Id: " + id));
	}

	public List<Chamado> findAll() {
		List<Chamado> obj = repository.findAll();
		return obj;
	}

	public Chamado create(ChamadosDTO objDTO) {
		return repository.save(newChamado(objDTO));
	}
	
	public Chamado update(Long id, @Valid ChamadosDTO objDTO) {
		objDTO.setId(id);
		Chamado obj = findById(id);
		obj = newChamado(objDTO);
		return repository.save(obj);
	}

	private Chamado newChamado(ChamadosDTO objDTO) {
		Cliente cliente = clienteService.findById(objDTO.getCliente());
		Tecnico tecnico = tecnicoService.findById(objDTO.getTecnico());

		Chamado chamado = new Chamado();

		if (objDTO.getId() != null) {
			chamado.setId(objDTO.getId());
		}
		
		if (objDTO.getStatus().equals(2)) {
			chamado.setDtFechamento(LocalDate.now());
		}

		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(objDTO.getPrioridade()));
		chamado.setStatus(Status.toEnum(objDTO.getStatus()));
		chamado.setTitulo(objDTO.getTitulo());
		chamado.setObservacoes(objDTO.getObservacoes());
		return chamado;

	}

}
