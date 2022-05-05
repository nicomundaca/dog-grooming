package com.patan.app.dao;

import com.patan.app.models.Groomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroomerDAO extends JpaRepository<Groomer, Long> {
}
