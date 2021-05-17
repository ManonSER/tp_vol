package sopra.vol.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import sopra.vol.Application;
import sopra.vol.model.Adresse;
import sopra.vol.repository.IAdresseRepositoryJpa;

public class AdresseRepoJpa implements IAdresseRepositoryJpa {

	@Override
	public List<Adresse> findAll() {
		// TODO Auto-generated method stub
		
		List<Adresse> adresses = new ArrayList<Adresse>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Adresse> query = em.createQuery("select e from Adresse e", Adresse.class);

			adresses = query.getResultList();

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

		} finally {
			if (em != null) {
				em.close();
			}
		}

		return adresses;
	}

	@Override
	public Adresse findById(Long id) {
		// TODO Auto-generated method stub
		Adresse adresse = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			adresse = em.find(Adresse.class, id);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

		} finally {
			if (em != null) {
				em.close();
			}
		}

		return adresse;
	}
	
}
