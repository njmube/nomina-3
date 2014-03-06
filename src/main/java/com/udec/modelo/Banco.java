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
@Table(name = "banco", catalog = "nomina2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Banco.findAll", query = "SELECT b FROM Banco b"),
    @NamedQuery(name = "Banco.findByIdbanco", query = "SELECT b FROM Banco b WHERE b.idbanco = :idbanco"),
    @NamedQuery(name = "Banco.findByCodigo", query = "SELECT b FROM Banco b WHERE b.codigo = :codigo"),
    @NamedQuery(name = "Banco.findByBanco", query = "SELECT b FROM Banco b WHERE b.banco = :banco")})
public class Banco implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbanco")
    private Integer idbanco;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "banco")
    private String banco;
    @OneToMany(mappedBy = "bancoIdbanco")
    private List<Novedadxconcepto> novedadxconceptoList;
    @OneToMany(mappedBy = "bancoIdbanco")
    private List<Empleado> empleadoList;

    public Banco() {
    }

    public Banco(Integer idbanco) {
        this.idbanco = idbanco;
    }

    public Integer getIdbanco() {
        return idbanco;
    }

    public void setIdbanco(Integer idbanco) {
        Integer oldIdbanco = this.idbanco;
        this.idbanco = idbanco;
        changeSupport.firePropertyChange("idbanco", oldIdbanco, idbanco);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        String oldCodigo = this.codigo;
        this.codigo = codigo;
        changeSupport.firePropertyChange("codigo", oldCodigo, codigo);
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        String oldBanco = this.banco;
        this.banco = banco;
        changeSupport.firePropertyChange("banco", oldBanco, banco);
    }

    @XmlTransient
    public List<Novedadxconcepto> getNovedadxconceptoList() {
        return novedadxconceptoList;
    }

    public void setNovedadxconceptoList(List<Novedadxconcepto> novedadxconceptoList) {
        this.novedadxconceptoList = novedadxconceptoList;
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
        hash += (idbanco != null ? idbanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banco)) {
            return false;
        }
        Banco other = (Banco) object;
        if ((this.idbanco == null && other.idbanco != null) || (this.idbanco != null && !this.idbanco.equals(other.idbanco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udec.modelo.Banco[ idbanco=" + idbanco + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
