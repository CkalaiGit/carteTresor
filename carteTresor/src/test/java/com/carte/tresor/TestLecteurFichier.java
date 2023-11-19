package test.java.com.carte.tresor;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.com.carte.tresor.io.LecteurFichier;
import main.java.com.carte.tresor.model.Carte;
import main.java.com.carte.tresor.utils.CarteUtils;

public class TestLecteurFichier {

	private static final Logger logger = LogManager.getLogger(TestLecteurFichier.class);

	public void testLectureCarteValide() {

		URL res = CarteUtils.getRessource("CarteValide.txt");

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
		URL res = CarteUtils.getRessource("carteInvalide.txt");

		try {
			String path = Paths.get(res.toURI()).toString();
			Carte carte = LecteurFichier.lireFichier(path);
			if (carte == null) {
				throw new AssertionError("La carte ne devrait pas être null pour un fichier valide");
			}
			throw new AssertionError("Une exception était attendue pour un fichier invalide");
		} catch (Exception e) {
			//logger.debug("testLectureCarteInvalide passé avec succès (exception attendue) {}", e.getMessage());
		}
	}

	public static void main(String[] args) {
		TestLecteurFichier test = new TestLecteurFichier();

		try {
			test.testLectureCarteValide();
			// test.testLectureCarteInvalide();

		} catch (AssertionError e) {
			logger.error("Échec du test : {}", e.getMessage());
		}
	}

}
