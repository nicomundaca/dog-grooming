package com.patan.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patan.app.models.Client;

public interface ClientDAO extends JpaRepository<Client, Integer>{

}
