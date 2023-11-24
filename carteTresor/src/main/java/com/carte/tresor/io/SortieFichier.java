package main.java.com.carte.tresor.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import main.java.com.carte.tresor.model.aventurier.Aventurier;
import main.java.com.carte.tresor.model.carte.Carte;
import main.java.com.carte.tresor.model.carte.Case;

public class SortieFichier {

	private SortieFichier() {
		throw new UnsupportedOperationException();
	}

	public static void writeSimulation(Carte carte, List<Aventurier> aventuriers) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("C - %d - %d %n", carte.getLargeur(), carte.getHauteur()));

		for (int y = 0; y < carte.getHauteur(); y++) {
			for (int x = 0; x < carte.getLargeur(); x++) {
				Case caseActuelle = carte.getCase(x, y);
				switch (caseActuelle.getType()) {
				case MONTAGNE:
					sb.append(String.format("M - %d - %d %n", x, y));
					break;
				case TRESOR:
					sb.append(String.format("T - %d - %d - %d %n", x, y, caseActuelle.getTresors()));
					break;
				default:
					break;
				}
			}
		}

		for (Aventurier aventurier : aventuriers) {
			sb.append(String.format("A - %s - %d - %d - %s - %d %n", aventurier.getName(), aventurier.getX(),
					aventurier.getY(), aventurier.getOrientation(), aventurier.getNombreDeTresors()));
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
			writer.write(sb.toString());
			writer.flush();
		}
	}

}
