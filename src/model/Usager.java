package model;

/**
 * Classe Usager
 * 
 * @author anais peigney
 * @version 1.0
 */
public class Usager {
	private Integer id;
	private String nom;
	private String prenom;
	private Integer anneeNaissance;
	private Integer tarifReduit;
	
	/**
	 * Constructeur
	 * 
	 * @param id l'ID de l'usager
	 * @param nom le nom de l'usager
	 * @param prenom le prenom de l'usager
	 * @param anneeNaissance la date de naissance de l'usager
	 * @param tarifReduit le tarif reduit de l'usager
	 */
	public Usager (Integer id, String nom, String prenom, Integer anneeNaissance, Integer tarifReduit) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.anneeNaissance = anneeNaissance;
		this.tarifReduit = tarifReduit;
	}
	
	/**
	 * Constructeur avec seulement l'ID de l'usager
	 * 
	 * @param aId l'ID de l'usager
	 */
	public Usager (Integer aId) {
		id = aId;
	}

	/**
	 * Permet d'afficher les infos des usagers dans le menu principal
	 *
	 * @return string contenant les infos
	 */
	public String toString() {
	    return "Usager n°" + this.id + " : " + this.nom + " " + this.prenom + ", né(e) en " + this.anneeNaissance + " (tarif réduit = " + this.tarifReduit + ")";
	}

	/**
	 * getter de l'ID de l'usager
	 * 
	 * @return l'ID de l'usager
	 */
	public Integer getIdUsager() {
		return id;
	}

	/**
	 * setter de l'ID de l'usager
	 * 
	 * @param id
	 */
	public void setIdUsager(Integer id) {
		this.id = id;
	}

	/**
	 * getter du nom de l'usager
	 * 
	 * @return le nom de l'usager
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * setter du nom de l'usager
	 * 
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * getter du prenom de l'usager
	 * @return le prenom de l'usager
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * setter du prenom de l'usager
	 * 
	 * @param prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * getter de l'annee de naissance de l'usager
	 * 
	 * @return l'annee de naissance de l'usager
	 */
	public Integer getAnneeNaissance() {
		return anneeNaissance;
	}

	/**
	 * setter de l'annee de naissance de l'usager
	 * 
	 * @param anneeNaissance
	 */
	public void setAnneeNaissance(Integer anneeNaissance) {
		this.anneeNaissance = anneeNaissance;
	}

	/**
	 * getter du tarif reduit de l'usager
	 * 
	 * @return tarif reduit de l'usager
	 */
	public int getTarifReduit() {
		return tarifReduit;
	}

	/**
	 * setter du tarif reduit de l'usager
	 * 
	 * @param tarifReduit
	 */
	public void setTarifReduit(Integer tarifReduit) {
		this.tarifReduit = tarifReduit;
	}
	
	/**
	 * fonction pour afficher les infos de l'usager
	 */
	public void display() {
		System.out.println("L'ID de l'usager est : " + id);
		System.out.println("Le nom de l'usager est : " + nom);
		System.out.println("Le prénom de l'usager est : " + prenom);
		System.out.println("L'année de naissance de l'usager est : " + anneeNaissance);
		System.out.println("L'usager possède-t-il un tarif réduit ? " + tarifReduit);
	}
	
}
