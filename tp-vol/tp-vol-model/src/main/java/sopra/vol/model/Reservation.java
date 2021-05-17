package sopra.vol.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Reservation")
public class Reservation {
	@Id
	@GeneratedValue
	private Integer numero;
	@Column(name = "dt_reservation")
	private Date dtReservation;
	private StatutReservation statut;
	private Client client;
	private Passager passager;
	
	private List<Billet> billets = new ArrayList<>();

	public Reservation() {
		super();
	}

	public Reservation(Integer numero, Date dtReservation, StatutReservation statut) {
		super();
		this.numero = numero;
		this.dtReservation = dtReservation;
		this.statut = statut;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Date getDtReservation() {
		return dtReservation;
	}

	public void setDtReservation(Date dtReservation) {
		this.dtReservation = dtReservation;
	}

	public StatutReservation getStatut() {
		return statut;
	}

	public void setStatut(StatutReservation statut) {
		this.statut = statut;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Passager getPassager() {
		return passager;
	}

	public void setPassager(Passager passager) {
		this.passager = passager;
	}

	public List<Billet> getBillets() {
		return billets;
	}

	public void setBillets(List<Billet> billets) {
		this.billets = billets;
	}

	@Override
	public String toString() {
		return "Reservation [numero=" + numero + ", dtReservation=" + dtReservation + ", statut=" + statut + ", client="
				+ client + ", passager=" + passager + ", billets=" + billets + "]";
	}

}
