package com.vbaggio.desafio03.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vbaggio.desafio03.dto.ClientDTO;
import com.vbaggio.desafio03.entities.Client;
import com.vbaggio.desafio03.repositories.ClientRepository;
import com.vbaggio.desafio03.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable) {
		Page<Client> clients = repository.findAll(pageable);
		return clients.map(client -> modelMapper.map(client, ClientDTO.class));
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
		return modelMapper.map(client, ClientDTO.class);
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client client = modelMapper.map(dto, Client.class);
		client = repository.save(client);
		return modelMapper.map(client, ClientDTO.class);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client client = repository.getReferenceById(id);

			if (modelMapper.getTypeMap(ClientDTO.class, Client.class) == null)
				modelMapper.typeMap(ClientDTO.class, Client.class).addMappings(mapper -> mapper.skip(Client::setId));

			modelMapper.map(dto, client);

			client = repository.save(client);

			return modelMapper.map(client, ClientDTO.class);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException();
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if (!repository.existsById(id))
			throw new ResourceNotFoundException();

		repository.deleteById(id);
	}

}
