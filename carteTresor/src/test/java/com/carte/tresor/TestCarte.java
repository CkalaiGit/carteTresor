package test.java.com.carte.tresor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import main.java.com.carte.tresor.model.carte.Carte;
import main.java.com.carte.tresor.model.carte.TypeCase;


public class TestCarte {

	private Carte carte;

	private static final Logger logger = LogManager.getLogger(TestCarte.class);

	public void setUp() {
		carte = new Carte(5, 5); // Exemple avec une carte 5x5
	}

	public void testInitialisationCarte() {
		if (carte == null) {
			throw new AssertionError("La carte ne doit pas être null");
		}
		if (carte.getLargeur() != 5) {
			throw new AssertionError("La largeur de la carte doit être 5");
		}
		if (carte.getHauteur() != 5) {
			throw new AssertionError("La hauteur de la carte doit être 5");
		}
	}

	public void testAjouterMontagne() {
		carte.ajouterMontagne(2, 2);
		if (carte.getCase(2, 2).getType() != TypeCase.MONTAGNE) {
			throw new AssertionError("La case (2,2) doit être une montagne");
		}
	}

	public void testAjouterTresor() {
		carte.ajouterTresor(1, 1, 3);
		if (carte.getCase(1, 1).getType() != TypeCase.TRESOR) {
			throw new AssertionError("La case (1,1) doit contenir un trésor");
		}
		if (carte.getCase(1, 1).getTresors() != 3) {
			throw new AssertionError("La case (1,1) doit contenir 3 trésors");
		}
	}

	public static void main(String[] args) {
		
	    Configurator.initialize(null, "log4j2.xml");

		TestCarte test = new TestCarte();

		try {
			test.setUp();

			test.testInitialisationCarte();
			logger.info("testInitialisationCarte passé avec succès");

			test.testAjouterMontagne();
			logger.info("testAjouterMontagne passé avec succès");

			test.testAjouterTresor();
			logger.info("testAjouterTresor passé avec succès");

		} catch (AssertionError | Exception e) {
			logger.error("Échec du test {} : ", e.getMessage());
		}
	}
}
