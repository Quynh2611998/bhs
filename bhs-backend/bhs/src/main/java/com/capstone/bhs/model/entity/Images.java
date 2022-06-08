package com.capstone.bhs.model.entity;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "option_image")
public class Images {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long imageId;

	@Column(name = "image_src")
	private String srcImage;

	@Column(name = "alt")
	private String altImage;

	@Column(name = "option_id")
	private Long optionId;

	@Column(name = "created_date")
	private Instant createdDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "modified_date")
	private Instant modifiedDate;

	@Column(name = "modified_by")
	private String modifiedBy;

	public Images() {
	}

	public Images(Long imageId, String srcImage, String altImage, Long optionId, Instant createdDate, String createdBy,
			Instant modifiedDate, String modifiedBy) {
		this.imageId = imageId;
		this.srcImage = srcImage;
		this.altImage = altImage;
		this.optionId = optionId;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
	}

	public Long getImageId() {
		return imageId;
	}

	public String getSrcImage() {
		return srcImage;
	}

	public String getAltImage() {
		return altImage;
	}

	public Long getOptionId() {
		return optionId;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Instant getModifiedDate() {
		return modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public void setSrcImage(String srcImage) {
		this.srcImage = srcImage;
	}

	public void setAltImage(String altImage) {
		this.altImage = altImage;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setModifiedDate(Instant modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
