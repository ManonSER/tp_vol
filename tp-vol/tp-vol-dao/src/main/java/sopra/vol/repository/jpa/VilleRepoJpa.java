package sopra.vol.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import sopra.vol.Application;

import sopra.vol.model.Ville;
import sopra.vol.repository.IVilleRepositoryJpa;

public class VilleRepoJpa implements IVilleRepositoryJpa{

	@Override
	public List<Ville> findAll() {
		List<Ville> villes = new ArrayList<Ville>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Ville> query = em.createQuery("select v from Ville v", Ville.class);

			villes = query.getResultList();

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

		return villes;
	}

	@Override
	public Ville findById(Long id) {
		Ville ville = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			ville = em.find(Ville.class, id);

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

		return ville;
	}

}
