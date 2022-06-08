package com.capstone.bhs.service;

import java.util.List;
import java.util.Optional;

import com.capstone.bhs.model.entity.Categories;
import com.capstone.bhs.model.vm.CategoryVM;

public interface CategoryService {

	public List<Categories> getAllListCategories();

	public Categories createCategory(CategoryVM categoryVM);

	public Optional<Categories> updateCategory(CategoryVM category, Long id);

	void delete(long[] ids);

	public Optional<Categories> getCategoryById(Long id);

	public void deleteCategory(Long id);
}
