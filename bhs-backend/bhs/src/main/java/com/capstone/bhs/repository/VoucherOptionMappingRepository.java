package com.capstone.bhs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.bhs.model.entity.VoucherOptionMapping;

@Repository
public interface VoucherOptionMappingRepository extends JpaRepository<VoucherOptionMapping, Long>{
	
	Optional<VoucherOptionMapping> findOneById(Long id);

	Optional<VoucherOptionMapping> findOneByOptionId(Long id);

}
