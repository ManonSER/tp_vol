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
import sopra.vol.dao.IClientDao;
import sopra.vol.model.Client;
import sopra.vol.model.Entreprise;
import sopra.vol.model.Particulier;
import sopra.vol.model.StatutJuridique;

public class ClientDaoSql implements IClientDao {

	@Override
	public List<Client> findAll() {
		
		List<Client> clients = new ArrayList<Client>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = Application.getInstance().getConnection();
			preparedStatement = connection.prepareStatement("SELLECT id, type, nom, prenom, siret, numeroTva, statutJuridique FROM client");
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String type = resultSet.getString("type");
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String siret = resultSet.getString("siret");
				String numeroTva = resultSet.getString("numeroTva");
				StatutJuridique statutJuridique = StatutJuridique.valueOf(resultSet.getString("statutJuridique"));
								
				Particulier particulier = null;
				Entreprise entreprise = null;
			
				if(type.equals("P")) {
					particulier = new Particulier();
					particulier.setId(id);
					particulier.setNom(nom);
					particulier.setPrenom(prenom);
					clients.add(particulier);
				}
				
				if(type.equals("E")) {
					entreprise = new Entreprise();
					entreprise.setId(id);
					entreprise.setNom(nom);
					entreprise.setSiret(siret);
					entreprise.setNumeroTVA(numeroTva);
					entreprise.setStatutJuridique(statutJuridique);
					clients.add(entreprise);
				}			
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

		return clients;
	}

	@Override
	public Client findById(Long id) {
		
		Client client = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = Application.getInstance().getConnection();
			preparedStatement = connection.prepareStatement("SELLECT id, type, nom, prenom, siret, numeroTva, statutJuridique FROM client WHERE id = ?");
			resultSet = preparedStatement.executeQuery();
			
			preparedStatement.setLong(1, id);
			
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
	
				String type = resultSet.getString("type");
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String siret = resultSet.getString("siret");
				String numeroTva = resultSet.getString("numeroTva");
				StatutJuridique statutJuridique = StatutJuridique.valueOf(resultSet.getString("statutJuridique"));
								
				Particulier particulier = null;
				Entreprise entreprise = null;
			
				if(type.equals("P")) {
					particulier = new Particulier();
					particulier.setId(id);
					particulier.setNom(nom);
					particulier.setPrenom(prenom);
					client = particulier;
				}
				
				if(type.equals("E")) {
					entreprise = new Entreprise();
					entreprise.setId(id);
					entreprise.setNom(nom);
					entreprise.setSiret(siret);
					entreprise.setNumeroTVA(numeroTva);
					entreprise.setStatutJuridique(statutJuridique);
					client = entreprise;
				}			
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

		return client;
	}

	@Override
	public void create(Client obj) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = Application.getInstance().getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO client (type, nom, prenom, siret, numero_tva, statut_juridique) VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		
			if(obj instanceof Particulier) {
				
				preparedStatement.setString(1, "P");
				preparedStatement.setString(2, obj.getNom());
				preparedStatement.setString(3, ((Particulier)obj).getPrenom());
				preparedStatement.setNull(4, Types.VARCHAR);
				preparedStatement.setNull(5, Types.VARCHAR);
				preparedStatement.setNull(6, Types.VARCHAR);
			}
			
			if(obj instanceof Entreprise) {
			
				preparedStatement.setString(1, "P");
				preparedStatement.setString(2, obj.getNom());
				preparedStatement.setNull(3, Types.VARCHAR);
				preparedStatement.setString(4, ((Entreprise)obj).getSiret());
				preparedStatement.setString(5, ((Entreprise)obj).getNumeroTVA());
				preparedStatement.setString(4, ((Entreprise)obj).getStatutJuridique().toString());
			}
		
			int rows = preparedStatement.executeUpdate();

			if (rows == 1) {
				resultSet = preparedStatement.getGeneratedKeys();

				if (resultSet.next()) {
					Long id = resultSet.getLong(1);

					obj.setId(id);
				}
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
	}

	@Override
	public void update(Client obj) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = Application.getInstance().getConnection();
			preparedStatement = connection.prepareStatement("UPDATE client SET type = ?, nom = ?, prenom = ?, siret = ?, numeroTva = ?, statutJuridique = ? WHERE id = ?");
			resultSet = preparedStatement.executeQuery();

			if(obj instanceof Particulier) {
				
				preparedStatement.setString(1, "P");
				preparedStatement.setString(2, obj.getNom());
				preparedStatement.setString(3, ((Particulier)obj).getPrenom());
				preparedStatement.setNull(4, Types.VARCHAR);
				preparedStatement.setNull(5, Types.VARCHAR);
				preparedStatement.setNull(6, Types.VARCHAR);
			}
			
			if(obj instanceof Entreprise) {
			
				preparedStatement.setString(1, "P");
				preparedStatement.setString(2, obj.getNom());
				preparedStatement.setNull(3, Types.VARCHAR);
				preparedStatement.setString(4, ((Entreprise)obj).getSiret());
				preparedStatement.setString(5, ((Entreprise)obj).getNumeroTVA());
				preparedStatement.setString(4, ((Entreprise)obj).getStatutJuridique().toString());
			}
		
			int rows = preparedStatement.executeUpdate();

			if (rows == 1) {
				resultSet = preparedStatement.getGeneratedKeys();

				if (resultSet.next()) {
					Long id = resultSet.getLong(1);

					obj.setId(id);
				}
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
	}

	@Override
	public void delete(Client obj) {
		deleteById(obj.getId());
	}

	@Override
	public void deleteById(Long id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = Application.getInstance().getConnection();

//			preparedStatement = connection.prepareStatement("DELETE client WHERE type = ? AND id = ?");
			preparedStatement = connection.prepareStatement("DELETE client WHERE id = ?");
			
//			preparedStatement.setString(1, obj.getString() ? "P" : "E"); // comment faire ?
			preparedStatement.setLong(2, id);

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
