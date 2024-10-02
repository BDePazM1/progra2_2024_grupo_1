/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.programa2_2024_grupo_1;

import java.math.BigDecimal;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author kfsji
 */
public class Transaccion {
    public static void main(String[] args) {

        Transaccion ingresoTransaccion = new Transaccion();
        Cuenta ingresoCuenta = new Cuenta();

        Scanner s = new Scanner(System.in);
        String texFase = null;
        int opcion;
        double saldo = 1000;
        double monto;
        double retiro;
        double total = 0;
        double transferencia;
        double deposito;
        BigDecimal numCuenta;
        String tipo = null;
        int fecha;

        System.out.println("Ingrese su numero de cuenta");
        numCuenta = s.nextBigDecimal();
        s.nextLine();

        System.out.println("Ingrese el monto que desea retirar, depositar o transferir");
        monto = s.nextDouble();
        ingresoTransaccion.setMonto(monto);
        s.nextLine();

        do {
            System.out.println("---------------------------------------------------");
            System.out.println("Escriba el tipo de cuenta: Monetaria o Ahorro      ");
            System.out.println("Si deseas salir, escriba: salir                    ");
            System.out.println("---------------------------------------------------");
            texFase = s.nextLine().toLowerCase();
            ingresoTransaccion.setTipo(tipo);
            switch (texFase.toLowerCase()) {

                case "monetaria":
                    System.out.println("Ingreso a su cuenta monetaria");
                    System.out.println("Su saldo actualmente es de:" + ingresoCuenta.getSaldo());
                    System.out.println("--------------------------------");
                    System.out.println("Si desea retirar presione 1     ");
                    System.out.println("Si desea transferir presione 2  ");
                    System.out.println("Si desea depositar presione 3   ");
                    System.out.println("--------------------------------");
                    opcion = s.nextInt();
                    s.nextLine();
                    switch (opcion) {
                        case 1:
                            System.out.println("La cantidad a retirar es de: " + monto);
                            saldo -= monto;
                            System.out.println("Ahora su saldo es de: " + saldo);
                            break;
                        case 2:
                            System.out.println("La cantidad a transferir es de: " + monto);
                            saldo -= monto;
                            System.out.println("Ahora su saldo es de:" + saldo);
                            break;
                        case 3:
                            System.out.println("La cantidad a depositar es de: " + monto);
                            saldo += monto;
                            System.out.println("Ahora su saldo es de:" + saldo);
                            break;
                    }
                    break;
                case "ahorro":
                    System.out.println("Ingreso a su cuenta de ahorro");
                    System.out.println("Su saldo actualmente es de:" + saldo);
                    System.out.println("--------------------------------");
                    System.out.println("Si desea retirar presione 1     ");
                    System.out.println("Si desea depositar presione 2   ");
                    System.out.println("Si desea depositar presione 3   ");
                    System.out.println("--------------------------------");
                    opcion = s.nextInt();
                    s.nextLine();
                    switch (opcion) {
                       case 1:
                            System.out.println("La cantidad a retirar es de: " + monto);
                            saldo -= monto;
                            System.out.println("Ahora su saldo es de: " + saldo);
                            break;
                        case 2:
                            System.out.println("La cantidad a transferir es de: " + monto);
                            saldo -= monto;
                            System.out.println("Ahora su saldo es de:" + saldo);
                            break;
                        case 3:
                            System.out.println("La cantidad a depositar es de: " + monto);
                            saldo += monto;
                            System.out.println("Ahora su saldo es de:" + saldo);
                            break;
                    }
                    break;
            }
        } while (texFase.equals("salir"));
        System.out.println("Que tenga un buen día");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_dbtest_jar_1.0-SNAPSHOTPU");

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(ingresoTransaccion);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Ocurrio un error");
        }
    }

   
}
