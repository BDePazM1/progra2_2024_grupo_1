/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.bd;

import gt.edu.miumg.bancobdf.Tarjeta;
import gt.edu.miumg.bancobdf.TarjetaJpaController;
import java.util.ArrayList;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Freddy Elias
 */
public class resp {
    public static void mostrarMenuTarjetas() {
        Scanner scanner = new Scanner(System.in);
        //MENU DE TARJETAS
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Crear tarjeta");
            System.out.println("2. Obtener tarjetas");
            System.out.println("3. Actualizar tarjeta");
            System.out.println("4. Eliminar tarjeta");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    crearTarjeta();
                    break;
                case 2:
                    obtenerTarjetas();
                    break;
                case 3:
                    actualizarTarjeta();
                    break;
                case 4:
                    eliminarTarjeta();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    public static void crearTarjeta() {
        Scanner scanner = new Scanner(System.in);
        Tarjeta ingresoTarjeta = new Tarjeta();
        System.out.println("Ingrese número de tarjeta:");
        int numTarjeta = scanner.nextInt();
        ingresoTarjeta.setNumTarjeta(numTarjeta);

        System.out.println("Ingrese tipo de tarjeta:");
        String tipoTarjeta = scanner.nextLine();
        ingresoTarjeta.setTipoTarjeta(tipoTarjeta);

        System.out.println("Ingrese CVV:");
        int cvv = scanner.nextInt();
        ingresoTarjeta.setCvv(numTarjeta);

        System.out.println("Tarjeta creada con éxito.");
    }

    public static void obtenerTarjetas() {
        Scanner scanner = new Scanner(System.in);
        Tarjeta ingresoTarjeta = new Tarjeta();
        ArrayList<Tarjeta> lstCrud = new ArrayList<>();
        System.out.println("Lista de tarjetas:");
        for (Tarjeta tarjeta : lstCrud) {
            System.out.println(tarjeta);
        }
    }

    public static void actualizarTarjeta() {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gt.edu.miumg_BANCOBDF_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        TarjetaJpaController ac = new TarjetaJpaController(emf);
        ArrayList<Tarjeta> lstCrud = new ArrayList<>();

        System.out.println("Ingrese número de tarjeta a actualizar:");
        int numTarjeta = scanner.nextInt();
        Tarjeta est = ac.findTarjeta(numTarjeta);

        System.out.println("Ingrese nuevo tipo de tarjeta:");
        String tipoTarjeta = scanner.nextLine();
        est.setTipoTarjeta(tipoTarjeta);

        System.out.println("Ingrese nuevo CVV:");
        int cvv = scanner.nextInt();
        est.setCvv(cvv);

        em.getTransaction().begin();
        em.merge(est);
        em.getTransaction().commit();
        try {
            lstCrud = (ArrayList<Tarjeta>) ac.findTarjetaEntities();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        System.out.println("Tarjeta actualizada con éxito.");
    }

    public static void eliminarTarjeta() {
        Scanner scanner = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gt.edu.miumg_BANCOBDF_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        TarjetaJpaController ac = new TarjetaJpaController(emf);

        System.out.println("Ingrese número de tarjeta a eliminar:");
        int numTarjeta = scanner.nextInt();
        Tarjeta est = ac.findTarjeta(numTarjeta);
        em.getTransaction().begin();
        Tarjeta es = em.merge(est);
        em.remove(es);
        em.getTransaction().commit();
        System.out.println("Tarjeta eliminada con éxito.");
    }
}
