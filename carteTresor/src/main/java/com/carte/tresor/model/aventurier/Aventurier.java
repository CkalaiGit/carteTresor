package main.java.com.carte.tresor.model.aventurier;

public class Aventurier {
	private int x;
	private int y;
	private int xInitial;
	private int yInitial;
	private Orientation orientation;
	private String sequenceMouvements;

	public Aventurier(int x, int y, Orientation orientation, String sequenceMouvements) {
		this.setX(x);
		this.setY(y);
		this.setxInitial(x);
		this.setyInitial(y);
		this.orientation = orientation;
		this.setSequenceMouvements(sequenceMouvements);
	}

	public void avancer() {
		switch (orientation) {
		case NORD:
			setY(getY() - 1); // Déplacement vers le haut sur la carte
			break;
		case SUD:
			setY(getY() + 1); // Déplacement vers le bas sur la carte
			break;
		case EST:
			setX(getX() + 1); // Déplacement vers la droite sur la carte
			break;
		case OUEST:
			setX(getX() - 1); // Déplacement vers la gauche sur la carte
			break;
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

	public void effectuerMouvements() {
		// TODO Auto-generated method stub
	}


}