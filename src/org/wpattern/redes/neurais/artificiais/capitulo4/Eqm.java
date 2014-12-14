package org.wpattern.redes.neurais.artificiais.capitulo4;

/**
 * Algoritmo implementado tendo como base o pseudocodigo apresentado no
 * livro "Redes Neurais Artificiais para engenharia e ciencias aplicadas"
 * do autor Ivan Nunes da Silva.
 * 
 * Capitulo 4.3.
 * 
 * @author Augusto Branquinho
 */
public class Eqm {

	public static double calculateEQM(double[][] amostras, double[] saidas, double[] pesos) {
		// 1. Obtendo a quantidade de padroes de treinamento.
		int quantidadePadroes = saidas.length;

		// 2. Inicializando a variavel EQM.
		double eqm = 0.0;

		// 3. Para cada amostra de treinamento realizamos o calculo
		// de "u" e posteriormente o calculo do somatorio do EQM.
		for (int k = 0; k < quantidadePadroes; k++) {
			// 3.1. Multiplicar o peso por uma dada amostra.
			// Pode ser usado um vetor para guardar os resultados.
			// Contudo, achei mais facil ja fazer a soma diretamente.
			double u = 0.0;

			for (int linha = 0; linha < amostras.length; linha++) {
				u += pesos[linha] * amostras[linha][k];
			}

			// 3.2. Realizado o calculo do EQM.
			eqm += Math.pow(saidas[k] - u, 2.0);
		}

		// 3. Calculado da media do EQM.
		double eqmMedio = eqm / quantidadePadroes;

		return eqmMedio;
	}

}
