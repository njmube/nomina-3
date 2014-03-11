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
@Table(name = "nomina", catalog = "nomina2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nomina.findAll", query = "SELECT n FROM Nomina n"),
    @NamedQuery(name = "Nomina.findByIdnomina", query = "SELECT n FROM Nomina n WHERE n.idnomina = :idnomina"),
    @NamedQuery(name = "Nomina.findByValor", query = "SELECT n FROM Nomina n WHERE n.valor = :valor")})
public class Nomina implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnomina")
    private Integer idnomina;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Double valor;
    @JoinColumn(name = "empleado_codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Empleado empleadoCodigo;
    @JoinColumn(name = "concepto_idconcepto", referencedColumnName = "idconcepto")
    @ManyToOne(optional = false)
    private Concepto conceptoIdconcepto;
    @JoinColumn(name = "periodo_idperiodo", referencedColumnName = "idperiodo")
    @ManyToOne(optional = false)
    private Periodo periodoIdperiodo;

    public Nomina() {
    }

    public Nomina(Integer idnomina) {
        this.idnomina = idnomina;
    }

    public Integer getIdnomina() {
        return idnomina;
    }

    public void setIdnomina(Integer idnomina) {
        Integer oldIdnomina = this.idnomina;
        this.idnomina = idnomina;
        changeSupport.firePropertyChange("idnomina", oldIdnomina, idnomina);
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        Double oldValor = this.valor;
        this.valor = valor;
        changeSupport.firePropertyChange("valor", oldValor, valor);
    }

    public Empleado getEmpleadoCodigo() {
        return empleadoCodigo;
    }

    public void setEmpleadoCodigo(Empleado empleadoCodigo) {
        Empleado oldEmpleadoCodigo = this.empleadoCodigo;
        this.empleadoCodigo = empleadoCodigo;
        changeSupport.firePropertyChange("empleadoCodigo", oldEmpleadoCodigo, empleadoCodigo);
    }

    public Concepto getConceptoIdconcepto() {
        return conceptoIdconcepto;
    }

    public void setConceptoIdconcepto(Concepto conceptoIdconcepto) {
        Concepto oldConceptoIdconcepto = this.conceptoIdconcepto;
        this.conceptoIdconcepto = conceptoIdconcepto;
        changeSupport.firePropertyChange("conceptoIdconcepto", oldConceptoIdconcepto, conceptoIdconcepto);
    }

    public Periodo getPeriodoIdperiodo() {
        return periodoIdperiodo;
    }

    public void setPeriodoIdperiodo(Periodo periodoIdperiodo) {
        Periodo oldPeriodoIdperiodo = this.periodoIdperiodo;
        this.periodoIdperiodo = periodoIdperiodo;
        changeSupport.firePropertyChange("periodoIdperiodo", oldPeriodoIdperiodo, periodoIdperiodo);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnomina != null ? idnomina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nomina)) {
            return false;
        }
        Nomina other = (Nomina) object;
        if ((this.idnomina == null && other.idnomina != null) || (this.idnomina != null && !this.idnomina.equals(other.idnomina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udec.modelo.Nomina[ idnomina=" + idnomina + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
