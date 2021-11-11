package com.patan.app.dao;

import com.patan.app.models.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDAO extends JpaRepository<ClientEntity, Long> {
}
