import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Resistores {

    private static final Map<String, String> colorCode = new HashMap<>();
    private static final Map<String, Integer> multipliers = new HashMap<>();

    static {
        colorCode.put("0", "preto");
        colorCode.put("1", "marrom");
        colorCode.put("2", "vermelho");
        colorCode.put("3", "laranja");
        colorCode.put("4", "amarelo");
        colorCode.put("5", "verde");
        colorCode.put("6", "azul");
        colorCode.put("7", "violeta");
        colorCode.put("8", "cinza");
        colorCode.put("9", "branco");


        multipliers.put("", 0);
        multipliers.put("k", 3);
        multipliers.put("M", 6);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String continuar = "S";


        while (continuar.equalsIgnoreCase("S")) {
            System.out.println("Digite o valor do resistor (ou pressione ENTER para sair):");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                break;
            }

            try {
                String result = convertToColorCode(input);
                System.out.println("Codificação de cores: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("Valor inválido: " + e.getMessage());
            }

            System.out.print("Tente novamente? (S/N): ");
            continuar = scanner.nextLine();

            while (!continuar.equalsIgnoreCase("S") && !continuar.equalsIgnoreCase("N")) {
                System.out.print("Valor inválida. Tente novamente? (S/N): ");
                continuar = scanner.nextLine();
            }

            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        System.out.println("Programa finalizado. Obrigado!");
        scanner.close();
    }

    private static String convertToColorCode(String input) {
        if (!input.toLowerCase().endsWith(" ohms")) {
            throw new IllegalArgumentException("O valor deve terminar com ' ohms'");
        }

        String value = input.substring(0, input.length() - 5).trim();
        String multiplier = "";

        if (value.endsWith("k") || value.endsWith("M")) {
            multiplier = value.substring(value.length() - 1);
            value = value.substring(0, value.length() - 1);
        }

        double numericValue;
        try {
            numericValue = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Número inválido: " + value);
        }

        int exponent = multipliers.getOrDefault(multiplier, 0);

        while (numericValue >= 100) {
            numericValue /= 10;
            exponent++;
        }

        String valueString = String.format("%.0f", numericValue);
        if (valueString.length() == 1) {
            valueString = "0" + valueString;
        }

        String firstColor = colorCode.get(valueString.charAt(0) + "");
        String secondColor = colorCode.get(valueString.charAt(1) + "");
        String thirdColor;
        String fourthColor = "dourado";

        if (multiplier.equals("k") || multiplier.equals("M")) {
            firstColor = colorCode.get(valueString.charAt(1) + "");
            secondColor = colorCode.get(valueString.charAt(0) + "");
        }

        switch (multiplier) {
            case "k":
                thirdColor = "vermelho";
                break;
            case "M":
                thirdColor = "verde";
                break;
            default:
                thirdColor = colorCode.get(String.valueOf(exponent));
        }

        return String.format("%s %s %s %s", firstColor, secondColor, thirdColor, fourthColor);
    }
}
