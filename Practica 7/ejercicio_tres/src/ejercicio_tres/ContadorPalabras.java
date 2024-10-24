/*************************************************************************************************
ARCHIVO		: ContadorPalabras.JAVA
DESCRIPCIÓN	: Programa que permite seleccionar archivo y contar las palabras contenidas.
*************************************************************************************************/
package ejercicio_tres;

import java.io.*;
import java.util.*;

public class ContadorPalabras {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la ruta del archivo: ");
        String rutaArchivo = scanner.nextLine();

        try {
            File archivo = new File(rutaArchivo);
            BufferedReader lector = new BufferedReader(new FileReader(archivo));
            String linea;
            int totalLineas = 0, totalPalabras = 0, totalCaracteres = 0;
            Map<String, Integer> frecuenciaPalabras = new HashMap<>();

            while ((linea = lector.readLine()) != null) {
                totalLineas++;
                String[] palabras = linea.split("\\s+");
                totalPalabras += palabras.length;
                for (String palabra : palabras) {
                    totalCaracteres += palabra.length();
                    palabra = palabra.toLowerCase();
                    frecuenciaPalabras.put(palabra, frecuenciaPalabras.getOrDefault(palabra, 0) + 1);
                }
            }
            lector.close();

            // Resultados
            System.out.println("Total de líneas: " + totalLineas);
            System.out.println("Total de palabras: " + totalPalabras);
            System.out.println("Total de caracteres: " + totalCaracteres);
            System.out.println("Promedio de palabras por línea: " + (double) totalPalabras / totalLineas);

            // Palabras más frecuentes
            System.out.println("Palabras más frecuentes:");
            frecuenciaPalabras.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .limit(5).forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
