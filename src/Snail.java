import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Snail {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String continuar = "S";


        while (continuar.equalsIgnoreCase("S")) {

            System.out.println("Digite o tamanho da matriz (n x n): ");
            int n = entradaValida(scanner, "Tamanho da matriz: ");

            int[][] matriz = new int[n][n];

            System.out.println("Digite os números da matriz, linha por linha: ");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.printf("Elemento [%d][%d]: ", i + 1, j + 1);
                    matriz[i][j] = entradaValida(scanner, "");
                }
            }

            System.out.println("\nMatriz fornecida:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(matriz[i][j] + "\t");
                }
                System.out.println();
            }

            System.out.println("\nOrdem espiral dos elementos:");
            System.out.println(percorrerMatrizEspiral(matriz));

            System.out.print("Tente novamente? (S/N): ");
            continuar = scanner.next();

            while (!continuar.equalsIgnoreCase("S") && !continuar.equalsIgnoreCase("N")) {
                System.out.print("Valor inválido. Tente novamente? (S/N): ");
                continuar = scanner.next();
            }
        }

        System.out.println("Sistema encerrado. Obrigado!");
        scanner.close();
    }

    private static int entradaValida(Scanner scanner, String mensagem) {
        int valor = -1;
        boolean entradaValida = false;
        while (!entradaValida) {
            System.out.print(mensagem);
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                entradaValida = true;
            } else {
                System.out.println("Valor inválido. Por favor, insira um número inteiro.");
                scanner.next();
            }
        }
        return valor;
    }

    public static List<Integer> percorrerMatrizEspiral(int[][] matriz) {
        List<Integer> resultado = new ArrayList<>();

        if (matriz == null || matriz.length == 0) {
            return resultado;
        }

        int topo = 0, base = matriz.length - 1;
        int esquerda = 0, direita = matriz[0].length - 1;

        while (topo <= base && esquerda <= direita) {
            for (int i = esquerda; i <= direita; i++) {
                resultado.add(matriz[topo][i]);
            }
            topo++;

            for (int i = topo; i <= base; i++) {
                resultado.add(matriz[i][direita]);
            }
            direita--;

            if (topo <= base) {
                for (int i = direita; i >= esquerda; i--) {
                    resultado.add(matriz[base][i]);
                }
                base--;
            }

            if (esquerda <= direita) {

                for (int i = base; i >= topo; i--) {
                    resultado.add(matriz[i][esquerda]);
                }
                esquerda++;
            }
        }

        return resultado;
    }

}
