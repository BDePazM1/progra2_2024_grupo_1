/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.bd;

import static gt.edu.miumg.bd.crudClientes.editarInformacion;
import static gt.edu.miumg.bd.crudClientes.eliminarInformacion;
import static gt.edu.miumg.bd.crudClientes.mostrarInformacion;
import static gt.edu.miumg.bd.crudClientes.registrarClientes;
import static gt.edu.miumg.bd.crudCuentas.creacionCuentas;
import static gt.edu.miumg.bd.crudPrestamo.agregacionDinero;
import static gt.edu.miumg.bd.crudTransaccion.traspasoDinero;
import static gt.edu.miumg.bd.resp.mostrarMenuTarjetas;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Freddy Elias
 */
public class menus {

    public static void menuUsuarios() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("----- Menú -----");
            System.out.println("1. Interactuar con Cuentas Bancarias");
            System.out.println("2. Interactuar con Tarjetas: credito o debito");
            System.out.println("3. Realizar una transaccion");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la Opción 1.");
                        creacionCuentas();
                        break;
                    case 2:
                        System.out.println("Has seleccionado la Opción 2.");
                        mostrarMenuTarjetas();
                        break;
                    case 3:
                        System.out.println("Has seleccionado la Opción 3.");
                        traspasoDinero();
                        break;
                    case 4:
                        System.out.println("Vuelva pronto");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                scanner.next();
            }
        } while (opcion != 4);

        scanner.close();

    }

    public static void menuDirectiva() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("----- Menú -----");
            System.out.println("1. Interactuar con Cuentas Tabla Clientes");
            System.out.println("2. Interactuar con Prestamos");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la Opción 1.");
                        do {
                            System.out.println("----- Menú -----");
                            System.out.println("1. Crear un nuevo usuario");
                            System.out.println("2. Mostrar los datos ingresados");
                            System.out.println("3. Para editar datos");
                            System.out.println("4. Para eliminar datos");
                            System.out.println("5. Salir");
                            System.out.print("Selecciona una opción: ");

                            try {
                                opcion = scanner.nextInt();

                                switch (opcion) {
                                    case 1:
                                        System.out.println("Has seleccionado la Opción 1.");
                                        registrarClientes();
                                        break;
                                    case 2:
                                        System.out.println("Has seleccionado la Opción 2.");
                                        mostrarInformacion();
                                        break;
                                    case 3:
                                        System.out.println("Has seleccionado la Opción 3.");
                                        editarInformacion();
                                        break;
                                    case 4:
                                        System.out.println("Has seleccionado la Opción 4.");
                                        eliminarInformacion();
                                        break;
                                    case 5:
                                        System.out.println("Vuelva pronto");
                                        break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Error: Debes ingresar un número válido.");
                                scanner.next();
                            }
                        } while (opcion != 5);

                        scanner.close();
                        break;
                    case 2:
                        System.out.println("Has seleccionado la Opción 2.");
                        System.out.println("Prestamos");
                        agregacionDinero();
                        break;
                    case 3:
                        System.out.println("Vuelva pronto");
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
