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

import sopra.vol.dao.IBilletDao;
import sopra.vol.model.Billet;

public class BilletDaoSql implements IBilletDao {

	@Override
	public List<Billet> findAll() {
		List<Billet> billets = new ArrayList<Billet>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = sopra.vol.Application.getInstance().getConnection();

			preparedStatement = connection.prepareStatement("SELECT id, numero_place, classe, prix, ordre FROM billet");

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String numeroPlace = resultSet.getString("numero_place");
				String classe = resultSet.getString("classe");
				int ordre = resultSet.getInt("ordre");
//					Long idEvaluation = resultSet.getLong("evaluation_id");
//					Long idFiliere = resultSet.getLong("filiere_id");

				Float prix = resultSet.getFloat("prix");

				Billet billet = new Billet(id, numeroPlace, classe, prix, ordre);

//					
//					if (idEvaluation != null) {
//						Evaluation evaluation = Application.getInstance().getEvaluationDao().findById(idEvaluation);
//
//						stagiaire.setEvaluation(evaluation);
//					}
//
//					if (idFiliere != null) {
//						Filiere filiere = Application.getInstance().getFiliereDao().findById(idFiliere);
//
//						stagiaire.setFiliere(filiere);
//					}

				billets.add(billet);
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

		return billets;
	}

	@Override
	public Billet findById(Long id) {
		Billet billet = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = sopra.vol.Application.getInstance().getConnection();

			preparedStatement = connection
					.prepareStatement("SELECT numero_place, classe, prix, ordre FROM billet WHERE id= ?");
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				String numeroPlace = resultSet.getString("numero_place");
				String classe = resultSet.getString("classe");
				int ordre = resultSet.getInt("ordre");
//				Long idEvaluation = resultSet.getLong("evaluation_id");
//				Long idFiliere = resultSet.getLong("filiere_id");

				Float prix = resultSet.getFloat("prix");

				billet = new Billet(id, numeroPlace, classe, prix, ordre);

//				
//				if (idEvaluation != null) {
//					Evaluation evaluation = Application.getInstance().getEvaluationDao().findById(idEvaluation);
//
//					stagiaire.setEvaluation(evaluation);
//				}
//
//				if (idFiliere != null) {
//					Filiere filiere = Application.getInstance().getFiliereDao().findById(idFiliere);
//
//					stagiaire.setFiliere(filiere);
//				}

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

		return billet;
	}

	@Override
	public void create(Billet obj) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = sopra.vol.Application.getInstance().getConnection();

			preparedStatement = connection.prepareStatement(
					"INSERT INTO billet (numero_place, classe, prix, ordre, reservation_numero, vol_id) VALUES (?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			if (obj.getNumeroPlace() != null) {
				preparedStatement.setString(1, obj.getNumeroPlace());
			} else {
				preparedStatement.setNull(1, Types.VARCHAR);
			}
			if (obj.getClasse() != null) {
				preparedStatement.setString(2, obj.getClasse());
			} else {
				preparedStatement.setNull(2, Types.VARCHAR);
			}
			if (obj.getPrix() != 0F) {
				preparedStatement.setFloat(3, obj.getPrix());
			} else {
				preparedStatement.setNull(3, Types.FLOAT);
			}
			if (obj.getOrdre() != 0) {
				preparedStatement.setInt(4, obj.getOrdre());
			} else {
				preparedStatement.setNull(4, Types.INTEGER);
			}
			if (obj.getReservation() != null) {
				preparedStatement.setInt(5, obj.getReservation().getNumero());
			} else {
				preparedStatement.setNull(5, Types.INTEGER);
			}
			if (obj.getVol() != null) {
				preparedStatement.setLong(6, obj.getVol().getId());
			} else {
				preparedStatement.setNull(6, Types.LONGVARBINARY);
			}
			
			int rows = preparedStatement.executeUpdate();

			if (rows == 1) {


			}

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
	public void update(Billet obj) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = sopra.vol.Application.getInstance().getConnection();

			preparedStatement = connection.prepareStatement(
					"Update billet numero_place = ?, classe= ?, prix= ?, ordre= ?) Where id= ?",
					Statement.RETURN_GENERATED_KEYS);
			if (obj.getNumeroPlace() != null) {
				preparedStatement.setString(1, obj.getNumeroPlace());
			} else {
				preparedStatement.setNull(1, Types.VARCHAR);
			}
			if (obj.getClasse() != null) {
				preparedStatement.setString(2, obj.getClasse());
			} else {
				preparedStatement.setNull(2, Types.VARCHAR);
			}
			if (obj.getPrix() != 0F) {
				preparedStatement.setFloat(3, obj.getPrix());
			} else {
				preparedStatement.setNull(3, Types.VARCHAR);
			}
			if (obj.getOrdre() != 0) {
				preparedStatement.setInt(4, obj.getOrdre());
			} else {
				preparedStatement.setNull(4, Types.VARCHAR);
			}
			preparedStatement.setLong(5, obj.getId());

			int rows = preparedStatement.executeUpdate();

			if (rows == 1) {

			}

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
	public void delete(Billet obj) {
		deleteById(obj.getId());
		
	}

	@Override
	public void deleteById(Long id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = sopra.vol.Application.getInstance().getConnection();

			preparedStatement = connection.prepareStatement(
					"Delete billet Where id= ?",
					Statement.RETURN_GENERATED_KEYS);
	
			preparedStatement.setLong(1, id);

			int rows = preparedStatement.executeUpdate();

			if (rows == 1) {

			}

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
