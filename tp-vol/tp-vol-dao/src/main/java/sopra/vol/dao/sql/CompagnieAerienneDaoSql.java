package sopra.vol.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sopra.vol.Application;
import sopra.vol.dao.ICompagnieAerienneDao;
import sopra.vol.model.CompagnieAerienne;


public class CompagnieAerienneDaoSql implements ICompagnieAerienneDao {

	@Override
	public List<CompagnieAerienne> findAll() {
		List<CompagnieAerienne> compagnieAeriennes = new ArrayList<CompagnieAerienne>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Application.getInstance().getConnection();
			ps = conn.prepareStatement("SELECT code, nom FROM Compagnie_Aerienne");

			rs = ps.executeQuery();

			while (rs.next()) {
				String code = rs.getString("code");
				String nom = rs.getString("nom");

				CompagnieAerienne compagnieAerienne = new CompagnieAerienne(code, nom);

				compagnieAeriennes.add(compagnieAerienne);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return compagnieAeriennes;
	}

	@Override
	public CompagnieAerienne findById(String code) {
		CompagnieAerienne compagnieAerienne = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Application.getInstance().getConnection();
			ps = conn.prepareStatement("SELECT nom FROM Compagnie_Aerienne WHERE code = ?");

			ps.setString(1, code);

			rs = ps.executeQuery();

			if (rs.next()) {
				String nom = rs.getString("name");

				compagnieAerienne = new CompagnieAerienne(code, nom);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return compagnieAerienne;
	}


	@Override
	public void create(CompagnieAerienne compagnieAerienne) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = Application.getInstance().getConnection();
			ps = conn.prepareStatement("INSERT INTO CompagnieAerienne (nom) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, compagnieAerienne.getNom());

			int rows = ps.executeUpdate();

			if (rows == 1) {
				ResultSet keys = ps.getGeneratedKeys();

				if (keys.next()) {
					String code = keys.getString(1);
					compagnieAerienne.setCode(code);
				}
			} else {
				throw new SQLException("Insertion en échec");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(CompagnieAerienne compagnieAerienne) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = Application.getInstance().getConnection();
			ps = conn.prepareStatement("UPDATE CompagnieAerienne SET nom = ? WHERE code = ?");

			ps.setString(1, compagnieAerienne.getNom());
			ps.setString(2, compagnieAerienne.getCode());

			int rows = ps.executeUpdate();

			if (rows != 1) {
				throw new SQLException("Mise à jour en échec");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(CompagnieAerienne compagnieAerienne) {
		deleteById(compagnieAerienne.getCode());
	}

		
	

	@Override
	public void deleteById(String code) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = Application.getInstance().getConnection();
			ps = conn.prepareStatement("DELETE FROM Compagnie_Aerienne WHERE code = ?");

			ps.setString(1, code);

			int rows = ps.executeUpdate();

			if (rows != 1) {
				throw new SQLException("Suppression en échec");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
