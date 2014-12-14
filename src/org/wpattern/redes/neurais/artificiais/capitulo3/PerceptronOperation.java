package org.wpattern.redes.neurais.artificiais.capitulo3;

/**
 * Algoritmo implementado tendo como base o pseudocodigo apresentado no
 * livro "Redes Neurais Artificiais para engenharia e ciencias aplicadas"
 * do autor Ivan Nunes da Silva.
 * 
 * Capitulo 3.4.
 * 
 * @author Augusto Branquinho
 */
public class PerceptronOperation {

	public static void main(String[] args) {
		// 1. Amostra a ser classificada (Exemplo) (x).
		//double[] amostra = new double[] { -1.0, 0.1, 0.4, 0.7 }; // Classe A (1).
		//double[] amostra = new double[] { -1.0, 0.3, 0.7, 0.2 }; // Classe B (-1).
		//double[] amostra = new double[] { -1.0, 0.6, 0.9, 0.8 }; // Classe B (-1).
		double[] amostra = new double[] { -1.0, 0.5, 0.7, 0.1 }; // Classe A (1).

		// 2. Valores ajustados de pesos encontrados no treinamento (Exemplo) (w).
		double[] pesos = new double[] {-1.89043,6.29312,-7.26876,0.68572};

		// 3. Executar as seguintes instrucoes.
		// 3.1. Multiplicar os pesos pela amostra e resultar com a soma (u).
		double u = 0.0;
		for (int k = 0; k < amostra.length; k++) {
			u += pesos[k] * amostra[k];
		}

		// 3.2. Verificar o sinal.
		int y = sinal(u);

		// 3.3. Verificando a classe da amostra.
		if (y == -1) {
			System.out.println("Amostra da classe A (-1).");
		} else if (y == 1) {
			System.out.println("Amostra da classe B (1).");
		}
	}

	private static int sinal(double u) {
		return (u >= 0.0) ? 1 : -1;
	}

}
