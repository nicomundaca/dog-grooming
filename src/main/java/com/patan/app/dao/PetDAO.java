package com.patan.app.dao;

import com.patan.app.models.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetDAO extends JpaRepository<PetEntity, Long> {
}
