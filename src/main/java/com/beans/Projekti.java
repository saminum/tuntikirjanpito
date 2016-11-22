package com.beans;

import java.util.Date;

public interface Projekti {

	int getProjekti_Id();

	void setProjekti_id(int projekti_id);

	String getNimi();

	void setNimi(String nimi);

	String getKuvaus();

	void setKuvaus(String kuvaus);

	Date getAlku_pvm();

	void setAlku_pvm(Date alku_pvm);

	Date getLoppu_pvm();

	void setLoppu_pvm(Date loppu_pvm);

	String toString();

	int getProjekti_id();

}