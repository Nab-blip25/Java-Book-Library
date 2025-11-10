package ui.console;

import java.util.ArrayList;
import java.util.Scanner;

import dao.LivreDAO;
import dao.PretDAO;
import dao.UsagerDAO;
import model.Fichier;

/**
 * Classe representant un menu "Fichier" tres basique, sans controles de saisie.
 * 
 * @author anais peigney
 * @version 1.0
 * */
public class FichierMenu {
	
	/**
	 * Le scanner utilise pour lire sur la console
	 */
	private Scanner sc = new Scanner(System.in);
	
	/**
	 * Les classes DAO utilisees pour interagir avec la BDD
	 */
	private LivreDAO livreDAO = new LivreDAO();
	private PretDAO pretDAO = new PretDAO();
	private UsagerDAO usagerDAO = new UsagerDAO();
	
	/**
	 * Lance le menu fichier.
	 */
	public void launch() {
		int choix;
		do {
			System.out.println("Vous êtes bien dans le menu du fichier de résultats.\n"
					+ "Ce fichier comportera :\n"
					+ "- Le nombre d'utilisateurs par année de naissance\n"
					+ "- La durée effective moyenne d'un prêt\n"
					+ "- Le nombre de livres contenant un mot que vous pouvez choisir\n"
					+ "1 - Créer le fichier de résultats\n"
					+ "0 - Quitter\n");
			choix = sc.nextInt();
			sc.nextLine();
			switch (choix) {
				case 1 :
					// Demande du mot a l'utilisateur
					System.out.println("Veuillez rentrez le mot recherché : ");
					String mot = sc.nextLine();
					
					// Ouverture du fichier de résultats
					Fichier.init();
					
					// Log du nombre d'usagers par annee de naissance
				    ArrayList<String> stats = usagerDAO.getNbUsersByBirthYear();
				    Fichier.log("Nombre d'usagers par année de naissance : ");
				    for (int i = 0; i < stats.size(); i++) {
				        String ligne = stats.get(i);
				        Fichier.log(ligne);
				    }
				    Fichier.log("");
				    
				    // Log de la moyenne effective des prets (en jours)
				    int moyenneEffectivePret = pretDAO.getMoyenneEffectivePret();
				    Fichier.log("Moyenne effective des prêts : " + moyenneEffectivePret + " jours");
				    Fichier.log("");
				    
				    // Log du nombre de livres contenant le mot choisi par l'utilisateur
					int nbLivresContenantMot = livreDAO.getNbLivresContenantMot(mot);
				    Fichier.log("Nombre de livres contenant le mot \"" + mot + "\" dans leur titre : " + nbLivresContenantMot);
					
				    // Fermeture du fichier de résultats
				    Fichier.close();
				    
				    // Print dans la console
				    System.out.println("Le fichier de résultats a bien été créé.");
				    System.out.println();
					break;
				case 0:
					break;
				default: 
					System.out.println("Choix non valide\n");
					break;
			}
		} while (choix != 0);
	}
}
