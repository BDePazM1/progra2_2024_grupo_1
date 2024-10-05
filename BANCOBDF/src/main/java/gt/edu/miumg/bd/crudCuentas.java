/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.bd;
import gt.edu.miumg.bancobdf.Cuentas;
import gt.edu.miumg.bancobdf.CuentasJpaController;
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
    public static void creacionCuentas() {

        Cuentas ingresoCuenta = new Cuentas();
        try (Scanner s = new Scanner(System.in)) {
            int numCuenta;
            String tipoCuenta;
            double saldo;
            int texFase;

            do {

                System.out.println("---------------------------------------------");
                System.out.println("Si desea crear una cuenta presione 1         ");
                System.out.println("Si desea consultar su informacion presione 2 ");
                System.out.println("Si desea eliminar la cuenta presione 3       ");
                System.out.println("Si desea salir presione 4                    ");
                System.out.println("---------------------------------------------");
                texFase = s.nextInt();

                EntityManagerFactory emf = Persistence.createEntityManagerFactory("gt.edu.miumg_BANCOBDF_jar_1.0-SNAPSHOTPU");

                EntityManager em = emf.createEntityManager();

                switch (texFase) {

                    case 1:
                        System.out.println("Ingrese su numero de cuenta");
                        numCuenta = s.nextInt();
                        ingresoCuenta.setNumCuenta(numCuenta);
                        s.nextLine();
                        System.out.println("Ingrese su tipo de cuenta");
                        tipoCuenta = s.nextLine();
                        ingresoCuenta.setTipoCuenta(tipoCuenta);
                        System.out.println("Ingrese su saldo actual");
                        saldo = s.nextDouble();
                        ingresoCuenta.setSaldo(saldo);

                        System.out.println("Los datos ingresados:  " + ingresoCuenta.getNumCuenta() + ingresoCuenta.getTipoCuenta() + ingresoCuenta.getSaldo());

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
                        em.close();
                        emf.close();
                        break;
                    case 3:

                        System.out.println("Ingrese el número de cuenta que desea eliminar");
                        numCuenta = s.nextInt();

                        CuentasJpaController ac = new CuentasJpaController(emf);

                        try {

                            Cuentas cuentaAEliminar = ac.findCuentas(numCuenta);

                            if (cuentaAEliminar != null) {
                                em.getTransaction().begin(); // Iniciar la transacción

                                Cuentas cuentaGestionada = em.merge(cuentaAEliminar);

                                em.remove(cuentaGestionada);
                                em.getTransaction().commit(); // Confirmar la transacción
                                System.out.println("La cuenta con número " + numCuenta + " ha sido eliminada.");
                            } else {
                                System.out.println("No se encontró ninguna cuenta con el número: " + numCuenta);
                            }
                        } catch (Exception e) {
                            em.getTransaction().rollback(); // Hacer rollback si hay error
                            System.out.println("Ocurrió un error al intentar eliminar la cuenta.");
                        }

                        break;

                    case 4:
                        System.out.println("¡Que tenga buen día!");
                        break;

                }

            } while (texFase == 1 || texFase == 2 || texFase == 3 || texFase > 4 || texFase < 1);

        }
    }

}
