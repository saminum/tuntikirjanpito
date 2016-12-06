package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.beans.HenkilotImpl;

public class HenkilotRowMapper implements RowMapper<HenkilotImpl>{
	
	public HenkilotImpl mapRow(ResultSet rs, int rowNum) throws SQLException {
		HenkilotImpl henkilot = new HenkilotImpl();
		henkilot.setEtunimi(rs.getString("etunimi"));
		henkilot.setTunnus(rs.getString("kayttajatunnus"));
		henkilot.setEmail(rs.getString("email"));
		henkilot.setSukunimi(rs.getString("sukunimi"));
		henkilot.setSalasana(rs.getString("salasana"));
		henkilot.setId(rs.getInt("id"));
		
		return henkilot;
	}
}
