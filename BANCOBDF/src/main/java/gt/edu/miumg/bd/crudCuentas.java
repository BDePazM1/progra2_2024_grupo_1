/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.bd;
import gt.edu.miumg.bancobdf.Cuentas;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author Freddy Elias
 */
public class crudCuentas {
        public static boolean creacionCuentas() {
           Cuentas ingresoCuenta = new Cuentas();
           boolean regresarMenu = false;

           Scanner s = new Scanner(System.in);
           EntityManagerFactory emf = Persistence.createEntityManagerFactory("gt.edu.miumg_BANCOBDF_jar_1.0-SNAPSHOTPU");
           EntityManager em = emf.createEntityManager(); // Crear el EntityManager una vez

           int numCuenta;
           String tipoCuenta;
           double saldo;
           int texFase;

           do {
               System.out.println("---------------------------------------------");
               System.out.println("Si desea crear una cuenta presione 1         ");
               System.out.println("Si desea consultar su información presione 2 ");
               System.out.println("Si desea eliminar la cuenta presione 3       ");
               System.out.println("Si desea salir presione 4                    ");
               System.out.println("---------------------------------------------");
               texFase = s.nextInt();

               switch (texFase) {
                   case 1:
                      
                       do {
                           System.out.println("Ingrese su número de cuenta:");
                           try {
                               numCuenta = s.nextInt();
                               ingresoCuenta.setNumCuenta(numCuenta);
                               s.nextLine();
                               break;
                           } catch (InputMismatchException e) {
                               System.out.println("Entrada inválida. Debe ingresar una serie de número enteros.");
                               s.nextLine();
                           }
                       } while (true);

                       do {
                           System.out.println("Ingrese su tipo de cuenta (Ahorro o Monetario):");
                           tipoCuenta = s.nextLine().trim();
                           if (tipoCuenta.equalsIgnoreCase("Ahorro") || tipoCuenta.equalsIgnoreCase("Monetario")) {
                               ingresoCuenta.setTipoCuenta(tipoCuenta);
                               break;
                           } else {
                               System.out.println("Entrada inválida. Debe ser 'Ahorro' o 'Monetario'. Intente de nuevo.");
                           }
                       } while (true);

                       do {
                           System.out.println("Ingrese su saldo actual:");
                           try {
                               saldo = s.nextDouble();
                               ingresoCuenta.setSaldo(saldo);
                               s.nextLine();
                               break;
                           } catch (InputMismatchException e) {
                               System.out.println("Entrada inválida. Debe ingresar un número decimal (saldo).");
                               s.nextLine();
                           }
                       } while (true);

                       System.out.println("Los datos ingresados: ");
                       System.out.println("Numero de cuenta:" + ingresoCuenta.getNumCuenta());
                       System.out.println("Tipo de cuenta:" + ingresoCuenta.getTipoCuenta());
                       System.out.println("Saldo disponible:" + ingresoCuenta.getSaldo());
                       try {
                           em.getTransaction().begin();
                           em.persist(ingresoCuenta);
                           em.getTransaction().commit();
                           System.out.println("Cuenta creada con éxito: " + ingresoCuenta.getNumCuenta());
                       } catch (Exception e) {
                           System.out.println("Ocurrió un error al crear la cuenta: " + e);
                           if (em.getTransaction().isActive()) {
                               em.getTransaction().rollback();
                           }
                       }
                       break;

                   case 2:
                       System.out.println("Ingrese su número de cuenta para consultar su información:");
                       numCuenta = s.nextInt();
                       try {
                           em.getTransaction().begin();
                           Query query = em.createQuery("SELECT c FROM Cuentas c WHERE c.numCuenta = :numCuenta");
                           query.setParameter("numCuenta", numCuenta);
                           List<Cuentas> resultados = query.getResultList();

                           if (!resultados.isEmpty()) {
                               for (Cuentas cuenta : resultados) {
                                   System.out.println("Número de Cuenta: " + cuenta.getNumCuenta());
                                   System.out.println("Tipo de Cuenta: " + cuenta.getTipoCuenta());
                                   System.out.println("Saldo Actual: " + cuenta.getSaldo());
                               }
                           } else {
                               System.out.println("No se encontró información para el número de cuenta ingresado.");
                           }
                           em.getTransaction().commit();
                       } catch (Exception e) {
                           System.out.println("Ocurrió un error al consultar la cuenta: " + e);
                           if (em.getTransaction().isActive()) {
                               em.getTransaction().rollback();
                           }
                       }
                       break;

                   case 3:
                       System.out.println("Ingrese el número de cuenta que desea eliminar:");
                       numCuenta = s.nextInt();
                       try {
                           Query query = em.createQuery("SELECT c FROM Cuentas c WHERE c.numCuenta = :numCuenta");
                           query.setParameter("numCuenta", numCuenta);
                           List<Cuentas> cuentasEncontradas = query.getResultList();

                           if (!cuentasEncontradas.isEmpty()) {
                               Cuentas cuentaAEliminar = cuentasEncontradas.get(0);
                               int idCuenta = cuentaAEliminar.getIdCuenta();

                               em.getTransaction().begin(); 
                               Cuentas cuentaGestionada = em.find(Cuentas.class, idCuenta);
                               if (cuentaGestionada != null) {
                                   em.remove(cuentaGestionada);
                                   em.getTransaction().commit();
                                   System.out.println("La cuenta con número " + numCuenta + " ha sido eliminada.");
                               } else {
                                   System.out.println("No se encontró la cuenta con id: " + idCuenta);
                               }
                           } else {
                               System.out.println("No se encontró ninguna cuenta con el número: " + numCuenta);
                           }
                       } catch (Exception e) {
                           if (em.getTransaction().isActive()) {
                               em.getTransaction().rollback();
                           }
                           System.out.println("Ocurrió un error al intentar eliminar la cuenta: " + e.getMessage());
                       }
                       break;

                   case 4:
                       System.out.println("¡Que tenga buen día!");
                       regresarMenu = true; 
                       break;

                   default:
                       System.out.println("Opción no válida. Intente de nuevo.");
               }
           } while (!regresarMenu);


           em.close();
           emf.close();

           return regresarMenu;
    }   
}


