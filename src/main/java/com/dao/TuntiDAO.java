package com.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.beans.Henkilot;
import com.beans.HenkilotImpl;


public interface TuntiDAO {

	void talleta(Henkilot henkilo);
	void lisaaKayttaja(HenkilotImpl kayttaja);

	List<HenkilotImpl> haeTunnit();

	void poista(int id);

	List<HenkilotImpl> summaaTunnit();
	List<HenkilotImpl> haeHenkilot();
	
	List<HenkilotImpl> haeHenkilonTunnit(int id);

	void daoVirheenHallinta(DataAccessException ex);
	
	HenkilotImpl haeKayttaja(String tunnus);
	
	void muutaSalasana(String tunnus, String uusisala);

}