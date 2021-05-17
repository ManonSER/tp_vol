package sopra.vol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Ticket")
public class Billet {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "place_number")
	private String numeroPlace;
	@Column(name = "class")
	private String classe;
	@Column(name = "price")
	private float prix;
	@Column(name = "order_notTheOneInSql")
	private int ordre;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reservation")
	private Reservation reservation;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "flight")
	private Vol vol;

	public Billet() {
		super();
	}

	public Billet(String numeroPlace, String classe, float prix, int ordre) {
		super();
		this.numeroPlace = numeroPlace;
		this.classe = classe;
		this.prix = prix;
		this.ordre = ordre;
	}

	public Billet(Long id, String numeroPlace, String classe, float prix, int ordre) {
		super();
		this.id = id;
		this.numeroPlace = numeroPlace;
		this.classe = classe;
		this.prix = prix;
		this.ordre = ordre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroPlace() {
		return numeroPlace;
	}

	public void setNumeroPlace(String numeroPlace) {
		this.numeroPlace = numeroPlace;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public int getOrdre() {
		return ordre;
	}

	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public Vol getVol() {
		return vol;
	}

	public void setVol(Vol vol) {
		this.vol = vol;
	}

}
