package com.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.beans.Tunnit;

public class tuntiDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void talleta(Tunnit tunti){
		String sql = "INSERT INTO Tunnit (tuntien_maara, kuvaus, kayttaja_id) VALUES(?,?,?)";
		Object[] parametrit = new Object[] {tunti.getTunnit(), tunti.getKuvaus(), tunti.getId()};
		jdbcTemplate.update(sql, parametrit);
	}
	
}
