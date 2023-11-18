package main.java.com.carte.tresor.model;


public class Carte {

	private Case[][] grille;
	private int largeur;
	private int hauteur;

	public Carte(int largeur, int hauteur) {
		// Initialisation de la carte avec des plaines
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
		// TODO Auto-generated method stub
		
	}

	public void ajouterTresor(int x, int y, int nbTresors) {
		// TODO Auto-generated method stub
	}

	// Méthode pour afficher la carte...
}
