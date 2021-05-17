package sopra.vol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import sopra.vol.dao.IReservationDao;
import sopra.vol.dao.sql.ReservationDaoSql;

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
	
	private final IPassagerDao passagerDao = new PassagerDaoSql();
	private final IAdresseDao adresseDao = new AdresseDaoSql();
	private final ICompagnieAerienneDao compagnieAerienneDao = new CompagnieAerienneDaoSql();
	private final ICompagnieAerienneVolDao compagnieAerienneVolDao = new CompagnieAerienneVolDaoSql();

//	private final String jdbcUrl = "jdbc:mysql://localhost:3306/tp_vol";
//	private final String username = "root";
//	private final String password = "admin";
	
	private final IReservationDao reservationDao = new ReservationDaoSql();

	private final IClientDao clientDao = new ClientDaoSql();
	
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
