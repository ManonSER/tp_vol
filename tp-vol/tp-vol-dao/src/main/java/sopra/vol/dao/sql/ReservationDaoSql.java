package sopra.vol.dao.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import sopra.vol.Application;
import sopra.vol.dao.IReservationDao;
import sopra.vol.model.Reservation;
import sopra.vol.model.StatutReservation;

public class ReservationDaoSql implements IReservationDao{

	@Override
	public List<Reservation> findAll() {
		List<Reservation> reservations = new ArrayList<Reservation>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Application.getInstance().getConnection();
			ps = conn.prepareStatement("SELECT numero, dt_Reservation, statut_reservation FROM reservation");
			rs = ps.executeQuery();

			while (rs.next()) {
				Integer numero = rs.getInt("numero");
				Date dtReservation = rs.getDate("dt_reservation");
				StatutReservation statut = StatutReservation.valueOf(rs.getString("statut_reservation"));

				Reservation reservation = new Reservation(numero, dtReservation, statut);

				reservations.add(reservation);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return reservations;
	}

	@Override
	public Reservation findById(Integer numero) {
		Reservation reservation = null;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Application.getInstance().getConnection();
			ps = conn.prepareStatement("SELECT dt_reservation, statut_reservation FROM reservation WHERE numero = ?");
			
			ps.setLong(1, numero);
			
			rs = ps.executeQuery();

			if (rs.next()) {
				Date dtReservation = rs.getDate("dt_reservation");
				StatutReservation statut = StatutReservation.valueOf(rs.getString("statut_reservation"));

				reservation = new Reservation(numero, dtReservation, statut);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return reservation;
	}

	@Override
	public void create(Reservation obj) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = Application.getInstance().getConnection();
			ps = conn.prepareStatement("INSERT INTO reservation (numero, dt_reservation, statut_reservation) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
						
			ps.setInt(1, obj.getNumero());
			
			if (obj.getDtReservation() != null) {
				ps.setDate(2, new java.sql.Date(obj.getDtReservation().getTime()));
			} else {
				ps.setNull(2, Types.DATE);
			}
			
			if (obj.getStatut() != null) {
				ps.setString(3, obj.getStatut().toString());
			} else {
				ps.setNull(3, Types.VARCHAR);
			}
			
			int rows = ps.executeUpdate();
			
			if(rows > 0) {
				rs = ps.getGeneratedKeys();
				if(rs.next()) {
					Integer numero = rs.getInt(1);
					obj.setNumero(numero);
				}
			}
		

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void update(Reservation obj) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = Application.getInstance().getConnection();

			preparedStatement = connection.prepareStatement(
					"UPDATE reservation SET dt_reservation = ?, statut_reservation = ? WHERE numero = ?");

			if (obj.getDtReservation() != null) {
				preparedStatement.setDate(1, new java.sql.Date(obj.getDtReservation().getTime()));
			} else {
				preparedStatement.setNull(1, Types.DATE);
			}
		
			if (obj.getStatut() != null) {
				preparedStatement.setString(2, obj.getStatut().toString());
			} else {
				preparedStatement.setNull(2, Types.VARCHAR);
			}
	

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
	public void delete(Reservation obj) {
		deleteById(obj.getNumero());
		
	}

	@Override
	public void deleteById(Integer numero) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = Application.getInstance().getConnection();
			ps = conn.prepareStatement("DELETE FROM reservation WHERE numero= ?");
			
			ps.setInt(1, numero);
			
			int rows = ps.executeUpdate();
			
			if(rows != 1) {
				// TODO renvoyer une exception
			}
		

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
