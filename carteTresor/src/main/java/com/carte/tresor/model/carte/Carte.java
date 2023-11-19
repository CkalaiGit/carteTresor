package main.java.com.carte.tresor.model.carte;

public class Carte {

	private Case[][] grille;
	private int largeur;
	private int hauteur;

	public Carte(int largeur, int hauteur) {
		initialiserCarte(largeur, hauteur);
	}

	private void initialiserCarte(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		grille = new Case[hauteur][largeur];

		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < largeur; x++) {
				grille[y][x] = new Case(); // Initialiser toutes les cases comme des plaines
			}
		}
	}

	public void ajouterMontagne(int x, int y) {
		if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
			grille[y][x].setType(TypeCase.MONTAGNE);
		}
	}

	public void ajouterTresor(int x, int y, int nbTresors) {
		if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
			grille[y][x].setType(TypeCase.TRESOR);
			grille[y][x].setTresors(nbTresors);
		}
	}

	public boolean checkElementsWithinBounds() {
	    for (int y = 0; y < hauteur; y++) {
	        for (int x = 0; x < largeur; x++) {
	            Case caseCourante = grille[y][x];
	            if ((caseCourante.getType() == TypeCase.MONTAGNE || caseCourante.getType() == TypeCase.TRESOR) &&
	                !isWithinBounds(x, y)) {
	                return false;
	            }
	        }
	    }
	    return true;
	}

	private boolean isWithinBounds(int x, int y) {
		return x >= 0 && x < largeur && y >= 0 && y < hauteur;
	}

	public Case[][] getGrille() {
		return grille;
	}

	public Case getCase(int x, int y) {
		if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
			return grille[y][x];
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	public void setGrille(Case[][] grille) {
		this.grille = grille;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

}
