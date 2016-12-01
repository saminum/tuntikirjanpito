package com.beans;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

public class ProjektiImpl implements Projekti {
	
	private int projekti_id;
	
	@NotBlank
	private String nimi;
	
	@NotBlank
	private String kuvaus;
	private Date alku_pvm;
	private Date loppu_pvm;
	/* (non-Javadoc)
	 * @see com.beans.Projekti#getId()
	 */
	@Override
	public int getProjekti_id() {
		return projekti_id;
	}
	
	@Override
	public void setProjekti_id(int projekti_id) {
		this.projekti_id = projekti_id;
	}
	
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
	public ProjektiImpl(int id, String nimi, String kuvaus, Date alku_pvm, Date loppu_pvm, int projekti_id) {
		super();
		this.projekti_id = projekti_id;
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
		return "ProjektiImpl [id=" + projekti_id + ", nimi=" + nimi + ", kuvaus=" + kuvaus + ", alku_pvm=" + alku_pvm
				+ ", loppu_pvm=" + loppu_pvm + "]";
	}

	@Override
	public int getProjekti_Id() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}
