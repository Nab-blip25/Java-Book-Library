package dao;
import java.sql.*;
import java.util.ArrayList;
import model.*;

/**
 * Classe d'acces aux donnees contenues dans la table livre
 * 
 * @author anais peigney
 * @version 1.0
 * */
public class LivreDAO extends ConnexionDAO {
	/**
	 * Constructor
	 * 
	 */
	public LivreDAO() {
		super();
	}

	/**
	 * Permet d'ajouter un livre dans la table livre.
	 * Le mode est auto-commit par defaut : chaque insertion est validee
	 * 
	 * @param livre le livre a ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(Livre livre) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, chaque ? represente une valeur
			// a communiquer dans l'insertion.
			// les getters permettent de recuperer les valeurs des attributs souhaites
			ps = con.prepareStatement("INSERT INTO livre(isbn, titre, nb_pages, nb_exemplaires) VALUES(?, ?, ?, ?)");
			ps.setLong(1, livre.getIsbn());
			ps.setString(2, livre.getTitre());
			ps.setInt(3, livre.getNbPages());
			ps.setInt(4, livre.getNbExemplaires());

			// Execution de la requete
			returnValue = ps.executeUpdate();

			// Ajout du livre dans le fichier log
			Log.log("Livre ajouté : " + livre.getIsbn() + ", " + livre.getTitre() + ", " + livre.getNbPages() + ", " + livre.getNbExemplaires());

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant de livre existe deja. Ajout impossible !");
			else
				e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}

	/**
	 * Permet de modifier un livre dans la table livre.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param livre le livre a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int update(Livre livre) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, chaque ? represente une valeur
			// a communiquer dans la modification.
			// les getters permettent de recuperer les valeurs des attributs souhaites
			ps = con.prepareStatement("UPDATE livre set titre = ?, nb_pages = ?, nb_exemplaires = ? WHERE isbn = ?");
			ps.setString(1, livre.getTitre());
			ps.setInt(2, livre.getNbPages());
			ps.setInt(3, livre.getNbExemplaires());
			ps.setLong(4, livre.getIsbn());

			// Execution de la requete
			returnValue = ps.executeUpdate();

			// Modification du livre dans le fichier log
			Log.log("Livre modifié : " + livre.getIsbn() + ", " + livre.getTitre() + ", " + livre.getNbPages() + ", " + livre.getNbExemplaires());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}

	/**
	 * Permet de supprimer un livre par isbn dans la table livre.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param isbn l'id du livre a supprimer
	 * @return retourne le nombre de lignes supprimees dans la table
	 */
	public int delete(long id) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, le ? represente la valeur de l'ISBN
			// a communiquer dans la suppression.
			// le getter permet de recuperer la valeur de l'ISBN du livre
			ps = con.prepareStatement("DELETE FROM livre WHERE isbn = ?");
			ps.setLong(1, id);

			// Execution de la requete
			returnValue = ps.executeUpdate();

			// Suppression du livre dans le fichier log
			Log.log("Livre supprimé : " + id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// fermeture du preparedStatement et de la connexion
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}


