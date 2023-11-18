package main.java.com.carte.tresor.model;


public class Carte {

	private Case[][] grille;
	private int largeur;
	private int hauteur;

	public Carte(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        grille = new Case[hauteur][largeur];
        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                grille[y][x] = new Case(); // Initialiser toutes les cases comme des plaines
            }
        }
    }

	public Case[][] getGrille() {
		return grille;
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

	public void ajouterMontagne(int x, int y) {
	    if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
	        grille[y][x].setType(TypeCase.MONTAGNE);
	    } else {
	        // Gérer le cas où les coordonnées sont hors limites
	        // Par exemple, enregistrer un message d'erreur ou lancer une exception
	    }
	}


	public void ajouterTresor(int x, int y, int nbTresors) {
	    if (x >= 0 && x < largeur && y >= 0 && y < hauteur) {
	        grille[y][x].setType(TypeCase.TRESOR);
	        grille[y][x].setTresors(nbTresors);
	    } else {
	        // Gérer le cas où les coordonnées sont hors limites
	    }
	}

	// Méthode pour afficher la carte...
}
