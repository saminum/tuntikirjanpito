package com.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.beans.Henkilot;
import com.beans.HenkilotImpl;

public interface TuntiDAO {

	void talleta(Henkilot henkilo);

	List<HenkilotImpl> haeTunnit();

	void poista(int id);

	List<HenkilotImpl> summaaTunnit();

	void daoVirheenHallinta(DataAccessException ex);

}