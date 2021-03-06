package sopra.vol;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import sopra.vol.repository.IAdresseRepositoryJpa;
import sopra.vol.repository.IBilletRepositoryJpa;
import sopra.vol.repository.IClientRepositoryJpa;
import sopra.vol.repository.ICompagnieAerienneRepositoryJpa;
import sopra.vol.repository.ICompagnieAerienneVolRepositoryJpa;
import sopra.vol.repository.IPassagerRepositoryJpa;
import sopra.vol.repository.IReservationRepositoryJpa;
import sopra.vol.repository.IVilleRepositoryJpa;
import sopra.vol.repository.jpa.AdresseRepoJpa;
import sopra.vol.repository.jpa.BilletRepoJpa;
import sopra.vol.repository.jpa.ClientRepoJpa;
import sopra.vol.repository.jpa.CompagnieAerienneRepoJpa;
import sopra.vol.repository.jpa.CompagnieAerienneVolRepoJpa;
//import sopra.vol.repository.jpa.PassagerRepoJpa;
import sopra.vol.repository.jpa.ReservationRepoJpa;
import sopra.vol.repository.jpa.VilleRepoJpa;

public class Application {
	
	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("vol_jpa");
	private static Application instance = null;
	
	
	private final IBilletRepositoryJpa billetDao = new BilletRepoJpa();
//	private final IPassagerRepositoryJpa passagerDao = new PassagerRepoJpa();
	private final IAdresseRepositoryJpa adresseDao = new AdresseRepoJpa();
	private final ICompagnieAerienneRepositoryJpa compagnieAerienneDao = new CompagnieAerienneRepoJpa();
	private final ICompagnieAerienneVolRepositoryJpa compagnieAerienneVolDao = new CompagnieAerienneVolRepoJpa();
	private final IReservationRepositoryJpa reservationDao = new ReservationRepoJpa();
	private final IClientRepositoryJpa clientDao = new ClientRepoJpa();
	private final IVilleRepositoryJpa villeDao = new VilleRepoJpa();
	
	private Application() {
		super();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
//	public IPassagerRepositoryJpa getPassagerDao() {
//		return passagerDao;
//	}
	
	public IAdresseRepositoryJpa getAdresseDao() {
		return adresseDao;
	}
	public ICompagnieAerienneRepositoryJpa getCompagnieAerienneDao() {
		return compagnieAerienneDao;
	}
	public ICompagnieAerienneVolRepositoryJpa getCompagnieAerienneVolDao() {
		return compagnieAerienneVolDao;
	}

	public IBilletRepositoryJpa getBilletDao() {
		return billetDao;
	}

	public static Application getInstance() {
		if (instance == null) {
			instance = new Application();
		}

		return instance;
	}

	
	public IClientRepositoryJpa getClientDao() {
		return clientDao;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public IReservationRepositoryJpa getReservationDao() {
		return reservationDao;
	}
	public IVilleRepositoryJpa getVilleDao() {
		return villeDao;
	}

	
}
