package com.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beans.Henkilot;
import com.beans.HenkilotImpl;
import com.beans.ProjektiHenkiloImpl;
import com.beans.ProjektiImpl;


@Repository
public class TuntiDAOImplementation implements TuntiDAO {
	
	final static Logger logger = LoggerFactory.getLogger(TuntiDAOImplementation.class);
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
		
	public int lisaaHenkiloProjektiin(int henkilo_id, int projekti_id){
		String sql_insert = "INSERT INTO Proj_kayt VALUES (?,?)";
		String sql_select = "Select * from Proj_kayt WHERE kayt_id = "+henkilo_id+" AND proj_id  = "+ projekti_id;
		Object[] parametrit = new Object[] {henkilo_id, projekti_id};
		List<ProjektiHenkiloImpl> kayttajatProjekteissa = null;
		try {
			kayttajatProjekteissa = jdbcTemplate.query(sql_select, new KayttajatProjekteissaRowMapper());
			for (int i=0;i<kayttajatProjekteissa.size();i++){
				if(kayttajatProjekteissa.get(i).gethenkilo_id() == henkilo_id){
					return 0;
				}
			}
			jdbcTemplate.update(sql_insert, parametrit);
			logger.info("lisätään henkilö id:llä " + henkilo_id + " projektiin id:llä " + "projekti_id");
		} catch (DataAccessException ex) {
			daoVirheenHallinta(ex);
		}		
		return 1;
	}
	
	// Lisätään transaktion
	public int luoProjekti(ProjektiImpl projekti){
		int onnistui = 0;
		String insert_sql = "INSERT INTO Projekti(nimi, kuvaus) VALUES(?,?);";
		Object[] parametrit = new Object[] {projekti.getNimi(), projekti.getKuvaus()};
		try {
			List<ProjektiImpl> projektit = haeProjektit();
			
			boolean loytyi = false;
			for(int i=0; i<projektit.size(); i++){
				if (projektit.get(i).getNimi().equalsIgnoreCase(projekti.getNimi())){
					logger.info(projekti.getNimi() + " niminen projekti on jo olemassa");
					loytyi = true;
				}
			}
			if (loytyi == true){
				return onnistui;
			}else{
				jdbcTemplate.update(insert_sql, parametrit);
				logger.info("Lisättiin uusi projekti tietokantaan");
				onnistui = 1;
			}				
		} catch (DataAccessException ex) {
			daoVirheenHallinta(ex);
		}
		return onnistui;
	}
	
	@Transactional(readOnly=true)
	public List<ProjektiImpl> haeProjektit(){
		String sql = "select id, nimi, kuvaus, alku_pvm, loppu_pvm from Projekti;";
		List<ProjektiImpl> projektit = null;
		try {
			projektit = jdbcTemplate.query(sql, new ProjektitRowMapper());
			logger.info("Haetaan kaikki projektit tietokannasta");
		} catch (DataAccessException ex) {
			daoVirheenHallinta(ex);
		}
		return projektit;
	}
	
	@Transactional(readOnly=true)
	public List<HenkilotImpl> summaaTunnit(){
		String sql = "select t.kayttaja_id, sum(t.tuntien_maara) as tunnit, k.etunimi, k.sukunimi from Tunnit t JOIN Kayttajat k ON t.kayttaja_id=k.id group by t.kayttaja_id;";
		List<HenkilotImpl> summatutTunnit = null;
		try {
			summatutTunnit = jdbcTemplate.query(sql, new SummatutTunnitRowMapper());
			logger.info("Summattiin tietokannasta löytyvät tunnit yhteen");
		} catch (DataAccessException ex) {
			daoVirheenHallinta(ex);
		}
		return summatutTunnit;

	}
	 
	@Transactional(readOnly=true)
	public List<HenkilotImpl> haeTunnit(int projekti_id){
		String sql = "SELECT p.proj_id, Tunnit.id as 'tunti_id', Tunnit.tuntien_maara, Tunnit.paivamaara, Tunnit.kuvaus, Kayttajat.etunimi, Kayttajat.sukunimi, Kayttajat.id as kayttaja_id FROM Tunnit" 
			+	" JOIN Kayttajat ON Tunnit.kayttaja_id = Kayttajat.id" 
			+	" JOIN Proj_kayt p ON Kayttajat.id=p.kayt_id"
			+	" WHERE p.proj_id="+projekti_id
			+	" ORDER BY Tunnit.paivamaara;";
		List<HenkilotImpl> henkilot = null;
		try {
			henkilot = jdbcTemplate.query(sql, new TunnitRowMapper());
			logger.info("Haettiin kaikki tallennetut tunnit tietokannasta");
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
			logger.info("Haettiin kaikki henkilöt tietokannasta");
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
			logger.info("Tallennettiin henkilön tunnit tietokantaan käyttäjä ID:llä: " + henkilo.getId() + " ");
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
			logger.info("Haettiin kaikki tallennetut tunnit tietokannasta");
		} catch (DataAccessException ex) {
			daoVirheenHallinta(ex);
		}	
		return henkilot;
	}


	
	public void poista(int id){
		String sql = "DELETE FROM Tunnit WHERE id=" + id;
		try {
			jdbcTemplate.execute(sql);
			logger.info("Tunnit ID:llä: " + id + ", poistettu tietokannasta");
		} catch (DataAccessException ex) {
			daoVirheenHallinta(ex);
		}
	}


	
	public void daoVirheenHallinta(DataAccessException ex){
		System.out.println("Tietokantayhteydess� ongelmia " + ex);
		logger.debug("Tietokantayhteydessä ongelmia " + ex);
	}
	
}

