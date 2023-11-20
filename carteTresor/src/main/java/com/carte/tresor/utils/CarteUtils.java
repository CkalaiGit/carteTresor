package main.java.com.carte.tresor.utils;

import java.net.URL;

public class CarteUtils {
	
	private CarteUtils() {
		throw new UnsupportedOperationException("Cette classe ne doit pas être instancié");
	}
	
	public static URL getRessource(String fileName) throws AssertionError {
		URL res = CarteUtils.class.getClassLoader().getResource(fileName);
		if (res == null) {
            throw new AssertionError("Le fichier carteValide.txt n'a pas été trouvé");
        }
		return res;
	}

}
