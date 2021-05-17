package sopra.vol.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import sopra.vol.Application;
import sopra.vol.model.Aeroport;
import sopra.vol.repository.IAeroportRepositoryJpa;

public class AeroportRepoJpa implements IAeroportRepositoryJpa {

	@Override
	public List<Aeroport> findAll() {
		
		List<Aeroport> aeroports = new ArrayList<Aeroport>();
			
			EntityManager em = null;
			EntityTransaction tx = null;
			
			try {
				em = Application.getInstance().getEmf().createEntityManager();
				tx = em.getTransaction();
				tx.begin();

				TypedQuery<Aeroport> query = em.createQuery("select a from Aeroport a", Aeroport.class);

				aeroports = query.getResultList();

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

			return aeroports;
	}

	@Override
	public Aeroport findById(String code) {
		Aeroport aeroport = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			aeroport = em.find(Aeroport.class, code);

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

		return aeroport;
	}

}
