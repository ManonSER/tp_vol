package sopra.vol.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import sopra.vol.Application;


public class PassagerRepositoryJpa implements IPassagerRepository{

	@Override
	public List<Passager> findAll() {
		List<Passager> evaluations = new ArrayList<Passager>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Passager> query = em.createQuery("select e from Evaluation e", Evaluation.class);

			evaluations = query.getResultList();

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

		return evaluations;
	}


	@Override
	public Passager findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Passager obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Passager obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Passager obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
