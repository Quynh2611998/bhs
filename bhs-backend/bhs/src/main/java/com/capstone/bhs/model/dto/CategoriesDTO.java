package com.capstone.bhs.model.dto;

public class CategoriesDTO extends ExtendsDTO{
	private Long Categoryid;
	private String categoryName;

	public CategoriesDTO() {
	}

	public CategoriesDTO(Long categoryid, String categoryName) {
		Categoryid = categoryid;
		this.categoryName = categoryName;
	}

	public Long getCategoryid() {
		return Categoryid;
	}

	public void setCategoryid(Long categoryid) {
		Categoryid = categoryid;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
