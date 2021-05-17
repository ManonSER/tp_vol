package sopra.tp_vol.test;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;

import sopra.vol.Application;
import sopra.vol.model.Ville;
import sopra.vol.repository.IVilleRepositoryJpa;

public class TestGeoffrey {
	@Test
	public void VilleCreate() {
		IVilleRepositoryJpa villeRepo = Application.getInstance().getVilleDao();
		Ville ville = new Ville("Paris");
		ville = villeRepo.save(ville);
		Ville villeFind = villeRepo.findById(ville.getId());
		Assert.assertEquals("Paris", villeFind.getNom());

	}
}
