package sopra.vol.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import sopra.vol.Application;
import sopra.vol.model.Vol;
import sopra.vol.repository.IVolRepositoryJpa;

public class VolRepoJpa implements IVolRepositoryJpa {

	@Override
	public List<Vol> findAll() {
		
		List<Vol> vols = new ArrayList<Vol>();
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Vol> query = em.createQuery("select v from Vol v", Vol.class);

			vols = query.getResultList();

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

		return vols;
	}

	@Override
	public Vol findById(Long id) {
		
		Vol vol = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			vol = em.find(Vol.class, id);

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

		return vol;
	}

}
