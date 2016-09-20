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
		String sql = "SELECT Tunnit.id as 'tunti_id', Tunnit.tuntien_maara, Tunnit.paivamaara, Tunnit.kuvaus, Kayttajat.etunimi,"
				+ " Kayttajat.sukunimi, Kayttajat.id as kayttaja_id FROM Tunnit JOIN Kayttajat ON Tunnit.kayttaja_id = Kayttajat.id"
				+ " ORDER BY Tunnit.paivamaara;";
		RowMapper<Henkilot> mapper = new TunnitRowMapper();
		List<Henkilot> henkilot = jdbcTemplate.query(sql, mapper);
		return henkilot;
	}
	

	public void poista(int id){
		String sql = "DELETE FROM Tunnit WHERE id=77";
		Object[] parametrit = new Object[] {id};
		System.out.println("id daosta " + id);
		//jdbcTemplate.update(sql, parametrit);
		System.out.println("sql string " + sql);
		jdbcTemplate.execute(sql);
	}

	public List<Henkilot> summaaTunnit(){
		String sql = "select t.kayttaja_id, sum(t.tuntien_maara) as tunnit, k.etunimi, k.sukunimi from Tunnit t JOIN Kayttajat k ON t.kayttaja_id=k.id group by t.kayttaja_id;";
		RowMapper<Henkilot> mapper = new SummatutTunnitRowMapper();
		List<Henkilot> summatutTunnit = jdbcTemplate.query(sql, mapper);
		return summatutTunnit;

	}
	
}
