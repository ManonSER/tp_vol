package sopra.tp_vol.test;

import javax.persistence.PersistenceException;

import org.junit.Assert;
import org.junit.Test;

import sopra.vol.Application;
import sopra.vol.model.Passager;
import sopra.vol.model.TypeIdentite;
import sopra.vol.repository.IPassagerRepositoryJpa;

public class TestJunit {
@Test	

public void passagerCreate() {
	
	IPassagerRepositoryJpa passagerRepo = Application.getInstance().getPassagerDao();
	
	Passager pass1 = new Passager("Dupont", "Jean","azerty",TypeIdentite.PASSEPORT);
	
	pass1 = passagerRepo.save(pass1);
	
	Passager passFind = passagerRepo.findById(pass1.getId());
	
	
	
	Assert.assertEquals("Dupont", passFind.getNom());
	Assert.assertEquals("Jean", passFind.getPrenom());
	Assert.assertEquals("azerty", passFind.getNumeroIdentite());
	Assert.assertEquals(TypeIdentite.PASSEPORT, passFind.getTypeIdentite());
	
}

@Test	

public void passagerUpdate() {
	
	IPassagerRepositoryJpa passagerRepo = Application.getInstance().getPassagerDao();
	Passager pass1 = new Passager("Dupont", "Jean","azerty",TypeIdentite.PASSEPORT);
	
	Passager passFind = passagerRepo.findById(pass1.getId());
	
	pass1.setNom("dupond");
	pass1.setPrenom("Robert");
	pass1.setNumeroIdentite("abcdef");
	pass1.setTypeIdentite(TypeIdentite.CARTE_IDENTITE);
	
	pass1 = passagerRepo.save(pass1);
	pass1 = passagerRepo.findById(pass1.getId());
	
	
	Assert.assertEquals("dupond", passFind.getNom());
	Assert.assertEquals("Robert", passFind.getPrenom());
	Assert.assertEquals("abcdef", passFind.getNumeroIdentite());
	Assert.assertEquals(TypeIdentite.CARTE_IDENTITE, passFind.getTypeIdentite());
	
}
}

