package com.beans;

import java.util.ArrayList;

public interface Henkilot {
	
	

	double getTunnitYhteensa();

	void setTunnitYhteensa(double tunnitYhteensa);

	int getId();

	void setId(int id);

	String getTunnus();

	void setTunnus(String tunnus);

	String getEtunimi();

	void setEtunimi(String etunimi);

	String getSukunimi();

	void setSukunimi(String sukunimi);

	String getSalasana();

	void setSalasana(String salasana);

	ArrayList<Tunnit> getTunnit();

	void setTunnit(ArrayList<Tunnit> tunnit);

	void addTunti(Tunnit tunnin_tiedot);

}