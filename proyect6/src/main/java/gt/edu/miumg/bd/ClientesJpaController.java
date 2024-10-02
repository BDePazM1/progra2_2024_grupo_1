/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.bd;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import gt.edu.miumg.proyect6.Cuenta;
import gt.edu.miumg.bd.Prestamo;
import gt.edu.miumg.bd.exceptions.NonexistentEntityException;
import gt.edu.miumg.proyect6.Clientes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Freddy Elias
 */
public class ClientesJpaController implements Serializable {

    public ClientesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Clientes clientes) {
        if (clientes.getPrestamoList() == null) {
            clientes.setPrestamoList(new ArrayList<Prestamo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuenta numCuenta = clientes.getNumCuenta();
            if (numCuenta != null) {
                numCuenta = em.getReference(numCuenta.getClass(), numCuenta.getNumCuenta());
                clientes.setNumCuenta(numCuenta);
            }
            List<Prestamo> attachedPrestamoList = new ArrayList<Prestamo>();
            for (Prestamo prestamoListPrestamoToAttach : clientes.getPrestamoList()) {
                prestamoListPrestamoToAttach = em.getReference(prestamoListPrestamoToAttach.getClass(), prestamoListPrestamoToAttach.getId());
                attachedPrestamoList.add(prestamoListPrestamoToAttach);
            }
            clientes.setPrestamoList(attachedPrestamoList);
            em.persist(clientes);
            if (numCuenta != null) {
                numCuenta.getClientesList().add(clientes);
                numCuenta = em.merge(numCuenta);
            }
            for (Prestamo prestamoListPrestamo : clientes.getPrestamoList()) {
                Clientes oldIdClienteOfPrestamoListPrestamo = prestamoListPrestamo.getIdCliente();
                prestamoListPrestamo.setIdCliente(clientes);
                prestamoListPrestamo = em.merge(prestamoListPrestamo);
                if (oldIdClienteOfPrestamoListPrestamo != null) {
                    oldIdClienteOfPrestamoListPrestamo.getPrestamoList().remove(prestamoListPrestamo);
                    oldIdClienteOfPrestamoListPrestamo = em.merge(oldIdClienteOfPrestamoListPrestamo);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clientes clientes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes persistentClientes = em.find(Clientes.class, clientes.getId());
            Cuenta numCuentaOld = persistentClientes.getNumCuenta();
            Cuenta numCuentaNew = clientes.getNumCuenta();
            List<Prestamo> prestamoListOld = persistentClientes.getPrestamoList();
            List<Prestamo> prestamoListNew = clientes.getPrestamoList();
            if (numCuentaNew != null) {
                numCuentaNew = em.getReference(numCuentaNew.getClass(), numCuentaNew.getNumCuenta());
                clientes.setNumCuenta(numCuentaNew);
            }
            List<Prestamo> attachedPrestamoListNew = new ArrayList<Prestamo>();
            for (Prestamo prestamoListNewPrestamoToAttach : prestamoListNew) {
                prestamoListNewPrestamoToAttach = em.getReference(prestamoListNewPrestamoToAttach.getClass(), prestamoListNewPrestamoToAttach.getId());
                attachedPrestamoListNew.add(prestamoListNewPrestamoToAttach);
            }
            prestamoListNew = attachedPrestamoListNew;
            clientes.setPrestamoList(prestamoListNew);
            clientes = em.merge(clientes);
            if (numCuentaOld != null && !numCuentaOld.equals(numCuentaNew)) {
                numCuentaOld.getClientesList().remove(clientes);
                numCuentaOld = em.merge(numCuentaOld);
            }
            if (numCuentaNew != null && !numCuentaNew.equals(numCuentaOld)) {
                numCuentaNew.getClientesList().add(clientes);
                numCuentaNew = em.merge(numCuentaNew);
            }
            for (Prestamo prestamoListOldPrestamo : prestamoListOld) {
                if (!prestamoListNew.contains(prestamoListOldPrestamo)) {
                    prestamoListOldPrestamo.setIdCliente(null);
                    prestamoListOldPrestamo = em.merge(prestamoListOldPrestamo);
                }
            }
            for (Prestamo prestamoListNewPrestamo : prestamoListNew) {
                if (!prestamoListOld.contains(prestamoListNewPrestamo)) {
                    Clientes oldIdClienteOfPrestamoListNewPrestamo = prestamoListNewPrestamo.getIdCliente();
                    prestamoListNewPrestamo.setIdCliente(clientes);
                    prestamoListNewPrestamo = em.merge(prestamoListNewPrestamo);
                    if (oldIdClienteOfPrestamoListNewPrestamo != null && !oldIdClienteOfPrestamoListNewPrestamo.equals(clientes)) {
                        oldIdClienteOfPrestamoListNewPrestamo.getPrestamoList().remove(prestamoListNewPrestamo);
                        oldIdClienteOfPrestamoListNewPrestamo = em.merge(oldIdClienteOfPrestamoListNewPrestamo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clientes.getId();
                if (findClientes(id) == null) {
                    throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientes clientes;
            try {
                clientes = em.getReference(Clientes.class, id);
                clientes.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clientes with id " + id + " no longer exists.", enfe);
            }
            Cuenta numCuenta = clientes.getNumCuenta();
            if (numCuenta != null) {
                numCuenta.getClientesList().remove(clientes);
                numCuenta = em.merge(numCuenta);
            }
            List<Prestamo> prestamoList = clientes.getPrestamoList();
            for (Prestamo prestamoListPrestamo : prestamoList) {
                prestamoListPrestamo.setIdCliente(null);
                prestamoListPrestamo = em.merge(prestamoListPrestamo);
            }
            em.remove(clientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clientes> findClientesEntities() {
        return findClientesEntities(true, -1, -1);
    }

    public List<Clientes> findClientesEntities(int maxResults, int firstResult) {
        return findClientesEntities(false, maxResults, firstResult);
    }

    private List<Clientes> findClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clientes.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Clientes findClientes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clientes> rt = cq.from(Clientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
