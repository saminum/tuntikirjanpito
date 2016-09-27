package com.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.beans.Henkilot;

public interface TuntiDAO {

	void talleta(Henkilot henkilo);

	List<Henkilot> haeTunnit();

	void poista(int id);

	List<Henkilot> summaaTunnit();

	void daoVirheenHallinta(DataAccessException ex);

}