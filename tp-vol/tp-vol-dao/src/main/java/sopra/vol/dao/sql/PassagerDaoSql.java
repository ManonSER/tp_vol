package sopra.vol.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import sopra.vol.Application;
import sopra.vol.dao.IPassagerDao;
import sopra.vol.model.Passager;
import sopra.vol.model.TypeIdentite;

public class PassagerDaoSql implements IPassagerDao {

	@Override
	public List<Passager> findAll() {
		List<Passager> passagers = new ArrayList<Passager>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = Application.getInstance().getConnection();

			preparedStatement = connection.prepareStatement(
					"SELECT id, nom, prenom, numero_Identite, type_Identite FROM passager ");

			preparedStatement.setString(1, "Passager");

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String numeroIdentite = resultSet.getString("numero_Identite");
				TypeIdentite typeIdentite = TypeIdentite.valueOf(resultSet.getString("type_Identite"));
				

				Passager passager = new Passager(id, nom, prenom, numeroIdentite, typeIdentite);

				
				passagers.add(passager);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}

		return passagers;
	}
		
	
	@Override
	public Passager findById(Long id) {
		
		Passager passager = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = Application.getInstance().getConnection();

			preparedStatement = connection.prepareStatement(
					"SELECT nom, prenom, numero_Identite, type_Identite FROM passager WHERE  id = ?");
			
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
			
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String numeroIdentite = resultSet.getString("numero_Identite");
				TypeIdentite typeIdentite = TypeIdentite.valueOf(resultSet.getString("type_Identite"));


			passager = new Passager(id, nom, prenom, numeroIdentite, typeIdentite);
			}
						

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	return passager;
}


	@Override
	public void create(Passager obj) {
	
		Passager passager = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = Application.getInstance().getConnection();

			preparedStatement = connection.prepareStatement(
					"INSERT INTO passager (nom, prenom, numero_Identite, type_Identite)  VALUES (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, "Passager");
			
			if (obj.getTypeIdentite() != null) {
				preparedStatement.setString(4, obj.getTypeIdentite().toString());
			} else {
				preparedStatement.setNull(4, Types.VARCHAR);
			}
			
			preparedStatement.setString(1, obj.getNom());
			preparedStatement.setString(2, obj.getPrenom());
			preparedStatement.setString(3, obj.getNumeroIdentite());
							
			
			int rows = preparedStatement.executeUpdate();
			
			if(rows > 0) {
				resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()) {
					Long id = resultSet.getLong(1);
					obj.setId(id);
				}
			}
		
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			
		}
	}
	
		
	}

	@Override
	public void update(Passager obj) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = Application.getInstance().getConnection();

			preparedStatement = connection.prepareStatement(
					"UPDATE passager SET  nom = ?, prenom = ?, numero_Identite= ?,  type_Identite = ? where id = ?");

			if (obj.getTypeIdentite() != null) {
				preparedStatement.setString(4, obj.getTypeIdentite().toString());
			} else {
				preparedStatement.setNull(4, Types.VARCHAR);
			}

			preparedStatement.setString(1, obj.getNom());
			preparedStatement.setString(2, obj.getPrenom());
			preparedStatement.setString(3, obj.getNumeroIdentite());
			preparedStatement.setLong(5, obj.getId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
	}
		

	@Override
	public void delete(Passager obj) {
		deleteById(obj.getId());
		
	}

	@Override
	public void deleteById(Long id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = Application.getInstance().getConnection();

			preparedStatement = connection.prepareStatement("DELETE passager from passager WHERE id = ?");

			preparedStatement.setLong(1, id);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

}
