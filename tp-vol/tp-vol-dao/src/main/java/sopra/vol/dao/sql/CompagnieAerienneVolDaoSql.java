package sopra.vol.dao.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sopra.vol.Application;
import sopra.vol.dao.ICompagnieAerienneVolDao;
import sopra.vol.model.CompagnieAerienne;
import sopra.vol.model.CompagnieAerienneVol;

public class CompagnieAerienneVolDaoSql implements ICompagnieAerienneVolDao {

	@Override
	public List<CompagnieAerienneVol> findAll() {
		List<CompagnieAerienneVol> compagnieAerienneVols = new ArrayList<CompagnieAerienneVol>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Application.getInstance().getConnection();
			ps = conn.prepareStatement("SELECT id, numero_Vol FROM Compagnie_Aerienne_Vol");

			rs = ps.executeQuery();

			while (rs.next()) {
				Long id = rs.getLong("id");
				String numeroVol = rs.getString("numeroVol");

				CompagnieAerienneVol compagnieAerienneVol = new CompagnieAerienneVol(id, numeroVol);

				compagnieAerienneVols.add(compagnieAerienneVol);
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

		return compagnieAerienneVols;
	}

	@Override
	public CompagnieAerienneVol findById(Long id) {
		CompagnieAerienneVol compagnieAerienneVol = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Application.getInstance().getConnection();
			ps = conn.prepareStatement("SELECT numero_Vol FROM Compagnie_Aerienne_Vol WHERE id = ?");

			ps.setLong(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				String numeroVol = rs.getString("numeroVol");

				compagnieAerienneVol = new CompagnieAerienneVol(id, numeroVol);
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

		return compagnieAerienneVol;
	}

	@Override
	public void create(CompagnieAerienneVol obj) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = Application.getInstance().getConnection();
			ps = conn.prepareStatement("INSERT INTO CompagnieAerienneVol (numero_Vol) VALUES (?)", Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, obj.getNumeroVol());

			int rows = ps.executeUpdate();

			if (rows == 1) {
				ResultSet keys = ps.getGeneratedKeys();

				if (keys.next()) {
					Long id = keys.getLong(1);
					obj.setId(id);
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
	public void update(CompagnieAerienneVol obj) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = Application.getInstance().getConnection();
			ps = conn.prepareStatement("UPDATE CompagnieAerienneVol SET numero_Vol = ? WHERE id = ?");

			ps.setString(1, obj.getNumeroVol());
			ps.setLong(2, obj.getId());

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
	public void delete(CompagnieAerienneVol obj) {
		deleteById(obj.getId());
	}
	

	@Override
	public void deleteById(Long id) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = Application.getInstance().getConnection();
			ps = conn.prepareStatement("DELETE FROM Compagnie_Aerienne_Vol WHERE id = ?");

			ps.setLong(1, id);

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
