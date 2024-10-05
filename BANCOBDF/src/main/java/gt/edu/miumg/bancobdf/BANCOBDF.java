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

        int opcionM = 0;

        do {
            Scanner scannerM = new Scanner(System.in);
            
            System.out.println("----- Menú -----------");
            System.out.println("1. Menu de Usuarios---");
            System.out.println("2. Menu de Directiva--");
            System.out.println("3. Salir--------------");
            System.out.println("Selecciona una opción:");

            try {
                
                opcionM = scannerM.nextInt();
                
                switch (opcionM) {
                    case 1:
                        System.out.println("Has seleccionado la Opción 1.");
                        menuUsuarios();
                        break;
                    case 2:
                        System.out.println("Has seleccionado la Opción 2.");
                        menuDirectiva();
                        break;
                    case 3:
                        System.out.println("Que tengas un Buen Día");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                scannerM.next(); 
            }
        } while (opcionM != 3);



    }
}