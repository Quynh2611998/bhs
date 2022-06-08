package com.capstone.bhs.model.dto;

public class ImagesDTO extends ExtendsDTO {

	private String srcImage;
	private String altImage;
	private Long optionId;

	public ImagesDTO() {
	}

	public ImagesDTO(String srcImage, String altImage, Long optionId) {
		this.srcImage = srcImage;
		this.altImage = altImage;
		this.optionId = optionId;
	}

	public String getSrcImage() {
		return srcImage;
	}

	public void setSrcImage(String srcImage) {
		this.srcImage = srcImage;
	}

	public String getAltImage() {
		return altImage;
	}

	public void setAltImage(String altImage) {
		this.altImage = altImage;
	}

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

}
