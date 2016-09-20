package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.beans.Henkilot;


public class SummatutTunnitRowMapper implements RowMapper<Henkilot> {

	public Henkilot mapRow(ResultSet rs, int rowNum) throws SQLException {
		Henkilot h = new Henkilot();
		h.setEtunimi(rs.getString("etunimi"));
		h.setSukunimi(rs.getString("sukunimi"));
		h.setTunnitYhteensa(rs.getDouble("tunnit"));
		return h;
	}
	
	
}
