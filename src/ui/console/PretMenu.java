package ui.console;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import dao.PretDAO;
import model.Pret;

/**
 * Classe representant un menu "Pret" tres basique, sans controles de saisie.
 * 
 * @author anais peigney
 * @version 1.0
 * */
public class PretMenu {
	
	/**
	 * Le scanner utilise pour lire sur la console
	 */
	private Scanner sc = new Scanner(System.in);
	
	/**
	 * La classe DAO utilisee pour interagir avec la BDD
	 */
	private PretDAO dao = new PretDAO();
	
	/**
	 * Lance le menu usager.
	 */
	public void launch() {
		int choix;
		do {
			System.out.println("Vous êtes bien dans le menu des prêts. Que souhaitez-vous faire ?\n"
					+ "1 - Ajouter un prêt\n"
					+ "2 - Modifier un prêt\n"
					+ "3 - Supprimer un prêt\n"
					+ "4 - Lister tous les prêts\n"
					+ "0 - Quitter\n");
			choix = sc.nextInt();
			sc.nextLine();
			switch (choix) {
				case 1 :
					System.out.println("Entrez les informations du prêt à ajouter\n");
					Pret pretToAdd = readPretFromConsole();
					dao.add(pretToAdd);
					break;
				case 2:
					System.out.println("Entrez les nouvelles informations du prêt à modifier\n");
					Pret pretToUpdate = readPretFromConsole();
					dao.update(pretToUpdate);
					break;
				case 3:
					System.out.println("Entrez l'ID du prêt à supprimer\n");
					int idToDelete = sc.nextInt();
					sc.nextLine();
					dao.delete(idToDelete);
					break;
				case 4:
					ArrayList<Pret> allPrets = dao.getList();
					for (Pret p : allPrets) {
						// Appel implicite de la methode .toString()
						System.out.println(p);
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
	 * Permet de lire les differentes informations d'un objet prêt via la console.
	 * 
	 * @return un objet prêt rempli
	 */
	private Pret readPretFromConsole() {
		System.out.println("Entrez l'ID du prêt\n");
		int id_pret = sc.nextInt();
		// NB : Cette ligne permet de supprimer le "\n" qui reste dans le buffer apres la lecture du int
		sc.nextLine();
		
		// Date d'emprunt
		DateTimeFormatter formatterDateEmprunt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Entrer la date d'emprunt (format jj/mm/aaaa) : ");
		String inputDateEmprunt = sc.nextLine();
		LocalDate date_emprunt = LocalDate.parse(inputDateEmprunt, formatterDateEmprunt);
		
		// Durée
		System.out.println("Entrez la durée\n");
		Integer duree = sc.nextInt();
		sc.nextLine();
		
		// Date de retour effective
		DateTimeFormatter formatterDateRetourEffective = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Entrer la date de retour effective (format jj/mm/aaaa) : ");
		String inputDateRetourEffective = sc.nextLine();
		LocalDate date_retour_effective = LocalDate.parse(inputDateRetourEffective, formatterDateRetourEffective);
		
		// ISBN
		System.out.println("Entrez l'ISBN\n");
		Long isbn = sc.nextLong();
		
		// Id usager
		System.out.println("Entrez l'ID de l'usager\n");
		Integer id_usager = sc.nextInt();
		
		// Création de l'objet Prêt
		Pret pretToAdd = new Pret(id_pret, date_emprunt, duree, date_retour_effective, isbn, id_usager);
		return pretToAdd;
	}

}
