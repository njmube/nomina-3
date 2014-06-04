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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "concepto", catalog = "nomina2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Concepto.findAll", query = "SELECT c FROM Concepto c"),
    @NamedQuery(name = "Concepto.findByIdconcepto", query = "SELECT c FROM Concepto c WHERE c.idconcepto = :idconcepto"),
    @NamedQuery(name = "Concepto.findByCodigo", query = "SELECT c FROM Concepto c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "Concepto.findByConcepto", query = "SELECT c FROM Concepto c WHERE c.concepto = :concepto"),
    @NamedQuery(name = "Concepto.findByTipo", query = "SELECT c FROM Concepto c WHERE c.tipo = :tipo"),
    @NamedQuery(name = "Concepto.findByFormato", query = "SELECT c FROM Concepto c WHERE c.formato = :formato"),
    @NamedQuery(name = "Concepto.findByGeneral", query = "SELECT c FROM Concepto c WHERE c.general = :general")})
public class Concepto implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idconcepto")
    private Integer idconcepto;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "concepto")
    private String concepto;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "formato")
    private String formato;
    @Column(name = "general")
    private String general;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conceptoIdconcepto")
    private List<Nomina> nominaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conceptoIdconcepto")
    private List<Novedadxconcepto> novedadxconceptoList;

    public Concepto() {
    }

    public Concepto(Integer idconcepto) {
        this.idconcepto = idconcepto;
    }

    public Integer getIdconcepto() {
        return idconcepto;
    }

    public void setIdconcepto(Integer idconcepto) {
        Integer oldIdconcepto = this.idconcepto;
        this.idconcepto = idconcepto;
        changeSupport.firePropertyChange("idconcepto", oldIdconcepto, idconcepto);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        String oldCodigo = this.codigo;
        this.codigo = codigo;
        changeSupport.firePropertyChange("codigo", oldCodigo, codigo);
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        String oldConcepto = this.concepto;
        this.concepto = concepto;
        changeSupport.firePropertyChange("concepto", oldConcepto, concepto);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        String oldTipo = this.tipo;
        this.tipo = tipo;
        changeSupport.firePropertyChange("tipo", oldTipo, tipo);
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        String oldFormato = this.formato;
        this.formato = formato;
        changeSupport.firePropertyChange("formato", oldFormato, formato);
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        String oldGeneral = this.general;
        this.general = general;
        changeSupport.firePropertyChange("general", oldGeneral, general);
    }

    @XmlTransient
    public List<Nomina> getNominaList() {
        return nominaList;
    }

    public void setNominaList(List<Nomina> nominaList) {
        this.nominaList = nominaList;
    }

    @XmlTransient
    public List<Novedadxconcepto> getNovedadxconceptoList() {
        return novedadxconceptoList;
    }

    public void setNovedadxconceptoList(List<Novedadxconcepto> novedadxconceptoList) {
        this.novedadxconceptoList = novedadxconceptoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idconcepto != null ? idconcepto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Concepto)) {
            return false;
        }
        Concepto other = (Concepto) object;
        if ((this.idconcepto == null && other.idconcepto != null) || (this.idconcepto != null && !this.idconcepto.equals(other.idconcepto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udec.modelo.Concepto[ idconcepto=" + idconcepto + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
