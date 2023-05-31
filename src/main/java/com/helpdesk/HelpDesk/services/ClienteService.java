
package com.helpdesk.HelpDesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.helpdesk.HelpDesk.domains.Cliente;
import com.helpdesk.HelpDesk.domains.Pessoa;
import com.helpdesk.HelpDesk.domains.dtos.ClienteDTO;
import com.helpdesk.HelpDesk.repositories.ClienteRepository;
import com.helpdesk.HelpDesk.repositories.PessoaRepository;
import com.helpdesk.HelpDesk.services.exceptions.DataIntegrityViolationException;
import com.helpdesk.HelpDesk.services.exceptions.ObjectNotFoudException;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repository;

	@Autowired
	PessoaRepository pessoaRepository;

	public Cliente findById(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoudException("Cliente não encontrado!"));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente created(ClienteDTO objDTO) {
		objDTO.setId(null);
		validaCpfEEmail(objDTO);
		Cliente obj = new Cliente(objDTO);
		return repository.save(obj);
	}

	public Cliente update(Long id, ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente obj = findById(id);
		validaCpfEEmail(objDTO);
		obj = new Cliente(objDTO);
		return repository.save(obj);
	}

	public void delete(Long id) {
		Cliente obj = findById(id);

		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui chamados cadastrado!");
		}

		repository.deleteById(id);
	}

	private void validaCpfEEmail(ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado!");
		}

		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado!");
		}

	}

}
