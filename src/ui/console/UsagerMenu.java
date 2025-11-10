package ui.console;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.InputMismatchException;

import javax.swing.JOptionPane;

import dao.UsagerDAO;
import model.Usager;

/**
 * Classe representant un menu "Usager" tres basique, sans controles de saisie.
 * 
 * @author anais peigney
 * @version 1.0
 * */
public class UsagerMenu {
	
	/**
	 * Le scanner utilise pour lire sur la console
	 */
	private Scanner sc = new Scanner(System.in);
	
	/**
	 * La classe DAO utilisee pour interagir avec la BDD
	 */
	private UsagerDAO dao = new UsagerDAO();
	
	/**
	 * Lance le menu usager.
	 */
	public void launch() {
		int choix;
		do {
			System.out.println("Vous êtes bien dans le menu des usagers. Que souhaitez-vous faire ?\n"
					+ "1 - Ajouter un usager\n"
					+ "2 - Modifier un usager\n"
					+ "3 - Supprimer un usager\n"
					+ "4 - Lister tous les usagers\n"
					+ "0 - Quitter\n");
			try {
				choix = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException ex) {
	            System.out.println("Erreur : le choix doit être un nombre.");
	            choix = -1;
	        }
			
			switch (choix) {
				case 1 :
					System.out.println("Entrez les informations de l'usager à ajouter\n");
					Usager usagerToAdd = readUsagerFromConsole();
					if (usagerToAdd != null) {
						if (dao.add(usagerToAdd) == 0) {
							System.out.println("Un usager avec cet ID existe déjà, usager non ajouté.");
						}
					}
					break;
				case 2:
					System.out.println("Entrez les nouvelles informations de l'usager à modifier\n");
					Usager usagerToUpdate = readUsagerFromConsole();
					dao.update(usagerToUpdate);
					break;
				case 3:
					System.out.println("Entrez l'ID de l'usager à supprimer\n");
					int idToDelete = sc.nextInt();
					sc.nextLine();
					dao.delete(idToDelete);
					break;
				case 4:
					ArrayList<Usager> allUsagers = dao.getList();
					for (Usager u : allUsagers) {
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
	 * Permet de lire les differentes informations d'un objet usager via la console.
	 * 
	 * @return un objet usager rempli
	 */
	private Usager readUsagerFromConsole() {
		try {
			System.out.println("Entrez l'ID\n");
			int id_usager = sc.nextInt();
			// NB : Cette ligne permet de supprimer le "\n" qui reste dans le buffer apres la lecture du int
			sc.nextLine();
			System.out.println("Entrez le nom\n");
			String nom = sc.nextLine();
			System.out.println("Entrez le prénom\n");
			String prenom = sc.nextLine();
			System.out.println("Entrez l'année de naissance\n");
			Integer annee_naissance = sc.nextInt();
			System.out.println("Entrez le tarif réduit\n");
			Integer tarif_reduit = sc.nextInt();
			Usager usager = new Usager(id_usager, nom, prenom, annee_naissance, tarif_reduit);
			return usager;
		} catch (InputMismatchException ex) {
            System.out.println("Erreur : la date de naissance doit être un nombre.");
            return null;
        }
	}
}