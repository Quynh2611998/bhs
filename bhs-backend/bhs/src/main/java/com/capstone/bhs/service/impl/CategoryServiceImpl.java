package com.capstone.bhs.service.impl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.bhs.model.entity.Categories;
import com.capstone.bhs.model.vm.CategoryVM;
import com.capstone.bhs.repository.CategoryRepository;
import com.capstone.bhs.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Categories> getAllListCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Categories createCategory(CategoryVM categoryVM) {
		Categories saveObj = new Categories();
		Optional<Categories> name = categoryRepository.findOneByCategoryName(categoryVM.getCategoryName());
		if (name.isPresent()) {
			throw new RuntimeErrorException(null);
		}
		saveObj.setCategoryName(categoryVM.getCategoryName());
		saveObj.setCreatedDate(Instant.now());
		saveObj.setCreatedBy(categoryVM.getCreatedBy());
		saveObj.setModifiedDate(Instant.now());
		saveObj.setModifiedBy(categoryVM.getModifiedBy());
		categoryRepository.save(saveObj);
		return saveObj;
	}

	@Override
	public Optional<Categories> updateCategory(CategoryVM categoryVM, Long id) {
		return categoryRepository.findOneById(id).map(user -> {
			user.setCategoryName(categoryVM.getCategoryName());
			user.setCreatedBy(categoryVM.getCreatedBy());
			user.setModifiedBy(categoryVM.getModifiedBy());
			categoryRepository.save(user);
			return user;
		});
	}

	@Override
	public void delete(long[] ids) {
		for (long item : ids) {
			categoryRepository.deleteById(item);
		}
	}

	@Override
	public Optional<Categories> getCategoryById(Long id) {
		return categoryRepository.findById(id);
	}

	@Override
	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);

	}
}
