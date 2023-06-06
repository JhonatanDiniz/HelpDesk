package com.helpdesk.HelpDesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.helpdesk.HelpDesk.domains.Pessoa;
import com.helpdesk.HelpDesk.repositories.PessoaRepository;
import com.helpdesk.HelpDesk.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	PessoaRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Pessoa> usuario = repository.findByEmail(email);
		if(usuario.isPresent()) {
			return new UserSpringSecurity(usuario.get().getId(), usuario.get().getEmail(), usuario.get().getPassword(), usuario.get().getPerfis());
		}
		throw new UsernameNotFoundException(email);
	}

}
