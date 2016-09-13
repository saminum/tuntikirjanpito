package com.beans;

import java.sql.Date;

/**@author Heikki Petrell
 * @since 13.9.2016
 */
public class Tunnit {
	private int tunnit, id;
	private String kuvaus;
	private Date pvm;
	private Henkilot henkilo;
	public Tunnit(int tunnit, String kuvaus, Date pvm, Henkilot henkilo) {
		super();
		this.tunnit = tunnit;
		this.kuvaus = kuvaus;
		this.pvm = pvm;
		this.henkilo = henkilo;
	}
	public Tunnit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getTunnit() {
		return tunnit;
	}
	public void setTunnit(int tunnit) {
		this.tunnit = tunnit;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKuvaus() {
		return kuvaus;
	}
	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}
	public Date getPvm() {
		return pvm;
	}
	public void setPvm(Date pvm) {
		this.pvm = pvm;
	}
	public Henkilot getHenkilo() {
		return henkilo;
	}
	public void setHenkilo(Henkilot henkilo) {
		this.henkilo = henkilo;
	}
	@Override
	public String toString() {
		return "Tunnit [tunnit=" + tunnit + ", id=" + id + ", kuvaus=" + kuvaus
				+ ", pvm=" + pvm + ", henkilo=" + henkilo + "]";
	}
	
	
}
