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
import javax.persistence.Table;

/**
 *
 * @author Freddy Elias
 */
@Entity
@Table(name = "Cuentas", catalog = "BANCOBDF", schema = "dbo")
public class Cuentas implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Cuenta", nullable = false)
    private Integer idCuenta;
    @Column(name = "num_Cuenta")
    private Integer numCuenta;
    @Column(name = "tipo_Cuenta", length = 40)
    private String tipoCuenta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Saldo", precision = 53)
    private Double Saldo;

    public Cuentas(Integer idCuenta, Integer numCuenta, String tipoCuenta, Double Saldo) {
        this.idCuenta = idCuenta;
        this.numCuenta = numCuenta;
        this.tipoCuenta = tipoCuenta;
        this.Saldo = Saldo;
    }

    public Cuentas() {
    }
    
    
    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public int getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(int numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldo() {
        return Saldo;
    }

    public void setSaldo(double Saldo) {
        this.Saldo = Saldo;
    }

    @Override
    public String toString() {
        return "Cuentas{" + "idCuenta=" + idCuenta + ", numCuenta=" + numCuenta + ", tipoCuenta=" + tipoCuenta + ", Saldo=" + Saldo + '}';
    }
}
