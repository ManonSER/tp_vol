package sopra.vol.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import sopra.vol.Application;
import sopra.vol.dao.IAdresseDao;
import sopra.vol.model.Adresse;


public class AdresseDaoSql implements IAdresseDao {

	@Override
	public List<Adresse> findAll() {
		
List<Adresse> adresses = new ArrayList<Adresse>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = Application.getInstance().getConnection();

			preparedStatement = connection.prepareStatement(
					"SELECT id, rue, complement, code_Postal, ville, pays FROM Adresse ");

			//preparedStatement.setString(1, "Adresse");

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String rue = resultSet.getString("rue");
				String complement = resultSet.getString("complement");
				String codePostal = resultSet.getString("code_Postal");
				String ville = resultSet.getString("ville");
				String pays = resultSet.getString("pays");
				
						

				Adresse adresse = new Adresse(id, rue, complement, codePostal, ville, pays);

				
				adresses.add(adresse);
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

		return adresses;
	}

	@Override
	public Adresse findById(Long id) {
		
		Adresse adresse = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = Application.getInstance().getConnection();

			preparedStatement = connection.prepareStatement(
					"SELECT rue, complement, code_Postal, ville, pays FROM adresse WHERE  id = ?");
			
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
			
								String rue = resultSet.getString("rue");
				String complement = resultSet.getString("complement");
				String codePostal = resultSet.getString("code_Postal");
				String ville = resultSet.getString("ville");
				String pays = resultSet.getString("pays");
			


				adresse = new Adresse(id, rue, complement, codePostal, ville, pays);
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

	return adresse;
}

	@Override
	public void create(Adresse obj) {
		
		Adresse adresse = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = Application.getInstance().getConnection();

			preparedStatement = connection.prepareStatement(
					"INSERT INTO adresse (rue, complement, code_Postal, ville, pays )  VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			
			//preparedStatement.setString(1, "adresse");
			
					
			preparedStatement.setString(1, obj.getRue());
			preparedStatement.setString(2, obj.getComplement());
			preparedStatement.setString(3, obj.getCodePostal());
			preparedStatement.setString(4, obj.getVille());
			preparedStatement.setString(5, obj.getPays());
			
							
			
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
	public void update(Adresse obj) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = Application.getInstance().getConnection();

			preparedStatement = connection.prepareStatement(
					"UPDATE adresse SET  rue = ?, complement = ?, code_Postal= ?,  ville = ?,  pays = ? where id = ?");

			
			preparedStatement.setString(1, obj.getRue());
			preparedStatement.setString(2, obj.getComplement());
			preparedStatement.setString(3, obj.getCodePostal());
			preparedStatement.setString(4, obj.getVille());
			preparedStatement.setString(5, obj.getPays());
			preparedStatement.setLong(6, obj.getId());
			
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
	public void delete(Adresse obj) {
		deleteById(obj.getId());
		
	}

	@Override
	public void deleteById(Long id) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = Application.getInstance().getConnection();

			preparedStatement = connection.prepareStatement("DELETE adresse from adresse WHERE id = ?");

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
