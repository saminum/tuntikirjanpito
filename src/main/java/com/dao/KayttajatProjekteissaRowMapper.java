package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.beans.ProjektiHenkiloImpl;



public class KayttajatProjekteissaRowMapper implements RowMapper<ProjektiHenkiloImpl>  {
	
	public ProjektiHenkiloImpl mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProjektiHenkiloImpl projekti = new ProjektiHenkiloImpl();
		projekti.sethenkilo_id(rs.getInt("kayt_id"));
		projekti.setprojekti_id(rs.getInt("proj_id"));
		return projekti;
	}
}
