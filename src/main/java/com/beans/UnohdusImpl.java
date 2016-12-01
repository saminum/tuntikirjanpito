package com.beans;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class UnohdusImpl implements Unohdus{
	@NotBlank
	private String tunnus;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Size(min=8)
	private String salasana;

	public UnohdusImpl() {
		super();
	}

	public UnohdusImpl(String tunnus, String email, String salasana) {
		super();
		this.tunnus = tunnus;
		this.email = email;
		this.salasana = salasana;
	}
	
	

	@Override
	public String getTunnus() {
		return tunnus;
	}

	@Override
	public void setTunnus(String tunnus) {
		this.tunnus = tunnus;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getSalasana() {
		return salasana;
	}

	@Override
	public void setSalasana(String salasana) {
		this.salasana = salasana;
	}

	
	@Override
	public String toString() {
		return "Unohdus [tunnus=" + tunnus + ", email=" + email + ", salasana="
				+ salasana + "]";
}
}
