package sopra.vol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import sopra.vol.dao.IAdresseDao;
import sopra.vol.dao.IPassagerDao;
import sopra.vol.dao.sql.AdresseDaoSql;
import sopra.vol.dao.sql.PassagerDaoSql;

public class Application {
	private static Application instance = null;
	
	private final IPassagerDao passagerDao = new PassagerDaoSql();
	private final IAdresseDao adresseDao = new AdresseDaoSql();

	private final String jdbcUrl = "jdbc:mysql://localhost:3306/tp_vol";
	private final String username = "root";
	private final String password = "admin";

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

	public static Application getInstance() {
		if (instance == null) {
			instance = new Application();
		}

		return instance;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(jdbcUrl, username, password);
	}

}
