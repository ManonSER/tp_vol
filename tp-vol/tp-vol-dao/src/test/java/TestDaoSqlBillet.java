import java.util.List;

import sopra.vol.Application;
import sopra.vol.dao.IBilletDao;
import sopra.vol.model.Billet;
import sopra.vol.model.Reservation;
import sopra.vol.model.Vol;

public class TestDaoSqlBillet {

	public static void main(String[] args) {
		IBilletDao billetDao = Application.getInstance().getBilletDao();
		
		Vol vol1 = new Vol();
		vol1.setId(8L);
		Reservation reservation1 = new Reservation();
		reservation1.setNumero(1);
		
		Billet billet1 = new Billet("25", "2eme", 450.2F, 8);
		billet1.setReservation(reservation1);
		billet1.setVol(vol1);
		billetDao.create(billet1);
//		billetDao.create(billet1);
//		billetDao.delete(billet1);
//		List<Billet> billets = billetDao.findAll();
//		System.out.println(billets);
//		
		
		
		
		

	}

}
