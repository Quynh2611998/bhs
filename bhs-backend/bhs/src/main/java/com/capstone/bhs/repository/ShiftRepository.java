package com.capstone.bhs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.bhs.model.entity.Shift;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long>{

	
}
