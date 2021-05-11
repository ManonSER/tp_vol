package sopra.vol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import sopra.vol.dao.IReservationDao;
import sopra.vol.dao.sql.ReservationDaoSql;

import sopra.vol.dao.IBilletDao;
import sopra.vol.dao.sql.BilletDaoSql;

public class Application {
	private static Application instance = null;
	private final IBilletDao billetDao = new BilletDaoSql();

	private final String jdbcUrl = "jdbc:mysql://localhost:3306/tp_vol";
	private final String username = "root";
	private final String password = "admin";
	
	private final IReservationDao reservationDao = new ReservationDaoSql();

	private Application() {
		super();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
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

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(jdbcUrl, username, password);
	}

	public IReservationDao getReservationDao() {
		return reservationDao;
	}

	
}
