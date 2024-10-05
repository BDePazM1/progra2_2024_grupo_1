/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.db;

import com.mycompany.programa2_2024_grupo_1.CuentasNewJpaController;
import com.mycompany.programa2_2024_grupo_1.exceptions.CuentasNew;
import java.math.BigDecimal;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author OVANDO
 */
public class transaccion {

    public static void main(String[] args) {

        Transacciones ingresarTransaccion = new Transacciones();
        CuentasNew ingresoCuenta = new CuentasNew();
        Scanner s = new Scanner(System.in);
        String texFase = null;
        double monto;
        BigDecimal numCuenta;
        String tipo;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_programa2_2024_grupo_1_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

        System.out.println("Ingrese su número de cuenta:");
        numCuenta = s.nextBigDecimal();

        CuentasNewJpaController cuentaController = new CuentasNewJpaController(emf);
        CuentasNew cuentaExistente = cuentaController.findCuentasNew(numCuenta);

        if (cuentaExistente == null) {
            System.out.println("No se encontró ninguna cuenta con el número ingresado.");
            return; 
        }

        System.out.println("Su saldo actual es: " + cuentaExistente.getSaldo());

        System.out.println("Ingrese el monto que desea retirar, transferir o depositar:");
        monto = s.nextDouble();
        ingresarTransaccion.setMonto(monto);
        s.nextLine();

        System.out.println("Ingrese la operación que desea realizar: retiro, transferencia o deposito");
        tipo = s.nextLine();
        ingresarTransaccion.setTipoTransaccion(tipo);

        double saldoActual = cuentaExistente.getSaldo();
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
                BigDecimal numCuentaDestino = s.nextBigDecimal();
                CuentasNew cuentaDestino = cuentaController.findCuentasNew(numCuentaDestino);

                if (cuentaDestino != null && monto <= saldoActual) {
                    saldoActual -= monto;
                    double saldoDestino = cuentaDestino.getSaldo() + monto;

                    System.out.println("Transferencia exitosa. Su nuevo saldo es: " + saldoActual);
                    System.out.println("Saldo de la cuenta destino: " + saldoDestino);

                    cuentaDestino.setSaldo(saldoDestino);
                    em.getTransaction().begin();
                    em.merge(cuentaDestino);
                    em.getTransaction().commit();

                } else {
                    System.out.println("Transferencia fallida: saldo insuficiente o cuenta destino no encontrada.");
                }
                break;

            case "deposito":
                System.out.println("Ingrese el número de cuenta destino para realizar el depósito:");
                numCuentaDestino = s.nextBigDecimal();
                CuentasNew cuentaDestinoDeposito = cuentaController.findCuentasNew(numCuentaDestino);

                if (cuentaDestinoDeposito != null && monto <= saldoActual) {

                    saldoActual -= monto;

                    double saldoDestinoDeposito = cuentaDestinoDeposito.getSaldo() + monto;

                    System.out.println("Depósito exitoso. Su nuevo saldo es: " + saldoActual);
                    System.out.println("Saldo de la cuenta destino: " + saldoDestinoDeposito);

                    cuentaDestinoDeposito.setSaldo(saldoDestinoDeposito);

                    em.getTransaction().begin();
                    em.merge(cuentaDestinoDeposito);  
                    em.merge(cuentaExistente);        
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
            cuentaExistente.setSaldo(saldoActual);
            em.getTransaction().begin();
            em.merge(cuentaExistente);
            em.persist(ingresarTransaccion);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Ocurrió un error al realizar la transacción.");
        } finally {
            em.close();
        }

        System.out.println("Transacción realizada con éxito.");
    }
}