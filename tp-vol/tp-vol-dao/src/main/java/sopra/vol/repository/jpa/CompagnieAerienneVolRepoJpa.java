package sopra.vol.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import sopra.vol.Application;
import sopra.vol.model.CompagnieAerienne;
import sopra.vol.model.CompagnieAerienneVol;
import sopra.vol.repository.ICompagnieAerienneVolRepositoryJpa;

public class CompagnieAerienneVolRepoJpa implements ICompagnieAerienneVolRepositoryJpa {

	@Override
	public List<CompagnieAerienneVol> findAll() {
		List<CompagnieAerienneVol> compagnieAerienneVols = new ArrayList<CompagnieAerienneVol>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<CompagnieAerienneVol> query = em.createQuery("select c from Compagnie_Aerienne_Vol c", CompagnieAerienneVol.class);

			compagnieAerienneVols = query.getResultList();

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

		return compagnieAerienneVols;
	}


	@Override
	public CompagnieAerienneVol findById(Long id) {
		CompagnieAerienneVol compagnieAerienneVol = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			compagnieAerienneVol = em.find(CompagnieAerienneVol.class, id);

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

		return compagnieAerienneVol;
	}


}
