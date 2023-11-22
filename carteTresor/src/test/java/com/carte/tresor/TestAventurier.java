package test.java.com.carte.tresor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.com.carte.tresor.model.aventurier.Aventurier;
import main.java.com.carte.tresor.model.aventurier.Orientation;
import main.java.com.carte.tresor.model.carte.Carte;
import main.java.com.carte.tresor.model.carte.TypeCase;

public class TestAventurier {
	
	private static final Logger logger = LogManager.getLogger(TestAventurier.class);

    public static void main(String[] args) {
    	
    	TestAventurier test =  new TestAventurier();
        
    	try {
    		test.testSimulerAventureSansMontagneEtTresor();
    		test.testSimulerAventureSansTresor();
    	}catch (AssertionError | Exception e) {
			logger.error("Échec du test {} : ", e.getMessage());
		}
        
    }

	public void testSimulerAventureSansMontagneEtTresor() {
		Carte carte = new Carte(3, 4); 
        Aventurier aventurier = new Aventurier(1, 1, Orientation.SUD, "AADADA");
        aventurier.effectuerMouvements(aventurier.getSequenceMouvements(), carte);

        final int positionFinaleX = aventurier.getX();
        final int positionFinaleY = aventurier.getY();
        if (positionFinaleX == 0 && positionFinaleY == 2) {
        	logger.info("Position finale de l'aventurier ({}, {}) calculée avec succès : " , positionFinaleX , positionFinaleY);
        } else {
        	logger.error("Mauvaise position de l'aventurier ({}, {}) : " , positionFinaleX , positionFinaleY);
        	throw new AssertionError("Le test simulerAventure a échoué");
        }
	}
	
	public void testSimulerAventureSansTresor() {
		Carte carte = new Carte(3, 4); 
        Aventurier aventurier = new Aventurier(1, 1, Orientation.SUD, "AADADA");
		carte.getCase(0, 2).setType(TypeCase.MONTAGNE);
		aventurier.effectuerMouvements(aventurier.getSequenceMouvements(), carte);
		int positionFinaleX = aventurier.getX();
		int positionFinaleY = aventurier.getY();
		if (positionFinaleX == 0 && positionFinaleY == 3) {
			logger.info("Position finale de l'aventurier ({}, {}) calculée avec succès : ", positionFinaleX,
					positionFinaleY);
		} else {
			logger.error("Mauvaise position de l'aventurier ({}, {}) : ", positionFinaleX, positionFinaleY);
			throw new AssertionError("Le test simulerAventure a échoué");
		}
	}
}
