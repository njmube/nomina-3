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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ususario
 */
@Entity
@Table(name = "diastrabajados", catalog = "nomina2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Diastrabajados.findAll", query = "SELECT d FROM Diastrabajados d"),
    @NamedQuery(name = "Diastrabajados.findByIddiastrabajados", query = "SELECT d FROM Diastrabajados d WHERE d.iddiastrabajados = :iddiastrabajados"),
    @NamedQuery(name = "Diastrabajados.findByDias", query = "SELECT d FROM Diastrabajados d WHERE d.dias = :dias"),
    @NamedQuery(name = "Diastrabajados.findByDiasIncTotal", query = "SELECT d FROM Diastrabajados d WHERE d.diasIncTotal = :diasIncTotal"),
    @NamedQuery(name = "Diastrabajados.findByDiasIncDostercios", query = "SELECT d FROM Diastrabajados d WHERE d.diasIncDostercios = :diasIncDostercios"),
    @NamedQuery(name = "Diastrabajados.findByDiasIncCincuenta", query = "SELECT d FROM Diastrabajados d WHERE d.diasIncCincuenta = :diasIncCincuenta")})
public class Diastrabajados implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddiastrabajados")
    private Integer iddiastrabajados;
    @Column(name = "dias")
    private Integer dias;
    @Column(name = "dias_inc_total")
    private Integer diasIncTotal;
    @Column(name = "dias_inc_dostercios")
    private Integer diasIncDostercios;
    @Column(name = "dias_inc_cincuenta")
    private Integer diasIncCincuenta;
    @JoinColumn(name = "periodo_idperiodo", referencedColumnName = "idperiodo")
    @ManyToOne(optional = false)
    private Periodo periodoIdperiodo;
    @JoinColumn(name = "empleado_codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Empleado empleadoCodigo;

    public Diastrabajados() {
    }

    public Diastrabajados(Integer iddiastrabajados) {
        this.iddiastrabajados = iddiastrabajados;
    }

    public Integer getIddiastrabajados() {
        return iddiastrabajados;
    }

    public void setIddiastrabajados(Integer iddiastrabajados) {
        Integer oldIddiastrabajados = this.iddiastrabajados;
        this.iddiastrabajados = iddiastrabajados;
        changeSupport.firePropertyChange("iddiastrabajados", oldIddiastrabajados, iddiastrabajados);
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        Integer oldDias = this.dias;
        this.dias = dias;
        changeSupport.firePropertyChange("dias", oldDias, dias);
    }

    public Integer getDiasIncTotal() {
        return diasIncTotal;
    }

    public void setDiasIncTotal(Integer diasIncTotal) {
        Integer oldDiasIncTotal = this.diasIncTotal;
        this.diasIncTotal = diasIncTotal;
        changeSupport.firePropertyChange("diasIncTotal", oldDiasIncTotal, diasIncTotal);
    }

    public Integer getDiasIncDostercios() {
        return diasIncDostercios;
    }

    public void setDiasIncDostercios(Integer diasIncDostercios) {
        Integer oldDiasIncDostercios = this.diasIncDostercios;
        this.diasIncDostercios = diasIncDostercios;
        changeSupport.firePropertyChange("diasIncDostercios", oldDiasIncDostercios, diasIncDostercios);
    }

    public Integer getDiasIncCincuenta() {
        return diasIncCincuenta;
    }

    public void setDiasIncCincuenta(Integer diasIncCincuenta) {
        Integer oldDiasIncCincuenta = this.diasIncCincuenta;
        this.diasIncCincuenta = diasIncCincuenta;
        changeSupport.firePropertyChange("diasIncCincuenta", oldDiasIncCincuenta, diasIncCincuenta);
    }

    public Periodo getPeriodoIdperiodo() {
        return periodoIdperiodo;
    }

    public void setPeriodoIdperiodo(Periodo periodoIdperiodo) {
        Periodo oldPeriodoIdperiodo = this.periodoIdperiodo;
        this.periodoIdperiodo = periodoIdperiodo;
        changeSupport.firePropertyChange("periodoIdperiodo", oldPeriodoIdperiodo, periodoIdperiodo);
    }

    public Empleado getEmpleadoCodigo() {
        return empleadoCodigo;
    }

    public void setEmpleadoCodigo(Empleado empleadoCodigo) {
        Empleado oldEmpleadoCodigo = this.empleadoCodigo;
        this.empleadoCodigo = empleadoCodigo;
        changeSupport.firePropertyChange("empleadoCodigo", oldEmpleadoCodigo, empleadoCodigo);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddiastrabajados != null ? iddiastrabajados.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diastrabajados)) {
            return false;
        }
        Diastrabajados other = (Diastrabajados) object;
        if ((this.iddiastrabajados == null && other.iddiastrabajados != null) || (this.iddiastrabajados != null && !this.iddiastrabajados.equals(other.iddiastrabajados))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udec.modelo.Diastrabajados[ iddiastrabajados=" + iddiastrabajados + " ]";
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
}

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
