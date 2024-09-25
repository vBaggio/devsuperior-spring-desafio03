package com.vbaggio.desafio03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vbaggio.desafio03.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
