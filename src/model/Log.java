package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe du log contenant les actions faites dans la console par l'utilisateur
 * 
 * @author anais peigney
 * @version 1.0
 */
public class Log {
    private static BufferedWriter writer;

    /**
     * Initialisation du fichier log
     */
    public static void init() {
        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String fileName = "data/ProjetJavaAnaisPeigney_" + timestamp + ".log";
            writer = new BufferedWriter(new FileWriter(fileName, true)); // true pour append
            log("Fichier de log initialisé");
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du fichier log : " + e.getMessage());
        }
    }

    /**
     * Écriture dans le log
     * 
     * @param message la ligne a ajouter dans le log
     */
    public static void log(String message) {
        try {
            if (writer != null) {
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                writer.write(timestamp + " – " + message);
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier log : " + e.getMessage());
        }
    }

    /**
     * Fermeture du log
     */
    public static void close() {
        try {
            if (writer != null) {
                log("Fermeture du fichier log");
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la fermeture du fichier log : " + e.getMessage());
        }
    }
}
