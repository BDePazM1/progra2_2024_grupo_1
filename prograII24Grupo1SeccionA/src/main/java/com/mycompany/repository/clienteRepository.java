/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository;

import com.mycompany.model.Clientes;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;

/**
 *
 * @author Freddy Elias
 */
@RequestScoped
public class clienteRepository implements PanacheRepository<Clientes> {

    @Transactional
    public void crate(Clientes nuevoCliente) {
        this.persist(nuevoCliente);
    }

    public List<Clientes> findClientes() {
        try {
            Query q = getEntityManager().createQuery("SELECT C FROM Clientes C order by C.dni");
            return q.getResultList();
        } catch (Exception e) {
            System.out.println("e-> " + e);
            return null;
        }
    }

    @Transactional
    public void updateCliente(Clientes cliente) {
        this.getEntityManager().merge(cliente);  // Utiliza merge para actualizar la entidad en la base de datos
    }
}
