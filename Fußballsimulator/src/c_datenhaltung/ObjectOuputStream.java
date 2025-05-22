package c_datenhaltung;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ObjectOuputStream {

	private static final Path DATEI_PFAD = Paths.get(System.getProperty("user.home"), ".MeinProgramm", "Fußballsimulator.txt");
	
	public static void schreibeInDatei(Object o) {
	try (OutputStream fos = Files.newOutputStream(DATEI_PFAD, StandardOpenOption.CREATE);) {
	ObjectOutputStream oos = new ObjectOutputStream(fos);
	oos.writeObject(o);
	System.out.println("Fertig.");
	} catch (IOException e) {
	e.printStackTrace();
	}
	}
}
