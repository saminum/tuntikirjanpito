package com.beans;

import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**@author Heikki Petrell
 * @since 13.9.2016
 */
public class HenkilotImpl implements Henkilot {
	
	@NotNull(message="Valitse käyttäjä!")
	@Min(1)
	private int id;
	
	private String tunnus, email, etunimi, sukunimi, salasana;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private double tunnitYhteensa = 0;
	
	@NotNull
	@Valid
	ArrayList<Tunnit> tunnit = new ArrayList<Tunnit>();
	
	
	public double getTunnitYhteensa() {
		return tunnitYhteensa;
	}

	public void setTunnitYhteensa(double tunnitYhteensa) {
		this.tunnitYhteensa = tunnitYhteensa;
	}
	
	public HenkilotImpl(int id, String tunnus, String etunimi, String sukunimi,
			String salasana, ArrayList<Tunnit> tunnit) {
		super();
		this.id = id;
		this.tunnus = tunnus;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.salasana = salasana;
		this.tunnit = tunnit;
	}
	
	public HenkilotImpl(int id, ArrayList<Tunnit> tunnit){
		this.id = id;
		this.tunnit = tunnit;
	}
	//mvp constructori
	public HenkilotImpl(int id, String etunimi, String sukunimi, ArrayList<Tunnit> tunnit) {
		super();
		this.id = id;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.tunnit = tunnit;
	}
	public HenkilotImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see com.beans.Henkilot#getId()
	 */
	public int getId() {
		return id;
	}
	/* (non-Javadoc)
	 * @see com.beans.Henkilot#setId(int)
	 */
	public void setId(int id) {
		this.id = id;
	}
	/* (non-Javadoc)
	 * @see com.beans.Henkilot#getTunnus()
	 */
	public String getTunnus() {
		return tunnus;
	}
	/* (non-Javadoc)
	 * @see com.beans.Henkilot#setTunnus(java.lang.String)
	 */
	public void setTunnus(String tunnus) {
		this.tunnus = tunnus;
	}
	/* (non-Javadoc)
	 * @see com.beans.Henkilot#getEtunimi()
	 */
	public String getEtunimi() {
		return etunimi;
	}
	/* (non-Javadoc)
	 * @see com.beans.Henkilot#setEtunimi(java.lang.String)
	 */
	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}
	/* (non-Javadoc)
	 * @see com.beans.Henkilot#getSukunimi()
	 */
	public String getSukunimi() {
		return sukunimi;
	}
	/* (non-Javadoc)
	 * @see com.beans.Henkilot#setSukunimi(java.lang.String)
	 */
	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}
	/* (non-Javadoc)
	 * @see com.beans.Henkilot#getSalasana()
	 */
	public String getSalasana() {
		return salasana;
	}
	/* (non-Javadoc)
	 * @see com.beans.Henkilot#setSalasana(java.lang.String)
	 */
	public void setSalasana(String salasana) {
		this.salasana = salasana;
	}
	/* (non-Javadoc)
	 * @see com.beans.Henkilot#getTunnit()
	 */
	public ArrayList<Tunnit> getTunnit() {
		return tunnit;
	}
	/* (non-Javadoc)
	 * @see com.beans.Henkilot#setTunnit(java.util.ArrayList)
	 */
	public void setTunnit(ArrayList<Tunnit> tunnit) {
		this.tunnit = tunnit;
	}
	@Override
	public String toString() {
		return "HenkilotImpl [id=" + id + ", tunnus=" + tunnus + ", email="
				+ email + ", etunimi=" + etunimi + ", sukunimi=" + sukunimi
				+ ", salasana=" + salasana + ", tunnitYhteensa="
				+ tunnitYhteensa + ", tunnit=" + tunnit + "]";
	}

	/* (non-Javadoc)
	 * @see com.beans.Henkilot#addTunti(com.beans.Tunnit)
	 */
	public void addTunti(Tunnit tunnin_tiedot) {
		if(tunnin_tiedot != null){
			this.tunnit.add(tunnin_tiedot);
		}
		
	}

	

}
