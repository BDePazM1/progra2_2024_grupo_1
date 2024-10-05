/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.bd;

import gt.edu.miumg.bancobdf.Cuentas;
import gt.edu.miumg.bancobdf.CuentasJpaController;
import gt.edu.miumg.bancobdf.Prestamo;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Freddy Elias
 */
public class crudPrestamo {
    public static void agregacionDinero(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gt.edu.miumg_BANCOBDF_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        Prestamo crud = new Prestamo();
        Cuentas cru = new Cuentas();
        Scanner ent = new Scanner(System.in);

        System.out.println("Observe el menu siguiente para evitar inconvenientes");
        System.out.println("Si el prestamo < 2,000 tiene una taza de interes del 5%");
        System.out.println("Si el prestamo > 2,000 y <10,000 tiene una taza de interes del 10%");
        System.out.println("Si el prestamo > 10,000 tiene una taza de interes del 15%");
        
        System.out.println("ingrese su numero de cuenta");
        int numCuenta = ent.nextInt();
        CuentasJpaController cuentaController = new CuentasJpaController(emf);
        Cuentas cuentaExistente = cuentaController.findCuentas(numCuenta);
        
        System.out.println("Su saldo actual es: " + cuentaExistente.getSaldo());
        if (cuentaExistente == null) {
            System.out.println("No se encontró ninguna cuenta con el número ingresado.");
            return; 
        }

        System.out.println("Su saldo actual es: " + cuentaExistente.getSaldo());
        System.out.println("Por favor ingrese la cantidad de dinero que desea obtener");
        double monto = ent.nextDouble();
        crud.setMonto(monto);
        
        double saldoActual = cuentaExistente.getSaldo();
        cuentaController.findCuentas(numCuenta);
        double nuevoSaldo = cuentaExistente.getSaldo() + monto;
        
        System.out.println("Saldo de la cuenta destino: " + nuevoSaldo);
        cuentaExistente.setSaldo(nuevoSaldo);
        em.getTransaction().begin();
        em.merge(cuentaExistente);
        em.getTransaction().commit();
    }
}
