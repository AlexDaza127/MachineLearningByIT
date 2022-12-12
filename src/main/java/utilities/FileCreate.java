package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileCreate {

	/**
	 * Método que permite crear automaticamente las respectivas carpetas para alojar los archivos de los calculos de la información mutua
	 */
	public void createDirs() {
		List<File> directorioList = new ArrayList<>();

		directorioList.add(new File("./MutualInformationResult"));
		for (File directorio : directorioList) {
			if (!directorio.exists()) {
				if (directorio.mkdirs()) {
					System.out.println("Carpeta MutualInformationResult creada");
				} else {
					System.out.println("Error al crear la carpeta MutualInformationResult");
				}
			}
		}
	}

	/**
	 * Método para crear los archivos de los datos calculados de la información mutua
	 * 
	 * @param content     contenido del archivo
	 * @param dataID fecha de identificación del archivo generado
	 */
	public void createFiles(String content, String dataID) {
		try {
			String fileNameBin = "./MutualInformationResult/MutualInformation" + dataID + ".txt";
			List<String> lines = Arrays.asList(content);
			Path file = null;
				file = Paths.get(fileNameBin);
				System.out.println("Se creo: MutualInformation" + dataID + ".txt");
						
			try {
				Files.write(file, lines, Charset.forName("Windows-1252"));
			} catch (CharacterCodingException ex) {
				System.out.println(ex.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
