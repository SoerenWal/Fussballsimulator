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

	private static final Path DATEI_PFAD = Paths.get(System.getProperty("user.home"), ".MeinProgramm",
			"Fußballsimulator.txt");

	public static void schreibeInDatei(ArrayList mannschaften) {
		try (OutputStream fos = Files.newOutputStream(DATEI_PFAD, StandardOpenOption.CREATE);) {
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(mannschaften);
			System.out.println("Das Spiel wurde erfolgreich gespeichert.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList leseAusDatei() throws ClassNotFoundException {
		try (InputStream fis = Files.newInputStream(DATEI_PFAD, StandardOpenOption.CREATE);) {
			ObjectInputStream ois = new ObjectInputStream(fis);
			return (ArrayList) ois.readObject(); // throws ClassNotFoundException
		} catch (IOException e) { // Files.newInputStream
			e.printStackTrace();
			return null;
		}
	}
}
