package MachineLearningByIT;

import java.util.Scanner;

public class MutualInformation {

	private StringBuilder dataCalculate = new StringBuilder();

	public String ecuation2_6(int dimProb) {

		dataCalculate.append("\t\t\tCalculo de la Informacion Mutua\r\n\r\n");
		Double[][] matrixApriProb = matrixApriProbCalculate(dimProb);
		Double[] vectorApostProb = vectorApostProbCalculate(dimProb);
		Double[][] joinProb = joinProbCalculate(dimProb, matrixApriProb, vectorApostProb);
		Double[] marginalProb = marginalProbCalculate(dimProb, joinProb);

		double dataHy = dataHyCalculate(dimProb, marginalProb);
		double dataHy_Hyh = dataHy_HyhCalculate(dimProb, matrixApriProb, joinProb);
		double resultado = Math.round((dataHy - dataHy_Hyh) * 100.0) / 100.0;
		dataCalculate.append("\r\n\r\nInformacion Mutua Resultante = " + resultado);
		return dataCalculate.toString();
	}

	@SuppressWarnings("resource")
	private Double[][] matrixApriProbCalculate(int dimProb) {
		Double[][] matrixApriProb = new Double[dimProb + 1][dimProb + 1];
		Scanner dim = new Scanner(System.in);

		System.out.println("Digite los datos de la matriz de probabilidad apriori");

		for (int i = 0; i < dimProb; i++) {
			for (int j = 0; j < dimProb; j++) {

				System.out.println("[" + (i + 1) + "][" + (j + 1) + "] = ");
				Double data = dim.nextDouble();
				matrixApriProb[i][j] = data;
			}
		}

		dataCalculate.append("Datos de la matriz de probabilidad apriori = \r\n");
		String matrix = "";
		for (int i = 0; i < dimProb; i++) {
			matrix += "| ";
			for (int j = 0; j < dimProb; j++) {
				matrix += matrixApriProb[i][j] + " ";
			}
			matrix += "|\r\n";
		}
		dataCalculate.append(matrix + "\r\n");
		return matrixApriProb;
	}

	@SuppressWarnings("resource")
	private Double[] vectorApostProbCalculate(int dimProb) {
		Double[] vectorApostProb = new Double[dimProb + 1];
		Scanner dim = new Scanner(System.in);

		System.out.println("Digite los datos del vector de probabilidad aposteriori");
		dataCalculate.append("Datos del vector de probabilidad aporteriori\r\n");

		String vector = "{";
		for (int i = 0; i < dimProb; i++) {
			System.out.println("[" + (i + 1) + "] = ");
			Double data = dim.nextDouble();
			vectorApostProb[i] = data;
			if (i != dimProb-1) {
				vector += data + ", ";
			} else {
				vector += data;
			}
		}
		vector += "}";

		dataCalculate.append(vector + "\r\n\r\n");

		return vectorApostProb;
	}

	private Double[][] joinProbCalculate(int dimProb, Double[][] matrixApriProb, Double[] vectorApostProb) {
		Double[][] joinProb = new Double[dimProb + 1][dimProb + 1];

		dataCalculate.append("Probabilidad Conjunta\r\n");
		double sum = 0.0;
		for (int i = 0; i < dimProb; i++) {
			for (int j = 0; j < dimProb; j++) {
				joinProb[i][j] = Math.round((matrixApriProb[i][j] * vectorApostProb[i]) * 100.0) / 100.0;
				dataCalculate.append("P(X = X" + i + ",Y = Y" + j + ") = P(Y = Y" + j + "|X = X" + i + "P(X = X" + i
						+ " = " + Math.round((matrixApriProb[i][j]) * 100.0) / 100.0 + " * "
						+ Math.round((vectorApostProb[i]) * 100.0) / 100.0 + " = " + joinProb[i][j] + "\r\n");
				sum += joinProb[i][j];
			}
		}

		dataCalculate.append("Sumatoria de probabilidad conjunta = " + sum + "\r\n\r\n");
		return joinProb;
	}

	private Double[] marginalProbCalculate(int dimProb, Double[][] joinProb) {
		Double[] marginalProb = new Double[dimProb + 1];
		dataCalculate.append("Probabilidad Marginal\r\n");

		Double sum = 0.0;
		for (int i = 0; i < dimProb; i++) {
			Double sumProbJoin = 0.0;
			dataCalculate.append("P(Y = Y" + i + ")\r\n");
			for (int j = 0; j < dimProb; j++) {
				sumProbJoin += joinProb[j][i];
				sum += joinProb[j][i];
				dataCalculate.append("P(X = X" + j + ", Y = Y" + i + ") = " + joinProb[j][i] + "\r\n");
			}
			dataCalculate.append("P(Y = Y" + i + ") = " + sumProbJoin + "\r\n\r\n");

			marginalProb[i] = sumProbJoin;
		}
		dataCalculate.append("Sumatoria de probabilidad marginal = " + sum + "\r\n\r\n");
		return marginalProb;
	}

	private double dataHyCalculate(int dimProb, Double[] marginalProb) {
		double sumHy = 0.0;
		System.out.println("dataHyCalculate");
		dataCalculate.append("Calculo de H(y)\r\n");
		for (int i = 0; i < dimProb; i++) {
			dataCalculate.append(marginalProb[i] + " * Log2(1/" + marginalProb[i] + ") = "
					+ marginalProb[i] * (Math.log(1 / marginalProb[i]) / Math.log(2)) + "\r\n");
			sumHy += marginalProb[i] * (Math.log(1 / marginalProb[i]) / Math.log(2));
		}

		sumHy = Math.round(sumHy * 100.0) / 100.0;
		dataCalculate.append("Sumatoria de Hy = " + sumHy + "\r\n\r\n");
		return sumHy;
	}

	private double dataHy_HyhCalculate(int dimProb, Double[][] matrixApriProb, Double[][] joinProb) {
		double sumHy_Hyh = 0.0;

		dataCalculate.append("Calculo de H(y|h)\r\n");
		for (int i = 0; i < dimProb; i++) {
			for (int j = 0; j < dimProb; j++) {
				dataCalculate.append(joinProb[i][j] + " * Log2(1/" + matrixApriProb[i][j] + ") = "
						+ joinProb[i][j] * (Math.log(1 / matrixApriProb[i][j]) / Math.log(2)) + "\r\n");
				sumHy_Hyh += joinProb[i][j] * (Math.log(1 / matrixApriProb[i][j]) / Math.log(2));
			}
		}

		sumHy_Hyh = Math.round(sumHy_Hyh * 100.0) / 100.0;
		dataCalculate.append("Sumatoria de H(y|h) = " + sumHy_Hyh);
		return sumHy_Hyh;
	}
}
