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
import com.udec.modelo.Banco;
import com.udec.modelo.Concepto;
import com.udec.modelo.Novedadxconcepto;
import com.udec.modelo.Periodo;
import java.util.List;
import javax.persistence.EntityManager;


/**
 *
 * @author Oscar
 */
public class NovedadxconceptoJpaController implements Serializable {

    public NovedadxconceptoJpaController( ) {
        
    }
   
    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();

    }

    public List<Novedadxconcepto> findByList3(String property1, Object m1, String property2, Object m2, Periodo p) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Novedadxconcepto.class));
        Query q = getEntityManager().createQuery("SELECT c FROM " + Novedadxconcepto.class.getSimpleName() + " c WHERE c." + property1 + " = :name1 and c." + property2 + " = :name2 and ((c.fechaInicio BETWEEN :startDate AND :endDate) OR ((c.fechaInicio <:startDate) AND (c.tipoSaldo='Indefinido')))", Novedadxconcepto.class);
        q.setParameter("name1", m1);
        q.setParameter("name2", m2);
        q.setParameter("startDate", p.getDesde());
        q.setParameter("endDate", p.getHasta());
        return q.getResultList();
    }                                         //EmpleadoCodigo             , Concepto.tipo             periodo
    public List<Novedadxconcepto> findByList4(String property1, Object m1, String property2, Object m2, Periodo p) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Novedadxconcepto.class));
        Query q = getEntityManager().createQuery("SELECT c FROM " + Novedadxconcepto.class.getSimpleName() + " c "
                + "WHERE c." + property1 + " = :name1 and c." + property2 + " = :name2 and "
                + "((c.fechaInicio BETWEEN :startDate AND :endDate) "
                + "OR ((c.fechaInicio <:startDate) AND (c.tipoSaldo='Indefinido'))"
                + "OR ((c.fechaInicio <:startDate) AND (c.tipoSaldo='Hasta saldo') AND c.saldo > 0))", Novedadxconcepto.class);
        q.setParameter("name1", m1);
        q.setParameter("name2", m2);
        q.setParameter("startDate", p.getDesde());
        q.setParameter("endDate", p.getHasta());
        return q.getResultList();
    }
    
    public void create(Novedadxconcepto novedadxconcepto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleadoCodigo = novedadxconcepto.getEmpleadoCodigo();
            if (empleadoCodigo != null) {
                empleadoCodigo = em.getReference(empleadoCodigo.getClass(), empleadoCodigo.getCodigo());
                novedadxconcepto.setEmpleadoCodigo(empleadoCodigo);
            }
            Banco bancoIdbanco = novedadxconcepto.getBancoIdbanco();
            if (bancoIdbanco != null) {
                bancoIdbanco = em.getReference(bancoIdbanco.getClass(), bancoIdbanco.getIdbanco());
                novedadxconcepto.setBancoIdbanco(bancoIdbanco);
            }
            Concepto conceptoIdconcepto = novedadxconcepto.getConceptoIdconcepto();
            if (conceptoIdconcepto != null) {
                conceptoIdconcepto = em.getReference(conceptoIdconcepto.getClass(), conceptoIdconcepto.getIdconcepto());
                novedadxconcepto.setConceptoIdconcepto(conceptoIdconcepto);
            }
            em.persist(novedadxconcepto);
            if (empleadoCodigo != null) {
                empleadoCodigo.getNovedadxconceptoList().add(novedadxconcepto);
                empleadoCodigo = em.merge(empleadoCodigo);
            }
            if (bancoIdbanco != null) {
                bancoIdbanco.getNovedadxconceptoList().add(novedadxconcepto);
                bancoIdbanco = em.merge(bancoIdbanco);
            }
            if (conceptoIdconcepto != null) {
                conceptoIdconcepto.getNovedadxconceptoList().add(novedadxconcepto);
                conceptoIdconcepto = em.merge(conceptoIdconcepto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Novedadxconcepto novedadxconcepto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Novedadxconcepto persistentNovedadxconcepto = em.find(Novedadxconcepto.class, novedadxconcepto.getIdnovedad());
            Empleado empleadoCodigoOld = persistentNovedadxconcepto.getEmpleadoCodigo();
            Empleado empleadoCodigoNew = novedadxconcepto.getEmpleadoCodigo();
            Banco bancoIdbancoOld = persistentNovedadxconcepto.getBancoIdbanco();
            Banco bancoIdbancoNew = novedadxconcepto.getBancoIdbanco();
            Concepto conceptoIdconceptoOld = persistentNovedadxconcepto.getConceptoIdconcepto();
            Concepto conceptoIdconceptoNew = novedadxconcepto.getConceptoIdconcepto();
            if (empleadoCodigoNew != null) {
                empleadoCodigoNew = em.getReference(empleadoCodigoNew.getClass(), empleadoCodigoNew.getCodigo());
                novedadxconcepto.setEmpleadoCodigo(empleadoCodigoNew);
            }
            if (bancoIdbancoNew != null) {
                bancoIdbancoNew = em.getReference(bancoIdbancoNew.getClass(), bancoIdbancoNew.getIdbanco());
                novedadxconcepto.setBancoIdbanco(bancoIdbancoNew);
            }
            if (conceptoIdconceptoNew != null) {
                conceptoIdconceptoNew = em.getReference(conceptoIdconceptoNew.getClass(), conceptoIdconceptoNew.getIdconcepto());
                novedadxconcepto.setConceptoIdconcepto(conceptoIdconceptoNew);
            }
            novedadxconcepto = em.merge(novedadxconcepto);
            if (empleadoCodigoOld != null && !empleadoCodigoOld.equals(empleadoCodigoNew)) {
                empleadoCodigoOld.getNovedadxconceptoList().remove(novedadxconcepto);
                empleadoCodigoOld = em.merge(empleadoCodigoOld);
            }
            if (empleadoCodigoNew != null && !empleadoCodigoNew.equals(empleadoCodigoOld)) {
                empleadoCodigoNew.getNovedadxconceptoList().add(novedadxconcepto);
                empleadoCodigoNew = em.merge(empleadoCodigoNew);
            }
            if (bancoIdbancoOld != null && !bancoIdbancoOld.equals(bancoIdbancoNew)) {
                bancoIdbancoOld.getNovedadxconceptoList().remove(novedadxconcepto);
                bancoIdbancoOld = em.merge(bancoIdbancoOld);
            }
            if (bancoIdbancoNew != null && !bancoIdbancoNew.equals(bancoIdbancoOld)) {
                bancoIdbancoNew.getNovedadxconceptoList().add(novedadxconcepto);
                bancoIdbancoNew = em.merge(bancoIdbancoNew);
            }
            if (conceptoIdconceptoOld != null && !conceptoIdconceptoOld.equals(conceptoIdconceptoNew)) {
                conceptoIdconceptoOld.getNovedadxconceptoList().remove(novedadxconcepto);
                conceptoIdconceptoOld = em.merge(conceptoIdconceptoOld);
            }
            if (conceptoIdconceptoNew != null && !conceptoIdconceptoNew.equals(conceptoIdconceptoOld)) {
                conceptoIdconceptoNew.getNovedadxconceptoList().add(novedadxconcepto);
                conceptoIdconceptoNew = em.merge(conceptoIdconceptoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = novedadxconcepto.getIdnovedad();
                if (findNovedadxconcepto(id) == null) {
                    throw new NonexistentEntityException("The novedadxconcepto with id " + id + " no longer exists.");
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
            Novedadxconcepto novedadxconcepto;
            try {
                novedadxconcepto = em.getReference(Novedadxconcepto.class, id);
                novedadxconcepto.getIdnovedad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The novedadxconcepto with id " + id + " no longer exists.", enfe);
            }
            Empleado empleadoCodigo = novedadxconcepto.getEmpleadoCodigo();
            if (empleadoCodigo != null) {
                empleadoCodigo.getNovedadxconceptoList().remove(novedadxconcepto);
                empleadoCodigo = em.merge(empleadoCodigo);
            }
            Banco bancoIdbanco = novedadxconcepto.getBancoIdbanco();
            if (bancoIdbanco != null) {
                bancoIdbanco.getNovedadxconceptoList().remove(novedadxconcepto);
                bancoIdbanco = em.merge(bancoIdbanco);
            }
            Concepto conceptoIdconcepto = novedadxconcepto.getConceptoIdconcepto();
            if (conceptoIdconcepto != null) {
                conceptoIdconcepto.getNovedadxconceptoList().remove(novedadxconcepto);
                conceptoIdconcepto = em.merge(conceptoIdconcepto);
            }
            em.remove(novedadxconcepto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Novedadxconcepto> findNovedadxconceptoEntities() {
        return findNovedadxconceptoEntities(true, -1, -1);
    }

    public List<Novedadxconcepto> findNovedadxconceptoEntities(int maxResults, int firstResult) {
        return findNovedadxconceptoEntities(false, maxResults, firstResult);
    }

    private List<Novedadxconcepto> findNovedadxconceptoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Novedadxconcepto.class));
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

    public Novedadxconcepto findNovedadxconcepto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Novedadxconcepto.class, id);
        } finally {
            em.close();
        }
    }

    public int getNovedadxconceptoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Novedadxconcepto> rt = cq.from(Novedadxconcepto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
