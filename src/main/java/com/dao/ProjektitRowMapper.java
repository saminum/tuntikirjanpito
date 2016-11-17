package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.beans.ProjektiImpl;

public class ProjektitRowMapper implements RowMapper<ProjektiImpl> {
		
		public ProjektiImpl mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProjektiImpl projekti = new ProjektiImpl();
			projekti.setNimi(rs.getString("nimi"));
			projekti.setId(rs.getInt("id"));
			projekti.setKuvaus(rs.getString("kuvaus"));
			projekti.setAlku_pvm(rs.getDate("alku_pvm"));
			projekti.setLoppu_pvm(rs.getDate("loppu_pvm"));
			return projekti;
	}
	
}
