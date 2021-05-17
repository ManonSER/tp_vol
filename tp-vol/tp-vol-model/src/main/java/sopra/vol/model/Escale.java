package sopra.vol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stopover")
public class Escale {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "start_hour")
	private Horaire hDepart;
	@Column(name = "arrival_hour")
	private Horaire hArrivee;
	@ManyToOne(fetch = FetchType.LAZY)
	private Vol vol;
	@ManyToOne(fetch = FetchType.LAZY)
	private Aeroport aeroport;

	public Escale() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Horaire gethDepart() {
		return hDepart;
	}

	public void sethDepart(Horaire hDepart) {
		this.hDepart = hDepart;
	}

	public Horaire gethArrivee() {
		return hArrivee;
	}

	public void sethArrivee(Horaire hArrivee) {
		this.hArrivee = hArrivee;
	}

	public Vol getVol() {
		return vol;
	}

	public void setVol(Vol vol) {
		this.vol = vol;
	}

	public Aeroport getAeroport() {
		return aeroport;
	}

	public void setAeroport(Aeroport aeroport) {
		this.aeroport = aeroport;
	}

}
