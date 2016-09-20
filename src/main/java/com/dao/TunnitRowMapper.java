package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.beans.Henkilot;
import com.beans.Tunnit;

public class TunnitRowMapper implements RowMapper<Henkilot> {

	public Henkilot mapRow(ResultSet rs, int rowNum) throws SQLException {
		Henkilot h = new Henkilot();
		h.setEtunimi(rs.getString("etunimi"));
		h.setSukunimi(rs.getString("sukunimi"));
		h.setId(rs.getInt("kayttaja_id"));
		
		Tunnit t = new Tunnit();
		t.setTunnit(rs.getDouble("tuntien_maara"));
		t.setKuvaus(rs.getString("kuvaus"));
		t.setPvm(rs.getDate("paivamaara"));
		t.setId(rs.getInt("tunti_id"));
		h.addTunti(t);
		return h;
	}
	
}
