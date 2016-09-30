package com.dao;

import java.util.List;

import javax.inject.Inject;



import org.springframework.util.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beans.Henkilot;
import com.beans.HenkilotImpl;

@Repository
public class TuntiDAOImplementation implements TuntiDAO {
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/* (non-Javadoc)
	 * @see com.dao.TuntiDAO#talleta(com.beans.Henkilot)
	 */
	
	public void talleta(Henkilot henkilo){
		String paivamaara= henkilo.getTunnit().get(0).getStringdate();
		String[] osat = new String[3];
		osat = paivamaara.split("[.]", 3);
		String pv = osat[0];
		String kk = osat[1];
		String vv = osat[2];
		String kantapaiva = ""+ vv + "-" + kk + "-" + pv + " 00:00:01";
	
		String sql = "INSERT INTO Tunnit (tuntien_maara, kuvaus, kayttaja_id, paivamaara) VALUES(?,?,?,?)";
		Object[] parametrit = new Object[] {henkilo.getTunnit().get(0).getTunnit(), henkilo.getTunnit().get(0).getKuvaus(), henkilo.getId(), kantapaiva};
		try {
			jdbcTemplate.update(sql, parametrit);
//			logger.info("Tallennettiin henkilön tunnit tietokantaan");
		} catch (DataAccessException ex) {
			daoVirheenHallinta(ex);
		}		
	}
	
	/* (non-Javadoc)
	 * @see com.dao.TuntiDAO#haeTunnit()
	 */
	
	public List<HenkilotImpl> haeTunnit(){
		String sql = "SELECT Tunnit.id as 'tunti_id', Tunnit.tuntien_maara, Tunnit.paivamaara, Tunnit.kuvaus, Kayttajat.etunimi,"
				+ " Kayttajat.sukunimi, Kayttajat.id as kayttaja_id FROM Tunnit JOIN Kayttajat ON Tunnit.kayttaja_id = Kayttajat.id"
				+ " ORDER BY Tunnit.paivamaara;";
		RowMapper<HenkilotImpl> mapper = new TunnitRowMapper();
		List<HenkilotImpl> henkilot = null;
		try {
			henkilot = jdbcTemplate.query(sql, mapper);
//			logger.info("Haettiin kaikki tallennetut tunnit tietokannasta");
		} catch (DataAccessException ex) {
			daoVirheenHallinta(ex);
		}	
		return henkilot;
	}
	

	/* (non-Javadoc)
	 * @see com.dao.TuntiDAO#poista(int)
	 */
	
	public void poista(int id){
		String sql = "DELETE FROM Tunnit WHERE id=" + id;
		try {
			jdbcTemplate.execute(sql);
//			logger.info("id: " + id + " poistettu tietokannasta");
		} catch (DataAccessException ex) {
			daoVirheenHallinta(ex);
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.TuntiDAO#summaaTunnit()
	 */
	
	public List<HenkilotImpl> summaaTunnit(){
		String sql = "select t.kayttaja_id, sum(t.tuntien_maara) as tunnit, k.etunimi, k.sukunimi from Tunnit t JOIN Kayttajat k ON t.kayttaja_id=k.id group by t.kayttaja_id;";
		RowMapper<HenkilotImpl> mapper = new SummatutTunnitRowMapper();
		List<HenkilotImpl> summatutTunnit = null;
		try {
			summatutTunnit = jdbcTemplate.query(sql, mapper);
//			logger.info("Summattiin tietokannasta löytyvät tunnit yhteen");
		} catch (DataAccessException ex) {
			daoVirheenHallinta(ex);
		}
		return summatutTunnit;

	}
	
	/* (non-Javadoc)
	 * @see com.dao.TuntiDAO#daoVirheenHallinta(org.springframework.dao.DataAccessException)
	 */
	
	public void daoVirheenHallinta(DataAccessException ex){
//		logger.debug("Tietokantayhteydessä ongelmia " + ex);
	}
	
}

