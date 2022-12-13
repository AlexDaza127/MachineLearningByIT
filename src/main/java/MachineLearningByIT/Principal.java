package MachineLearningByIT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import utilities.FileCreate;

public class Principal {

	public static void main(String[] args) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		LocalDateTime date_of_today = LocalDateTime.now();
		String formattedDate = date_of_today.format(format);
		String messageOne = "Digite la dimension de los datos de probabilidad (2 a 10)";
		System.out.println(messageOne);
		try {
			Scanner dim = new Scanner(System.in);
			int dimProb = dim.nextInt();

			if (dimProb <= 10 && dimProb >= 0) {
				FileCreate fileCreate = new FileCreate();
				fileCreate.createDirs();
				MutualInformation mutualInformation = new MutualInformation();
				fileCreate.createFiles(mutualInformation.ecuation2_6(dimProb), formattedDate);
				System.out.println("Fin del procesamiento");
				dim.close();
			} else {
				System.out.println("La cantidad no pertenece al rango aceptable para calcular");
			}
		} catch (Exception e) {
			System.out.println("No se digito caracter numérico");		}

	}
}
