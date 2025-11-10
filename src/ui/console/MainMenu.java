package ui.console;

import java.util.Scanner;
import model.Log;

/**
 * Classe principale de l'application (c'est celle-ci qu'il faut lancer), qui contient le menu principal
 * 
 * @author anais peigney
 * @version 1.0
 */
public class MainMenu {

	/**
	 * Permet de lancer le programme.
	 * 
	 * @param args non utilise
	 */
	public static void main(String[] args) {
		
		// On créé le fichier log
		Log.init();
		Log.log("Lancement de l'application");

		int choix;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Bonjour et bienvenue dans l'application de gestion des prêts\n"
					+ "Que souhaitez-vous faire ?\n"
					+ "1 - Gérer les usagers\n"
					+ "2 - Gérer les livres\n"
					+ "3 - Gérer les prêts\n"
					+ "4 - Générer un fichier de résultats\n"
					+ "0 - Quitter\n");
			choix = sc.nextInt();
			// NB : Cette ligne permet de supprimer le "\n" qui reste dans le buffer apres la lecture du int
			sc.nextLine();
			switch (choix) {
				case 1 :
					// On lance le sous-menu usager
					UsagerMenu usagerMenu = new UsagerMenu();
					usagerMenu.launch();
					break;
				case 2 :
					// On lance le sous-menu livre
					LivreMenu livreMenu = new LivreMenu();
					livreMenu.launch();
					break;
				case 3:
					// On lance le sous-menu pret
					PretMenu pretMenu = new PretMenu();
					pretMenu.launch();
					break;
				case 4 :
					// On lance le sous-menu fichier
					FichierMenu fichierMenu = new FichierMenu();
					fichierMenu.launch();
					break;
				case 0:
					System.out.println("Fermeture de l'application");
					Log.log("Fermeture de l'application");
					Log.close();
					break;
			}
		} while (choix != 0);
		sc.close();
	}

}
