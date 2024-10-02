/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.proyect6;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Freddy Elias
 */
public class registroClientes {
    public static void registroClientes(){
        Scanner entrada = new Scanner(System.in);
        Clientes cliente_uno;
        cliente_uno = new Clientes();
        String var1;
        int numCuenta;
        
        System.out.println("por favor ingrese el nombre del propietario de la cuenta");   
        var1 = entrada.nextLine();
        cliente_uno.setNombre(var1);
        
        System.out.println("por favor ingrese la direccion del propietario de la cuenta");
        var1 = entrada.nextLine();
        cliente_uno.setDireccion(var1);
        
        System.out.println("por favor ingrese numero telefonico del propietario de la cuenta");
        var1 = entrada.nextLine();
        cliente_uno.setTelefono(var1);
        
        System.out.println("por favor ingrese correo electronico del propietario de la cuenta");
        var1 = entrada.nextLine();
        cliente_uno.setGmail(var1);
        
        System.out.print("por favor ingrese la id del propietario de la cuenta");
        numCuenta = entrada.nextInt();
        cliente_uno.setId(numCuenta);
        
        System.out.print("por favor ingrese el numero de cuenta del propietario de la cuenta");
        numCuenta = entrada.nextInt();
        cliente_uno.setNumCuenta(numCuenta);
        System.out.print("");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gt.edu.miumg_proyect6_jar_1.0-SNAPSHOTPU");
        
        EntityManager em = emf.createEntityManager();
        
        try {
            em.getTransaction().begin();
            em.persist(cliente_uno);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
            System.out.println("Exception -->" + e);
        } finally {
            em.close();
        }
    }    
}
