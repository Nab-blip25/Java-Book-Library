package dao;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import model.*;

/**
 * Classe d'acces aux donnees contenues dans la table pret
 * 
 * @author anais peigney
 * @version 1.0
 * */
public class PretDAO extends ConnexionDAO {
	/**
	 * Constructor
	 * 
	 */
	public PretDAO() {
		super();
	}

	/**
	 * Permet d'ajouter un pret dans la table pret.
	 * Le mode est auto-commit par defaut : chaque insertion est validee
	 * 
	 * @param pret le pret a ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(Pret pret) {
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
			ps = con.prepareStatement("INSERT INTO pret(id_pret, date_emprunt, duree, date_retour_effective, isbn, id_usager) VALUES(?, ?, ?, ?, ?, ?)");
			ps.setInt(1, pret.getIdPret());
			ps.setObject(2, pret.getDateEmprunt());
			ps.setInt(3, pret.getDureeEmprunt());
			ps.setObject(4, pret.getDateRetourEffective());
			ps.setLong(5, pret.getIsbn());
			ps.setInt(6, pret.getIdUsager());

			// Execution de la requete
			returnValue = ps.executeUpdate();
			
			// Ajout du prêt dans le fichier log
			Log.log("Prêt ajouté : " + pret.getIdPret() + ", " + pret.getDateEmprunt() + ", " + pret.getDureeEmprunt() + ", " + pret.getDateRetourEffective() + ", " + pret.getIsbn() + ", " + pret.getIdUsager());


		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				System.out.println("Cet identifiant de pret existe deja. Ajout impossible !");
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
	 * Permet de modifier un pret dans la table pret.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param pret le pret a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int update(Pret pret) {
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
			ps = con.prepareStatement("UPDATE pret set date_emprunt = ?, duree = ?, date_retour_effective = ?, isbn = ?, id_usager = ? WHERE id_pret = ?");
			ps.setObject(1, pret.getDateEmprunt());
			ps.setInt(2, pret.getDureeEmprunt());
			ps.setObject(3, pret.getDateRetourEffective());
			ps.setLong(4, pret.getIsbn());
			ps.setInt(5, pret.getIdUsager());
			ps.setInt(6, pret.getIdPret());

			// Execution de la requete
			returnValue = ps.executeUpdate();
			
			// Modification du prêt dans le fichier log
			Log.log("Prêt modifié : " + pret.getIdPret() + ", " + pret.getDateEmprunt() + ", " + pret.getDureeEmprunt() + ", " + pret.getDateRetourEffective() + ", " + pret.getIsbn() + ", " + pret.getIdUsager());


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
	 * Permet de supprimer un pret par id dans la table pret.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param id_pret l'id du pret a supprimer
	 * @return retourne le nombre de lignes supprimees dans la table
	 */
	public int delete(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		int returnValue = 0;

		// connexion a la base de donnees
		try {

			// tentative de connexion
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			// preparation de l'instruction SQL, le ? represente la valeur de l'id_usager
			// a communiquer dans la suppression.
			// le getter permet de recuperer la valeur de l'ID du pret
			ps = con.prepareStatement("DELETE FROM pret WHERE id_pret = ?");
			ps.setInt(1, id);

			// Execution de la requete
			returnValue = ps.executeUpdate();

			// Suppression du prêt dans le fichier log
			Log.log("Prêt supprimé : " + id);

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
	 * Permet de recuperer un pret a partir de son id_pret
	 * 
	 * @param id_pret l'id du pret a recuperer
	 * @return le pret trouve;
	 * 			null si aucun pret ne correspond a cet id
	 */
	public Pret get(int id_pret) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Pret returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM pret WHERE id_pret = ?");
			ps.setInt(1, id_pret);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Pret(rs.getInt("id_pret"),
									       rs.getObject("date_emprunt", LocalDate.class),
									       rs.getInt("duree"),
									       rs.getObject("date_retour_effective", LocalDate.class),
									       rs.getLong("isbn"),
									       rs.getInt("id_usager"));
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
	 * Permet de recuperer tous les prets stockes dans la table pret
	 * 
	 * @return une ArrayList de prets
	 */
	public ArrayList<Pret> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Pret> returnValue = new ArrayList<Pret>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM pret NATURAL JOIN usager ORDER BY id_pret");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Pret(rs.getInt("id_pret"),
						                     rs.getObject("date_emprunt", LocalDate.class),
						                     rs.getInt("duree"),
						                     rs.getObject("date_retour_effective", LocalDate.class),
						                     rs.getLong("isbn"),
						                     rs.getString("prenom"),
						                     rs.getString("nom")));
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
	 * Permet de recuperer tous les prets d'un usager
	 * 
	 * @return une ArrayList de prets
	 */
	public ArrayList<String> getPretsUsager(int id_usager) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> returnValue = new ArrayList<String>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM pret NATURAL JOIN livre LEFT JOIN auteur ON livre.id_auteur = auteur.id_auteur WHERE id_usager = ? ORDER BY id_pret");
			ps.setInt(1, id_usager);
			
			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				int id_pret = rs.getInt("id_pret");
				LocalDate date_emprunt = rs.getObject("date_emprunt", LocalDate.class);
				LocalDate date_retour_effective = rs.getObject("date_retour_effective", LocalDate.class);
				String titre = rs.getString("titre");
				String prenom_auteur = rs.getString("prenom");
				String nom_auteur = rs.getString("nom");
				if (date_retour_effective != null && prenom_auteur != null && nom_auteur != null) // Si on a la date de retour effective et le prenom et le nom de l'auteur
					returnValue.add("Prêt n°" + id_pret + " : " + "\"" + titre + "\" écrit par " + prenom_auteur + " " + nom_auteur + ". Emprunté le " + date_emprunt + ", rendu le " + date_retour_effective + ".");
				else if (date_retour_effective == null && prenom_auteur != null && nom_auteur != null) // Si on n'a pas la date de retour effective mais qu'on a le prenom et le nom de l'auteur
					returnValue.add("Prêt n°" + id_pret + " : " + "\"" + titre + "\" écrit par " + prenom_auteur + " " + nom_auteur + ". Emprunté le " + date_emprunt + ", pas encore rendu.");
				else if (date_retour_effective == null && (prenom_auteur == null || nom_auteur == null)) // Si on n'a pas la date de retour effective ni le prénom ou le nom de l'auteur
					returnValue.add("Prêt n°" + id_pret + " : " + "\"" + titre + "\" écrit par un(e) auteur(e) inconnu(e). Emprunté le " + date_emprunt + ", pas encore rendu.");
				else if (date_retour_effective != null && (prenom_auteur == null || nom_auteur == null)) // Si on a la date de retour effective mais pas le prénom ou le nom de l'auteur
						returnValue.add("Prêt n°" + id_pret + " : " + "\"" + titre + "\" écrit par un(e) auteur(e) inconnu(e). Emprunté le " + date_emprunt + ", rendu le " + date_retour_effective + ".");
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
	 * Permet de recuperer la moyenne effective de tous les prets
	 * 
	 * @return la moyenne effective de tous les prets
	 */
	public Integer getMoyenneEffectivePret() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT ROUND(AVG(date_retour_effective - date_emprunt)) AS moyenne_effective_pret FROM pret");

