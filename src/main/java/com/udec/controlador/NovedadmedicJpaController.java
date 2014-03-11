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
import com.udec.modelo.Novedadmedic;
import com.udec.modelo.Periodo;
import java.util.List;
import javax.persistence.EntityManager;


/**
 *
 * @author Oscar
 */
public class NovedadmedicJpaController implements Serializable {

    public NovedadmedicJpaController( ) {
    
    }


    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();

    }
        public List<Novedadmedic> findByPeriodoEmpleadoO(Periodo p, Empleado e, String origen) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Novedadmedic.class));
        Query q = getEntityManager().createQuery("SELECT c FROM " + Novedadmedic.class.getSimpleName() + " c WHERE c.empleadoCodigo=:empleado AND c.tipo=:tipo AND ((c.fechaInicio BETWEEN :startDate AND :endDate) OR (c.fechaFinal BETWEEN :startDate AND :endDate))", Novedadmedic.class);
        q.setParameter("startDate", p.getDesde());
        q.setParameter("endDate", p.getHasta());
        q.setParameter("empleado", e);
        q.setParameter("tipo", origen);
        return  q.getResultList();
        }
   
    
    public void create(Novedadmedic novedadmedic) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleadoCodigo = novedadmedic.getEmpleadoCodigo();
            if (empleadoCodigo != null) {
                empleadoCodigo = em.getReference(empleadoCodigo.getClass(), empleadoCodigo.getCodigo());
                novedadmedic.setEmpleadoCodigo(empleadoCodigo);
            }
            em.persist(novedadmedic);
            if (empleadoCodigo != null) {
                empleadoCodigo.getNovedadmedicList().add(novedadmedic);
                empleadoCodigo = em.merge(empleadoCodigo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Novedadmedic novedadmedic) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Novedadmedic persistentNovedadmedic = em.find(Novedadmedic.class, novedadmedic.getIdnovedadmedic());
            Empleado empleadoCodigoOld = persistentNovedadmedic.getEmpleadoCodigo();
            Empleado empleadoCodigoNew = novedadmedic.getEmpleadoCodigo();
            if (empleadoCodigoNew != null) {
                empleadoCodigoNew = em.getReference(empleadoCodigoNew.getClass(), empleadoCodigoNew.getCodigo());
                novedadmedic.setEmpleadoCodigo(empleadoCodigoNew);
            }
            novedadmedic = em.merge(novedadmedic);
            if (empleadoCodigoOld != null && !empleadoCodigoOld.equals(empleadoCodigoNew)) {
                empleadoCodigoOld.getNovedadmedicList().remove(novedadmedic);
                empleadoCodigoOld = em.merge(empleadoCodigoOld);
            }
            if (empleadoCodigoNew != null && !empleadoCodigoNew.equals(empleadoCodigoOld)) {
                empleadoCodigoNew.getNovedadmedicList().add(novedadmedic);
                empleadoCodigoNew = em.merge(empleadoCodigoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = novedadmedic.getIdnovedadmedic();
                if (findNovedadmedic(id) == null) {
                    throw new NonexistentEntityException("The novedadmedic with id " + id + " no longer exists.");
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
            Novedadmedic novedadmedic;
            try {
                novedadmedic = em.getReference(Novedadmedic.class, id);
                novedadmedic.getIdnovedadmedic();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The novedadmedic with id " + id + " no longer exists.", enfe);
            }
            Empleado empleadoCodigo = novedadmedic.getEmpleadoCodigo();
            if (empleadoCodigo != null) {
                empleadoCodigo.getNovedadmedicList().remove(novedadmedic);
                empleadoCodigo = em.merge(empleadoCodigo);
            }
            em.remove(novedadmedic);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Novedadmedic> findNovedadmedicEntities() {
        return findNovedadmedicEntities(true, -1, -1);
    }

    public List<Novedadmedic> findNovedadmedicEntities(int maxResults, int firstResult) {
        return findNovedadmedicEntities(false, maxResults, firstResult);
    }

    private List<Novedadmedic> findNovedadmedicEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Novedadmedic.class));
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

    public Novedadmedic findNovedadmedic(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Novedadmedic.class, id);
        } finally {
            em.close();
        }
    }

    public int getNovedadmedicCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Novedadmedic> rt = cq.from(Novedadmedic.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
