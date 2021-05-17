package sopra.vol.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import sopra.vol.Application;
import sopra.vol.model.Billet;
import sopra.vol.repository.IBilletRepositoryJpa;

public class BilletRepoJpa implements IBilletRepositoryJpa{

	@Override
	public List<Billet> findAll() {
		
		List<Billet> billets = new ArrayList<Billet>();
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Billet> query = em.createQuery("select b from Billet r", Billet.class);

			billets = query.getResultList();

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

		return billets;
	}

	@Override
	public Billet findById(Long id) {
		
		Billet billet = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			billet = em.find(Billet.class, id);

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

		return billet;
	}
}