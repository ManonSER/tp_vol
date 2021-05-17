package sopra.vol.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name = "City")
public class Ville {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "name")
	private String nom;
	@ManyToMany(mappedBy = "villes")
	private List<Aeroport> aeroports = new ArrayList<Aeroport>();

	public Ville() {
		super();
	}

	public Ville(String nom) {
		super();
		this.nom = nom;
	}
	
	public Ville(Long id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Aeroport> getAeroports() {
		return aeroports;
	}

	public void setAeroports(List<Aeroport> aeroports) {
		this.aeroports = aeroports;
	}

}
