package com.helpdesk.HelpDesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.helpdesk.HelpDesk.domains.Pessoa;
import com.helpdesk.HelpDesk.domains.Tecnico;
import com.helpdesk.HelpDesk.domains.dtos.TecnicoDTO;
import com.helpdesk.HelpDesk.repositories.PessoaRepository;
import com.helpdesk.HelpDesk.repositories.TecnicoRepository;
import com.helpdesk.HelpDesk.services.exceptions.DataIntegrityViolationException;
import com.helpdesk.HelpDesk.services.exceptions.ObjectNotFoudException;

import jakarta.validation.Valid;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Tecnico findById(Long id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoudException("Tecnico não encontrado! id: " + id));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico created(TecnicoDTO objDTO) {
		objDTO.setId(null);
		objDTO.setPassword(encoder.encode(objDTO.getPassword()));
		validaCpfEEmail(objDTO);
		Tecnico obj = new Tecnico(objDTO);
		return repository.save(obj);
	}

	public Tecnico update(Long id, @Valid TecnicoDTO objDTO) {
		objDTO.setId(id);
		Tecnico obj = findById(id);
		validaCpfEEmail(objDTO);
		obj = new Tecnico(objDTO);
		return repository.save(obj);
	}

	public void delete(Long id) {
		Tecnico obj = findById(id);

		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("O Técnico possui chamados cadastrado!");
		}

		repository.deleteById(id);
	}

	private void validaCpfEEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado!");
		}

		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado!");
		}
	}

}
