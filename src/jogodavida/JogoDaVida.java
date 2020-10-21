package jogodavida;

import java.io.IOException;
import java.util.Scanner;

public class JogoDaVida {

	public static void main(String[] args) throws IOException {

		int matrizEntrada[][] = new int[5][5];
		int matrizSaida[][] = new int[5][5];
		int soma = 0;
		int loop = 1;
		boolean continua;
		Scanner ler = new Scanner(System.in);
		matrizEntrada[1][1] = 1;
		matrizEntrada[1][3] = 1;
		matrizEntrada[2][2] = 1;
		matrizEntrada[2][3] = 1;
		matrizEntrada[3][2] = 1;
		System.out.println("MATRIZ DE ENTRADA");
		imprimeMatriz(matrizEntrada);

		do {

			System.out.println("JOGADA " + loop);

			for (int l = 0; l < matrizEntrada.length; l++) {
				// System.out.println("primeiro" + "valor de l é " + l + "");
				for (int c = 0; c < matrizEntrada.length; c++) {
					// System.out.println("segundo" + "valor de l é " + l + "valor de c é "+ c);
					if (matrizEntrada[l][c] == 1) {// celula viva
						// System.out.println("terceiro");
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
						// System.out.println("quarto" + "valor de l = " + l + "valor de c é = " + c);
						soma = verificaBordaSoma(matrizEntrada, l, c);
						// System.out.println("quinto");
						if (soma == 3) {
							// System.out.println("sexto");
							matrizSaida[l][c] = 1;
						}

					}
				}
			}
			imprimeMatriz(matrizSaida);
			trocaMatriz(matrizEntrada, matrizSaida);
			loop++;
			char c = (char) System.in.read();
			
			if(c == ' ') {
				continua = false;
				
			}else {
				continua = true;
				
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
		// torquei mat por matriz
		// e troquei m por l e n por c
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
