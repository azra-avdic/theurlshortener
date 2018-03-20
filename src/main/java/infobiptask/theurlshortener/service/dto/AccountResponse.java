package infobiptask.theurlshortener.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AccountResponse {
	private static final long serialVersionUID = 1L;  

	
	private Boolean success;  
	private String  description; 
	private String  password;

	public AccountResponse() {
		super();

	}
	
	
	public AccountResponse(Boolean success, String description) {
		super();
		this.success = success;
		this.description = description;
	}
	



	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	@Override
	public String toString() {
		return "AccountResponse [success=" + success + ", description="
				+ description + ", password=" + password + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((success == null) ? 0 : success.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountResponse other = (AccountResponse) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (success == null) {
			if (other.success != null)
				return false;
		} else if (!success.equals(other.success))
			return false;
		return true;
	}
	
	
	
	
	
}
