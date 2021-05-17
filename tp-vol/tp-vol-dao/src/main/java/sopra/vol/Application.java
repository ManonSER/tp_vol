package sopra.vol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import sopra.vol.dao.IReservationDao;
import sopra.vol.dao.IVilleDao;
import sopra.vol.dao.sql.ReservationDaoSql;
import sopra.vol.repository.IAdresseRepositoryJpa;
import sopra.vol.repository.IClientRepositoryJpa;
import sopra.vol.repository.ICompagnieAerienneRepositoryJpa;
import sopra.vol.repository.ICompagnieAerienneVolRepositoryJpa;
import sopra.vol.repository.IPassagerRepositoryJpa;
import sopra.vol.repository.IReservationRepositoryJpa;
import sopra.vol.repository.IVilleRepositoryJpa;
import sopra.vol.repository.jpa.CompagnieAerienneRepoJpa;
import sopra.vol.repository.jpa.CompagnieAerienneVolRepoJpa;
import sopra.vol.repository.jpa.PassagerRepoJpa;
import sopra.vol.repository.jpa.PassagerRepoJpa;
import sopra.vol.repository.jpa.VilleRepoJpa;
import sopra.vol.dao.IBilletDao;
import sopra.vol.dao.sql.BilletDaoSql;

import sopra.vol.dao.IAdresseDao;
import sopra.vol.dao.ICompagnieAerienneDao;
import sopra.vol.dao.ICompagnieAerienneVolDao;
import sopra.vol.dao.IPassagerDao;
import sopra.vol.dao.sql.AdresseDaoSql;
import sopra.vol.dao.sql.CompagnieAerienneDaoSql;
import sopra.vol.dao.sql.CompagnieAerienneVolDaoSql;
import sopra.vol.dao.sql.PassagerDaoSql;

import sopra.vol.dao.IClientDao;
import sopra.vol.dao.IDao;
import sopra.vol.dao.sql.ClientDaoSql;
//import sopra.vol.model.Client;

public class Application {
	
	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("vol-jpa");
	private static Application instance = null;
	
	
	private final IBilletDao billetDao = new BilletDaoSql();
	private final IPassagerRepositoryJpa passagerDao = new PassagerRepoJpa();
	private final IAdresseRepositoryJpa adresseDao = new AdresseDaoSql();
	private final ICompagnieAerienneRepositoryJpa compagnieAerienneDao = new CompagnieAerienneRepoJpa();
	private final ICompagnieAerienneVolRepositoryJpa compagnieAerienneVolDao = new CompagnieAerienneVolRepoJpa();
	private final IReservationRepositoryJpa reservationDao = new ReservationDaoSql();
	private final IClientRepositoryJpa clientDao = new ClientDaoSql();
	private final IVilleRepositoryJpa villeDao = new VilleRepoJpa();
	
	private Application() {
		super();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public IPassagerDao getPassagerDao() {
		return passagerDao;
	}
	
	public IAdresseDao getAdresseDao() {
		return adresseDao;
	}
	public ICompagnieAerienneDao getCompagnieAerienneDao() {
		return compagnieAerienneDao;
	}
	public ICompagnieAerienneVolDao getCompagnieAerienneVolDao() {
		return compagnieAerienneVolDao;
	}

	public IBilletDao getBilletDao() {
		return billetDao;
	}

	public static Application getInstance() {
		if (instance == null) {
			instance = new Application();
		}

		return instance;
	}

	
	
	public IClientDao getClientDao() {
		return clientDao;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public IReservationDao getReservationDao() {
		return reservationDao;
	}

	
}
