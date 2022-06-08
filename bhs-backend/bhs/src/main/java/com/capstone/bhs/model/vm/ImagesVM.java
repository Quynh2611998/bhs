package com.capstone.bhs.model.vm;

public class ImagesVM extends ExtendsVM {

	private Long optionId;
	private String srcImage;
	private String altImage;

	public Long getOptionId() {
		return optionId;
	}

	public String getSrcImage() {
		return srcImage;
	}

	public String getAltImage() {
		return altImage;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public void setSrcImage(String srcImage) {
		this.srcImage = srcImage;
	}

	public void setAltImage(String altImage) {
		this.altImage = altImage;
	}

}
