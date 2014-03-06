/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.modelo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "cargo", catalog = "nomina2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c"),
    @NamedQuery(name = "Cargo.findByCargoid", query = "SELECT c FROM Cargo c WHERE c.cargoid = :cargoid"),
    @NamedQuery(name = "Cargo.findByCodigo", query = "SELECT c FROM Cargo c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Cargo.findByCargo", query = "SELECT c FROM Cargo c WHERE c.cargo = :cargo"),
    @NamedQuery(name = "Cargo.findByFunciones", query = "SELECT c FROM Cargo c WHERE c.funciones = :funciones")})
public class Cargo implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cargoid")
    private Integer cargoid;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "cargo")
    private String cargo;
    @Column(name = "funciones")
    private String funciones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cargoCargoid")
    private List<Empleado> empleadoList;

    public Cargo() {
    }

    public Cargo(Integer cargoid) {
        this.cargoid = cargoid;
    }

    public Integer getCargoid() {
        return cargoid;
    }

    public void setCargoid(Integer cargoid) {
        Integer oldCargoid = this.cargoid;
        this.cargoid = cargoid;
        changeSupport.firePropertyChange("cargoid", oldCargoid, cargoid);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        String oldCodigo = this.codigo;
        this.codigo = codigo;
        changeSupport.firePropertyChange("codigo", oldCodigo, codigo);
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        String oldCargo = this.cargo;
        this.cargo = cargo;
        changeSupport.firePropertyChange("cargo", oldCargo, cargo);
    }

    public String getFunciones() {
        return funciones;
    }

    public void setFunciones(String funciones) {
        String oldFunciones = this.funciones;
        this.funciones = funciones;
        changeSupport.firePropertyChange("funciones", oldFunciones, funciones);
    }

    @XmlTransient
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cargoid != null ? cargoid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargo)) {
            return false;
        }
        Cargo other = (Cargo) object;
        if ((this.cargoid == null && other.cargoid != null) || (this.cargoid != null && !this.cargoid.equals(other.cargoid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udec.modelo.Cargo[ cargoid=" + cargoid + " ]";
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
}

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
