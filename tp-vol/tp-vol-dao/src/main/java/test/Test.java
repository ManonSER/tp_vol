package test;

import java.util.List;


import sopra.vol.Application;
import sopra.vol.dao.IPassagerDao;
import sopra.vol.model.Passager;

public class Test {

	public static void main(String[] args) {

		IPassagerDao passagerDao = Application.getInstance().getPassagerDao();
		
	

		Passager passager = new Passager();
		
		passager.setNom("dupond");
		passager.setPrenom("jean");
		passager.setNumeroIdentite("abcd");

	

		passagerDao.create(passager);
		
		
		
		

	}

}
