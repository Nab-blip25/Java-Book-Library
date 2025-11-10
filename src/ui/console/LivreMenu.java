package ui.console;

import java.util.ArrayList;
import java.util.Scanner;

import dao.LivreDAO;
import model.Livre;

/**
 * Classe representant un menu "Livre" tres basique, sans controles de saisie.
 * 
 * @author anais peigney
 * @version 1.0
 * */
public class LivreMenu {
	
	/**
	 * Le scanner utilise pour lire sur la console
	 */
	private Scanner sc = new Scanner(System.in);
	
	/**
	 * La classe DAO utilisee pour interagir avec la BDD
	 */
	private LivreDAO dao = new LivreDAO();
	
	/**
	 * Lance le menu livre.
	 */
	public void launch() {
		int choix;
		do {
			System.out.println("Vous êtes bien dans le menu des livres. Que souhaitez-vous faire ?\n"
					+ "1 - Ajouter un livre\n"
					+ "2 - Modifier un livre\n"
					+ "3 - Supprimer un livre\n"
					+ "4 - Lister tous les livres\n"
					+ "0 - Quitter\n");
			choix = sc.nextInt();
			sc.nextLine();
			switch (choix) {
				case 1 :
					System.out.println("Entrez les informations du livre à ajouter\n");
					Livre livreToAdd = readLivreFromConsole();
					dao.add(livreToAdd);
					break;
				case 2:
					System.out.println("Entrez les nouvelles informations du livre à modifier\n");
					Livre livreToUpdate = readLivreFromConsole();
					dao.update(livreToUpdate);
					break;
				case 3:
					System.out.println("Entrez l'ISBN du livre à supprimer\n");
					int idToDelete = sc.nextInt();
					sc.nextLine();
					dao.delete(idToDelete);
					break;
				case 4:
					ArrayList<Livre> allLivres = dao.getList();
					for (Livre u : allLivres) {
						// Appel implicite de la methode .toString()
						System.out.println(u);
					}
					break;
				case 0:
					break;
				default: 
					System.out.println("Choix non valide\n");
					break;
			}
		} while (choix != 0);
	}

	/**
	 * Permet de lire les differentes informations d'un objet livre via la console.
	 * 
	 * @return un objet livre rempli
	 */
	private Livre readLivreFromConsole() {
		System.out.println("Entrez l'ISBN\n");
		long isbn = sc.nextLong();
		// NB : Cette ligne permet de supprimer le "\n" qui reste dans le buffer apres la lecture du int
		sc.nextLine();
		System.out.println("Entrez le titre\n");
		String titre = sc.nextLine();
		System.out.println("Entrez le nombre de pages\n");
		Integer nb_pages = sc.nextInt();
		System.out.println("Entrez le nombre d'exemplaires\n");
		Integer nb_exemplaires = sc.nextInt();
		Livre livreToAdd = new Livre(isbn, titre, nb_pages, nb_exemplaires);
		return livreToAdd;
	}

}
