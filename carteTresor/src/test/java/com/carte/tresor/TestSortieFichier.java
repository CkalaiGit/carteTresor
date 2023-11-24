package test.java.com.carte.tresor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.com.carte.tresor.io.SortieFichier;
import main.java.com.carte.tresor.model.aventurier.Aventurier;
import main.java.com.carte.tresor.model.aventurier.Orientation;
import main.java.com.carte.tresor.model.carte.Carte;

public class TestSortieFichier {

	private static final Logger logger = LogManager.getLogger(TestSortieFichier.class);

	public static void main(String[] args) {

		try {
			TestSortieFichier.testWriteSimulation();
		} catch (AssertionError | Exception e) {
			logger.error("Échec du test {} : ", e.getMessage());
		}

	}

	public static void testWriteSimulation() {
		Carte carte = new Carte(3, 4);
		carte.ajouterMontagne(1, 0);
		carte.ajouterMontagne(2, 1);
		carte.ajouterTresor(1, 3, 3);
		carte.ajouterTresor(0, 3, 2);

		Aventurier aventurier1 = new Aventurier(1, 1, Orientation.SUD, "AADADA", 0, "aventurier1");
		// Aventurier aventurier2 = new Aventurier(0, 2, Orientation.EST, "AGADAG", 0,
		// "aventurier2");

		List<Aventurier> aventuriers = Arrays.asList(aventurier1);

		try {
			SortieFichier.writeSimulation(carte, aventuriers);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
