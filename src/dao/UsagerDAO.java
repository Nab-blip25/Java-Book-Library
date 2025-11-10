package dao;
import java.sql.*;
import java.util.ArrayList;
import model.*;

/**
 * Classe d'acces aux donnees contenues dans la table usager
 * 
 * @author anais peigney
 * @version 1.0
 * */
public class UsagerDAO extends ConnexionDAO {
	/**
	 * Constructor
	 * 
	 */
	public UsagerDAO() {
		super();
	}

	/**
	 * Permet d'ajouter un usager dans la table usager.
	 * Le mode est auto-commit par defaut : chaque insertion est validee
	 * 
	 * @param usager l'usager a ajouter
	 * @return retourne le nombre de lignes ajoutees dans la table
	 */
	public int add(Usager usager) {
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
			ps = con.prepareStatement("INSERT INTO usager(id_usager, nom, prenom, annee_naissance, tarif_reduit) VALUES(?, ?, ?, ?, ?)");
			ps.setInt(1, usager.getIdUsager());
			ps.setString(2, usager.getNom());
			ps.setString(3, usager.getPrenom());
			ps.setInt(4, usager.getAnneeNaissance());
			ps.setInt(5, usager.getTarifReduit());

			// Execution de la requete
			returnValue = ps.executeUpdate();

			// Ajout de l'utilisateur dans le fichier log
			Log.log("Utilisateur ajouté : " + usager.getIdUsager() + ", " + usager.getPrenom() + " " + usager.getNom() + ", " + usager.getAnneeNaissance() + ", " + usager.getTarifReduit());

		} catch (Exception e) {
			if (e.getMessage().contains("ORA-00001"))
				returnValue = -1; // Permet de reconnaitre l'erreur quand on utilise cette fonction
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
	 * Permet de modifier un usager dans la table usager.
	 * Le mode est auto-commit par defaut : chaque modification est validee
	 * 
	 * @param usager l'usager a modifier
	 * @return retourne le nombre de lignes modifiees dans la table
	 */
	public int update(Usager usager) {
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
			ps = con.prepareStatement("UPDATE usager set nom = ?, prenom = ?, annee_naissance = ?, tarif_reduit = ? WHERE id_usager = ?");
			ps.setString(1, usager.getNom());
			ps.setString(2, usager.getPrenom());
			ps.setInt(3, usager.getAnneeNaissance());
			ps.setInt(4, usager.getTarifReduit());
			ps.setInt(5, usager.getIdUsager());

			// Execution de la requete
			returnValue = ps.executeUpdate();

			// Modification de l'utilisateur dans le fichier log
			Log.log("Utilisateur modifié : " + usager.getIdUsager() + ", " + usager.getPrenom() + " " + usager.getNom() + ", " + usager.getAnneeNaissance() + ", " + usager.getTarifReduit());

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
	 * Permet de supprimer un usager par id dans la table usager.
	 * Le mode est auto-commit par defaut : chaque suppression est validee
	 * 
	 * @param id_usager l'id de l'usager a supprimer
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
			// le getter permet de recuperer la valeur de l'ID de l'usager
			
			// On supprime d'abord tous les prets de l'usager
			ps = con.prepareStatement("DELETE FROM pret WHERE id_usager = ?");
			ps.setInt(1, id);
			// Execution de la requete
			returnValue = ps.executeUpdate();
			
			// Puis on peut supprimer l'usager
			ps = con.prepareStatement("DELETE FROM usager WHERE id_usager = ?");
			ps.setInt(1, id);

			// Execution de la requete
			returnValue = ps.executeUpdate();

			// Suppression de l'utilisateur dans le fichier log
			Log.log("Utilisateur supprimé : " + id);

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
	 * Permet de recuperer un usager a partir de son id_usager
	 * 
	 * @param id_usager l'id de l'usager a recuperer
	 * @return l'usager trouve;
	 * 			null si aucun usager ne correspond a cet id
	 */
	public Usager get(int id_usager) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Usager returnValue = null;

		// connexion a la base de donnees
		try {

			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM usager WHERE id_usager = ?");
			ps.setInt(1, id_usager);

			// on execute la requete
			// rs contient un pointeur situe juste avant la premiere ligne retournee
			rs = ps.executeQuery();
			// passe a la premiere (et unique) ligne retournee
			if (rs.next()) {
				returnValue = new Usager(rs.getInt("id_usager"),
									       rs.getString("nom"),
									       rs.getString("prenom"),
									       rs.getInt("annee_naissance"),
									       rs.getInt("tarif_reduit"));
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
	 * Permet de recuperer tous les usagers stockes dans la table usager
	 * 
	 * @return une ArrayList d'usagers
	 */
	public ArrayList<Usager> getList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Usager> returnValue = new ArrayList<Usager>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT * FROM usager ORDER BY id_usager");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
				returnValue.add(new Usager(rs.getInt("id_usager"),
						                     rs.getString("nom"),
						                     rs.getString("prenom"),
						                     rs.getInt("annee_naissance"),
						                     rs.getInt("tarif_reduit")));
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
	 * Permet de recuperer le nombre d'utilisateurs par annee de naissance
	 * 
	 * @return une ArrayList de String
	 */
	public ArrayList<String> getNbUsersByBirthYear() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<String> returnValue = new ArrayList<String>();

		// connexion a la base de donnees
		try {
			con = DriverManager.getConnection(URL, LOGIN, PASS);
			ps = con.prepareStatement("SELECT annee_naissance, COUNT(*) AS compte FROM usager GROUP BY annee_naissance");

			// on execute la requete
			rs = ps.executeQuery();
			// on parcourt les lignes du resultat
			while (rs.next()) {
	            int annee = rs.getInt("annee_naissance");
	            int compte = rs.getInt("compte");
	            returnValue.add("Année " + annee + " : " + compte + " usager(s)");
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
		UsagerDAO usagerDAO = new UsagerDAO();
		// Ce test va utiliser directement votre BDD, on essaie d'eviter les collisions avec vos donnees en prenant de grands ID
		int[] ids = {424242, 424243, 424244};
		// test du constructeur
		Usager u1 = new Usager(ids[0], "Premier usager", "Rouen", 1958, 0);
		Usager u2 = new Usager(ids[1], "Deuxième usager", "Le Havre", 1975, 1);
		Usager u3 = new Usager(ids[2], "Troisième usager", "Paris", 1947, 0);
		// test de la methode add
		returnValue = usagerDAO.add(u1);
		System.out.println(returnValue + " usager ajoute");
		returnValue = usagerDAO.add(u2);
		System.out.println(returnValue + " usager ajoute");
		returnValue = usagerDAO.add(u3);
		System.out.println(returnValue + " usager ajoute");
		System.out.println();
		
		// test de la methode get
		Usager ug = usagerDAO.get(ids[0]);
		// appel implicite de la methode toString
		System.out.println(ug);
		System.out.println();
		
		// test de la methode getList
		ArrayList<Usager> list = usagerDAO.getList();
		for (Usager u : list) {
			// appel explicite de la methode toString
			System.out.println(u.toString());
		}
		System.out.println();
		// test de la methode delete
		// On supprime les 3 usagers qu'on a cree
		returnValue = 0;
		for (int id : ids) {
			returnValue = usagerDAO.delete(id);
			System.out.println(returnValue + " usager supprime");
		}
		
		System.out.println();
	}
}