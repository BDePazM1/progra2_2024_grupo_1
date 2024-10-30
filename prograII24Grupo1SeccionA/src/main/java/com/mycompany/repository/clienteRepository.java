/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.repository;

import com.mycompany.model.Clientes;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

/**
 *
 * @author Freddy Elias
 */
@RequestScoped
public class clienteRepository implements PanacheRepository<Clientes>{
    @Transactional
    public void crate(Clientes nuevoCliente){
        this.persist(nuevoCliente);
    }
}
