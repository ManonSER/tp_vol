package sopra.tp_vol.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import sopra.vol.Application;
import sopra.vol.dao.IReservationDao;
import sopra.vol.model.Reservation;
import sopra.vol.model.StatutReservation;


public class TestReservationDao {
	
	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		IReservationDao reservationDao = Application.getInstance().getReservationDao();

		List<Reservation> reservations = reservationDao.findAll();
		
		Reservation reservation = new Reservation(3000, sdf.parse("12/05/2021"), StatutReservation.CONFIRMER);
		
		reservationDao.create(reservation);
		System.out.println(reservationDao.findAll());
		
		reservationDao.deleteById(1000);
		System.out.println(reservationDao.findAll());
		
	}
}
