/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.proyect6;

import gt.edu.miumg.bd.Prestamo;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Freddy Elias
 */
public class registroPrestamo {
     public static void registroCuenta(){
        Prestamo ingresarPrestamo = new  Prestamo();
        Scanner entrada = new Scanner(System.in);
        float variable1;
        String variable2;
        
        //INGRESO DE DATOS EN TABLA PRESTAMO
        System.out.print("Por favor ingrese el monto: ");
        variable1 = entrada.nextFloat();
        ingresarPrestamo.setMonto(variable1);
        entrada.nextLine();
        
        System.out.print("Su taza de initeres es la siguiente: ");
        variable1 = entrada.nextFloat();
        ingresarPrestamo.setTazaInteres(variable1);
        
        System.out.print("El plazo de tiempo a pagar es el siguiente: ");
        variable2 = entrada.nextLine();
        ingresarPrestamo.setPlazo(variable2);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gt.edu.miumg_proyect6_jar_1.0-SNAPSHOTPU");
        
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(ingresarPrestamo);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
            System.out.println("Exception -->" + e);
        } finally {
            em.close();
        }
    }
}
