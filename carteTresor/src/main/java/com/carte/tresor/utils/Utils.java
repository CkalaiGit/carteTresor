package main.java.com.carte.tresor.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class Utils {

	private Utils() {
		throw new UnsupportedOperationException("Cette classe ne doit pas être instancié");
	}

	public static URL getRessource(String fileName) throws AssertionError {
		URL res = Utils.class.getClassLoader().getResource(fileName);
		if (res == null) {
			throw new AssertionError("Un fichier d'input n'a pas été trouvé");
		}
		return res;

	}

	public static void writingOutput(StringBuilder sb, String name) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(name))) {
			writer.write(sb.toString());
			writer.flush();
		}
	}
}
