package main.java.com.carte.tresor.io;

import java.io.IOException;
import java.util.List;

import main.java.com.carte.tresor.model.aventurier.Aventurier;
import main.java.com.carte.tresor.model.carte.Carte;
import main.java.com.carte.tresor.model.carte.Case;
import main.java.com.carte.tresor.utils.Utils;

public class SortieFichier {

	private SortieFichier() {
		throw new UnsupportedOperationException();
	}

	public static void writeSimulation(Carte carte, List<Aventurier> aventuriers, String name) throws IOException {

		StringBuilder sb = new StringBuilder();
		sb.append("# {C comme Carte} - {Nb. de case en largeur} - {Nb. de case en hauteur} \n");
		sb.append("# {M comme Montagne} - {Axe horizontal} - {Axe vertical} \n");
		sb.append("# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants} \n");
		sb.append("# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés} \n");
		sb.append(String.format("C - %d - %d %n", carte.getLargeur(), carte.getHauteur()));

		for (int y = 0; y < carte.getHauteur(); y++) {
			for (int x = 0; x < carte.getLargeur(); x++) {

				Case caseXY = carte.getCase(x, y);
				if (caseXY.getType() != null) {
					switch (caseXY.getType()) {
					case MONTAGNE:
						sb.append(String.format("M - %d - %d %n", x, y));
						break;
					case TRESOR:
						sb.append(String.format("T - %d - %d - %d %n", x, y, caseXY.getTresors()));
						break;
					default:
						break;
					}
				}
			}
		}

		for (Aventurier aventurier : aventuriers) {
			sb.append(String.format("A - %s - %d - %d - %s - %d %n", aventurier.getName(), aventurier.getX(),
					aventurier.getY(), aventurier.getOrientation(), aventurier.getNombreDeTresors()));
		}

		Utils.writingOutput(sb, name);
	}

}
