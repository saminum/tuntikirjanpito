package com.beans;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProjektiHenkiloImpl implements ProjektiHenkilo{
	
	@NotNull(message="Valitse projekti!")
	@Min(0)
	private int projekti_id;
	
	@NotNull(message="Valitse käyttäjä!")
	@Min(0)
	private int henkilo_id;
	
	private List<HenkilotImpl>henkilot;
	private List<ProjektiImpl>projektit;
	
	@Override
	public int getprojekti_id() {
		return projekti_id;
		
	}
	@Override
	public void setprojekti_id(int projekti_id) {
		this.projekti_id = projekti_id;
	}
	@Override
	public int gethenkilo_id() {
		return henkilo_id;
		
	}
	@Override
	public void sethenkilo_id(int henkilo_id) {
		this.henkilo_id = henkilo_id;
	}
	@Override
	public List<HenkilotImpl> gethenkilot() {
		return henkilot;
	}
	@Override
	public void sethenkilot(List<HenkilotImpl> henkilot) {
		this.henkilot = henkilot;
		
	}
	@Override
	public List<ProjektiImpl> getprojektit() {
		return projektit;
	}
	@Override
	public void setprojektit(List<ProjektiImpl> projektit) {
		this.projektit = projektit;
		
	}
	
}