/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository;

import com.mycompany.model.Cuentas;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 *
 * @author OVANDO
 */
@RequestScoped
public class cuentaRepository implements PanacheRepository<Cuentas>{
    
    @Transactional
    public void crate(Cuentas nuevoCuenta){
        this.persist(nuevoCuenta);
        }
 
    public List<Cuentas> findCuentas(){
        try{
            Query q = getEntityManager().createQuery("SELECT C FROM Cuentas C order by C.idCuenta");
            return q.getResultList();
        } catch(Exception e){
            System.out.println("e->" + e);
            return null;
        }
    }
    
    @Transactional
    public void updateCuenta(Cuentas cuenta){
        this.getEntityManager().merge(cuenta);
    }
}