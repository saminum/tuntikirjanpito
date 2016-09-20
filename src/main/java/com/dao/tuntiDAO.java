package com.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
	
	public List<Henkilot> haeTunnit(){
		String sql = "SELECT * FROM Tunnit JOIN Kayttajat ON Tunnit.kayttaja_id = Kayttajat.id ORDER BY Tunnit.paivamaara DESC;";
		RowMapper<Henkilot> mapper = new TunnitRowMapper();
		List<Henkilot> henkilot = jdbcTemplate.query(sql, mapper);
		return henkilot;
	}
	
}
