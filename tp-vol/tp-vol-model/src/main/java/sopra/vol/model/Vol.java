package sopra.vol.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "Vol")
public class Vol {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "statut_Vol")
	private StatutVol statutVol;
	@Column(name = "dt_Depart")
	private Date dtDepart;
	@Column(name = "dt_Arrivee")
	private Date dtArrivee;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aeroport_depart")
	private Aeroport depart;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "aeroport_arrivee")
	private Aeroport arrivee;
	@Column(name  = "nb_de_place_dispo")
	private int nbPlaceDispo;
	@ManyToOne(mappedBy = "vol")
	private List<Billet> billets = new ArrayList<>();
	@ManyToOne(mappedBy = "vol")
	private List<CompagnieAerienneVol> compagnieAeriennes = new ArrayList<CompagnieAerienneVol>();

	public Vol() {
		super();
	}
	
	public Vol(StatutVol statutVol, Date dtDepart, Date dtArrivee, int nbPlaceDispo) {
		super();
		this.statutVol = statutVol;
		this.dtDepart = dtDepart;
		this.dtArrivee = dtArrivee;
		this.nbPlaceDispo = nbPlaceDispo;
	}

	public Vol(Long id, StatutVol statutVol, Date dtDepart, Date dtArrivee, int nbPlaceDispo) {
		super();
		this.id = id;
		this.statutVol = statutVol;
		this.dtDepart = dtDepart;
		this.dtArrivee = dtArrivee;
		this.nbPlaceDispo = nbPlaceDispo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatutVol getStatutVol() {
		return statutVol;
	}

	public void setStatutVol(StatutVol statutVol) {
		this.statutVol = statutVol;
	}

	public List<Billet> getBillets() {
		return billets;
	}

	public void setBillets(List<Billet> billets) {
		this.billets = billets;
	}

	public Date getDtDepart() {
		return dtDepart;
	}

	public void setDtDepart(Date dtDepart) {
		this.dtDepart = dtDepart;
	}

	public Date getDtArrivee() {
		return dtArrivee;
	}

	public void setDtArrivee(Date dtArrivee) {
		this.dtArrivee = dtArrivee;
	}

	public Aeroport getDepart() {
		return depart;
	}

	public void setDepart(Aeroport depart) {
		this.depart = depart;
	}

	public Aeroport getArrivee() {
		return arrivee;
	}

	public int getNbPlaceDispo() {
		return nbPlaceDispo;
	}

	public void setNbPlaceDispo(int nbPlaceDispo) {
		this.nbPlaceDispo = nbPlaceDispo;
	}

	public void setArrivee(Aeroport arrivee) {
		this.arrivee = arrivee;
	}

	public List<CompagnieAerienneVol> getCompagnieAeriennes() {
		return compagnieAeriennes;
	}

	public void setCompagnieAeriennes(List<CompagnieAerienneVol> compagnieAeriennes) {
		this.compagnieAeriennes = compagnieAeriennes;
	}

}
