package test.java.com.carte.tresor;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.com.carte.tresor.model.aventurier.Aventurier;
import main.java.com.carte.tresor.model.aventurier.Orientation;
import main.java.com.carte.tresor.model.carte.Carte;
import main.java.com.carte.tresor.model.carte.TypeCase;

public class TestAventurier {

	private static final Logger logger = LogManager.getLogger(TestAventurier.class);
	
	private static final String MSG_POSITION_SUCCESS = "Position finale de l'aventurier ({}, {}) calculée avec succès : ";
	private static final String MSG_POSITION_FAILURE = "Mauvaise position de l'aventurier ({}, {}) : ";
	private static final String AADADA = "AADADA";
	public static void main(String[] args) {

		TestAventurier test = new TestAventurier();

		try {
			test.testSimulerAventureSansMontagneEtTresor();
			test.testSimulerAventureSansTresor();
			test.testSimulerAventure();
			test.testSimulerAventures();
		} catch (AssertionError | Exception e) {
			logger.error("Échec du test {} : ", e.getMessage());
		}

	}

	public void testSimulerAventureSansMontagneEtTresor() {
		Carte carte = new Carte(3, 4);
		Aventurier aventurier = new Aventurier(1, 1, Orientation.SUD, AADADA, 0, "toto");
		List<Aventurier> aventuriers = new ArrayList<>();
		aventuriers.add(aventurier);
		aventurier.effectuerMouvements(aventurier.getSequenceMouvements(), carte, Aventurier.initialisePositions(aventuriers));

		final int positionFinaleX = aventurier.getX();
		final int positionFinaleY = aventurier.getY();
		if (positionFinaleX == 0 && positionFinaleY == 2) {
			logger.info(MSG_POSITION_SUCCESS, positionFinaleX,
					positionFinaleY);
		} else {
			logger.error(MSG_POSITION_FAILURE, positionFinaleX, positionFinaleY);
			throw new AssertionError("Le test testSimulerAventureSansMontagneEtTresor a échoué");
		}
	}

	public void testSimulerAventureSansTresor() {
		Carte carte = new Carte(3, 4);
		Aventurier aventurier = new Aventurier(1, 1, Orientation.SUD, AADADA, 0, null);
		carte.getCase(0, 2).setType(TypeCase.MONTAGNE);
		List<Aventurier> aventuriers = new ArrayList<>();
		aventuriers.add(aventurier);
		aventurier.effectuerMouvements(aventurier.getSequenceMouvements(), carte, Aventurier.initialisePositions(aventuriers));
		int positionFinaleX = aventurier.getX();
		int positionFinaleY = aventurier.getY();
		if (positionFinaleX == 0 && positionFinaleY == 3) {
			logger.info(MSG_POSITION_SUCCESS, positionFinaleX,
					positionFinaleY);
		} else {
			logger.error(MSG_POSITION_FAILURE, positionFinaleX, positionFinaleY);
			throw new AssertionError("Le test testSimulerAventureSansTresor a échoué");
		}
	}
	
	public void testSimulerAventure() {
		Carte carte = new Carte(3, 4);
		Aventurier aventurier = new Aventurier(1, 1, Orientation.SUD, AADADA, 0, null);
		carte.getCase(0, 2).setType(TypeCase.MONTAGNE);
		carte.getCase(1, 2).setType(TypeCase.TRESOR);
		carte.getCase(1, 2).setTresors(2);
		List<Aventurier> aventuriers = new ArrayList<>();
		aventuriers.add(aventurier);
		aventurier.effectuerMouvements(aventurier.getSequenceMouvements(), carte, Aventurier.initialisePositions(aventuriers));
		int positionFinaleX = aventurier.getX();
		int positionFinaleY = aventurier.getY();
		if (positionFinaleX == 0 && positionFinaleY == 3 && carte.getCase(1, 2).getTresors() == 1 ) {
			logger.info(MSG_POSITION_SUCCESS, positionFinaleX,
					positionFinaleY);
			logger.info("nombre de trésor restants = {} ", carte.getCase(1, 2).getTresors());
		} else {
			logger.error(MSG_POSITION_FAILURE, positionFinaleX, positionFinaleY);
			throw new AssertionError("Le test simulerAventure a échoué");
		}
	}
	
	private void testSimulerAventures() {
		Carte carte = new Carte(3, 4);
		Aventurier lara  = new Aventurier(1, 1, Orientation.SUD, AADADA, 0, "Lara");
		Aventurier indiana  = new Aventurier(2, 2, Orientation.EST, "A", 0, "Indiana");
		carte.getCase(0, 2).setType(TypeCase.MONTAGNE);
		carte.getCase(1, 2).setType(TypeCase.TRESOR);
		carte.getCase(1, 2).setTresors(2);
		List<Aventurier> aventuriers = new ArrayList<>();
		aventuriers.add(lara);
		aventuriers.add(indiana);
		lara.effectuerMouvements(lara.getSequenceMouvements(), carte, Aventurier.initialisePositions(aventuriers));
		indiana.effectuerMouvements(indiana.getSequenceMouvements(), carte, Aventurier.initialisePositions(aventuriers));
		int positionFinaleLaraX = lara.getX();
		int positionFinaleLaraY = lara.getY();
		int positionFinaleIndianaX = indiana.getX();
		int positionFinaleIndianaY = indiana.getY();
		aventuriers.forEach(e -> System.out.println(e.getHistoriquePositions()));
		if (positionFinaleLaraX == 0 && positionFinaleLaraY == 3 && positionFinaleIndianaX == 2 && positionFinaleIndianaY == 2) {
			logger.info("Le test simulerAventure a réussi");
		} else {
			throw new AssertionError("Le test testSimulerAventures a échoué");
		}
		
	}
	
	
	
}
