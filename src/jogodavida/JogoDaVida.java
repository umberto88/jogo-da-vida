package jogodavida;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class JogoDaVida {

	public static void main(String[] args) {
		int linhaMax = 100;
		int colunaMax = 100;
		int matrizEntrada[][] = new int[linhaMax][colunaMax];
		int matrizSaida[][] = new int[linhaMax][colunaMax];
		int soma = 0;
		int loop = 1;
		boolean continua;

		for (int linha = 0; linha < matrizSaida.length; linha++) {

			for (int coluna = 0; coluna < matrizSaida.length; coluna++) {
				int inserirValor = JOptionPane.showConfirmDialog(null, "Deseja inserir valores?");

				if (inserirValor == 0) {

					String posicaoLinha = JOptionPane.showInputDialog("entre com a posição da linha, posição ate " + linhaMax);
					int l = Integer.valueOf(posicaoLinha)-1;
					
					if (l < 0 || l >= linhaMax) {
						do {
							System.out.println("limite de linha estourado\n digite um numero menor");
							posicaoLinha = JOptionPane.showInputDialog("entre com a posição da linha, posição ate " + linhaMax);
							l = Integer.valueOf(posicaoLinha)-1;
						} while (l < 0 || l >= linhaMax );
						
					}
					
					String posicaoColuna = JOptionPane.showInputDialog("entre com a posição da coluna, posição ate " + colunaMax);
					int c = Integer.valueOf(posicaoColuna)-1;
					if ( c < 0 || c >= colunaMax) {
						do {
							System.out.println("limite de linha estourado\n digite um numero menor");
							posicaoColuna = JOptionPane.showInputDialog("entre com a posição da coluna, posição ate " + colunaMax);
							c = Integer.valueOf(posicaoColuna)-1;
						} while (c < 0 || c >= colunaMax );
						
					}

					matrizEntrada[l][c] = 1;
					// imprimeMatriz(matrizEntrada);
				} else if (inserirValor == 1) {
					System.out.println("valores adicionados");
					linha = matrizSaida.length; // se o valor for 1 entao ele sai do for
					coluna = matrizSaida.length;
					// break;

				}

			}
		}

		System.out.println("MATRIZ DE ENTRADA");

		imprimeMatriz(matrizEntrada);

		do {

			System.out.println("JOGADA " + loop);

			for (int l = 0; l < matrizEntrada.length; l++) {
				for (int c = 0; c < matrizEntrada.length; c++) {
					if (matrizEntrada[l][c] == 1) {// celula viva
						soma = verificaBordaSoma(matrizEntrada, l, c);
						// System.out.println(soma);
						if (soma == 2 || soma == 3) {
							matrizSaida[l][c] = 1;
						} else if (soma > 3) {
							matrizSaida[l][c] = 0;
						} else if (soma < 2) {
							matrizSaida[l][c] = 0;
						}
						soma = 0;

						// imprimeMatriz(matrizA);
						// System.out.println("NOVA JOGADA");
					}
					if (matrizEntrada[l][c] == 0) { // celula morta
						soma = verificaBordaSoma(matrizEntrada, l, c);
						if (soma == 3) {
							matrizSaida[l][c] = 1;
						}

					}
				}
			}
			imprimeMatriz(matrizSaida);
			trocaMatriz(matrizEntrada, matrizSaida);
			loop++;
			int cancelar = JOptionPane.showConfirmDialog(null, "Deseja fazer a proxima jogada?");

			if (cancelar == 0) {

				// char c = (char) System.in.read();

				continua = true;
				// continua = false;

			} else {
				continua = false;
				// continua = true;

			}
		} while (continua);
	}

	private static void trocaMatriz(int[][] matrizEntrada, int[][] matrizSaida) {
		for (int i = 0; i < matrizSaida.length; i++) {
			for (int j = 0; j < matrizSaida.length; j++) {
				matrizEntrada[i][j] = matrizSaida[i][j];// jogando a saida na matriz de entrada
			}
		}
		matrizSaida = new int[matrizSaida.length][matrizSaida.length];
	}

	private static void imprimeMatriz(int[][] matriz) {
		// TODO: trocar os nomes mat n e m
		for (int l = 0; l < matriz.length; l++) {
			for (int c = 0; c < matriz.length; c++) {
				System.out.print(matriz[l][c] + " ");
			}
			System.out.println();
		}
	}

	private static int verificaBordaSoma(int[][] matriz, int l, int c) {
		int soma = 0;
		int tamanhoMatriz = matriz.length - 1;
		if (l == 0 && c == 0) {
			// não olha pra cima e nem para a esquerda

			soma = soma + matriz[l + 1][c] + matriz[l][c + 1] + matriz[l + 1][c + 1];
		} else if (l == 0 && c == tamanhoMatriz) {
			// nao olha p cima e p direita
			soma = soma + matriz[l + 1][c] + matriz[l][c - 1] + matriz[l + 1][c - 1];
		} else if (l == tamanhoMatriz && c == 0) {
			// não olha p baixo e p esquerda
			soma = soma + matriz[l - 1][c] + matriz[l][c + 1] + matriz[l - 1][c + 1];

		} else if (l == tamanhoMatriz && c == tamanhoMatriz) {
			// nao olha p baixo e nem p direita
			soma = soma + matriz[l - 1][c] + matriz[l][c - 1] + matriz[l - 1][c - 1];
		} else if (l == 0 && (c > 0 && c < tamanhoMatriz)) {
			// só nao pode olhar p cima
			soma = soma + matriz[l + 1][c] + matriz[l][c - 1] + matriz[l][c + 1] + matriz[l + 1][c - 1]
					+ matriz[l + 1][c + 1];
		} else if (l == tamanhoMatriz && (c > 0 && c < tamanhoMatriz)) {
			// só nao olha p baixo
			soma = soma + matriz[l - 1][c] + matriz[l][c - 1] + matriz[l][c + 1] + matriz[l - 1][c - 1]
					+ matriz[l - 1][c + 1];
		} else if ((l > 0 && l < tamanhoMatriz) && c == 0) {
			// só nao pode olhar esquerda
			soma = soma + matriz[l - 1][c] + matriz[l + 1][c] + matriz[l][c + 1] + matriz[l - 1][c + 1]
					+ matriz[l + 1][c + 1];
		} else if ((l > 0 && l < tamanhoMatriz) && c == tamanhoMatriz) {
			// só nao pode olhar direita
			soma = soma + matriz[l - 1][c] + matriz[l + 1][c] + matriz[l][c - 1] + matriz[l - 1][c - 1]
					+ matriz[l + 1][c - 1];
		} else {
			// pode olhar p qualquer lugar
			soma = soma + matriz[l - 1][c] + matriz[l + 1][c] + matriz[l][c - 1] + matriz[l][c + 1]
					+ matriz[l - 1][c - 1] + matriz[l - 1][c + 1] + matriz[l + 1][c - 1] + matriz[l + 1][c + 1];
		}
		return soma;
	}
}
