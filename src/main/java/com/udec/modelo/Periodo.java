/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.modelo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "periodo", catalog = "nomina2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Periodo.findAll", query = "SELECT p FROM Periodo p"),
    @NamedQuery(name = "Periodo.findByIdperiodo", query = "SELECT p FROM Periodo p WHERE p.idperiodo = :idperiodo"),
    @NamedQuery(name = "Periodo.findByNombre", query = "SELECT p FROM Periodo p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Periodo.findByMes", query = "SELECT p FROM Periodo p WHERE p.mes = :mes"),
    @NamedQuery(name = "Periodo.findByAnio", query = "SELECT p FROM Periodo p WHERE p.anio = :anio"),
    @NamedQuery(name = "Periodo.findByQuincena", query = "SELECT p FROM Periodo p WHERE p.quincena = :quincena"),
    @NamedQuery(name = "Periodo.findByDesde", query = "SELECT p FROM Periodo p WHERE p.desde = :desde"),
    @NamedQuery(name = "Periodo.findByHasta", query = "SELECT p FROM Periodo p WHERE p.hasta = :hasta"),
    @NamedQuery(name = "Periodo.findByActual", query = "SELECT p FROM Periodo p WHERE p.actual = :actual")})
public class Periodo implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idperiodo")
    private Integer idperiodo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "mes")
    private Integer mes;
    @Column(name = "anio")
    private Integer anio;
    @Column(name = "quincena")
    private Integer quincena;
    @Column(name = "desde")
    @Temporal(TemporalType.DATE)
    private Date desde;
    @Column(name = "hasta")
    @Temporal(TemporalType.DATE)
    private Date hasta;
    @Column(name = "actual")
    private String actual;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodoIdperiodo")
    private List<Nomina> nominaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodoIdperiodo")
    private List<Diastrabajados> diastrabajadosList;

    public Periodo() {
    }

    public Periodo(Integer idperiodo) {
        this.idperiodo = idperiodo;
    }

    public Integer getIdperiodo() {
        return idperiodo;
    }

    public void setIdperiodo(Integer idperiodo) {
        this.idperiodo = idperiodo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        String oldNombre = this.nombre;
        this.nombre = nombre;
        changeSupport.firePropertyChange("nombre", oldNombre, nombre);
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        Integer oldMes = this.mes;
        this.mes = mes;
        changeSupport.firePropertyChange("mes", oldMes, mes);
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        Integer oldAnio = this.anio;
        this.anio = anio;
        changeSupport.firePropertyChange("anio", oldAnio, anio);
    }

    public Integer getQuincena() {
        return quincena;
    }

    public void setQuincena(Integer quincena) {
        Integer oldQuincena = this.quincena;
        this.quincena = quincena;
        changeSupport.firePropertyChange("quincena", oldQuincena, quincena);
    }

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        Date oldDesde = this.desde;
        this.desde = desde;
        changeSupport.firePropertyChange("desde", oldDesde, desde);

    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        Date oldHasta = this.hasta;
        this.hasta = hasta;
        changeSupport.firePropertyChange("hasta", oldHasta, hasta);

    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    @XmlTransient
    public List<Nomina> getNominaList() {
        return nominaList;
    }

    public void setNominaList(List<Nomina> nominaList) {
        this.nominaList = nominaList;
    }

    @XmlTransient
    public List<Diastrabajados> getDiastrabajadosList() {
        return diastrabajadosList;
    }

    public void setDiastrabajadosList(List<Diastrabajados> diastrabajadosList) {
        this.diastrabajadosList = diastrabajadosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idperiodo != null ? idperiodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodo)) {
            return false;
        }
        Periodo other = (Periodo) object;
        if ((this.idperiodo == null && other.idperiodo != null) || (this.idperiodo != null && !this.idperiodo.equals(other.idperiodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udec.modelo.Periodo[ idperiodo=" + idperiodo + " ]";
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
}

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
}
