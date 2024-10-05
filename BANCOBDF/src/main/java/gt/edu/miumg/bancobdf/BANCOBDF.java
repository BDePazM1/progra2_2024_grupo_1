/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package gt.edu.miumg.bancobdf;

import static gt.edu.miumg.bd.menus.menuDirectiva;
import static gt.edu.miumg.bd.menus.menuUsuarios;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Freddy Elias
 */
public class BANCOBDF {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            // Mostrar el menú
            System.out.println("----- Menú -----");
            System.out.println("1. Menu de Usuarios");
            System.out.println("2. Menu de Directiva");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = scanner.nextInt();
                
                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la Opción 1.");
                        menuUsuarios();
                        break;
                    case 2:
                        System.out.println("Has seleccionado la Opción 2.");
                        menuDirectiva();
                        break;
                    case 3:
                        System.out.println("Has seleccionado la Opción 3.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                scanner.next(); 
            }
        } while (opcion != 3);

        scanner.close();

    }
}
