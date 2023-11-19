package test.java.com.carte.tresor;

import main.java.com.carte.tresor.model.aventurier.Aventurier;
import main.java.com.carte.tresor.model.aventurier.Orientation;
import main.java.com.carte.tresor.model.carte.Carte;

public class TestAventurier {

    public static void main(String[] args) {
        
    	Carte carte = new Carte(3, 4); // Exemple de dimensions
        // Ajouter des obstacles (montagnes, etc.) à la carte si nécessaire

        Aventurier aventurier = new Aventurier(1, 1, Orientation.SUD, "AADADA");
        aventurier.effectuerMouvements();

        int positionFinaleX = aventurier.getX();
        int positionFinaleY = aventurier.getY();
        System.out.println("Position finale de l'aventurier : " + positionFinaleX + ", " + positionFinaleY);

        // Ici, ajoutez votre logique de test pour vérifier si la position finale est celle attendue
    }
}
