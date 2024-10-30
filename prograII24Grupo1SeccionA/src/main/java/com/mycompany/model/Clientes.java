/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Freddy Elias
 */
@Entity
@Table(name = "Clientes", catalog = "BANCOBDF", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c")})
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Dni", nullable = false)
    private long dni;
    @Size(max = 50)
    @Column(name = "Nombre", length = 50)
    private String nombre;
    @Size(max = 50)
    @Column(name = "Apellidos", length = 50)
    private String apellidos;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Domicilio", length = 2147483647)
    private String domicilio;
    @Column(name = "Telefono")
    private Integer telefono;

    public Clientes() {
    }

    public Clientes(Integer dni, String nombre, String apellidos, String domicilio, Integer telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.domicilio = domicilio;
        this.telefono = telefono;
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Clientes{" + "dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", domicilio=" + domicilio + ", telefono=" + telefono + '}';
    }

    
}
