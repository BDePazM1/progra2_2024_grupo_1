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
public class CrearCuenta {
    public static void main(String[] args) {

        Cuenta ingresoCuenta = new Cuenta();
        Scanner s = new Scanner(System.in);

        BigDecimal numCuenta;
        String tipoCuenta;
        double saldo;

        System.out.println("Ingrese su numero de cuenta");
        numCuenta = s.nextBigDecimal();
        ingresoCuenta.setNumcuenta(numCuenta);
        s.nextLine();
        System.out.println("Ingrese su tipo de cuenta");
        tipoCuenta = s.nextLine();
        ingresoCuenta.setTipocuenta(tipoCuenta);
        System.out.println("Ingrese su saldo actual");
        saldo = s.nextDouble();
        ingresoCuenta.setSaldo(saldo);
        
        System.out.println("Los datos ingresados: " + ingresoCuenta.getNumcuenta() + ingresoCuenta.getTipocuenta() + ingresoCuenta.getSaldo());

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_dbtest_jar_1.0-SNAPSHOTPU");

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(ingresoCuenta);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Ocurrio un error");
        }

    }
}


