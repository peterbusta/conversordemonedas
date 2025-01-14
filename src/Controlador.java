import java.util.Scanner;

public class Controlador {
    public void ejecucionPrograma() {
        System.out.println("Bienvenido al conversor de monedas");
        Scanner lectura = new Scanner(System.in);
        Servicio servicio = new Servicio();

        while (true) {
            // Menú para seleccionar la moneda de origen
            System.out.println("\n\u250C" + "\u2500".repeat(41) + "\u2510");
            System.out.println("\u2502 Seleccione la moneda de origen:         \u2502");
            System.out.println("\u2502 1) Dólar estadounidense (USD)           \u2502");
            System.out.println("\u2502 2) Euro (EUR)                           \u2502");
            System.out.println("\u2502 3) Peso argentino (ARS)                 \u2502");
            System.out.println("\u2502 4) Real brasileño (BRL)                 \u2502");
            System.out.println("\u2502 5) Peso chileno (CLP)                   \u2502");
            System.out.println("\u2502 6) Peso uruguayo (UYU)                  \u2502");
            System.out.println("\u2502 7) Guaraní paraguayo (PYG)              \u2502");
            System.out.println("\u2502 8) Boliviano (BOB)                      \u2502");
            System.out.println("\u2502 9) Sol peruano (PEN)                    \u2502");
            System.out.println("\u2502 0) Salir                                \u2502");
            System.out.println("\u2514" + "\u2500".repeat(41) + "\u2518\n");

            try {
                var opcionOrigen = Integer.valueOf(lectura.nextLine());

                if (opcionOrigen < 0 || opcionOrigen > 9) {
                    System.out.println("\nPor favor ingrese una opción válida");
                    continue;
                } else if (opcionOrigen == 0) {
                    System.out.println("\nGracias por utilizar el programa. ¡Adiós!");
                    return;
                }

                String monedaOrigen = obtenerCodigoMoneda(opcionOrigen);

                // Menú para seleccionar la moneda de destino
                System.out.println("\n\u250C" + "\u2500".repeat(41) + "\u2510");
                System.out.println("\u2502 Seleccione la moneda de destino:        \u2502");
                System.out.println("\u2502 1) Dólar estadounidense (USD)           \u2502");
                System.out.println("\u2502 2) Euro (EUR)                           \u2502");
                System.out.println("\u2502 3) Peso argentino (ARS)                 \u2502");
                System.out.println("\u2502 4) Real brasileño (BRL)                 \u2502");
                System.out.println("\u2502 5) Peso chileno (CLP)                   \u2502");
                System.out.println("\u2502 6) Peso uruguayo (UYU)                  \u2502");
                System.out.println("\u2502 7) Guaraní paraguayo (PYG)              \u2502");
                System.out.println("\u2502 8) Boliviano (BOB)                      \u2502");
                System.out.println("\u2502 9) Sol peruano (PEN)                    \u2502");
                System.out.println("\u2502 0) Salir                                \u2502");
                System.out.println("\u2514" + "\u2500".repeat(41) + "\u2518\n");

                var opcionDestino = Integer.valueOf(lectura.nextLine());

                if (opcionDestino < 0 || opcionDestino > 9) {
                    System.out.println("\nPor favor ingrese una opción válida");
                    continue;
                } else if (opcionDestino == 0) {
                    System.out.println("\nGracias por utilizar el programa. ¡Adiós!");
                    return;
                }

                String monedaDestino = obtenerCodigoMoneda(opcionDestino);

                if (monedaOrigen.equals(monedaDestino)) {
                    System.out.println("\nLa moneda de origen y destino no pueden ser iguales. Intente nuevamente.");
                    continue;
                }

                // Solicitar valor a convertir
                System.out.println("\nIngrese el valor a convertir\n");
                var valorOrigen = Float.valueOf(lectura.nextLine());
                Respuesta respuesta = servicio.consultaApi(monedaOrigen, monedaDestino, valorOrigen);

                if (respuesta != null) {
                    System.out.printf(
                            "\nEl valor de %.2f [%s], con una tasa de conversión del %.2f, corresponde al valor final de %.2f [%s]\n",
                            valorOrigen, respuesta.base_code(), respuesta.conversion_rate(), respuesta.conversion_result(),
                            respuesta.target_code()
                    );
                }
            } catch (Exception e) {
                System.out.println("\nSe produjo un error: " + e.getMessage());
            }
        }
    }

    private String obtenerCodigoMoneda(int opcion) {
        return switch (opcion) {
            case 1 -> "USD";
            case 2 -> "EUR";
            case 3 -> "ARS";
            case 4 -> "BRL";
            case 5 -> "CLP";
            case 6 -> "UYU";
            case 7 -> "PYG";
            case 8 -> "BOB";
            case 9 -> "PEN";
            default -> throw new IllegalArgumentException("Opción inválida");
        };
    }
}
