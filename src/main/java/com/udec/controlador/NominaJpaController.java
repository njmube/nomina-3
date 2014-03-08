/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.controlador;

import com.udec.connection.jpaConnection;
import com.udec.controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.udec.modelo.Empleado;
import com.udec.modelo.Concepto;
import com.udec.modelo.Nomina;
import com.udec.modelo.Periodo;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Oscar
 */
public class NominaJpaController implements Serializable {

    public NominaJpaController( ) {
      
    }
   

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Nomina nomina) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleadoCodigo = nomina.getEmpleadoCodigo();
            if (empleadoCodigo != null) {
                empleadoCodigo = em.getReference(empleadoCodigo.getClass(), empleadoCodigo.getCodigo());
                nomina.setEmpleadoCodigo(empleadoCodigo);
            }
            Concepto conceptoIdconcepto = nomina.getConceptoIdconcepto();
            if (conceptoIdconcepto != null) {
                conceptoIdconcepto = em.getReference(conceptoIdconcepto.getClass(), conceptoIdconcepto.getIdconcepto());
                nomina.setConceptoIdconcepto(conceptoIdconcepto);
            }
            Periodo periodoIdperiodo = nomina.getPeriodoIdperiodo();
            if (periodoIdperiodo != null) {
                periodoIdperiodo = em.getReference(periodoIdperiodo.getClass(), periodoIdperiodo.getIdperiodo());
                nomina.setPeriodoIdperiodo(periodoIdperiodo);
            }
            em.persist(nomina);
            if (empleadoCodigo != null) {
                empleadoCodigo.getNominaList().add(nomina);
                empleadoCodigo = em.merge(empleadoCodigo);
            }
            if (conceptoIdconcepto != null) {
                conceptoIdconcepto.getNominaList().add(nomina);
                conceptoIdconcepto = em.merge(conceptoIdconcepto);
            }
            if (periodoIdperiodo != null) {
                periodoIdperiodo.getNominaList().add(nomina);
                periodoIdperiodo = em.merge(periodoIdperiodo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nomina nomina) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nomina persistentNomina = em.find(Nomina.class, nomina.getIdnomina());
            Empleado empleadoCodigoOld = persistentNomina.getEmpleadoCodigo();
            Empleado empleadoCodigoNew = nomina.getEmpleadoCodigo();
            Concepto conceptoIdconceptoOld = persistentNomina.getConceptoIdconcepto();
            Concepto conceptoIdconceptoNew = nomina.getConceptoIdconcepto();
            Periodo periodoIdperiodoOld = persistentNomina.getPeriodoIdperiodo();
            Periodo periodoIdperiodoNew = nomina.getPeriodoIdperiodo();
            if (empleadoCodigoNew != null) {
                empleadoCodigoNew = em.getReference(empleadoCodigoNew.getClass(), empleadoCodigoNew.getCodigo());
                nomina.setEmpleadoCodigo(empleadoCodigoNew);
            }
            if (conceptoIdconceptoNew != null) {
                conceptoIdconceptoNew = em.getReference(conceptoIdconceptoNew.getClass(), conceptoIdconceptoNew.getIdconcepto());
                nomina.setConceptoIdconcepto(conceptoIdconceptoNew);
            }
            if (periodoIdperiodoNew != null) {
                periodoIdperiodoNew = em.getReference(periodoIdperiodoNew.getClass(), periodoIdperiodoNew.getIdperiodo());
                nomina.setPeriodoIdperiodo(periodoIdperiodoNew);
            }
            nomina = em.merge(nomina);
            if (empleadoCodigoOld != null && !empleadoCodigoOld.equals(empleadoCodigoNew)) {
                empleadoCodigoOld.getNominaList().remove(nomina);
                empleadoCodigoOld = em.merge(empleadoCodigoOld);
            }
            if (empleadoCodigoNew != null && !empleadoCodigoNew.equals(empleadoCodigoOld)) {
                empleadoCodigoNew.getNominaList().add(nomina);
                empleadoCodigoNew = em.merge(empleadoCodigoNew);
            }
            if (conceptoIdconceptoOld != null && !conceptoIdconceptoOld.equals(conceptoIdconceptoNew)) {
                conceptoIdconceptoOld.getNominaList().remove(nomina);
                conceptoIdconceptoOld = em.merge(conceptoIdconceptoOld);
            }
            if (conceptoIdconceptoNew != null && !conceptoIdconceptoNew.equals(conceptoIdconceptoOld)) {
                conceptoIdconceptoNew.getNominaList().add(nomina);
                conceptoIdconceptoNew = em.merge(conceptoIdconceptoNew);
            }
            if (periodoIdperiodoOld != null && !periodoIdperiodoOld.equals(periodoIdperiodoNew)) {
                periodoIdperiodoOld.getNominaList().remove(nomina);
                periodoIdperiodoOld = em.merge(periodoIdperiodoOld);
            }
            if (periodoIdperiodoNew != null && !periodoIdperiodoNew.equals(periodoIdperiodoOld)) {
                periodoIdperiodoNew.getNominaList().add(nomina);
                periodoIdperiodoNew = em.merge(periodoIdperiodoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nomina.getIdnomina();
                if (findNomina(id) == null) {
                    throw new NonexistentEntityException("The nomina with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nomina nomina;
            try {
                nomina = em.getReference(Nomina.class, id);
                nomina.getIdnomina();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nomina with id " + id + " no longer exists.", enfe);
            }
            Empleado empleadoCodigo = nomina.getEmpleadoCodigo();
            if (empleadoCodigo != null) {
                empleadoCodigo.getNominaList().remove(nomina);
                empleadoCodigo = em.merge(empleadoCodigo);
            }
            Concepto conceptoIdconcepto = nomina.getConceptoIdconcepto();
            if (conceptoIdconcepto != null) {
                conceptoIdconcepto.getNominaList().remove(nomina);
                conceptoIdconcepto = em.merge(conceptoIdconcepto);
            }
            Periodo periodoIdperiodo = nomina.getPeriodoIdperiodo();
            if (periodoIdperiodo != null) {
                periodoIdperiodo.getNominaList().remove(nomina);
                periodoIdperiodo = em.merge(periodoIdperiodo);
            }
            em.remove(nomina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nomina> findNominaEntities() {
        return findNominaEntities(true, -1, -1);
    }

    public List<Nomina> findNominaEntities(int maxResults, int firstResult) {
        return findNominaEntities(false, maxResults, firstResult);
    }

    private List<Nomina> findNominaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Nomina.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Nomina findNomina(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nomina.class, id);
        } finally {
            em.close();
        }
    }

    public int getNominaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nomina> rt = cq.from(Nomina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
