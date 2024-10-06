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
 * @author byron
 */
@Entity
@Table(name = "Tarjetas", catalog = "BANCOBDF", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Tarjetas.findAll", query = "SELECT t FROM Tarjetas t"),
    @NamedQuery(name = "Tarjetas.findByIdTarjeta", query = "SELECT t FROM Tarjetas t WHERE t.idTarjeta = :idTarjeta"),
    @NamedQuery(name = "Tarjetas.findByCvv", query = "SELECT t FROM Tarjetas t WHERE t.cvv = :cvv"),
    @NamedQuery(name = "Tarjetas.findByNumTarjeta", query = "SELECT t FROM Tarjetas t WHERE t.numTarjeta = :numTarjeta"),
    @NamedQuery(name = "Tarjetas.findByTipoTarjeta", query = "SELECT t FROM Tarjetas t WHERE t.tipoTarjeta = :tipoTarjeta")})
public class Tarjetas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Tarjeta", nullable = false)
    private Integer idTarjeta;
    @Column(name = "cvv")
    private Integer cvv;
    @Column(name = "num_Tarjeta")
    private Integer numTarjeta;
    @Column(name = "tipo_Tarjeta", length = 20)
    private String tipoTarjeta;

    public Tarjetas() {
    }

    public Tarjetas(Integer idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public Integer getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(Integer idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTarjeta != null ? idTarjeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarjetas)) {
            return false;
        }
        Tarjetas other = (Tarjetas) object;
        if ((this.idTarjeta == null && other.idTarjeta != null) || (this.idTarjeta != null && !this.idTarjeta.equals(other.idTarjeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.miumg.bancobdf.Tarjetas[ idTarjeta=" + idTarjeta + " ]";
    }
    
}
