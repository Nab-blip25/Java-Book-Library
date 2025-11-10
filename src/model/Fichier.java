package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Classe du fichier avec les informations demandees
 * 
 * @author anais peigney
 * @version 1.0
 */
public class Fichier {

    private static BufferedWriter writer;

    /**
     * Permet d'initialiser le fichier avec le nom voulu
     */
    public static void init() {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String fileName = "data/FichierResultats_" + timestamp + ".log";
            writer = new BufferedWriter(new FileWriter(fileName, true));
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du fichier de résultats : " + e.getMessage());
        }
    }

    /**
     * Permet d'ecrire une ligne dans le fichier
     * 
     * @param message la ligne a ajouter dans le fichier
     */
    public static void log(String message) {
        try {
            if (writer != null) {
                writer.write(message);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier de résultats : " + e.getMessage());
        }
    }

    /**
     * Permet de fermer le fichier
     */
    public static void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la fermeture du fichier de résultats : " + e.getMessage());
        }
    }
}
