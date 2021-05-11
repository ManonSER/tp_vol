package sopra.vol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import sopra.vol.dao.IClientDao;
import sopra.vol.dao.IDao;
import sopra.vol.dao.sql.ClientDaoSql;
import sopra.vol.model.Client;

public class Application {
	private static Application instance = null;

	private final String jdbcUrl = "jdbc:mysql://localhost:3306/tp_vol";
	private final String username = "root";
	private final String password = "admin";

	private final IClientDao clientDao = new ClientDaoSql();
	
	private Application() {
		super();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
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

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(jdbcUrl, username, password);
	}



}
