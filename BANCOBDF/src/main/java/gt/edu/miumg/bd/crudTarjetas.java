/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.bd;

import gt.edu.miumg.bancobdf.Cuentas;
import gt.edu.miumg.bancobdf.Tarjetas;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author byron
 */
public class crudTarjetas {
    public static boolean creacionTarjetas(){
        Tarjetas ingresoTarjeta = new Tarjetas();
        boolean regresarMenuT = false;
        
        Scanner s = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gt.edu.miumg_BANCOBDF_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        
           int numTarjeta;
           String tipoTarjeta;
           int cvv;
        
           
           do {
               System.out.println("MENU DE TARJETA--------------------------------");
               System.out.println("1.Si desea Registrar una Tarjeta---------------");
               System.out.println("2.Si desea consultar Su Tarjeta----------------");
               System.out.println("3.Si desea Eliminar el Registro de tarjeta-----");               
               System.out.println("4.Si desea salir presione----------------------");
               System.out.println("-----------------------------------------------");
               int texFase = s.nextInt();

               switch (texFase) {
                   case 1:

                       do {
                           System.out.println("Ingrese su número de Tarjeta:");
                           try {
                               numTarjeta = s.nextInt();
                               ingresoTarjeta.setNumTarjeta(numTarjeta);
                               s.nextLine();
                               break;
                           } catch (InputMismatchException e) {
                               System.out.println("Entrada inválida. Debe ingresar una serie de número enteros.");
                               s.nextLine();
                           }
                       } while (true);

                       
                       
                       do {
                           System.out.println("Ingrese su tipo de Tarjeta ('Debito' o 'Credito'):");
                           tipoTarjeta = s.nextLine().trim();
                           if (tipoTarjeta.equalsIgnoreCase("Debito") || tipoTarjeta.equalsIgnoreCase("Credito")) {
                               ingresoTarjeta.setTipoTarjeta(tipoTarjeta);
                               break;
                           } else {
                               System.out.println("Entrada inválida. Debe ser 'Debito' o 'Credito'. Intente de nuevo.");
                           }
                       } while (true);

                       
                       do {
                           System.out.println("Ingrese su cvv:");
                           try {
                               cvv = s.nextInt();
                               ingresoTarjeta.setCvv(cvv);
                               s.nextLine();
                               break;
                           } catch (InputMismatchException e) {
                               System.out.println("Entrada inválida. Debe ingresar una serie de número enteros.");
                               s.nextLine();
                           }
                       } while (true);

                       System.out.println("Los datos ingresados: " + ingresoTarjeta.getNumTarjeta() + " " + ingresoTarjeta.getTipoTarjeta() + " " + ingresoTarjeta.getCvv());

                       try {
                           em.getTransaction().begin();
                           em.persist(ingresoTarjeta);
                           em.getTransaction().commit();
                           System.out.println("Cuenta creada con éxito: " + ingresoTarjeta.getNumTarjeta());
                       } catch (Exception e) {
                           System.out.println("Ocurrió un error al crear la cuenta: " + e);
                           if (em.getTransaction().isActive()) {
                               em.getTransaction().rollback();
                           }
                       }
                       break;

                   case 2:
                       System.out.println("Ingrese su número de Tarjeta para consultar su información:");
                       numTarjeta = s.nextInt();
                       try {
                           em.getTransaction().begin();
                           Query query = em.createQuery("SELECT c FROM Tarjetas c WHERE c.numTarjeta = :numTarjeta");
                           query.setParameter("numTarjeta", numTarjeta);
                           List<Tarjetas> resultados = query.getResultList();

                           if (!resultados.isEmpty()) {
                               for (Tarjetas cuenta : resultados) {
                                   System.out.println("Número de Cuenta: " + cuenta.getNumTarjeta());
                                   System.out.println("Tipo de Cuenta: " + cuenta.getTipoTarjeta());
                                   System.out.println("Su Cvv es: " + cuenta.getCvv());
                               }
                           } else {
                               System.out.println("No se encontró información para el número de Tarjeta ingresado.");
                           }
                           em.getTransaction().commit();
                       } catch (Exception e) {
                           System.out.println("Ocurrió un error al consultar la Tarjeta: " + e);
                           if (em.getTransaction().isActive()) {
                               em.getTransaction().rollback();
                           }
                       }
                       break;

                   case 3:
                       System.out.println("Ingrese el número de la Tarjeta que desea eliminar:");
                       numTarjeta = s.nextInt();
                       try {
                           Query query = em.createQuery("SELECT c FROM Tarjetas c WHERE c.numTarjeta = :numTarjeta");
                           query.setParameter("numTarjeta", numTarjeta);
                           List<Tarjetas> cuentasEncontradas = query.getResultList();

                           if (!cuentasEncontradas.isEmpty()) {
                               Tarjetas cuentaAEliminar = cuentasEncontradas.get(0);
                               int idTarjeta = cuentaAEliminar.getIdTarjeta();

                               em.getTransaction().begin(); 
                               Tarjetas cuentaGestionada = em.find(Tarjetas.class, idTarjeta);
                               if (cuentaGestionada != null) {
                                   em.remove(cuentaGestionada);
                                   em.getTransaction().commit();
                                   System.out.println("La cuenta con número " + numTarjeta + " ha sido eliminada.");
                               } else {
                                   System.out.println("No se encontró la Tarjeta con id: " + idTarjeta);
                               }
                           } else {
                               System.out.println("No se encontró ninguna Tarjeta con el número: " + numTarjeta);
                           }
                       } catch (Exception e) {
                           if (em.getTransaction().isActive()) {
                               em.getTransaction().rollback();
                           }
                           System.out.println("Ocurrió un error al intentar eliminar la Tarjeta: " + e.getMessage());
                       }
                       break;
                       
                   case 4:
                      
                       System.out.println("¡Que tenga buen día!");
                       regresarMenuT = true; 
                       break;

                    default:
                       System.out.println("Opción no válida. Intente de nuevo.");
               }
                    } while (!regresarMenuT);


                    em.close();
                    emf.close();

                    return regresarMenuT;
    }
}
