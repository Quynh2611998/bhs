package com.capstone.bhs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.bhs.model.entity.BookingOptionMapping;

@Repository
public interface BookingOptionMappingRepository extends JpaRepository<BookingOptionMapping, Long>{

	Optional<BookingOptionMapping> findOneByOptionId(Long id);
}
