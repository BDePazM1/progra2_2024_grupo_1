/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.db;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author kfsji
 */
@Entity
@Table(name = "CUENTAS", catalog = "", schema = "SYSTEM")
@NamedQueries({
    @NamedQuery(name = "Cuentas.findAll", query = "SELECT c FROM Cuentas c"),
    @NamedQuery(name = "Cuentas.findByNumCuenta", query = "SELECT c FROM Cuentas c WHERE c.numCuenta = :numCuenta"),
    @NamedQuery(name = "Cuentas.findByTipoCuenta", query = "SELECT c FROM Cuentas c WHERE c.tipoCuenta = :tipoCuenta"),
    @NamedQuery(name = "Cuentas.findBySaldo", query = "SELECT c FROM Cuentas c WHERE c.saldo = :saldo")})
public class Cuentas implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "NUM_CUENTA", nullable = false, precision = 38, scale = 0)
    private BigDecimal numCuenta;
    @Basic(optional = false)
    @Column(name = "TIPO_CUENTA", nullable = false, length = 100)
    private String tipoCuenta;
    @Basic(optional = false)
    @Column(name = "SALDO", nullable = false)
    private double saldo;

    public Cuentas() {
    }

    public Cuentas(BigDecimal numCuenta) {
        this.numCuenta = numCuenta;
    }

    public Cuentas(BigDecimal numCuenta, String tipoCuenta, double saldo) {
        this.numCuenta = numCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
    }

    public BigDecimal getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(BigDecimal numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numCuenta != null ? numCuenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuentas)) {
            return false;
        }
        Cuentas other = (Cuentas) object;
        if ((this.numCuenta == null && other.numCuenta != null) || (this.numCuenta != null && !this.numCuenta.equals(other.numCuenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.umg.db.Cuentas[ numCuenta=" + numCuenta + " ]";
    }
    
}
