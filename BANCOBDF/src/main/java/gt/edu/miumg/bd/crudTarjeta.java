/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.bd;

import gt.edu.miumg.bancobdf.Tarjeta;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author Freddy Elias
 */
public class crudTarjeta {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("gt.edu.miumg_savery_jar_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();

    public crudTarjeta() {
        this.emf = Persistence.createEntityManagerFactory("gt.edu.miumg_BANCOBDF_jar_1.0-SNAPSHOTPU");
    }

    public void crearTarjeta(Tarjeta tarjeta) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(tarjeta);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al crear tarjeta: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Tarjeta> obtenerTarjetas() {
        EntityManager em = emf.createEntityManager();
        List<Tarjeta> tarjetas = new ArrayList<>();
        try {
            tarjetas = em.createQuery("SELECT t FROM Tarjeta t", Tarjeta.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener tarjetas: " + e.getMessage());
        } finally {
            em.close();
        }
        return tarjetas;
    }

    public void actualizarTarjeta(Tarjeta tarjeta) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(tarjeta);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al actualizar tarjeta: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void eliminarTarjeta(String numTarjeta) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Tarjeta tarjeta = em.find(Tarjeta.class, numTarjeta);
            if (tarjeta != null) {
                em.remove(tarjeta);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error al eliminar tarjeta: " + e.getMessage());
        } finally {
            em.close();
        }
    }

}
