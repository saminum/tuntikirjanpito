package com.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.beans.Henkilot;
import com.beans.Tunnit;

public class tuntiDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void talleta(Henkilot henkilo){
		String sql = "INSERT INTO Tunnit (tuntien_maara, kuvaus, kayttaja_id) VALUES(?,?,?)";
		Object[] parametrit = new Object[] {henkilo.getTunnit().get(0).getTunnit(), henkilo.getTunnit().get(0).getKuvaus(), henkilo.getId()};
		jdbcTemplate.update(sql, parametrit);
	}
	
}
