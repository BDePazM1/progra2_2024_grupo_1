/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.miumg.bancobdf;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Freddy Elias
 */
@Entity
@Table(name = "Tarjeta", catalog = "BANCOBDF", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Tarjeta.findAll", query = "SELECT t FROM Tarjeta t"),
    @NamedQuery(name = "Tarjeta.findByNumTarjeta", query = "SELECT t FROM Tarjeta t WHERE t.numTarjeta = :numTarjeta"),
    @NamedQuery(name = "Tarjeta.findByTipoTarjeta", query = "SELECT t FROM Tarjeta t WHERE t.tipoTarjeta = :tipoTarjeta"),
    @NamedQuery(name = "Tarjeta.findByCvv", query = "SELECT t FROM Tarjeta t WHERE t.cvv = :cvv")})
public class Tarjeta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "num_Tarjeta", nullable = false)
    private Integer numTarjeta;
    @Basic(optional = false)
    @Column(name = "tipo_tarjeta", nullable = false, length = 100)
    private String tipoTarjeta;
    @Column(name = "cvv")
    private Integer cvv;

    public Tarjeta() {
    }

    public Tarjeta(Integer numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public Tarjeta(Integer numTarjeta, String tipoTarjeta) {
        this.numTarjeta = numTarjeta;
        this.tipoTarjeta = tipoTarjeta;
    }

    public Integer getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(Integer numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numTarjeta != null ? numTarjeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarjeta)) {
            return false;
        }
        Tarjeta other = (Tarjeta) object;
        if ((this.numTarjeta == null && other.numTarjeta != null) || (this.numTarjeta != null && !this.numTarjeta.equals(other.numTarjeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.miumg.bancobdf.Tarjeta[ numTarjeta=" + numTarjeta + " ]";
    }
    
}
