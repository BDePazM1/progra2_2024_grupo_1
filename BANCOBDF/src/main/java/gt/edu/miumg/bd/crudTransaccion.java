/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.bd;

import gt.edu.miumg.bancobdf.Cuentas;
import gt.edu.miumg.bancobdf.CuentasJpaController;
import gt.edu.miumg.bancobdf.Transacciones;
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
public class crudTransaccion {
    public static void traspasoDinero(){
        Transacciones ingresarTransaccion = new Transacciones();
        Cuentas ingresoCuenta = new Cuentas();
        Scanner s = new Scanner(System.in);
        String texFase = null;
        double monto;
        int numCuenta;
        String tipo;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gt.edu.miumg_BANCOBDF_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

        System.out.println("Ingrese su número de cuenta:");
        numCuenta = s.nextInt();
        
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT c FROM Cuentas c WHERE c.numCuenta = :numCuenta");
        query.setParameter("numCuenta", numCuenta);

        /*CuentasJpaController cuentaController = new CuentasJpaController(emf);
        Integer numCuentas = null;
        Cuentas cuentaExistente = cuentaController.findCuentas(numCuenta);*/
        List<Cuentas> resultados = query.getResultList();
        
        if (!resultados.isEmpty()){
            System.out.println("Cuenta encontrada.");
            
            System.out.println("Su saldo actual es: " + ingresoCuenta.getSaldo());

        System.out.println("Ingrese el monto que desea retirar, transferir o depositar:");
        monto = s.nextDouble();
        ingresarTransaccion.setMonto(monto);
        s.nextLine();

        System.out.println("Ingrese la operación que desea realizar: retiro, transferencia o deposito");
        tipo = s.nextLine();
        ingresarTransaccion.setTipoTransaccion(tipo);

        double saldoActual = ingresoCuenta.getSaldo();
        
        switch (tipo.toLowerCase()) {
            case "retiro":
                if (monto > saldoActual) {
                    System.out.println("Saldo insuficiente para realizar el retiro.");
                } else {
                    saldoActual -= monto;
                    System.out.println("Retiro exitoso. Su nuevo saldo es: " + saldoActual);
                }
                break;

            case "transferencia":
                System.out.println("Ingrese el número de cuenta de destino:");
                int numCuentaDestino = s.nextInt();
                /*Cuentas cuentaDestino = cuentaController.findCuentas(numCuentaDestino);*/

                if (ingresoCuenta != null && monto <= saldoActual) {
                    saldoActual -= monto;
                    double saldoDestino = ingresoCuenta.getSaldo() + monto;

                    System.out.println("Transferencia exitosa. Su nuevo saldo es: " + saldoActual);
                    System.out.println("Saldo de la cuenta destino: " + saldoDestino);

                    ingresoCuenta.setSaldo(saldoDestino);
                    em.getTransaction().begin();
                    em.merge(ingresoCuenta);
                    em.getTransaction().commit();

                } else {
                    System.out.println("Transferencia fallida: saldo insuficiente o cuenta destino no encontrada.");
                }
                break;

            case "deposito":
                System.out.println("Ingrese el número de cuenta destino para realizar el depósito:");
                numCuentaDestino = s.nextInt();
                //Cuentas cuentaDestinoDeposito = cuentaController.findCuentas(numCuentaDestino);

                if (ingresoCuenta != null && monto <= saldoActual) {

                    saldoActual -= monto;

                    double saldoDestinoDeposito = ingresoCuenta.getSaldo() + monto;

                    System.out.println("Depósito exitoso. Su nuevo saldo es: " + saldoActual);
                    System.out.println("Saldo de la cuenta destino: " + saldoDestinoDeposito);

                    ingresoCuenta.setSaldo(saldoDestinoDeposito);

                    em.getTransaction().begin();
                    em.merge(ingresoCuenta);  
                    em.merge(ingresoCuenta);        
                    em.persist(ingresarTransaccion);  
                    em.getTransaction().commit();
                } else {
                    System.out.println("Depósito fallido: saldo insuficiente o cuenta destino no encontrada.");
                }
                break;

            default:
                System.out.println("Operación no reconocida.");
                return;
        }
        
        try {
            ingresoCuenta.setSaldo(saldoActual);
            em.getTransaction().begin();
            em.merge(ingresoCuenta);
            em.persist(ingresarTransaccion);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Ocurrió un error al realizar la transacción.");
        } finally {
            em.close();
        }

        System.out.println("Transacción realizada con éxito.");
    
            
        } else { 
            System.out.println("No se encontró la cuenta con el número ingresado.");
            return;
            }
    }
        
        

        /*if (cuentaExistente == null) {
            System.out.println("No se encontró ninguna cuenta con el número ingresado.");
            return; 
        }*/

        
        

        
}