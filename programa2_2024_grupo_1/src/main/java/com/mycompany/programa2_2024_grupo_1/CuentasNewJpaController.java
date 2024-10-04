/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.programa2_2024_grupo_1;


import com.mycompany.programa2_2024_grupo_1.exceptions.CuentasNew;


/**
 *
 * @author kfsji
 */
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class CuentasNewJpaController {

    private EntityManagerFactory emf = null;

    public CuentasNewJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CuentasNew findCuentasNew(BigDecimal numCuenta) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CuentasNew.class, numCuenta); 
        } finally {
            em.close();
        }
    }

    public List<CuentasNew> findCuentasNew() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT c FROM CuentasNew c");
            return query.getResultList();  
        } finally {
            em.close();
        }
    }
}

