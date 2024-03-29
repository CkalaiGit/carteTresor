package main.java.com.carte.tresor.model.aventurier;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import main.java.com.carte.tresor.model.carte.Carte;
import main.java.com.carte.tresor.model.carte.Case;
import main.java.com.carte.tresor.model.carte.TypeCase;

public class Aventurier {

	private int x;
	private int y;
	private int xInitial;
	private int yInitial;
	private Orientation orientation;
	private String sequenceMouvements;
	private int nombreDeTresors;
	private String name;
	private List<String> historiquePositions;

	public Aventurier(int x, int y, Orientation orientation, String sequenceMouvements, int nombreDeTresors,
			String name) {
		this.setX(x);
		this.setY(y);
		this.setxInitial(x);
		this.setyInitial(y);
		this.orientation = orientation;
		this.setSequenceMouvements(sequenceMouvements);
		this.setNombreDeTresors(nombreDeTresors);
		this.name = name;
		this.historiquePositions = new ArrayList<>();
	}

	public static Set<String> initialisePositions(List<Aventurier> aventuriers) {

		Set<String> positionsOccupees = new HashSet<>();

		for (Aventurier aventurier : aventuriers) {
			positionsOccupees.add(aventurier.getX() + "," + aventurier.getY());
		}

		return positionsOccupees;
	}

	public static void effectuerTour(Carte carte, List<Aventurier> aventuriers, Set<String> positionsOccupees) {

		for (Aventurier aventurier : aventuriers) {
			aventurier.effectuerMouvements(aventurier.getSequenceMouvements(), carte, positionsOccupees);
		}
	}

	public void avancer(Carte carte) {
		int newX = x;
		int newY = y;
		historiquePositions.add(x + "," + y);

		switch (orientation) {
		case N:
			setY(newY--); // Déplacement vers le haut sur la carte
			break;
		case S:
			setY(newY++); // Déplacement vers le bas sur la carte
			break;
		case E:
			setX(newX++); // Déplacement vers la droite sur la carte
			break;
		case O:
			setX(newX--); // Déplacement vers la gauche sur la carte
			break;
		}

		// On avance que si on sort pas de la carte et qu'on ne fait pas fasse à une montagne
		if (newX >= 0 && newX < carte.getLargeur() && newY >= 0 && newY < carte.getHauteur()
				&& carte.getCase(newX, newY).getType() != TypeCase.MONTAGNE) {
			x = newX;
			y = newY;

			Case caseActuelle = carte.getCase(x, y);
			ramasserTresor(caseActuelle);
		}
	}

	public void tournerAGauche() {
		switch (orientation) {
		case N:
			orientation = Orientation.O;
			break;
		case O:
			orientation = Orientation.S;
			break;
		case S:
			orientation = Orientation.E;
			break;
		case E:
			orientation = Orientation.N;
			break;
		}
	}

	public void tournerADroite() {
		switch (orientation) {
		case N:
			orientation = Orientation.E;
			break;
		case E:
			orientation = Orientation.S;
			break;
		case S:
			orientation = Orientation.O;
			break;
		case O:
			orientation = Orientation.N;
			break;
		}
	}

	public void effectuerMouvements(String mouvements, Carte carte, Set<String> positionsOccupees) {
		for (char mouvement : mouvements.toCharArray()) {
			int ancienX = x;
			int ancienY = y;
			switch (mouvement) {
			case 'A':
				avancer(carte);
				break;
			case 'G':
				tournerAGauche();
				break;
			case 'D':
				tournerADroite();
				break;
			default:
				break;
			}

			if (ancienX != x || ancienY != y) {
				positionsOccupees.remove(ancienX + "," + ancienY);
				positionsOccupees.add(x + "," + y);
			}
		}

	}

	public void ramasserTresor(Case caseActuelle) {
		if (caseActuelle.getType() == TypeCase.TRESOR && caseActuelle.getTresors() > 0) {
			nombreDeTresors++;
			caseActuelle.setTresors(caseActuelle.getTresors() - 1);
			if (caseActuelle.getTresors() == 0) {
				caseActuelle.setType(TypeCase.PLAINE);
			}
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getxInitial() {
		return xInitial;
	}

	public void setxInitial(int xInitial) {
		this.xInitial = xInitial;
	}

	public int getyInitial() {
		return yInitial;
	}

	public void setyInitial(int yInitial) {
		this.yInitial = yInitial;
	}

	public String getSequenceMouvements() {
		return sequenceMouvements;
	}

	public void setSequenceMouvements(String sequenceMouvements) {
		this.sequenceMouvements = sequenceMouvements;
	}

	public int getNombreDeTresors() {
		return nombreDeTresors;
	}

	public void setNombreDeTresors(int nombreDeTresors) {
		this.nombreDeTresors = nombreDeTresors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public List<String> getHistoriquePositions() {
		return historiquePositions;
	}

	public void setHistoriquePositions(List<String> historiquePositions) {
		this.historiquePositions = historiquePositions;
	}
}