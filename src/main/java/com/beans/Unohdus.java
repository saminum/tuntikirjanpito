package com.beans;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Unohdus {
	
	@NotBlank
	private String tunnus;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Size(min=8)
	private String salasana;

	public Unohdus() {
		super();
	}

	public Unohdus(String tunnus, String email, String salasana) {
		super();
		this.tunnus = tunnus;
		this.email = email;
		this.salasana = salasana;
	}
	
	

	public String getTunnus() {
		return tunnus;
	}

	public void setTunnus(String tunnus) {
		this.tunnus = tunnus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSalasana() {
		return salasana;
	}

	public void setSalasana(String salasana) {
		this.salasana = salasana;
	}

	@Override
	public String toString() {
		return "Unohdus [tunnus=" + tunnus + ", email=" + email + ", salasana="
				+ salasana + "]";
	}
	
}
