package ui.gui;

import java.awt.Component;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dao.LivreDAO;
import dao.PretDAO;
import dao.UsagerDAO;
import model.Usager;

/**
 * Classe du l'interface principale
 * 
 * @author anais peigney
 * @version 1.0
 */
public class MainGUI extends JFrame {

	LivreDAO livreDAO = new LivreDAO();
    UsagerDAO usagerDAO = new UsagerDAO();
	PretDAO pretDAO = new PretDAO();

    // Panneaux
    JPanel mainPanel, btnPanel, panelAjouter, panelModifier, panelSupprimer, panelAfficher, panelSQL;
    
    // Message de bienvenue
    JLabel messageBienvenue1, messageBienvenue2;

    // Champs pour ajouter un usager
    JTextField ajouterUsagerId, ajouterUsagerPrenom, ajouterUsagerNom, ajouterUsagerAnneeNaissance, ajouterUsagerTarifReduit;
    JButton btnAjouterConfirm;

    // Champs pour modifier un usager
    JTextField modifierUsagerId, modifierUsagerPrenom, modifierUsagerNom, modifierUsagerAnneeNaissance, modifierUsagerTarifReduit;
    JButton btnModifierConfirm;
    
    // Champs pour supprimer un usager
    JTextField supprimerUsagerId;
    JButton btnSupprimerConfirm;

    // Champs pour afficher un usager
    JTextField afficherUsagerId;
	JLabel idUsagerLabel, prenomUsagerLabel, nomUsagerLabel, anneeNaissanceUsagerLabel, tarifReduitUsagerLabel;
    JButton btnAfficherConfirm;
    
    // Champs pour choisir la requete SQL
    JButton btnRequeteSQL;
    
