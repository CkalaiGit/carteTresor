package main.java.com.carte.tresor.model.aventurier;

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

	public Aventurier(int x, int y, Orientation orientation, String sequenceMouvements, int nombreDeTresors) {
		this.setX(x);
		this.setY(y);
		this.setxInitial(x);
		this.setyInitial(y);
		this.orientation = orientation;
		this.setSequenceMouvements(sequenceMouvements);
		this.setNombreDeTresors(nombreDeTresors);
	}

	public void avancer(Carte carte) {
		int newX = x;
		int newY = y;

		switch (orientation) {
		case NORD:
			setY(newY--); // Déplacement vers le haut sur la carte
			break;
		case SUD:
			setY(newY++); // Déplacement vers le bas sur la carte
			break;
		case EST:
			setX(newX++); // Déplacement vers la droite sur la carte
			break;
		case OUEST:
			setX(newX--); // Déplacement vers la gauche sur la carte
			break;
		}

		// On avance que si on sort pas de la carte et qu'on ne fait pas fasse à une montagne
		if (newX >= 0 && newX < carte.getLargeur() && newY >= 0 && newY < carte.getHauteur()
				&& carte.getCase(newX, newY).getType() != TypeCase.MONTAGNE) {
			x = newX;
			y = newY;
		}
	}

	public void tournerAGauche() {
		switch (orientation) {
		case NORD:
			orientation = Orientation.OUEST;
			break;
		case OUEST:
			orientation = Orientation.SUD;
			break;
		case SUD:
			orientation = Orientation.EST;
			break;
		case EST:
			orientation = Orientation.NORD;
			break;
		}
	}

	public void tournerADroite() {
		switch (orientation) {
		case NORD:
			orientation = Orientation.EST;
			break;
		case EST:
			orientation = Orientation.SUD;
			break;
		case SUD:
			orientation = Orientation.OUEST;
			break;
		case OUEST:
			orientation = Orientation.NORD;
			break;
		}
	}

	public void effectuerMouvements(String mouvements, Carte carte) {
		for (char mouvement : mouvements.toCharArray()) {
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
				// Gérer les caractères inattendus dans la séquence de mouvements
				break;
			}
		}
	}
	
	public void ramasserTresor(Case caseActuelle) {
	    if (caseActuelle.getType() == TypeCase.TRESOR && caseActuelle.getTresors() > 0) {
	        nombreDeTresors++;
	        caseActuelle.setTresors(caseActuelle.getTresors() - 1); 
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

}