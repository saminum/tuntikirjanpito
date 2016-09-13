package com.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;

import com.beans.Henkilot;
import com.dao.DAOPoikkeus;
import com.dao.DBConnectionDao;

public class HenkiloDAO {
	
	/**
	 * Konstruktori
	 * lataa tietokantayhteyden ajurin
	 */
	public HenkiloDAO() throws DAOPoikkeus {
		try {
			Class.forName(DBConnectionDao.getInstance().getProperty("driver")).newInstance();
		} catch(Exception e) {
			throw new DAOPoikkeus("Tietokannan ajuria ei kyetty lataamaan.", e);
		}
	}
	

	private Connection avaaYhteys() throws DAOPoikkeus {
		
		try {
			return DriverManager.getConnection(
					DBConnectionDao.getInstance().getProperty("url"), 
					DBConnectionDao.getInstance().getProperty("username"),
					DBConnectionDao.getInstance().getProperty("password"));
		} catch (Exception e) {
			throw new DAOPoikkeus("Tietokantayhteyden avaaminen epäonnistui", e);
		}
	}
	

	private void suljeYhteys(Connection yhteys) throws DAOPoikkeus {
		try {
			if (yhteys != null && !yhteys.isClosed())
				yhteys.close();
		} catch(Exception e) {
			throw new DAOPoikkeus("Tietokantayhteys ei jostain syystä suostu menemään kiinni.", e);
		}
	}
	
	
//	public List<Henkilo> haeKaikki() throws DAOPoikkeus{		
//		
//		ArrayList<Henkilo> henkilot = new ArrayList<Henkilo>();
//		
//		//avataan yhteys
//		Connection yhteys = avaaYhteys();
//		
//		try {
//			
//			//suoritetaan haku
//			String sql = "select id, etunimi, sukunimi from henkilo";
//			Statement haku = yhteys.createStatement();
//			ResultSet tulokset = haku.executeQuery(sql);
//			
//			//käydään hakutulokset läpi
//			while(tulokset.next()) {
//				int id = tulokset.getInt("id");
//				String etunimi = tulokset.getString("etunimi");
//				String sukunimi = tulokset.getString("sukunimi");
//				
//				//lisätään henkilö listaan
//				Henkilo h = new Henkilo(id, etunimi, sukunimi);
//				henkilot.add(h);
//			}
//			
//		} catch(Exception e) {
//			//JOTAIN VIRHETTÄ TAPAHTUI
//			throw new DAOPoikkeus("Tietokantahaku aiheutti virheen", e);
//		}finally {
//			//LOPULTA AINA SULJETAAN YHTEYS
//			suljeYhteys(yhteys);
//		}
//		
//		System.out.println("HAETTIIN TIETOKANNASTA HENKILÖT: " +henkilot.toString());
//		
//		return henkilot;
//	}

	
	public void lisaaTunnitKantaan(Henkilot h) throws DAOPoikkeus{
			
		//avataan yhteys
		Connection yhteys = avaaYhteys();
		
		try {
			
			//suoritetaan haku
			
			//alustetaan sql-lause
			String sql = "insert into henkilo(etunimi, sukunimi) values(?,?)";
			PreparedStatement lause = yhteys.prepareStatement(sql);
			
			//täytetään puuttuvat tiedot
			lause.setString(1, h.getEtunimi());
			lause.setString(2, h.getSukunimi());
			
			//suoritetaan lause
			lause.executeUpdate();
			System.out.println("LISÄTTIIN HENKILÖ TIETOKANTAAN: "+h);
		} catch(Exception e) {
			//JOTAIN VIRHETTÄ TAPAHTUI
			throw new DAOPoikkeus("Henkilön lisäämisyritys aiheutti virheen", e);
		}finally {
			//LOPULTA AINA SULJETAAN YHTEYS
			suljeYhteys(yhteys);
		}

	}

}

