package test.java.com.carte.tresor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.com.carte.tresor.io.LecteurFichier;
import main.java.com.carte.tresor.model.carte.Carte;
import main.java.com.carte.tresor.utils.Utils;

public class TestLecteurFichier {

	private static final Logger logger = LogManager.getLogger(TestLecteurFichier.class);

	private void testLectureCarteValide() {

		URL res = Utils.getRessource("CarteValide.txt");

		try {

			String path = Paths.get(res.toURI()).toString();
			Carte carte = LecteurFichier.lireFichier(path);
			if (carte == null) {
				throw new AssertionError("La carte ne devrait pas être null pour un fichier valide");
			}
			logger.info("testLectureCarteValide passé avec succès");
		} catch (Exception e) {
			throw new AssertionError("Le test testLectureCarteValide a échoué avec l'exception: " + e.getMessage());
		}
	}

	public void testLectureCarteInvalide() {
		
		URL res = Utils.getRessource("carteInvalide.txt");
		try {
			String path = Paths.get(res.toURI()).toString();
			Carte carte = LecteurFichier.lireFichier(path);
			if (carte.checkElementsWithinBounds()) {
	           logger.info("Des Éléments en dehors des limites détectés et cela était attendu");
	        } 
		} catch (URISyntaxException | IOException e) {
			logger.error(" {}", e.getMessage());
		}
	}

	public static void main(String[] args) {
		TestLecteurFichier test = new TestLecteurFichier();

		try {
			test.testLectureCarteValide();
			test.testLectureCarteInvalide();

		} catch (AssertionError e) {
			logger.error("Échec du test : {}", e.getMessage());
		}
	}

}
