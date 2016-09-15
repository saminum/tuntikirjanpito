package com.beans;

import java.util.ArrayList;
/**@author Heikki Petrell
 * @since 13.9.2016
 */
public class Henkilot {
	private int id;
	private String tunnus, etunimi, sukunimi, salasana;
	ArrayList<Tunnit> tunnit;
	public Henkilot(int id, String tunnus, String etunimi, String sukunimi,
			String salasana, ArrayList<Tunnit> tunnit) {
		super();
		this.id = id;
		this.tunnus = tunnus;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.salasana = salasana;
		this.tunnit = tunnit;
	}
	
	public Henkilot(int id, ArrayList<Tunnit> tunnit){
		this.id = id;
		this.tunnit = tunnit;
	}
	//mvp constructori
	public Henkilot(int id, String etunimi, String sukunimi, ArrayList<Tunnit> tunnit) {
		super();
		this.id = id;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.tunnit = tunnit;
	}
	public Henkilot() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTunnus() {
		return tunnus;
	}
	public void setTunnus(String tunnus) {
		this.tunnus = tunnus;
	}
	public String getEtunimi() {
		return etunimi;
	}
	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}
	public String getSukunimi() {
		return sukunimi;
	}
	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}
	public String getSalasana() {
		return salasana;
	}
	public void setSalasana(String salasana) {
		this.salasana = salasana;
	}
	public ArrayList<Tunnit> getTunnit() {
		return tunnit;
	}
	public void setTunnit(ArrayList<Tunnit> tunnit) {
		this.tunnit = tunnit;
	}
	@Override
	public String toString() {
		return "Henkilot [id=" + id + ", tunnus=" + tunnus + ", etunimi="
				+ etunimi + ", sukunimi=" + sukunimi + ", salasana=" + salasana
				+ ", tunnit=" + tunnit + "]";
	}

}
