/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.programa2_2024_grupo_1;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author OVANDO
 */
public class Programa2_2024_grupo_1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("----- Menú -----");
            System.out.println("1. Cuentas.");
            System.out.println("2. Transacciones.");
            System.out.println("3. Tarjeta.");
            System.out.println("4. Opción 4.");
            System.out.println("5. Salir.");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Ha seleccionado la opción Cuentas.");
                        break;
                    case 2:
                        System.out.println("Ha seleccionado la opción Transacciones.");
                        break;
                    case 3:
                        System.out.println("Ha seleccionado la opción Tarjeta.");
                        break;
                    case 4:
                        System.out.println("Ha seleccionado la opción 4.");
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtelo de nuevo.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.next(); 
            }
        } while (opcion != 4);
        scanner.close();
    }
   }

