package com.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beans.Henkilot;
import com.beans.HenkilotImpl;


@Repository
public class TuntiDAOImplementation implements TuntiDAO {
	
//	final static Logger logger = LoggerFactory.getLogger(TuntiDAOImplementation.class);
	
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Transactional(readOnly=true)
	public List<HenkilotImpl> summaaTunnit(){
		String sql = "select t.kayttaja_id, sum(t.tuntien_maara) as tunnit, k.etunimi, k.sukunimi from Tunnit t JOIN Kayttajat k ON t.kayttaja_id=k.id group by t.kayttaja_id;";
		List<HenkilotImpl> summatutTunnit = null;
		try {
			summatutTunnit = jdbcTemplate.query(sql, new SummatutTunnitRowMapper());
//			logger.info("Summattiin tietokannasta löytyvät tunnit yhteen");
		} catch (DataAccessException ex) {
			daoVirheenHallinta(ex);
		}
		return summatutTunnit;

	}
	 
	@Transactional(readOnly=true)
	public List<HenkilotImpl> haeTunnit(){
		String sql = "SELECT Tunnit.id as 'tunti_id', Tunnit.tuntien_maara, Tunnit.paivamaara, Tunnit.kuvaus, Kayttajat.etunimi,"
				+ " Kayttajat.sukunimi, Kayttajat.id as kayttaja_id FROM Tunnit JOIN Kayttajat ON Tunnit.kayttaja_id = Kayttajat.id"
				+ " ORDER BY Tunnit.paivamaara;";
		List<HenkilotImpl> henkilot = null;
		try {
			henkilot = jdbcTemplate.query(sql, new TunnitRowMapper());
//			logger.info("Haettiin kaikki tallennetut tunnit tietokannasta");
		} catch (DataAccessException ex) {
			daoVirheenHallinta(ex);
		}	
		return henkilot;
	}

	@Transactional(readOnly=true)
	public List<HenkilotImpl> haeHenkilot(){
		String sql = "SELECT id, kayttajatunnus, email, etunimi, sukunimi, salasana FROM Kayttajat";
		List<HenkilotImpl> henkilot = null;
		try {
			henkilot = jdbcTemplate.query(sql, new HenkilotRowMapper());
	//		logger.info("Haettiin kaikki tallennetut tunnit tietokannasta");
		} catch (DataAccessException ex) {
			daoVirheenHallinta(ex);
		}	
		return henkilot;
	}
	
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
//			logger.info("Tallennettiin henkil�n tunnit tietokantaan k�ytt�j� ID:ll�: " + henkilo.getId() + " ");
		} catch (DataAccessException ex) {
			daoVirheenHallinta(ex);
		}		
	}
	
	@Transactional(readOnly=true)
	public List<HenkilotImpl> haeHenkilonTunnit(int id){
		String sql = "SELECT Tunnit.id as 'tunti_id', Tunnit.tuntien_maara, Tunnit.paivamaara, Tunnit.kuvaus, Kayttajat.etunimi,"
				+ " Kayttajat.sukunimi, Kayttajat.id as kayttaja_id FROM Tunnit JOIN Kayttajat ON Tunnit.kayttaja_id = Kayttajat.id"
				+ " WHERE kayttaja_id=" + id 
				+ " ORDER BY Tunnit.paivamaara";
		List<HenkilotImpl> henkilot = null;
		try {
			henkilot = jdbcTemplate.query(sql, new TunnitRowMapper());
//			logger.info("Haettiin kaikki tallennetut tunnit tietokannasta");
		} catch (DataAccessException ex) {
			daoVirheenHallinta(ex);
		}	
		return henkilot;
	}


	
	public void poista(int id){
		String sql = "DELETE FROM Tunnit WHERE id=" + id;
		try {
			jdbcTemplate.execute(sql);
//			logger.info("Tunnit ID:llä: " + id + ", poistettu tietokannasta");
		} catch (DataAccessException ex) {
			daoVirheenHallinta(ex);
		}
	}


	
	public void daoVirheenHallinta(DataAccessException ex){
		System.out.println("Tietokantayhteydess� ongelmia " + ex);
//		logger.debug("Tietokantayhteydess� ongelmia " + ex);
	}
	
}

