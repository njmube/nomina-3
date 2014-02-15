/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "novedadmedic", catalog = "nomina2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Novedadmedic.findAll", query = "SELECT n FROM Novedadmedic n"),
    @NamedQuery(name = "Novedadmedic.findByIdnovedadmedic", query = "SELECT n FROM Novedadmedic n WHERE n.idnovedadmedic = :idnovedadmedic"),
    @NamedQuery(name = "Novedadmedic.findByFechaInicio", query = "SELECT n FROM Novedadmedic n WHERE n.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Novedadmedic.findByFechaFinal", query = "SELECT n FROM Novedadmedic n WHERE n.fechaFinal = :fechaFinal"),
    @NamedQuery(name = "Novedadmedic.findByTipo", query = "SELECT n FROM Novedadmedic n WHERE n.tipo = :tipo")})
public class Novedadmedic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnovedadmedic")
    private Integer idnovedadmedic;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_final")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "empleado_codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Empleado empleadoCodigo;

    public Novedadmedic() {
    }

    public Novedadmedic(Integer idnovedadmedic) {
        this.idnovedadmedic = idnovedadmedic;
    }

    public Integer getIdnovedadmedic() {
        return idnovedadmedic;
    }

    public void setIdnovedadmedic(Integer idnovedadmedic) {
        this.idnovedadmedic = idnovedadmedic;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Empleado getEmpleadoCodigo() {
        return empleadoCodigo;
    }

    public void setEmpleadoCodigo(Empleado empleadoCodigo) {
        this.empleadoCodigo = empleadoCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnovedadmedic != null ? idnovedadmedic.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Novedadmedic)) {
            return false;
        }
        Novedadmedic other = (Novedadmedic) object;
        if ((this.idnovedadmedic == null && other.idnovedadmedic != null) || (this.idnovedadmedic != null && !this.idnovedadmedic.equals(other.idnovedadmedic))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udec.modelo.Novedadmedic[ idnovedadmedic=" + idnovedadmedic + " ]";
    }
    
}
