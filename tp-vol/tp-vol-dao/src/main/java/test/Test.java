package test;

import java.util.List;


import sopra.vol.Application;
import sopra.vol.dao.IAdresseDao;
import sopra.vol.dao.IPassagerDao;
import sopra.vol.model.Adresse;
import sopra.vol.model.Passager;
import sopra.vol.model.TypeIdentite;

public class Test {

	public static void main(String[] args) {

		IPassagerDao passagerDao = Application.getInstance().getPassagerDao();
		IAdresseDao adresseDao = Application.getInstance().getAdresseDao();
					

		Passager passager1 = new Passager();
		Passager passager2 = new Passager();
		
		passager1.setNom("dupond");
		passager1.setPrenom("jean");
		passager1.setNumeroIdentite("abcd");
		passager1.setTypeIdentite(TypeIdentite.PASSEPORT);
		
		passager2.setNom("dupond");
		passager2.setPrenom("michel");
		passager2.setNumeroIdentite("efgh");
		passager2.setTypeIdentite(TypeIdentite.PASSEPORT);

		//passagerDao.create(passager1);
		//passagerDao.create(passager2);
		
		passager1.setNom("dupond");
		passager1.setPrenom("jean");
		passager1.setNumeroIdentite("azerty");
		passager1.setTypeIdentite(TypeIdentite.CARTE_IDENTITE);
		
		//passagerDao.update(passager1);

		//System.out.println(passagerDao.findById(5L));
		
		//passagerDao.deleteById(5L);
		//passagerDao.deleteById(1L);
		
		//*******************Adresse************************
		
		Adresse adresse1 = new Adresse();
		Adresse adresse2 = new Adresse();
		
		adresse1.setRue("grande rue");
		adresse1.setComplement("bis");
		adresse1.setCodePostal("33000");
		adresse1.setVille("Bordeaux");
		adresse1.setPays("France");
		
		adresse2.setRue("grande rue");
		adresse2.setComplement("ter");
		adresse2.setCodePostal("80000");
		adresse2.setVille("Amiens");
		adresse2.setPays("France");
		
		adresseDao.create(adresse1);
		adresseDao.create(adresse2);
		
		adresse1.setRue("jean moulin");
		adresse1.setComplement("bis");
		adresse1.setCodePostal("33000");
		adresse1.setVille("Bordeaux");
		adresse1.setPays("France");
		
		adresseDao.update(adresse1);
		
		adresseDao.delete(adresse1);
		
		adresseDao.deleteById(1L);
		
		
		
		
		
		
		

	}

}
