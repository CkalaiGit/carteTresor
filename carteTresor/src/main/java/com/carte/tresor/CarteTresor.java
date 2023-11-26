package main.java.com.carte.tresor;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.java.com.carte.tresor.io.LecteurFichier;
import main.java.com.carte.tresor.io.SortieFichier;
import main.java.com.carte.tresor.model.aventurier.Aventurier;
import main.java.com.carte.tresor.model.carte.Carte;
import main.java.com.carte.tresor.utils.Utils;

public class CarteTresor {
	
	private static final Logger logger = LogManager.getLogger(CarteTresor.class);

	public static void main(String[] args) throws IOException {
		
		URL res = Utils.getRessource("Input.txt");
		String path = "";
		Carte carte = new Carte(0, 0);
		try {
			path = Paths.get(res.toURI()).toString();
			carte = LecteurFichier.lireFichier(path);
		} catch (URISyntaxException | IOException e) {
			logger.error("erreur dans la lecture des données");
		}
		
		List<Aventurier> aventuriers = LecteurFichier.traiterLigneAventurier(carte, path);
		
		for (Aventurier aventurier : aventuriers) {
			aventurier.effectuerMouvements(path, carte, Aventurier.initialisePositions(aventuriers));
		}
		
		try {
			SortieFichier.writeSimulation(carte, aventuriers, "Output.txt");
			System.out.println("Répertoire courant : " + new File(".").getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
