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
public class menus{
            
                public static boolean menuUsuario(Scanner scanner2) {
                    int opcion2 = 0;

                    do {
                        System.out.println("Menú ----------------------------------------");
                        System.out.println("1. Interactuar con Cuentas Bancarias---------");
                        System.out.println("2. Interactuar con Tarjetas: crédito o débito");
                        System.out.println("3. Realizar una transacción------------------");
                        System.out.println("4. Regresar al Menú Principal----------------");
                        System.out.println("Selecciona una opción:");

                        try {
                            opcion2 = scanner2.nextInt();
                            switch (opcion2) {
                                case 1:
                                    System.out.println("Has seleccionado la Opción 1.");
                                    boolean volverAlMenu = crudCuentas.creacionCuentas();
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
                                    System.out.println("Regresando al menú principal...");
                                    return true; 
                                default:
                                    System.out.println("Opción no válida. Intenta de nuevo.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Debes ingresar un número válido.");
                            scanner2.next(); 
                        }
                    } while (true); 
                }

    
                public static boolean menuDirectiva(Scanner scanner3) {
                    int opcion3 = 0;

                    do {
                        System.out.println("Menú-------------------------------------");
                        System.out.println("1. Interactuar con Cuentas Tabla Clientes");
                        System.out.println("2. Interactuar con Préstamos-------------");
                        System.out.println("3. Salir---------------------------------");
                        System.out.println("Selecciona una opción: ");

                        try {
                            opcion3 = scanner3.nextInt();

                            switch (opcion3) {
                                case 1:
                                    
                                    System.out.println("Has seleccionado la Opción 1.");
                                    boolean volverAlMenuDirectiva;
                                    do {
                                        volverAlMenuDirectiva = menuClientes(scanner3);
                                    } while (!volverAlMenuDirectiva);
                                    
                                    break;
                                case 2:
                                    System.out.println("Has seleccionado la Opción 2.");
                                    System.out.println("Préstamos");
                                    agregacionDinero();
                                    break;
                                case 3:
                                    System.out.println("Vuelva pronto");
                                    return true; 
                                default:
                                    System.out.println("Opción no válida. Intenta de nuevo.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Debes ingresar un número válido.");
                            scanner3.next(); 
                        }
                    } while (true); 
                }


                private static boolean menuClientes(Scanner scanner4) {
                    int opcion4 = 0;

                    do {
                        System.out.println("Menú---------------------------");
                        System.out.println("1. Crear un nuevo usuario------");
                        System.out.println("2. Mostrar los datos ingresados");
                        System.out.println("3. Para editar datos-----------");
                        System.out.println("4. Para eliminar datos---------");
                        System.out.println("5. Salir-----------------------");
                        System.out.print("Selecciona una opción:");

                        try {
                            opcion4 = scanner4.nextInt();

                            switch (opcion4) {
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
                                    System.out.println("Regresando al menú de directiva...");
                                    return false;
                                default:
                                    System.out.println("Opción no válida. Intenta de nuevo.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Error: Debes ingresar un número válido.");
                            scanner4.next();
                        }
                    } while (true);
                }
}
