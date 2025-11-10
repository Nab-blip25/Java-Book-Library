package model;

import java.time.LocalDate;

/**
 * Classe Pret
 * 
 * @author anais peigney
 * @version 1.0
 */
public class Pret {
	private Integer idPret;
	private LocalDate dateEmprunt;
	private Integer dureeEmprunt;
	private LocalDate dateRetourEffective;
	private Long isbn;
	private Integer idUsager;
	private String prenomUsager;
	private String nomUsager;
	private Livre aLivre;
	private Usager aUsager;
	
	/**
	 * Constructeur
	 * 
	 * @param aId l'ID du livre
	 * @param aDateEmprunt la date d'emprunt du livre
	 * @param aDureeEmprunt la duree d'emprunt du livre
	 * @param aDateRetourEffective la date de retour effective du livre
	 */
	public Pret (Integer aId, LocalDate aDateEmprunt, Integer aDureeEmprunt, LocalDate aDateRetourEffective) {
		idPret = aId;
		dateEmprunt = aDateEmprunt;
		dureeEmprunt = aDureeEmprunt;
		dateRetourEffective = aDateRetourEffective;
	}
	
	/**
	 * Constructeur avec l'ISBN du livre et l'ID de l'usager
	 * 
	 * @param aId l'ID du livre
	 * @param aDateEmprunt la date d'emprunt du livre
	 * @param aDureeEmprunt la duree d'emprunt du livre
	 * @param aDateRetourEffective la date de retour effective du livre
	 * @param aIsbn l'ISBN du livre
	 * @param aIdUsager l'ID de l'usager
	 */
	public Pret (Integer aId, LocalDate aDateEmprunt, Integer aDureeEmprunt, LocalDate aDateRetourEffective, Long aIsbn, Integer aIdUsager) {
		idPret = aId;
		dateEmprunt = aDateEmprunt;
		dureeEmprunt = aDureeEmprunt;
		dateRetourEffective = aDateRetourEffective;
		isbn = aIsbn;
		idUsager = aIdUsager;
	}
	
	/**
	 * Constructeur avec l'ISBN du livre et l'ID de l'usager mais pas de date de retour effective (pret en cours)
	 * 
	 * @param aId l'ID du livre
	 * @param aDateEmprunt la date d'emprunt du livre
	 * @param aDureeEmprunt la duree d'emprunt du livre
	 * @param aIsbn l'ISBN du livre
	 * @param aIdUsager l'ID de l'usager
	 */
	public Pret (Integer aId, LocalDate aDateEmprunt, Integer aDureeEmprunt, Long aIsbn, Integer aIdUsager) {
		idPret = aId;
		dateEmprunt = aDateEmprunt;
		dureeEmprunt = aDureeEmprunt;
		isbn = aIsbn;
		idUsager = aIdUsager;
	}
	
	/**
	 * Constructeur avec l'ISBN du livre et le prenom et le nom de l'usager
	 * 
	 * @param aId l'ID du livre
	 * @param aDateEmprunt la date d'emprunt du livre
	 * @param aDureeEmprunt la duree d'emprunt du livre
	 * @param aDateRetourEffective la date de retour effective du livre
	 * @param aIsbn l'ISBN du livre
	 * @param aPrenomUsager le prenom de l'usager
	 * @param aNomUsager le nom de l'usager
	 */
	public Pret (Integer aId, LocalDate aDateEmprunt, Integer aDureeEmprunt, LocalDate aDateRetourEffective, Long aIsbn, String aPrenomUsager, String aNomUsager) {
		idPret = aId;
		dateEmprunt = aDateEmprunt;
		dureeEmprunt = aDureeEmprunt;
		dateRetourEffective = aDateRetourEffective;
		isbn = aIsbn;
		prenomUsager = aPrenomUsager;
		nomUsager = aNomUsager;
	}

	/**
	 * Constructeur avec un objet Livre et un objet Usager
	 * 
	 * @param aLivre l'objet Livre
	 * @param aUsager l'objet Usager
	 */
	public Pret (Livre aLivre, Usager aUsager) {
		this.aLivre = aLivre;
		this.aUsager = aUsager;
	}