    /**
     * Classe de l'interface graphique
     */
    public MainGUI() {
        this.setTitle("Projet Java");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centre la fenetre automatiquement

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Permet de definir un layout pour le positionnement des boutons

        // Panneau des boutons du menu principal
        btnPanel = new JPanel();
        JButton btnAjouter = new JButton("Ajouter un usager");
        JButton btnModifier = new JButton("Modifier un usager");
        JButton btnSupprimer = new JButton("Supprimer un usager");
        JButton btnAfficher = new JButton("Afficher un usager");
        JButton btnSQL = new JButton("Inventaire des stocks");

        btnPanel.add(btnAjouter);
        btnPanel.add(btnModifier);
        btnPanel.add(btnSupprimer);
        btnPanel.add(btnAfficher);
        btnPanel.add(btnSQL);

        // Panneau d'ajout d'usager
        panelAjouter = new JPanel();
        ajouterUsagerId = new JTextField(5); // Chiffre en paramètre permet de gérer la largeur de l'input
        ajouterUsagerPrenom = new JTextField(10);
        ajouterUsagerNom = new JTextField(10);
        ajouterUsagerAnneeNaissance = new JTextField(5);
        ajouterUsagerTarifReduit = new JTextField(2);
        btnAjouterConfirm = new JButton("Ajouter");
        
        panelAjouter.add(new JLabel("ID :"));
        panelAjouter.add(ajouterUsagerId);
        panelAjouter.add(new JLabel("Prénom :"));
        panelAjouter.add(ajouterUsagerPrenom);
        panelAjouter.add(new JLabel("Nom :"));
        panelAjouter.add(ajouterUsagerNom);
        panelAjouter.add(new JLabel("Année de naissance :"));
        panelAjouter.add(ajouterUsagerAnneeNaissance);
        panelAjouter.add(new JLabel("Tarif réduit (0/1) :"));
        panelAjouter.add(ajouterUsagerTarifReduit);
        panelAjouter.add(btnAjouterConfirm);
        
        // Panneau de modification d'usager
        panelModifier = new JPanel();
        modifierUsagerId = new JTextField(5);
        modifierUsagerPrenom = new JTextField(10);
        modifierUsagerNom = new JTextField(10);
        modifierUsagerAnneeNaissance = new JTextField(5);
        modifierUsagerTarifReduit = new JTextField(2);
        btnModifierConfirm = new JButton("Modifier");
        
        panelModifier.add(new JLabel("ID :"));
        panelModifier.add(modifierUsagerId);
        panelModifier.add(new JLabel("Prénom :"));
        panelModifier.add(modifierUsagerPrenom);
        panelModifier.add(new JLabel("Nom :"));
        panelModifier.add(modifierUsagerNom);
        panelModifier.add(new JLabel("Année de naissance :"));
        panelModifier.add(modifierUsagerAnneeNaissance);
        panelModifier.add(new JLabel("Tarif réduit (0/1) :"));
        panelModifier.add(modifierUsagerTarifReduit);
        panelModifier.add(btnModifierConfirm);
        
        // Panneau de supression d'usager
        panelSupprimer = new JPanel();
        supprimerUsagerId = new JTextField(5);
        btnSupprimerConfirm = new JButton("Supprimer");
        
        panelSupprimer.add(new JLabel("ID :"));
        panelSupprimer.add(supprimerUsagerId);
        panelSupprimer.add(btnSupprimerConfirm);
        
        // Panneau d'affichage d'un usager
        panelAfficher = new JPanel();
        afficherUsagerId = new JTextField(5);
        btnAfficherConfirm = new JButton("Afficher");
		idUsagerLabel = new JLabel("");
		prenomUsagerLabel = new JLabel("");
		nomUsagerLabel = new JLabel("");
		anneeNaissanceUsagerLabel = new JLabel("");
        tarifReduitUsagerLabel = new JLabel("");
        JTextArea pretsTextArea = new JTextArea(10, 40);
        pretsTextArea.setEditable(false); // On ne peut pas aller ecrire dans cet textarea
        JScrollPane scrollPane = new JScrollPane(pretsTextArea); // Permet d'avoir une scrollbar s'il y a trop d'elements

        panelAfficher.add(afficherUsagerId);
        panelAfficher.add(btnAfficherConfirm);
        panelAfficher.add(idUsagerLabel);
        panelAfficher.add(prenomUsagerLabel);
        panelAfficher.add(nomUsagerLabel);
        panelAfficher.add(anneeNaissanceUsagerLabel);
        panelAfficher.add(tarifReduitUsagerLabel);
        panelAfficher.add(scrollPane);
        
        // Panneau de la requete SQL
        panelSQL = new JPanel();
        btnRequeteSQL = new JButton("Faire l'inventaire des livres");
        JTextArea requeteSQLTextArea = new JTextArea(10, 40);
        requeteSQLTextArea.setEditable(false);
        JScrollPane requeteSQLScrollPane = new JScrollPane(requeteSQLTextArea);
        
        panelSQL.add(btnRequeteSQL);
        panelSQL.add(requeteSQLScrollPane);

        // Tous les panneaux sont cachés au lancement de l'UI
        panelAjouter.setVisible(false);
        panelModifier.setVisible(false);
        panelSupprimer.setVisible(false);
        panelAfficher.setVisible(false);
        panelSQL.setVisible(false);

        // Ajout des panneaux au mainPanel
        mainPanel.add(btnPanel);
        mainPanel.add(panelAjouter);
        mainPanel.add(panelModifier);
        mainPanel.add(panelSupprimer);
        mainPanel.add(panelAfficher);
        mainPanel.add(panelSQL);
        
        // Ajout d'un message de bienvenue lors du lancement de l'UI
        messageBienvenue1 = new JLabel("Bienvenue sur l'application de gestion de prêts !");
        messageBienvenue2 = new JLabel("Veuillez cliquer sur le bouton correspondant à l'action que vous souhaitez faire.");
        
        // Aligner au centre
        messageBienvenue1.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageBienvenue2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Ajout de padding autour du message
	    messageBienvenue1.setBorder(new EmptyBorder(10, 20, 10, 20)); // top, left, bottom, right
	    messageBienvenue2.setBorder(new EmptyBorder(5, 20, 15, 20));
	
	    // Ajouter au main panel
        mainPanel.add(messageBienvenue1);
        mainPanel.add(messageBienvenue2);
        
        this.getContentPane().add(mainPanel); // Ajout du panneau principal a la fenetre
        this.pack(); // Taille automatique de la fenetre
        this.setLocationRelativeTo(null); // Centrer la fenêtre
        this.setVisible(true);

        // Boutons permettant d'afficher le panneau correspondant
        btnAjouter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                afficherPanelUnique(panelAjouter);
            }
        });

        btnModifier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                afficherPanelUnique(panelModifier);
            }
        });

        btnSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                afficherPanelUnique(panelSupprimer);
            }
        });

        btnAfficher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                afficherPanelUnique(panelAfficher);
            }
        });

        btnSQL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                afficherPanelUnique(panelSQL);
            }
        });

        // Action du bouton d'ajout
        btnAjouterConfirm.addActionListener(e -> {
            if (ajouterUsagerId.getText().length() > 0 && ajouterUsagerPrenom.getText().length() > 0 && ajouterUsagerNom.getText().length() > 0 && ajouterUsagerAnneeNaissance.getText().length() > 0 && ajouterUsagerTarifReduit.getText().length() > 0) {
                try {
                    int id = Integer.parseInt(ajouterUsagerId.getText());
                    String prenom = ajouterUsagerPrenom.getText();
                    String nom = ajouterUsagerNom.getText();
                    int anneeNaissance = Integer.parseInt(ajouterUsagerAnneeNaissance.getText());
                    int tarifReduit = Integer.parseInt(ajouterUsagerTarifReduit.getText());

                    if (tarifReduit != 0 && tarifReduit != 1) {
                        JOptionPane.showMessageDialog(this, "Le champ 'Tarif réduit' doit être 0 (non) ou 1 (oui).", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int result = ajouterUsager(id, prenom, nom, anneeNaissance, tarifReduit);
                    if (result == -1) {
                        JOptionPane.showMessageDialog(this, "Cet ID existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    JOptionPane.showMessageDialog(this, "Usager ajouté avec succès !");

                    // Réinitialisation des champs
                    ajouterUsagerId.setText("");
                    ajouterUsagerPrenom.setText("");
                    ajouterUsagerNom.setText("");
                    ajouterUsagerAnneeNaissance.setText("");
                    ajouterUsagerTarifReduit.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Les champs ID, année de naissance et tarif réduit doivent être des nombres.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Action du bouton de modification
        btnModifierConfirm.addActionListener(e -> {
            if (modifierUsagerId.getText().length() > 0 && modifierUsagerPrenom.getText().length() > 0 && modifierUsagerNom.getText().length() > 0 && modifierUsagerAnneeNaissance.getText().length() > 0 && modifierUsagerTarifReduit.getText().length() > 0) {   
            	try {
            		int id = Integer.parseInt(modifierUsagerId.getText());
                    String prenom = modifierUsagerPrenom.getText();
                    String nom = modifierUsagerNom.getText();
                    int anneeNaissance = Integer.parseInt(modifierUsagerAnneeNaissance.getText());
                    int tarifReduit = Integer.parseInt(modifierUsagerTarifReduit.getText());
                    
                    if (tarifReduit != 0 && tarifReduit != 1) {
                        JOptionPane.showMessageDialog(this, "Le champ 'Tarif réduit' doit être 0 (non) ou 1 (oui).", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    if (modifierUsager(id, prenom, nom, anneeNaissance, tarifReduit) == 0) { // Fonction return 0 si ID n'existe pas
                        JOptionPane.showMessageDialog(null, "Aucun usager trouvé avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Usager modifié avec succès !");
                        // Une fois la modification faite, on vide tous les champs
                        modifierUsagerId.setText("");
                        modifierUsagerPrenom.setText("");
                        modifierUsagerNom.setText("");
                        modifierUsagerAnneeNaissance.setText("");
                        modifierUsagerTarifReduit.setText("");
                    }
                    
            	} catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Les champs ID, année de naissance et tarif réduit doivent être des nombres.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            	
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Action du bouton de suppression
        btnSupprimerConfirm.addActionListener(e -> {
            if (supprimerUsagerId.getText().length() > 0) {
            	try {
            		int id = Integer.parseInt(supprimerUsagerId.getText());
                    
                    if (supprimerUsager(id) == 0) { // Fonction return 0 si ID n'existe pas
                        JOptionPane.showMessageDialog(null, "Aucun usager trouvé avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Usager supprimé avec succès !");
                        // Une fois la suppression faite, on vide le champ
                        supprimerUsagerId.setText("");
                    }	
            	} catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "L'identifiant doit être un nombre.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez remplir le champ.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Action du bouton d'affichage
        btnAfficherConfirm.addActionListener(e -> {
            if (afficherUsagerId.getText().length() > 0) {
                try {
                    int id = Integer.parseInt(afficherUsagerId.getText());

                    // Récupération des informations de l'usager
                    Usager usager = usagerDAO.get(id);
                    if (usager != null) {
                        idUsagerLabel.setText("ID : " + usager.getIdUsager() + ", ");
                        prenomUsagerLabel.setText("Usager : " + usager.getPrenom());
                        nomUsagerLabel.setText(usager.getNom() + ", ");
                        anneeNaissanceUsagerLabel.setText("Année de naissance : " + usager.getAnneeNaissance() + ", ");
                        tarifReduitUsagerLabel.setText("Tarif réduit : " + (usager.getTarifReduit() == 1 ? "Oui" : "Non"));
                    } else {
                        JOptionPane.showMessageDialog(this, "Aucun usager trouvé avec cet ID.", "Erreur", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Récupération des informations des prêts de l'usager
                    ArrayList<String> prets = pretDAO.getPretsUsager(id);
                    StringBuilder sb = new StringBuilder(); // String Builder permet de creer facilement toutes les lignes de prets (String immuable, StringBuilder non)

                    if (prets.isEmpty()) {
                        sb.append("Aucun prêt enregistré pour cet usager.");
                    } else {
                        for (String ligne : prets) {
                            sb.append(ligne).append("\n");
                        }
                    }

                    pretsTextArea.setText(sb.toString());

                    // Une fois l'affichage fait, on vide le champ
                    afficherUsagerId.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "L'identifiant doit être un nombre.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez remplir le champ.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });


        // Action du bouton inventaire des stocks
        btnRequeteSQL.addActionListener(e -> {
            ArrayList<String> inventaire = livreDAO.getInventaire();

            StringBuilder sb = new StringBuilder();
            if (inventaire.isEmpty()) {
                sb.append("Aucun livre disponible en stock.");
            } else {
                for (String ligne : inventaire) {
                    sb.append(ligne).append("\n");
                }
            }

            requeteSQLTextArea.setText(sb.toString());
        });

    }

    // Cacher tous les panneaux et afficher celui que l'on veut
    private void afficherPanelUnique(JPanel panel) {
    	// On cache d'abord le message de bienvenue
    	messageBienvenue1.setVisible(false);
    	messageBienvenue2.setVisible(false);
    	// Puis on cache tous les panneaux
        panelAjouter.setVisible(false);
        panelModifier.setVisible(false);
        panelSupprimer.setVisible(false);
        panelAfficher.setVisible(false);
        panelSQL.setVisible(false);
        // Et on affiche seulement celui que l'on voulait
        panel.setVisible(true);
        pack(); // Permet d'ajuster la taille
    }

    /**
     * fonction pour ajouter un usager
     * 
     * @param id l'ID de l'usager a ajouter
     * @param prenom le prenom de l'usager a ajouter
     * @param nom le nom de l'usager a ajouter
     * @param anneeNaissance l'annee de naissance de l'usager a ajouter
     * @param tarifReduit le tarif reduit de l'usager a ajouter
     * 
     * @return le nombre de lignes ajoutees dans la table
     */
    private int ajouterUsager(int id, String prenom, String nom, int anneeNaissance, int tarifReduit) {
        Usager u = new Usager(id, prenom, nom, anneeNaissance, tarifReduit);
        return usagerDAO.add(u);
    }
    
    /**
     * fonction pour modifier un usager
     * 
     * @param id l'ID de l'usager a modifier
     * @param prenom le prenom de l'usager modifie
     * @param nom le nom de l'usager modifie
     * @param anneeNaissance l'annee de naissance de l'usager modifie
     * @param tarifReduit le tarif reduit de l'usager modifie
     * 
     * @return le nombre de lignes modifiees dans la table
     */
    private int modifierUsager(int id, String prenom, String nom, int anneeNaissance, int tarifReduit) {
        Usager u = new Usager(id, prenom, nom, anneeNaissance, tarifReduit);
        return usagerDAO.update(u);
    }
    
    /**
     * fonction pour supprimer un usager
     * 
     * @param id l'id de l'usager a supprimer
     * 
     * @return le nombre de lignes supprimees dans la table
     */
    private int supprimerUsager(int id) {
        return usagerDAO.delete(id);
    }
    
    /**
     * main : lancement de l'interface utilisateur
     * 
     * @param args
     */
    public static void main(String[] args) {
        new MainGUI();
    }
}
