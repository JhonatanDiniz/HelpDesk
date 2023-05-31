package com.helpdesk.HelpDesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.helpdesk.HelpDesk.domains.Chamado;
import com.helpdesk.HelpDesk.domains.dtos.ChamadosDTO;
import com.helpdesk.HelpDesk.services.ChamadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {
	
	@Autowired
	private ChamadoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadosDTO> findById(@PathVariable Long id){
		Chamado obj = service.findById(id);
		return ResponseEntity.ok().body(new ChamadosDTO(obj));
	}
	
	@GetMapping
	public ResponseEntity<List<ChamadosDTO>> findAll(){
		List<Chamado> list = service.findAll();
		List<ChamadosDTO> objDTO = list.stream().map(obj -> new ChamadosDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(objDTO);
	}
	
	@PostMapping
	public ResponseEntity<ChamadosDTO> create(@Valid @RequestBody ChamadosDTO objDTO){
		Chamado obj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ChamadosDTO> update(@PathVariable Long id, @Valid @RequestBody ChamadosDTO objDTO){
		Chamado obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new ChamadosDTO(obj));
	}

}
