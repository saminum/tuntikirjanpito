package com.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.beans.Henkilot;
import com.beans.HenkilotImpl;
import com.beans.ProjektiImpl;


public interface TuntiDAO {

	void talleta(Henkilot henkilo, int projekti_id);

	List<HenkilotImpl> haeTunnit(int projekti_id);

	void poista(int id);

	List<HenkilotImpl> summaaTunnit(int projekti_id);
	List<HenkilotImpl> haeHenkilot();
	List<HenkilotImpl> haeProjektiHenkilot(int projekti_id);
	List<HenkilotImpl> haeHenkilonTunnit(int id, int projekti_id);

	void daoVirheenHallinta(DataAccessException ex);
	
	List<ProjektiImpl> haeProjektit();
	
	int lisaaHenkiloProjektiin(int henkilo_id, int projekti_id);
	
	int luoProjekti(ProjektiImpl projekti);
	
	void PoistaProjekti(ProjektiImpl projekti);
}