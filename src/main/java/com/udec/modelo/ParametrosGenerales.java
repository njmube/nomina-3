/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.modelo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "parametros_generales", catalog = "nomina2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametrosGenerales.findAll", query = "SELECT p FROM ParametrosGenerales p"),
    @NamedQuery(name = "ParametrosGenerales.findBySalarioMinimo", query = "SELECT p FROM ParametrosGenerales p WHERE p.salarioMinimo = :salarioMinimo"),
    @NamedQuery(name = "ParametrosGenerales.findBySubsidioTransporte", query = "SELECT p FROM ParametrosGenerales p WHERE p.subsidioTransporte = :subsidioTransporte"),
    @NamedQuery(name = "ParametrosGenerales.findBySubsidioAlimentacion", query = "SELECT p FROM ParametrosGenerales p WHERE p.subsidioAlimentacion = :subsidioAlimentacion"),
    @NamedQuery(name = "ParametrosGenerales.findByPorcentajeSalud", query = "SELECT p FROM ParametrosGenerales p WHERE p.porcentajeSalud = :porcentajeSalud"),
    @NamedQuery(name = "ParametrosGenerales.findByPorcentajePension", query = "SELECT p FROM ParametrosGenerales p WHERE p.porcentajePension = :porcentajePension"),
    @NamedQuery(name = "ParametrosGenerales.findByPorcentajeFps", query = "SELECT p FROM ParametrosGenerales p WHERE p.porcentajeFps = :porcentajeFps"),
    @NamedQuery(name = "ParametrosGenerales.findByIdParametrosgenerales", query = "SELECT p FROM ParametrosGenerales p WHERE p.idParametrosgenerales = :idParametrosgenerales")})
public class ParametrosGenerales implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salario_minimo")
    private Float salarioMinimo;
    @Column(name = "subsidio_transporte")
    private Float subsidioTransporte;
    @Column(name = "subsidio_alimentacion")
    private Float subsidioAlimentacion;
    @Column(name = "porcentaje_salud")
    private Float porcentajeSalud;
    @Column(name = "porcentaje_pension")
    private Float porcentajePension;
    @Column(name = "porcentaje_fps")
    private Float porcentajeFps;
    @Id
    @Basic(optional = false)
    @Column(name = "id_parametrosgenerales")
    private Integer idParametrosgenerales;

    public ParametrosGenerales() {
    }

    public ParametrosGenerales(Integer idParametrosgenerales) {
        this.idParametrosgenerales = idParametrosgenerales;
    }

    public Float getSalarioMinimo() {
        return salarioMinimo;
    }

    public void setSalarioMinimo(Float salarioMinimo) {
        Float oldSalarioMinimo = this.salarioMinimo;
        this.salarioMinimo = salarioMinimo;
        changeSupport.firePropertyChange("salarioMinimo", oldSalarioMinimo, salarioMinimo);
    }

    public Float getSubsidioTransporte() {
        return subsidioTransporte;
    }

    public void setSubsidioTransporte(Float subsidioTransporte) {
        Float oldSubsidioTransporte = this.subsidioTransporte;
        this.subsidioTransporte = subsidioTransporte;
        changeSupport.firePropertyChange("subsidioTransporte", oldSubsidioTransporte, subsidioTransporte);
    }

    public Float getSubsidioAlimentacion() {
        return subsidioAlimentacion;
    }

    public void setSubsidioAlimentacion(Float subsidioAlimentacion) {
        Float oldSubsidioAlimentacion = this.subsidioAlimentacion;
        this.subsidioAlimentacion = subsidioAlimentacion;
        changeSupport.firePropertyChange("subsidioAlimentacion", oldSubsidioAlimentacion, subsidioAlimentacion);
    }

    public Float getPorcentajeSalud() {
        return porcentajeSalud;
    }

    public void setPorcentajeSalud(Float porcentajeSalud) {
        Float oldPorcentajeSalud = this.porcentajeSalud;
        this.porcentajeSalud = porcentajeSalud;
        changeSupport.firePropertyChange("porcentajeSalud", oldPorcentajeSalud, porcentajeSalud);
    }

    public Float getPorcentajePension() {
        return porcentajePension;
    }

    public void setPorcentajePension(Float porcentajePension) {
        Float oldPorcentajePension = this.porcentajePension;
        this.porcentajePension = porcentajePension;
        changeSupport.firePropertyChange("porcentajePension", oldPorcentajePension, porcentajePension);
    }

    public Float getPorcentajeFps() {
        return porcentajeFps;
    }

    public void setPorcentajeFps(Float porcentajeFps) {
        Float oldPorcentajeFps = this.porcentajeFps;
        this.porcentajeFps = porcentajeFps;
        changeSupport.firePropertyChange("porcentajeFps", oldPorcentajeFps, porcentajeFps);
    }

    public Integer getIdParametrosgenerales() {
        return idParametrosgenerales;
    }

    public void setIdParametrosgenerales(Integer idParametrosgenerales) {
        Integer oldIdParametrosgenerales = this.idParametrosgenerales;
        this.idParametrosgenerales = idParametrosgenerales;
        changeSupport.firePropertyChange("idParametrosgenerales", oldIdParametrosgenerales, idParametrosgenerales);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametrosgenerales != null ? idParametrosgenerales.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametrosGenerales)) {
            return false;
        }
        ParametrosGenerales other = (ParametrosGenerales) object;
        if ((this.idParametrosgenerales == null && other.idParametrosgenerales != null) || (this.idParametrosgenerales != null && !this.idParametrosgenerales.equals(other.idParametrosgenerales))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udec.modelo.ParametrosGenerales[ idParametrosgenerales=" + idParametrosgenerales + " ]";
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
}

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
