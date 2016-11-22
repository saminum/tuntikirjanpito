package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.beans.ProjektiImpl;

public class ProjektitRowMapper implements RowMapper<ProjektiImpl> {
		
		public ProjektiImpl mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProjektiImpl projekti = new ProjektiImpl();
			projekti.setNimi(rs.getString("nimi"));
			projekti.setProjekti_id(rs.getInt("id"));
			projekti.setKuvaus(rs.getString("kuvaus"));
			projekti.setAlku_pvm(rs.getDate("alku_pvm"));
			Date date = null;
			try {
				date = rs.getDate("loppu_pvm");
			} catch (Exception e) {
				date = null;
			}
			projekti.setLoppu_pvm(date);
			return projekti;
	}	
}
