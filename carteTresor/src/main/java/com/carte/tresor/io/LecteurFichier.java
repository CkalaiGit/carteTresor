package main.java.com.carte.tresor.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.com.carte.tresor.model.aventurier.Aventurier;
import main.java.com.carte.tresor.model.aventurier.Orientation;
import main.java.com.carte.tresor.model.carte.Carte;

public class LecteurFichier {

	private LecteurFichier() {
		throw new UnsupportedOperationException();
	}

	private static final Logger logger = LogManager.getLogger(LecteurFichier.class);

	private static final String NUMBERFORMATEXCEPTION = "Erreur de format dans la ligne : {}. Erreur : {}";

	public static Carte lireFichier(String cheminFichier) throws IOException {
		
		List<String> lignes = new ArrayList<>();
		Carte carte = new Carte(0, 0);

		try (Stream<String> stream = Files.lines(Paths.get(cheminFichier))) {
			for (String ligne : stream.collect(Collectors.toList())) {
				if (ligne.startsWith("C")) {
					carte = traiterLigneCarte(ligne);
				} else if (ligne.startsWith("M") || ligne.startsWith("T")) {
					lignes.add(ligne);
				}
			}

			for (String ligne : lignes) {
				if (ligne.startsWith("M")) {
					traiterLigneMontagne(ligne, carte);
				} else if (ligne.startsWith("T")) {
					traiterLigneTresor(ligne, carte);
				}
			}
		}

		return carte;
	}

	public static List<Aventurier> traiterLigneAventurier(Carte carte, String cheminFichier) throws IOException {

		List<Aventurier> aventuriers = new ArrayList<>();
		List<String> lignes = getLines(cheminFichier);

		for (String ligne : lignes) {
			if (ligne.startsWith("A")) {
				String[] parties = ligne.split(" - ");
				String nom = parties[1];
				int x = Integer.parseInt(parties[2]);
				int y = Integer.parseInt(parties[3]);
				Orientation orientation = Orientation.valueOf(parties[4]);
				String sequenceMouvements = parties[5];

				if (isWithinMapBounds(x, y, carte)) {
					aventuriers.add(new Aventurier(x, y, orientation, sequenceMouvements, 0, nom));
				}
			}
		}

		return aventuriers;
	}

	private static List<String> getLines(String cheminFichier) throws IOException {
		try (Stream<String> stream = Files.lines(Paths.get(cheminFichier))) {
			return stream.collect(Collectors.toList());
		}
	}

	private static Carte traiterLigneCarte(String ligne) {
		try {
			String[] parties = ligne.split(" - ");
			int largeur = Integer.parseInt(parties[1]);
			int hauteur = Integer.parseInt(parties[2]);
			return new Carte(largeur, hauteur);
		} catch (NumberFormatException e) {
			logger.error(NUMBERFORMATEXCEPTION, ligne, e.getMessage());
			return new Carte(0, 0); // Valeur par défaut ou selon la logique métier
		}
	}

	private static void traiterLigneMontagne(String ligne, Carte carte) {
		try {
			String[] parties = ligne.split(" - ");
			int x = Integer.parseInt(parties[1]);
			int y = Integer.parseInt(parties[2]);
			if (isWithinMapBounds(x, y, carte)) {
				carte.ajouterMontagne(x, y);
			} else {
				logger.error("Des Éléments en dehors des limites détectés");
			}
		} catch (NumberFormatException e) {
			logger.error(NUMBERFORMATEXCEPTION, ligne, e.getMessage());
		}
	}

	private static void traiterLigneTresor(String ligne, Carte carte) {
		try {
			String[] parties = ligne.split(" - ");
			int x = Integer.parseInt(parties[1]);
			int y = Integer.parseInt(parties[2]);
			int nbTresors = Integer.parseInt(parties[3]);
			if (isWithinMapBounds(x, y, carte)) {
				carte.ajouterTresor(x, y, nbTresors);
			} else {
				logger.error("Des Éléments en dehors des limites détectés");
			}

		} catch (NumberFormatException e) {
			logger.error(NUMBERFORMATEXCEPTION, ligne, e.getMessage());
		}
	}

	public static boolean isWithinMapBounds(int x, int y, Carte carte) {
		return x >= 0 && x < carte.getLargeur() && y >= 0 && y < carte.getHauteur();
	}
}