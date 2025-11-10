package model;

/**
 * Classe Livre
 * 
 * @author anais peigney
 * @version 1.0
 */
public class Livre {
	private Long isbn;
	private String titre;
	private String prenomAuteur;
	private String nomAuteur;
	private Integer idAuteur;
	private Integer nbPages;
	private String genre;
	private Integer nbExemplaires;
	
	/**
	 * Constructeur avec l'ID de l'auteur
	 * 
	 * @param aIsbn l'ISBN du livre
	 * @param aTitre le titre du livre
	 * @param aIdAuteur l'ID de l'auteur
	 * @param aNbPages le nombre de pages du livre
	 * @param aGenre le genre du livre
	 * @param aNbExemplaires le nombre d'exemplaires du livre
	 */
	public Livre (Long aIsbn, String aTitre, Integer aIdAuteur, Integer aNbPages, String aGenre, Integer aNbExemplaires) {
		isbn = aIsbn;
		titre = aTitre;
		idAuteur = aIdAuteur;
		nbPages = aNbPages;
		genre = aGenre;
		nbExemplaires = aNbExemplaires;
	}
	
	/**
	 * Constructeur avec nom et prenom de l'auteur
	 * 
	 * @param aIsbn l'ISBN du livre
	 * @param aTitre le titre du livre
	 * @param aPrenomAuteur le prenom de l'auteur
	 * @param aNomAuteur le nom de l'auteur
	 * @param aNbPages le nombre de pages du livre
	 * @param aGenre le genre du livre
	 * @param aNbExemplaires le nombre d'exemplaires du livre
	 */
	public Livre (Long aIsbn, String aTitre, String aPrenomAuteur, String aNomAuteur, Integer aNbPages, String aGenre, Integer aNbExemplaires) {
		isbn = aIsbn;
		titre = aTitre;
		prenomAuteur = aPrenomAuteur;
		nomAuteur = aNomAuteur;
		nbPages = aNbPages;
		genre = aGenre;
		nbExemplaires = aNbExemplaires;
	}
	
	/**
	 * Constructeur sans l'auteur ni le genre
	 * @param aIsbn l'ISBN du livre
	 * @param aTitre le titre du livre
	 * @param aNbPages le nombre de pages du livre
	 * @param aNbExemplaires le nombre d'exemplaires du livre
	 */
	public Livre (Long aIsbn, String aTitre, Integer aNbPages, Integer aNbExemplaires) {
		isbn = aIsbn;
		titre = aTitre;
		nbPages = aNbPages;
		nbExemplaires = aNbExemplaires;
	}
	
	/**
	 * Constructeur avec seulement l'ISBN
	 * @param aIsbn l'ISBN du livre
	 */
	public Livre (Long aIsbn) {
		isbn = aIsbn;
	}
	
	/**
	 * Fonction pour afficher les infos des prêts dans le menu principal
	 * 
	 * @return string contenant les infos
	*/
	public String toString() {
		if (prenomAuteur != null && nomAuteur != null && genre != null)
			return "Livre " + isbn + " : \"" + titre + "\", écrit par  " + prenomAuteur + " " + nomAuteur + " (genre : " + genre + "), " + nbPages + " pages, " + nbExemplaires + " exemplaire(s)";
		else
			return "Livre " + isbn + " : \"" + titre + "\", écrit par un(e) auteur(e) inconnue() (genre : inconnu), " + nbPages + " pages, " + nbExemplaires + " exemplaire(s)";
	}
	
	/**
	 * getter de l'ISBN
	 * 
	 * @return l'ISBN
	 */
	public Long getIsbn() {
		return isbn;
	}
	
	/**
	 * getter du titre
	 * 
	 * @return le titre
	 */
	public String getTitre() {
		return titre;
	}
	
	/**
	 * getter de l'ID de l'auteur
	 * 
	 * @return l'ID de l'auteur
	 */
	public Integer getIdAuteur() {
		return idAuteur;
	}

	/**
	 * getter du prenom de l'auteur
	 * 
	 * @return le prenom de l'auteur
	 */
	public String getPrenomAuteur() {
		return prenomAuteur;
	}
	
	/**
	 * getter du nom de l'auteur
	 * 
	 * @return le nom de l'auteur
	 */
	public String getNomAuteur() {
		return nomAuteur;
	}
	
	/**
	 * getter du nombre de pages
	 * 
	 * @return le nombre de pages
	 */
	public Integer getNbPages() {
		return nbPages;
	}
	
	/**
	 * getter du genre
	 * 
	 * @return le genre
	 */
	public String getGenre() {
		return genre;
	}
	
	/**
	 * getter du nombre d'exemplaires
	 * 
	 * @return le nombre d'exemplaires
	 */
	public Integer getNbExemplaires() {
		return nbExemplaires;
	}
	
	/**
	 * setter de l'ISBN
	 * 
	 * @param aIsbn
	 */
	public void setIsbn(Long aIsbn) {
		isbn = aIsbn;
	}
	
	/**
	 * setter du titre
	 * 
	 * @param aTitre
	 */
	public void setTitre(String aTitre) {
		titre = aTitre;
	}
	
	/**
	 * setter du nom de l'auteur
	 * 
	 * @param aNomAuteur
	 */
	public void setNomAuteur(String aNomAuteur) {
		nomAuteur = aNomAuteur;
	}

	/**
	 * setter du prenom de l'auteur
	 * 
	 * @param aPrenomAuteur
	 */
	public void setPrenomAuteur(String aPrenomAuteur) {
		nomAuteur = aPrenomAuteur;
	}

	/** 
	 * setter de l'ID de l'auteur
	 * 
	 * @param aIdAuteur
	 */
	public void setIdAuteur(Integer aIdAuteur) {
		idAuteur = aIdAuteur;
	}
	
	/**
	 * setter du nombre de pages
	 * 
	 * @param aNbPages
	 */
	public void setNbPages(Integer aNbPages) {
		nbPages = aNbPages;
	}
	
	/**
	 * setter du genre
	 * 
	 * @param aGenre
	 */
	public void setGenre(String aGenre) {
		genre = aGenre;
	}
	
	/** 
	 * setter du nombre d'exemplaires
	 * 
	 * @param aNbExemplaires
	 */
	public void setNbExemplaires(Integer aNbExemplaires) {
		nbExemplaires = aNbExemplaires;
	}
	
	/**
	 * fonction pour afficher les infos du livre
	 */
	public void display() {
		System.out.println("L'ISBN est : " + isbn);
		System.out.println("Le titre est : " +titre);
		System.out.println("L'auteur est : " + idAuteur);
		System.out.println("Le nombre de pages est : " + nbPages);
		System.out.println("Le genre est : " + genre);
		System.out.println("Le nombre d'exemplaires est : " + nbExemplaires);
	}
}