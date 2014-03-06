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
@Table(name = "grupoconcepto", catalog = "nomina2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupoconcepto.findAll", query = "SELECT g FROM Grupoconcepto g"),
    @NamedQuery(name = "Grupoconcepto.findByIdgrupoconcepto", query = "SELECT g FROM Grupoconcepto g WHERE g.idgrupoconcepto = :idgrupoconcepto"),
    @NamedQuery(name = "Grupoconcepto.findByCodigo", query = "SELECT g FROM Grupoconcepto g WHERE g.codigo = :codigo"),
    @NamedQuery(name = "Grupoconcepto.findByNombre", query = "SELECT g FROM Grupoconcepto g WHERE g.nombre = :nombre")})
public class Grupoconcepto implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idgrupoconcepto")
    private Integer idgrupoconcepto;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "grupoconceptoIdgrupoconcepto")
    private List<Concepto> conceptoList;

    public Grupoconcepto() {
    }

    public Grupoconcepto(Integer idgrupoconcepto) {
        this.idgrupoconcepto = idgrupoconcepto;
    }

    public Integer getIdgrupoconcepto() {
        return idgrupoconcepto;
    }

    public void setIdgrupoconcepto(Integer idgrupoconcepto) {
        Integer oldIdgrupoconcepto = this.idgrupoconcepto;
        this.idgrupoconcepto = idgrupoconcepto;
        changeSupport.firePropertyChange("idgrupoconcepto", oldIdgrupoconcepto, idgrupoconcepto);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        String oldCodigo = this.codigo;
        this.codigo = codigo;
        changeSupport.firePropertyChange("codigo", oldCodigo, codigo);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        String oldNombre = this.nombre;
        this.nombre = nombre;
        changeSupport.firePropertyChange("nombre", oldNombre, nombre);
    }

    @XmlTransient
    public List<Concepto> getConceptoList() {
        return conceptoList;
    }

    public void setConceptoList(List<Concepto> conceptoList) {
        this.conceptoList = conceptoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgrupoconcepto != null ? idgrupoconcepto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupoconcepto)) {
            return false;
        }
        Grupoconcepto other = (Grupoconcepto) object;
        if ((this.idgrupoconcepto == null && other.idgrupoconcepto != null) || (this.idgrupoconcepto != null && !this.idgrupoconcepto.equals(other.idgrupoconcepto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udec.modelo.Grupoconcepto[ idgrupoconcepto=" + idgrupoconcepto + " ]";
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
}

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