			// on execute la requete
			rs = ps.executeQuery();

			if (rs.next())
			    returnValue = rs.getInt("moyenne_effective_pret");
			
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
	 * ATTENTION : Cette methode n'a pas vocation a etre executee lors d'une utilisation normale du programme !
	 * Elle existe uniquement pour TESTER les methodes ecrites au-dessus !
	 * 
	 * @param args non utilises
	 * @throws SQLException si une erreur se produit lors de la communication avec la BDD
	 */
	public static void main(String[] args) throws SQLException {
		int returnValue;
		PretDAO PretDAO = new PretDAO();
		// Ce test va utiliser directement votre BDD, on essaie d'eviter les collisions avec vos donnees en prenant de grands ID
		int[] ids = {424242, 424243, 424244};
		// test du constructeur
		Pret p1 = new Pret(ids[0], LocalDate.of(2022, 1, 20), 5, LocalDate.of(2022, 1, 25), 1000000000007L, 10);
		Pret p2 = new Pret(ids[1], LocalDate.of(2024, 7, 5), 10, LocalDate.of(2024, 1, 15), 1000000000008L, 10);
		Pret p3 = new Pret(ids[2], LocalDate.of(2025, 12, 18), 1, LocalDate.of(2024, 12, 19), 1000000000009L, 10);
		// test de la methode add
		returnValue = PretDAO.add(p1);
		System.out.println(returnValue + " pret ajoute");
		returnValue = PretDAO.add(p2);
		System.out.println(returnValue + " pret ajoute");
		returnValue = PretDAO.add(p3);
		System.out.println(returnValue + " pret ajoute");
		System.out.println();
		
		// test de la methode get
		Pret pg = PretDAO.get(ids[0]);
		// appel implicite de la methode toString
		System.out.println(pg);
		System.out.println();
		
		// test de la methode getList
		ArrayList<Pret> list = PretDAO.getList();
		for (Pret p : list) {
			// appel explicite de la methode toString
			System.out.println(p.toString());
		}
		System.out.println();
		// test de la methode delete
		// On supprime les 3 prets qu'on a cree
		returnValue = 0;
		for (int id : ids) {
			returnValue = PretDAO.delete(id);
			System.out.println(returnValue + " pret supprime");
		}
		
		System.out.println();
	}
}