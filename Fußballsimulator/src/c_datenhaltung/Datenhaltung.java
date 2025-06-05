package c_datenhaltung;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Datenhaltung {

	private static final Path DATEI_PFAD = Paths.get(System.getProperty("user.home"), "git", "Fussballsimulator",

			"Fußballsimulator", "src", "c_datenhaltung", "Fußballsimulator.txt");

	/**
	 * Speichert die übergebene Liste von Objekten in eine Datei.
	 *
	 * @param entitaeten
	 */

	public static void schreibeInDatei(ArrayList<Object> entitaeten) {
		try (OutputStream fos = Files.newOutputStream(DATEI_PFAD, StandardOpenOption.CREATE);) {
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(entitaeten);
			System.out.println("\nDein Fortschritt wurde erfolgreich gespeichert.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Liest die gespeicherte Datei aus und gibt die enthaltenen Entitäten zurück.
	 *
	 * @return Eine ArrayList mit den geladenen Entitäten oder null, wenn
	 *         ein Fehler auftritt
	 * 
	 */

	public static ArrayList<Object> leseAusDatei() throws ClassNotFoundException {
		try (InputStream fis = Files.newInputStream(DATEI_PFAD, StandardOpenOption.CREATE);) {
			ObjectInputStream ois = new ObjectInputStream(fis);
			return (ArrayList<Object>) ois.readObject(); // throws ClassNotFoundException
		} catch (IOException e) { // Files.newInputStream
			// e.printStackTrace();
			return null;
		}
	}
}
