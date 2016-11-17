package com.beans;

import java.util.Date;

public class ProjektiImpl implements Projekti {
	
	private int id;
	private String nimi, kuvaus;
	private Date alku_pvm;
	private Date loppu_pvm;
	/* (non-Javadoc)
	 * @see com.beans.Projekti#getId()
	 */
	@Override
	public int getId() {
		return id;
	}
	/* (non-Javadoc)
	 * @see com.beans.Projekti#setId(int)
	 */
	@Override
	public void setId(int id) {
		this.id = id;
	}
	/* (non-Javadoc)
	 * @see com.beans.Projekti#getNimi()
	 */
	@Override
	public String getNimi() {
		return nimi;
	}
	/* (non-Javadoc)
	 * @see com.beans.Projekti#setNimi(java.lang.String)
	 */
	@Override
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	/* (non-Javadoc)
	 * @see com.beans.Projekti#getKuvaus()
	 */
	@Override
	public String getKuvaus() {
		return kuvaus;
	}
	/* (non-Javadoc)
	 * @see com.beans.Projekti#setKuvaus(java.lang.String)
	 */
	@Override
	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}
	/* (non-Javadoc)
	 * @see com.beans.Projekti#getAlku_pvm()
	 */
	@Override
	public Date getAlku_pvm() {
		return alku_pvm;
	}
	/* (non-Javadoc)
	 * @see com.beans.Projekti#setAlku_pvm(java.util.Date)
	 */
	@Override
	public void setAlku_pvm(Date alku_pvm) {
		this.alku_pvm = alku_pvm;
	}
	/* (non-Javadoc)
	 * @see com.beans.Projekti#getLoppu_pvm()
	 */
	@Override
	public Date getLoppu_pvm() {
		return loppu_pvm;
	}
	/* (non-Javadoc)
	 * @see com.beans.Projekti#setLoppu_pvm(java.util.Date)
	 */
	@Override
	public void setLoppu_pvm(Date loppu_pvm) {
		this.loppu_pvm = loppu_pvm;
	}
	public ProjektiImpl(int id, String nimi, String kuvaus, Date alku_pvm, Date loppu_pvm) {
		super();
		this.id = id;
		this.nimi = nimi;
		this.kuvaus = kuvaus;
		this.alku_pvm = alku_pvm;
		this.loppu_pvm = loppu_pvm;
	}
	public ProjektiImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see com.beans.Projekti#toString()
	 */
	@Override
	public String toString() {
		return "ProjektiImpl [id=" + id + ", nimi=" + nimi + ", kuvaus=" + kuvaus + ", alku_pvm=" + alku_pvm
				+ ", loppu_pvm=" + loppu_pvm + "]";
	}
	
	
	
	
}
