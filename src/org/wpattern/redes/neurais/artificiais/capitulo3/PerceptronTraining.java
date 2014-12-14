package org.wpattern.redes.neurais.artificiais.capitulo3;

import java.util.Random;

/**
 * Algoritmo implementado tendo como base o pseudocodigo apresentado no
 * livro "Redes Neurais Artificiais para engenharia e ciencias aplicadas"
 * do autor Ivan Nunes da Silva.
 * 
 * Capitulo 3.4.
 * 
 * @author Augusto Branquinho
 */
public class PerceptronTraining {

	public static void main(String[] args) {
		Random random = new Random(System.currentTimeMillis());

		// 1. Criando o conjunto de amostra de testes. x^(k)
		double[][] amostras = new double[][] {
				{-1.0, -1.0, -1.0, -1.0 },
				{ 0.1,  0.3,  0.6,  0.5 },
				{ 0.4,  0.7,  0.9,  0.7 },
				{ 0.7,  0.2,  0.8,  0.1 }};

		// 2. Saida desejada para cada amostra. d^(k)
		double[] saidas = new double[] { 1.0, -1.0, -1.0, 1.0 };

		// 3. Realizar o sorteio dos pesos aleatoriamente.
		double[] pesos = new double[saidas.length];

		for (int i = 0; i < pesos.length; i++) {
			pesos[i] = 1.0 / (random.nextInt(100) + 1.0);
			System.out.println(String.format("Peso inicial %s = %s", i, pesos[i]));
		}

		// 4. Especificando a taxa de aprendizagem.
		double taxaAprendizado = 0.05;

		// 5. Iniciando o contador de epocas.
		int epoca = 0;

		// 6. Repetir as instrucoes enquanto existir erro.
		String erro;

		do {
			// 6.1. Nao existe erro.
			erro = "inexiste";

			// 6.2. Para todas as amostras de treinamento {x^(k), d^(k)}.
			for (int k = 0; k < amostras.length; k++) {
				// 6.2.1. Multiplicar o peso por uma dada amostra.
				// Pode ser usado um vetor para guardar os resultados.
				// Contudo, achei mais facil ja fazer a soma diretamente.
				double u = 0.0;

				// A inversao das variaveis i e k corresponde a transposta da matriz.
				for (int i = 0; i < pesos.length; i++) {
					u += pesos[i] * amostras[i][k];
				}

				// 6.2.2. Verificar o sinal de saida.
				int y = sinal(u);

				// 6.2.3. Verificando o sinal treinado e o valor desejado da amostra.
				if (y != saidas[k]) {
					// 6.2.3.1. É realizado o ajuste de pesos e marcamos que existe erro.
					double aprendizado = taxaAprendizado * (saidas[k] - y);
					for (int i = 0; i < pesos.length; i++) {
						pesos[i] = pesos[i] + aprendizado * amostras[i][k];
					}

					erro = "existe";
				}
			}

			// 6.3. Incremento da epoca.
			epoca++;
		} while (erro.equals("existe"));

		String formatoVetor = "{ ";
		System.out.println();
		System.out.println("Numero de epocas " + epoca);
		for (int i = 0; i < pesos.length; i++) {
			System.out.println(String.format("Peso final %s = %s", i, pesos[i]));
			formatoVetor += pesos[i] + (((i + 1) != pesos.length) ? ", " : "");
		}
		formatoVetor += " }";
		System.out.println(formatoVetor);
	}

	private static int sinal(double u) {
		return (u >= 0.0) ? 1 : -1;
	}

}
