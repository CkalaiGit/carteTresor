package main.java.com.carte.tresor.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import main.java.com.carte.tresor.model.Carte;

public class LecteurFichier {

    public static Carte lireFichier(String cheminFichier) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(cheminFichier))) {
            Carte carte = new Carte(0, 0); // Valeurs initiales par défaut

            stream.forEach(ligne -> {
                if (ligne.startsWith("C")) {
                    Carte nouvelleCarte = traiterLigneCarte(ligne);
                    carte.setLargeur(nouvelleCarte.getLargeur());
                    carte.setHauteur(nouvelleCarte.getHauteur());
                } else if (ligne.startsWith("M")) {
                    traiterLigneMontagne(ligne, carte);
                } else if (ligne.startsWith("T")) {
                    traiterLigneTresor(ligne, carte);
                }
            });

            return carte;
        }
    }

    private static Carte traiterLigneCarte(String ligne) {
        try {
            String[] parties = ligne.split(" - ");
            int largeur = Integer.parseInt(parties[1]);
            int hauteur = Integer.parseInt(parties[2]);
            return new Carte(largeur, hauteur);
        } catch (NumberFormatException e) {
        	e.printStackTrace(); // TO DO : logger.error("Erreur de format dans la ligne : " + ligne, e);
            return new Carte(0, 0); // Valeur par défaut ou selon la logique métier
        }
    }

    private static void traiterLigneMontagne(String ligne, Carte carte) {
        try {
            String[] parties = ligne.split(" - ");
            int x = Integer.parseInt(parties[1]);
            int y = Integer.parseInt(parties[2]);
            carte.ajouterMontagne(x, y);
        } catch (NumberFormatException e) {
        	e.printStackTrace();// TO DO : logger.error("Erreur de format dans la ligne : " + ligne, e);
        }
    }

    private static void traiterLigneTresor(String ligne, Carte carte) {
        try {
            String[] parties = ligne.split(" - ");
            int x = Integer.parseInt(parties[1]);
            int y = Integer.parseInt(parties[2]);
            int nbTresors = Integer.parseInt(parties[3]);
            carte.ajouterTresor(x, y, nbTresors);
        } catch (NumberFormatException e) {
        	e.printStackTrace();// TO DO : logger.error("Erreur de format dans la ligne : " + ligne, e);
        }
    }
}
