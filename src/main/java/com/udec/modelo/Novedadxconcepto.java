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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "novedadxconcepto", catalog = "nomina2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Novedadxconcepto.findAll", query = "SELECT n FROM Novedadxconcepto n"),
    @NamedQuery(name = "Novedadxconcepto.findByIdnovedad", query = "SELECT n FROM Novedadxconcepto n WHERE n.idnovedad = :idnovedad"),
    @NamedQuery(name = "Novedadxconcepto.findByValor", query = "SELECT n FROM Novedadxconcepto n WHERE n.valor = :valor"),
    @NamedQuery(name = "Novedadxconcepto.findBySaldo", query = "SELECT n FROM Novedadxconcepto n WHERE n.saldo = :saldo"),
    @NamedQuery(name = "Novedadxconcepto.findByTipoSaldo", query = "SELECT n FROM Novedadxconcepto n WHERE n.tipoSaldo = :tipoSaldo"),
    @NamedQuery(name = "Novedadxconcepto.findByAplicarQuincenal", query = "SELECT n FROM Novedadxconcepto n WHERE n.aplicarQuincenal = :aplicarQuincenal"),
    @NamedQuery(name = "Novedadxconcepto.findByTotalLibranza", query = "SELECT n FROM Novedadxconcepto n WHERE n.totalLibranza = :totalLibranza"),
    @NamedQuery(name = "Novedadxconcepto.findByNumeroCuotas", query = "SELECT n FROM Novedadxconcepto n WHERE n.numeroCuotas = :numeroCuotas"),
    @NamedQuery(name = "Novedadxconcepto.findByFechaInicio", query = "SELECT n FROM Novedadxconcepto n WHERE n.fechaInicio = :fechaInicio")})
public class Novedadxconcepto implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnovedad")
    private Integer idnovedad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Double valor;
    @Column(name = "saldo")
    private Double saldo;
    @Column(name = "tipo_saldo")
    private String tipoSaldo;
    @Column(name = "aplicar_quincenal")
    private String aplicarQuincenal;
    @Column(name = "total_libranza")
    private Double totalLibranza;
    @Column(name = "numero_cuotas")
    private Integer numeroCuotas;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @JoinColumn(name = "empleado_codigo", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Empleado empleadoCodigo;
    @JoinColumn(name = "banco_idbanco", referencedColumnName = "idbanco")
    @ManyToOne
    private Banco bancoIdbanco;
    @JoinColumn(name = "concepto_idconcepto", referencedColumnName = "idconcepto")
    @ManyToOne(optional = false)
    private Concepto conceptoIdconcepto;

    public Novedadxconcepto() {
    }

    public Novedadxconcepto(Integer idnovedad) {
        this.idnovedad = idnovedad;
    }

    public Integer getIdnovedad() {
        return idnovedad;
    }

    public void setIdnovedad(Integer idnovedad) {
        Integer oldIdnovedad = this.idnovedad;
        this.idnovedad = idnovedad;
        changeSupport.firePropertyChange("idnovedad", oldIdnovedad, idnovedad);
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        Double oldValor = this.valor;
        this.valor = valor;
        changeSupport.firePropertyChange("valor", oldValor, valor);
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        Double oldSaldo = this.saldo;
        this.saldo = saldo;
        changeSupport.firePropertyChange("saldo", oldSaldo, saldo);
    }

    public String getTipoSaldo() {
        return tipoSaldo;
    }

    public void setTipoSaldo(String tipoSaldo) {
        String oldTipoSaldo = this.tipoSaldo;
        this.tipoSaldo = tipoSaldo;
        changeSupport.firePropertyChange("tipoSaldo", oldTipoSaldo, tipoSaldo);
    }

    public String getAplicarQuincenal() {
        return aplicarQuincenal;
    }

    public void setAplicarQuincenal(String aplicarQuincenal) {
        String oldAplicarQuincenal = this.aplicarQuincenal;
        this.aplicarQuincenal = aplicarQuincenal;
        changeSupport.firePropertyChange("aplicarQuincenal", oldAplicarQuincenal, aplicarQuincenal);
    }

    public Double getTotalLibranza() {
        return totalLibranza;
    }

    public void setTotalLibranza(Double totalLibranza) {
         Double oldTotalLibranza = this.totalLibranza;
        this.totalLibranza = totalLibranza;
        changeSupport.firePropertyChange("totalLibranza", oldTotalLibranza, totalLibranza);
    }

    public Integer getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(Integer numeroCuotas) {
        Integer oldNumeroCuotas = this.numeroCuotas;
        this.numeroCuotas = numeroCuotas;
        changeSupport.firePropertyChange("numeroCuotas", oldNumeroCuotas, numeroCuotas);
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        Date oldFechaInicio = this.fechaInicio;
        this.fechaInicio = fechaInicio;
        changeSupport.firePropertyChange("fechaInicio", oldFechaInicio, fechaInicio);
    }

    public Empleado getEmpleadoCodigo() {
        return empleadoCodigo;
    }

    public void setEmpleadoCodigo(Empleado empleadoCodigo) {
        Empleado oldEmpleadoCodigo = this.empleadoCodigo;
        this.empleadoCodigo = empleadoCodigo;
        changeSupport.firePropertyChange("empleadoCodigo", oldEmpleadoCodigo, empleadoCodigo);
    }

    public Banco getBancoIdbanco() {
        return bancoIdbanco;
    }

    public void setBancoIdbanco(Banco bancoIdbanco) {
        Banco oldBancoIdbanco = this.bancoIdbanco;
        this.bancoIdbanco = bancoIdbanco;
        changeSupport.firePropertyChange("bancoIdbanco", oldBancoIdbanco, bancoIdbanco);
    }

    public Concepto getConceptoIdconcepto() {
        return conceptoIdconcepto;
    }

    public void setConceptoIdconcepto(Concepto conceptoIdconcepto) {
        Concepto oldConceptoIdconcepto = this.conceptoIdconcepto;
        this.conceptoIdconcepto = conceptoIdconcepto;
        changeSupport.firePropertyChange("conceptoIdconcepto", oldConceptoIdconcepto, conceptoIdconcepto);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnovedad != null ? idnovedad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Novedadxconcepto)) {
            return false;
        }
        Novedadxconcepto other = (Novedadxconcepto) object;
        if ((this.idnovedad == null && other.idnovedad != null) || (this.idnovedad != null && !this.idnovedad.equals(other.idnovedad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.udec.modelo.Novedadxconcepto[ idnovedad=" + idnovedad + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
}

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