	/**
	 * Permet de recuperer un livre a partir de son isbn
	 * 
	 * @param ids l'ISBN du livre a recuperer
	 * @return le livre trouve;
	 * 			null si aucun livre ne correspond a cet id
	 */
	public Livre get(long ids) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Livre returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM livre WHERE isbn = ?");
			ps.setLong(1, ids);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Livre(rs.getLong("isbn"),
									       rs.getString("titre"),
									       rs.getInt("nb_pages"),
									       rs.getInt("nb_exemplaires"));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du ResultSet, du PreparedStatement et de la Connexion
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (Exception ignore) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}

	/**
	 * Permet de recuperer tous les livres stockes dans la table livre
	 * 
	 * @return une ArrayList de livres
	 */
	public ArrayList<Livre> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Livre> returnValue = new ArrayList<Livre>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT livre.isbn, livre.titre, livre.nb_pages, livre.nb_exemplaires, auteur.prenom AS prenom_auteur, auteur.nom AS nom_auteur, genre.nom AS nom_genre FROM livre LEFT JOIN auteur ON livre.id_auteur = auteur.id_auteur LEFT JOIN genre ON livre.genre = genre.id_genre ORDER BY isbn");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Livre(rs.getLong("isbn"),
						                     rs.getString("titre"),
						                     rs.getString("prenom_auteur"),
						                     rs.getString("nom_auteur"),
						                     rs.getInt("nb_pages"),
						                     rs.getString("nom_genre"),
						                     rs.getInt("nb_exemplaires")));
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	

	/**
	 * Permet de recuperer le nombre de livres contenant un mot choisi par l'utilisateur
	 * 
	 * @param le mot choisi par l'utilisateur
	 * @return le compte de livres comprenant ce mot dans leur titre
	 */
	public Integer getNbLivresContenantMot(String mot) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer returnValue = null;

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT COUNT(titre) AS compte FROM livre WHERE LOWER(titre) LIKE ? OR LOWER(titre) LIKE ? OR LOWER(titre) LIKE ? OR LOWER(titre) = ?");
			
			String motMinuscule = mot.toLowerCase();
			ps.setString(1, "% " + motMinuscule + " %");
			ps.setString(2, motMinuscule + " %");
			ps.setString(3, "% " + motMinuscule);
			ps.setString(4, motMinuscule);

			
			// on execute la requete
			rs = ps.executeQuery();
			
			if (rs.next())
	            returnValue = rs.getInt("compte");
			
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	
	/**
	 * Permet de faire l'inventaire des livres actuellement en stock
	 * 
	 * 
	 * @return le compte des exemplaires de tous les livres actuellement non pretes
	 */
	public ArrayList<String> getInventaire() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> returnValue = new ArrayList<String>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT LIVRE.ISBN, TITRE, (NB_EXEMPLAIRES - COUNT(ID_PRET)) AS \"NB_EXEMPLAIRES_ACTUEL\" FROM LIVRE LEFT JOIN PRET ON LIVRE.ISBN = PRET.ISBN AND DATE_RETOUR_EFFECTIVE IS NULL AND ID_PRET IS NOT NULL GROUP BY LIVRE.ISBN, TITRE, NB_EXEMPLAIRES ORDER BY LIVRE.ISBN");
						
			// on execute la requete
			rs = ps.executeQuery();
			
			while (rs.next()) {
				long isbn = rs.getLong("isbn");
				String titre = rs.getString("titre");
				int nb_exemplaires_actuel = rs.getInt("nb_exemplaires_actuel");
				returnValue.add("\"" + titre + "\" (ISBN : " + isbn + ") : " + nb_exemplaires_actuel + " exemplaire(s)");
			}
			
		} catch (Exception ee) {
			ee.printStackTrace();
		} finally {
			// fermeture du rs, du preparedStatement et de la connexion
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ignore) {
			}
			try {
				if (ps != null)
					ps.close();
			} catch (Exception ignore) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ignore) {
			}
		}
		return returnValue;
	}
	
	/**
	 * ATTENTION : Cette methode n'a pas vocation a etre executee lors d'une utilisation normale du programme !
	 * Elle existe uniquement pour TESTER les methodes ecrites au-dessus !
	 * 
	 * @param args non utilises
	 * @throws SQLException si une erreur se produit lors de la communication avec la BDD
	 */
	public static void main(String[] args) throws SQLException {
		int returnValue;
		LivreDAO livreDAO = new LivreDAO();
		// Ce test va utiliser directement votre BDD, on essaie d'eviter les collisions avec vos donnees en prenant de grands ID
		long[] ids = {424242, 424243, 424244};
		// test du constructeur
		Livre l1 = new Livre(ids[0], "Le Rouge et le Noir", 3600, 1);
		Livre l2 = new Livre(ids[1], "L'assomoir", 12000, 5);
		Livre l3 = new Livre(ids[2], "Harry Pot de Beurre", 666, 666);
		// test de la methode add
		returnValue = livreDAO.add(l1);
		System.out.println(returnValue + " livre ajoute");
		returnValue = livreDAO.add(l2);
		System.out.println(returnValue + " livre ajoute");
		returnValue = livreDAO.add(l3);
		System.out.println(returnValue + " livre ajoute");
		System.out.println();
		
		// test de la methode get
		Livre lg = livreDAO.get(ids[0]);
		// appel implicite de la methode toString
		System.out.println(lg);
		System.out.println();
		
		// test de la methode getList
		ArrayList<Livre> list = livreDAO.getList();
		for (Livre l : list) {
			// appel explicite de la methode toString
			System.out.println(l.toString());
		}
		System.out.println();
		// test de la methode delete
		// On supprime les 3 livres qu'on a cree
		returnValue = 0;
		for (long id : ids) {
			returnValue = livreDAO.delete(id);
			System.out.println(returnValue + " livre supprime");
		}
		
		System.out.println();
	}
}