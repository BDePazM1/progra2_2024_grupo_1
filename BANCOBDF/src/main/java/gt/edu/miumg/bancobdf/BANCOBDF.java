package gt.edu.miumg.bancobdf;

import gt.edu.miumg.bd.menus;
import static gt.edu.miumg.bd.menus.menuDirectiva;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Freddy Elias
 */

public class BANCOBDF {
    public static void main(String[] args) {
        
        Scanner scanner1 = new Scanner(System.in);
        int opcion1 = 0;

        do {
            
            System.out.println("Menú------------------");
            System.out.println("1. Menu de Usuarios---");
            System.out.println("2. Menu de Directiva--");
            System.out.println("3. Salir--------------");
            System.out.println("Selecciona una opción:");

            try {
                
                opcion1 = scanner1.nextInt();
                
                switch (opcion1) {
                    case 1:
                        System.out.println("Has seleccionado la Opción 1.");
                        boolean volverAlMenuPrincipal;
                        
                        do {
                            
                            volverAlMenuPrincipal = menus.menuUsuario(scanner1);
                        } while (!volverAlMenuPrincipal);

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
                scanner1.next(); 
            }
        } while (opcion1 != 3);



    }
}