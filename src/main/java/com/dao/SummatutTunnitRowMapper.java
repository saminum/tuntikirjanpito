package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.beans.HenkilotImpl;


public class SummatutTunnitRowMapper implements RowMapper<HenkilotImpl> {

	public HenkilotImpl mapRow(ResultSet rs, int rowNum) throws SQLException {
		HenkilotImpl h = new HenkilotImpl();
		h.setEtunimi(rs.getString("etunimi"));
		h.setSukunimi(rs.getString("sukunimi"));
		h.setTunnitYhteensa(rs.getDouble("tunnit"));
		return h;
	}
	
	
}