	/**
	 * Fonction pour afficher les infos des prêts dans le menu principal
	 * 
	 * @return string contenant les infos
	*/
	public String toString() {
		if (dateRetourEffective != null)
			return "Prêt n°" + idPret + " : Livre " + isbn + " emprunté le " + dateEmprunt + " pour une durée de " + dureeEmprunt + " jour(s) par " + prenomUsager + " " + nomUsager + ", rendu le " + dateRetourEffective;
		else
			return "Prêt n°" + idPret + " : Livre " + isbn + " emprunté le " + dateEmprunt + " pour une durée de " + dureeEmprunt + " jour(s) par " + prenomUsager + " " + nomUsager + ", non rendu";
	}
		
	/**
	 * getter de l'ID du pret
	 * 
	 * @return l'ID du pret
	 */
	public Integer getIdPret() {
		return idPret;
	}

	/**
	 * setter de l'ID du pret
	 * 
	 * @param id
	 */
	public void setIdPret(Integer id) {
		this.idPret = id;
	}

	/**
	 * getter de la date d'emprunt
	 * 
	 * @return la date d'emprunt
	 */
	public LocalDate getDateEmprunt() {
		return dateEmprunt;
	}

	/**
	 * setter de la date d'emprunt
	 * 
	 * @param dateEmprunt
	 */
	public void setDateEmprunt(LocalDate dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	/**
	 * getter de la duree d'emprunt
	 * 
	 * @return la duree de l'emprunt
	 */
	public Integer getDureeEmprunt() {
		return dureeEmprunt;
	}

	/**
	 * setter de la duree d'emprunt
	 * 
	 * @param dureeEmprunt
	 */
	public void setDureeEmprunt(Integer dureeEmprunt) {
		this.dureeEmprunt = dureeEmprunt;
	}

	/**
	 * getter de la date de retour effective
	 * 
	 * @return la date de retour effective
	 */
	public LocalDate getDateRetourEffective() {
		return dateRetourEffective;
	}

	/**
	 * setter de la date de retour effective
	 * 
	 * @param dateRetourEffective
	 */
	public void setDateRetourEffective(LocalDate dateRetourEffective) {
		this.dateRetourEffective = dateRetourEffective;
	}
	
	/** 
	 * getter de l'ISBN du livre
	 * 
	 * @return l'ISBN du livre
	 */
	public Long getIsbn() {
		return isbn;
	}

	/**
	 * setter de l'ISBN du livre
	 * 
	 * @param isbn
	 */
	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}
	
	/**
	 * getter de l'ID de l'usager
	 * 
	 * @return l'ID de l'usager
	 */
	public Integer getIdUsager() {
		return idUsager;
	}

	/**
	 * setter de l'ID de l'usager
	 * 
	 * @param idUsager
	 */
	public void setIdUsager(Integer idUsager) {
		this.idUsager = idUsager;
	}
	
	/**
	 * getter du prenom de l'usager
	 * 
	 * @return le prenom de l'usager
	 */
	public String getPrenomUsager() {
		return prenomUsager;
	}

	/**
	 * setter du prenom de l'usager
	 * 
	 * @param prenomUsager
	 */
	public void setPrenomUsager(String prenomUsager) {
		this.prenomUsager = prenomUsager;
	}
	
	/**
	 * getter du nom de l'usager
	 * 
	 * @return le nom de l'usager
	 */
	public String getNomUsager() {
		return nomUsager;
	}

	/**
	 * setter du nom de l'usager
	 * 
	 * @param nomUsager
	 */
	public void setNomUsager(String nomUsager) {
		this.nomUsager = nomUsager;
	}
	
	/**
	 * getter de l'objet Livre
	 * 
	 * @return l'objet Livre
	 */
	public Livre getLivre() {
		return aLivre;
	}
	
	/**
	 * setter de l'objet Livre
	 * @param aLivre
	 */
	public void setLivre(Livre aLivre) {
		this.aLivre = aLivre;
	}
	
	/**
	 * getter de l'objet Usager
	 * 
	 * @return l'objet Usager
	 */
	public Usager getUsager() {
		return aUsager;
	}
	
	/**
	 * setter de l'objet Usager
	 * 
	 * @param aUsager
	 */
	public void setUsager(Usager aUsager) {
		this.aUsager = aUsager;
	}
	
	/**
	 * fonction pour afficher les infos du pret
	 */
	public void display() {
		System.out.println("L'ID du prêt est : " + idPret);
		System.out.println("La date d'emprunt est : " + dateEmprunt);
		System.out.println("La durée de l'emprunt est de : " + dureeEmprunt);
		System.out.println("La date de retour effective est le : " + dateRetourEffective);
	}
}