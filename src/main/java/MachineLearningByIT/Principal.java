package MachineLearningByIT;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		try {
			String messageOne = "Digite la dimension de la matriz de probabilidades P(h) (2 a 10)";
			System.out.println(messageOne);
			Scanner dim = new Scanner(System.in);
			int d = dim.nextInt();

			if (d <= 10 && d >= 0) {
				MutualInformation mutualInformation = new MutualInformation();
				mutualInformation.ecuation2_6();
			} else {
				System.out.println("la cantidad no pertenece al rango aceptable para calcular");
			}
		} catch (Exception e) {
			System.out.println("El dato no es un digito númerico");
		}
	}
}
