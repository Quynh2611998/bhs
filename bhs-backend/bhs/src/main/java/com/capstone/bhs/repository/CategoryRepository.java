package com.capstone.bhs.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.bhs.model.entity.Categories;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long>{
	
	Optional<Categories> findOneById(Long Id);
	
	Optional<Categories> findOneByCategoryName(String categoryName);
	
	Optional<Categories> findOneById(Categories categoryId);
}
