package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.beans.HenkilotImpl;
import com.beans.Tunnit;

public class TunnitRowMapper implements RowMapper<HenkilotImpl> {

	public HenkilotImpl mapRow(ResultSet rs, int rowNum) throws SQLException {
		HenkilotImpl h = new HenkilotImpl();
		h.setEtunimi(rs.getString("etunimi"));
		h.setSukunimi(rs.getString("sukunimi"));
		h.setId(rs.getInt("kayttaja_id"));
		h.setTunnus(rs.getString("kayttajatunnus"));
		h.setEmail(rs.getString("email"));
		
		Tunnit t = new Tunnit();
		t.setTunnit(rs.getDouble("tuntien_maara"));
		t.setKuvaus(rs.getString("kuvaus"));
		t.setPvm(rs.getDate("paivamaara"));
		t.setId(rs.getInt("tunti_id"));
		h.addTunti(t);
		return h;
	}
	
}
