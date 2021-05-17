package sopra.vol.repository.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import sopra.vol.Application;
import sopra.vol.model.Client;
import sopra.vol.model.Entreprise;
import sopra.vol.model.Particulier;
import sopra.vol.repository.IClientRepositoryJpa;

public class ClientRepoJpa implements IClientRepositoryJpa {

	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		
		List<Client> clients = new ArrayList<Client>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Client> query = em.createQuery("select c from Client c", Client.class);

			clients = query.getResultList();

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

		return clients;
	}

	@Override
	public Client findById(Long id) {
		// TODO Auto-generated method stub
		Client client = null;

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			client = em.find(Client.class, id);

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

		return client;
	}

	@Override
	public List<Particulier> findAllParticulier() {
		List<Particulier> particuliers = new ArrayList<Particulier>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Particulier> query = em.createQuery("select p from Particulier p", Particulier.class);

			particuliers = query.getResultList();

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

		return particuliers;
	}
	
	@Override
	public List<Entreprise> findAllEntreprise() {
		List<Entreprise> entreprises = new ArrayList<Entreprise>();

		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Entreprise> query = em.createQuery("select e from Entreprise e", Entreprise.class);

			entreprises = query.getResultList();

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

		return entreprises;
	}	
	
	@Override
	public Particulier findParticulierByPrenom(String prenom) {
		Particulier particulier = null; 
		
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Particulier> query = em.createQuery("select p from Particulier p where p.prenom = :prenom", Particulier.class);

			query.setParameter("prenom", prenom);
			
			particulier = query.getSingleResult();

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
		
		return particulier;
	}
	
	@Override
	public Entreprise findEntrepriseBySiret(String siret) {
		Entreprise entreprise = null; 
		
		EntityManager em = null;
		EntityTransaction tx = null;

		try {
			em = Application.getInstance().getEmf().createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			TypedQuery<Entreprise> query = em.createQuery("select e from Entreprise e where e.siret = :siret", Entreprise.class);

			query.setParameter("siret", siret);
			
			entreprise = query.getSingleResult();

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
		
		return entreprise;
	}
}
