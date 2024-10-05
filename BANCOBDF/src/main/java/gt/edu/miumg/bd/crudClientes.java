package gt.edu.miumg.bd;


import gt.edu.miumg.bancobdf.Clientes;
import gt.edu.miumg.bancobdf.ClientesJpaController;
import java.util.ArrayList;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Freddy Elias
 */
public class crudClientes {
    public static void registrarClientes(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gt.edu.miumg_BANCOBDF_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        Scanner ent = new Scanner(System.in);
        Clientes crud = new Clientes();
        ArrayList<Clientes> lstCrud = new ArrayList<>();
        
        System.out.print("Por favor ingrese el nombre del propietario");
        String variable2 = ent.nextLine();
        crud.setNombre(variable2);
        
        System.out.print("Por favor ingrese el domicilio del propietario");
        variable2 = ent.nextLine();
        crud.setDireccion(variable2);
        
        System.out.print("Por favor ingrese el numero telefonico  del propietario");
        int variable1 = ent.nextInt();
        crud.setTelefono(variable1);
        ent.nextLine();
        
        System.out.print("Por favor ingrese el email del propietario");
        variable2 = ent.nextLine();
        crud.setEmail(variable2);
        
        try {
            em.getTransaction().begin();
            em.persist(crud);
            em.getTransaction().commit();
        } catch(Exception e) {
            em.getTransaction().rollback();
            System.out.println("Exception -->" + e);
        } finally {
            em.close();
        }
        for(Clientes ess : lstCrud){
            System.out.println("id: "+ ess.getIdCliente());
            System.out.println("nombre: " + ess.getNombre());
            System.out.println("Direccion: " + ess.getDireccion());
            System.out.println("Telefono: " + ess.getTelefono());
            System.out.println("Email: " + ess.getEmail());
            }
    }
    
    
    public static void mostrarInformacion(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gt.edu.miumg_BANCOBDF_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        Scanner ent = new Scanner(System.in);
        ArrayList<Clientes> lstCrud = new ArrayList<>();
        ClientesJpaController ac = new ClientesJpaController(emf);
        
        try{
            lstCrud = (ArrayList<Clientes>) ac.findClientesEntities();
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            em.close();
        }
        for(Clientes est : lstCrud){
            System.out.println("nombre: " + est.getIdCliente());
            System.out.println("nombre: " + est.getNombre());
            System.out.println("apellido: " + est.getDireccion());
            System.out.println("domicilio: " + est.getTelefono());
            System.out.println("fecha de nacimiento: " + est.getEmail());
        }
    }
    
    public static void editarInformacion(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gt.edu.miumg_BANCOBDF_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        Scanner ent = new Scanner(System.in);
        ArrayList<Clientes> lstCrud = new ArrayList<>();
        ClientesJpaController ac = new ClientesJpaController(emf);
        
        System.out.println("por favor ingrese la ide de quien quiera cambiar la informacioin");
        int variable1 = ent.nextInt();
        
        try{
            System.out.println("--------------------");
            System.out.println("ACTUALIZAR");
            Clientes est = ac.findClientes(variable1);
            ent.nextLine();
            
            System.out.println("por favor ingrese el nuevo nombre");
            String variable2 = ent.nextLine();
            est.setNombre(variable2);
            
            System.out.println("por favor ingrese la nueva direccion");
            variable2 = ent.nextLine();
            est.setDireccion(variable2);
            
            System.out.println("por favor ingrese el numero de telefono");
            variable1 = ent.nextInt();
            est.setTelefono(variable1);
            ent.nextLine();
            
            System.out.println("por favor ingrese el email");
            variable2 = ent.nextLine();
            est.setEmail(variable2);
            
            em.getTransaction().begin();
            em.merge(est);
            em.getTransaction().commit();
            try {
                lstCrud = (ArrayList<Clientes>) ac.findClientesEntities();
            } catch(Exception e){
                e.printStackTrace();
            } finally {
            }
            for(Clientes ess : lstCrud){
            System.out.println("id: "+ ess.getIdCliente());
            System.out.println("nombre: " + ess.getNombre());
            System.out.println("Direccion: " + ess.getDireccion());
            System.out.println("Telefono: " + ess.getTelefono());
            System.out.println("Email: " + ess.getEmail());
            }
            
        } catch(Exception e){
            em.getTransaction().rollback();
            System.out.println("actualizacion e -->" + e);
        }
    }
    
    public static void eliminarInformacion(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gt.edu.miumg_BANCOBDF_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        Scanner ent = new Scanner(System.in);
        ArrayList<Clientes> lstCrud = new ArrayList<>();
        ClientesJpaController ac = new ClientesJpaController(emf);
        
        System.out.println("por favor ingrese la ide de quien quiera eliminar");
        int variable1 = ent.nextInt();
        
        try {
            System.out.println("---------------");
            System.out.println("ELIMINAR");
            Clientes est = ac.findClientes(variable1);
            em.getTransaction().begin();
            Clientes es = em.merge(est);
            em.remove(es);
            em.getTransaction().commit();
            System.out.println("Registro borrador");
            
            try {
                lstCrud = (ArrayList<Clientes>) ac.findClientesEntities();
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
            }
            for(Clientes ess : lstCrud){
            System.out.println("id: "+ ess.getIdCliente());
            System.out.println("nombre: " + est.getNombre());
            System.out.println("Direccion: " + ess.getDireccion());
            System.out.println("Telefono: " + ess.getTelefono());
            System.out.println("Email: " + est.getEmail());
            }
            
        } catch(Exception e){
            em.getTransaction().rollback();
            System.out.println("Delete -->" + e);
        }
    }
}
