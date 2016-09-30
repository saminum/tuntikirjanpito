package com.beans;

import java.sql.Date;

/**@author Heikki Petrell
 * @since 13.9.2016
 */
public class Tunnit {
	private int id;
	private double tunnit;
	private String kuvaus, stringdate;
	private Date pvm;
	public Tunnit(double tunnit, String kuvaus, Date pvm, String stringdate) {
		super();
		this.tunnit = tunnit;
		this.kuvaus = kuvaus;
		this.pvm = pvm;
		this.stringdate = stringdate;
	}

	public Tunnit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public double getTunnit() {
		return tunnit;
	}
	public void setTunnit(double tunnit) {
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

	public String getStringdate() {
		return stringdate;
	}
	public void setStringdate(String stringdate) {
		this.stringdate = stringdate;
	}
	
	@Override
	public String toString() {
		return "Tunnit [id=" + id + ", tunnit=" + tunnit + ", kuvaus=" + kuvaus
				+ ", stringdate=" + stringdate + ", pvm=" + pvm + "]";
	}
	
	
}
