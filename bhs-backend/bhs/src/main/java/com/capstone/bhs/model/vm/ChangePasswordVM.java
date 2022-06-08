package com.capstone.bhs.model.vm;

public class ChangePasswordVM {
	private String oldPassword;
	private String newPassword;
	private String rePassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

}
