package com.beans;

import java.sql.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

/**@author Heikki Petrell
 * @since 13.9.2016
 */
public class Tunnit {
	
	private int id;
	
	@NotNull(message = "Tunnit puuttuu!")
	@DecimalMax(message="Tuntim‰‰r‰ ei saa ylitt‰‰ 20!", value = "20") @DecimalMin(message="Tuntim‰‰r‰n pit‰‰ olla yli 0.5!", value = "0.5")
	private double tunnit;
	
	@NotNull(message = "Kuvaus puuttuu!")
	@Size(min = 1, max = 100, message = "Kuvauksen pit‰‰ olla 1-100 merkki‰ pitk‰!")
	private String kuvaus;
	
	@NotNull
	@Pattern(message = "Valitse p‰iv‰!", regexp="^((?:(?:[0-2]?\\d{1})|(?:[3][01]{1}))[-:\\/.](?:[0]?[1-9]|[1][012])[-:\\/.](?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3})))(?![\\d])$")
	private String stringdate;
	
	//@NotNull(message = "P‰iv‰m‰‰r‰ puuttuu! pvm")
	//@DateTimeFormat(pattern="dd.MMM.yyyy")
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
