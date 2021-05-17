package sopra.vol.repository;

import java.util.List;

import sopra.vol.model.Particulier;
import sopra.vol.model.Entreprise;
import sopra.vol.model.Client;

public interface IClientRepositoryJpa extends IRepository<Client, Long> {

	List<Particulier> findAllParticulier();

	List<Entreprise> findAllEntreprise();
	
	Particulier findParticulierByPrenom(String prenom);

	Entreprise findEntrepriseBySiret(String siret);
}
