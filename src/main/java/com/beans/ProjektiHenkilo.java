package com.beans;

import java.util.List;

public interface ProjektiHenkilo {
	
	int getprojekti_id();
	void setprojekti_id(int projekti_id);
	
	int gethenkilo_id();
	void sethenkilo_id(int henkilo_id);
	
	List<HenkilotImpl> gethenkilot();
	void sethenkilot(List<HenkilotImpl>henkilot);
	
	List<ProjektiImpl> getprojektit();
	void setprojektit(List<ProjektiImpl>projektit);
}
