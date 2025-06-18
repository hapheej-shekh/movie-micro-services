package com.starter.jpa.service.beans;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AttributeOverrides(value = { 
		@AttributeOverride(name="name", column=@Column(name="guarName")),
		@AttributeOverride(name="email", column=@Column(name="guarEmail")),
		@AttributeOverride(name="mobile", column=@Column(name="guarMobile"))
})
public class Guardian {

	private String name;
	private String email;
	private int mobile;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int i) {
		this.mobile = i;
	}
}
