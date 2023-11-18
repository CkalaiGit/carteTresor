package test.java.com.carte.tresor;

import main.java.com.carte.tresor.model.Carte;
import main.java.com.carte.tresor.model.TypeCase;

public class TestCarte {

    private Carte carte;

    public void setUp() {
        carte = new Carte(5, 5); // Exemple avec une carte 5x5
    }

    public void testInitialisationCarte() {
        if (carte == null) {
            throw new AssertionError("La carte ne doit pas être null");
        }
        if (carte.getLargeur() != 5) {
            throw new AssertionError("La largeur de la carte doit être 5");
        }
        if (carte.getHauteur() != 5) {
            throw new AssertionError("La hauteur de la carte doit être 5");
        }
    }

    public void testAjouterMontagne() {
        carte.ajouterMontagne(2, 2);
        if (carte.getCase(2, 2).getType() != TypeCase.MONTAGNE) {
            throw new AssertionError("La case (2,2) doit être une montagne");
        }
    }

    public void testAjouterTresor() {
        carte.ajouterTresor(1, 1, 3);
        if (carte.getCase(1, 1).getType() != TypeCase.TRESOR) {
            throw new AssertionError("La case (1,1) doit contenir un trésor");
        }
        if (carte.getCase(1, 1).getTresors() != 3) {
            throw new AssertionError("La case (1,1) doit contenir 3 trésors");
        }
    }

    public static void main(String[] args) {
        TestCarte test = new TestCarte();

        try {
            test.setUp();
           
            test.testInitialisationCarte();
            System.out.println("testInitialisationCarte passé avec succès");

            test.testAjouterMontagne();
            System.out.println("testAjouterMontagne passé avec succès");

            test.testAjouterTresor();
            System.out.println("testAjouterTresor passé avec succès");


        } catch (AssertionError e) {
            System.out.println("Échec du test : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
