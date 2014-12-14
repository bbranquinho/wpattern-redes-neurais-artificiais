package org.wpattern.redes.neurais.artificiais.capitulo4;

import java.util.Random;

/**
 * Algoritmo implementado tendo como base o pseudocodigo apresentado no
 * livro "Redes Neurais Artificiais para engenharia e ciencias aplicadas"
 * do autor Ivan Nunes da Silva.
 * 
 * Capitulo 4.3.
 * 
 * @author Augusto Branquinho
 */
public class AdalineTraining {

	public static void main(String[] args) {
		AdalineTraining treinamento = new AdalineTraining();

		double[][] amostras = new double[][] {
				{-1.0, -1.0, -1.0, -1.0 },
				{ 0.1,  0.3,  0.6,  0.5 },
				{ 0.4,  0.7,  0.9,  0.7 },
				{ 0.7,  0.2,  0.8,  0.1 }};

		double[] saidas = new double[] { 1.0, -1.0, -1.0, 1.0 };

		double[] pesos = treinamento.executarFaseTreinamento(amostras, saidas);

		String formatoVetor = "{ ";
		for (int i = 0; i < pesos.length; i++) {
			System.out.println(String.format("Peso final %s = %s", i, pesos[i]));
			formatoVetor += pesos[i] + (((i + 1) != pesos.length) ? ", " : "");
		}
		formatoVetor += " }";
		System.out.println(formatoVetor);
	}

	public double[] executarFaseTreinamento(double[][] amostras, double[] saidas) {
		Random random = new Random(System.currentTimeMillis());

		// 1. Conjunto de amostras de testes dadas como entrada. x^(k)

		// 2. Saidas das amostras dadas como entrada.

		// 3. Realizar o sorteio dos pesos aleatoriamente.
		double[] pesos = new double[saidas.length];

		for (int i = 0; i < pesos.length; i++) {
			pesos[i] = 1.0 / (random.nextInt(100) + 1.0);
		}

		// 4. Especificando a taxa de aprendizagem e precisao requerida.
		double taxaAprendizado = 0.05;
		double precisao = 0.0000;

		// 5. Iniciando o contador de epocas.
		int epoca = 0;

		// 6. Repetir as instrucoes equanto o erro estiver fora da precisao requerida.
		double eqmAnterior, eqmAtual;

		do {
			// 6.1. Calcular o EQM com os pesos atuais.
			eqmAnterior = Eqm.calculateEQM(amostras, saidas, pesos);

			// 6.2. Para todas as amostras de treinamento {x^(k), d^(k)}.
			for (int k = 0; k < amostras.length; k++) {
				// 6.2.1. Multiplicar o peso por uma dada amostra.
				// Pode ser usado um vetor para guardar os resultados e depois realizar a soma.
				// Contudo, achei mais facil ja fazer a soma diretamente.
				double u = 0.0;

				// A inversao das variaveis i e k corresponde a transposta da matriz.
				for (int i = 0; i < pesos.length; i++) {
					u += pesos[i] * amostras[i][k];
				}

				// 6.2.2. É realizado o ajuste de pesos e marcamos que existe erro.
				for (int i = 0; i < pesos.length; i++) {
					pesos[i] = pesos[i] + taxaAprendizado * (saidas[k] - u) * amostras[i][k];
				}
			}

			// 6.3. Incremento da epoca.
			epoca++;

			// 6.4. Calculamos oo EQM dos pesos atuais.
			eqmAtual = Eqm.calculateEQM(amostras, saidas, pesos);
		} while (Math.abs(eqmAtual - eqmAnterior) > precisao);

		System.out.println("Epoca " + epoca);

		return pesos;
	}

}
