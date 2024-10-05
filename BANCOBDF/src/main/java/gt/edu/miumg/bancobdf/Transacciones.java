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
@Table(name = "Transacciones", catalog = "BANCOBDF", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Transacciones.findAll", query = "SELECT t FROM Transacciones t"),
    @NamedQuery(name = "Transacciones.findByIdtransaccion", query = "SELECT t FROM Transacciones t WHERE t.idtransaccion = :idtransaccion"),
    @NamedQuery(name = "Transacciones.findByTipoTransaccion", query = "SELECT t FROM Transacciones t WHERE t.tipoTransaccion = :tipoTransaccion"),
    @NamedQuery(name = "Transacciones.findByMonto", query = "SELECT t FROM Transacciones t WHERE t.monto = :monto")})
public class Transacciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_transaccion", nullable = false)
    private Integer idtransaccion;
    @Basic(optional = false)
    @Column(name = "tipo_transaccion", nullable = false, length = 100)
    private String tipoTransaccion;
    @Basic(optional = false)
    @Column(name = "monto", nullable = false)
    private double monto;

    public Transacciones() {
    }

    public Transacciones(Integer idtransaccion) {
        this.idtransaccion = idtransaccion;
    }

    public Transacciones(Integer idtransaccion, String tipoTransaccion, double monto) {
        this.idtransaccion = idtransaccion;
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
    }

    public Integer getIdtransaccion() {
        return idtransaccion;
    }

    public void setIdtransaccion(Integer idtransaccion) {
        this.idtransaccion = idtransaccion;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtransaccion != null ? idtransaccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transacciones)) {
            return false;
        }
        Transacciones other = (Transacciones) object;
        if ((this.idtransaccion == null && other.idtransaccion != null) || (this.idtransaccion != null && !this.idtransaccion.equals(other.idtransaccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gt.edu.miumg.bancobdf.Transacciones[ idtransaccion=" + idtransaccion + " ]";
    }
    
}
