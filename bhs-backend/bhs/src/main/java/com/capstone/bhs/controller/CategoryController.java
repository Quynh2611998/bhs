package com.capstone.bhs.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.bhs.common.CommonFunction;
import com.capstone.bhs.common.HeaderUtil;
import com.capstone.bhs.common.SpringSecurityUtils;
import com.capstone.bhs.model.entity.Categories;
import com.capstone.bhs.model.vm.CategoryVM;
import com.capstone.bhs.repository.BookingRepository;
import com.capstone.bhs.service.CategoryService;

@RestController
@RequestMapping("/api")

public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	/*
	 * View list categories
	 * 
	 */
	@GetMapping("/get-all-list-categories")
	public ResponseEntity<List<Categories>> getAllListCategory() {
		return ResponseEntity.ok().body(categoryService.getAllListCategories());
	}

	@GetMapping("/get-category/{id}")
	public ResponseEntity<Categories> getCategoryId(@PathVariable("id") Long id) {
		Optional<Categories> obj = categoryService.getCategoryById(id);
		if (obj.isPresent()) {
			return ResponseEntity.ok().body(obj.get());
		} else {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("Category", "Category not found", "Category not found"))
					.body(null);
		}
	}

	@PostMapping("/admin/create-category")
	public ResponseEntity<?> createCategory(@RequestBody CategoryVM categoryVM) {
		try {
			String username = SpringSecurityUtils.getCurrentUserLogin().get();
			categoryVM.setCreatedBy(username);
			categoryVM.setModifiedBy(username);
			return ResponseEntity.ok().headers(HeaderUtil.createAlert("Create success", "Create success"))
					.body(categoryService.createCategory(categoryVM));
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("Categories", "Category Existed", "Category Existed"))
					.body(null);
		}
	}

	@PutMapping("/admin/update-category/{id}")
	public ResponseEntity<?> updateCategory(@RequestBody CategoryVM categoryVM, @PathVariable("id") long id) {
		try {
			String username = SpringSecurityUtils.getCurrentUserLogin().get();
			categoryVM.setCreatedBy(username);
			categoryVM.setModifiedBy(username);
			return ResponseEntity.ok().headers(HeaderUtil.createAlert("Update success", "Update success"))
					.body(categoryService.updateCategory(categoryVM, id));
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("Categories", "Update failed", "Updated fail")).body(null);
		}
	}

	@PostMapping("/admin/delete-category")
	public void deleteCategory(@RequestBody long[] ids) {
		categoryService.delete(ids);
		ResponseEntity.ok().headers(HeaderUtil.createAlert("Delete success", "Delete success"));
	}

	@Autowired
	BookingRepository bookingRepository;

	@PostMapping("/admin/delete-category-by-id/{id}")
	public ResponseEntity<?> deleteOptionById(@PathVariable("id") Long id) {
		try {
			if (getCategoryInBooking(id) == null || getCategoryInBooking(id).isEmpty()) {
				categoryService.deleteCategory(id);
				return ResponseEntity.ok().headers(HeaderUtil.createAlert("Delete success", "Delete success"))
						.body(null);
			}
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("Categories",
							"Can not delete! Option of this Category already on booking",
							"Can not delete! Option of this Category already on booking"))
					.body(null);
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.headers(HeaderUtil.createFailureAlert("Categories", "Category not exist", "Category not exist"))
					.body(null);
		}
	}

	@SuppressWarnings("rawtypes")
	private CommonFunction commonFunction = new CommonFunction();

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCategoryInBooking(Long id) {
		List<Map<String, Object>> queryData = bookingRepository.getCategoryInBooking(id);
		return commonFunction.convertListQueryResultToDTO(queryData);
	}
}
