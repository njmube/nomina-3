/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.controlador;

import com.udec.connection.jpaConnection;
import com.udec.controlador.exceptions.NonexistentEntityException;
import com.udec.modelo.Diastrabajados;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.udec.modelo.Periodo;
import com.udec.modelo.Empleado;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Oscar
 */
public class DiastrabajadosJpaController implements Serializable {

    public DiastrabajadosJpaController() {
        
    }


    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();

    }
    public Diastrabajados findBySingle2(String property1, Object m1, String property2, Object m2) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Diastrabajados.class));
        Query q = getEntityManager().createQuery("SELECT c FROM " + Diastrabajados.class.getSimpleName() + " c WHERE c." + property1 + " = :name1 and c." + property2 + " = :name2", Diastrabajados.class);

        q.setParameter("name1", m1);
        q.setParameter("name2", m2);
        return (Diastrabajados) q.getSingleResult();
    }

    
    public void create(Diastrabajados diastrabajados) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Periodo periodoIdperiodo = diastrabajados.getPeriodoIdperiodo();
            if (periodoIdperiodo != null) {
                periodoIdperiodo = em.getReference(periodoIdperiodo.getClass(), periodoIdperiodo.getIdperiodo());
                diastrabajados.setPeriodoIdperiodo(periodoIdperiodo);
            }
            Empleado empleadoCodigo = diastrabajados.getEmpleadoCodigo();
            if (empleadoCodigo != null) {
                empleadoCodigo = em.getReference(empleadoCodigo.getClass(), empleadoCodigo.getCodigo());
                diastrabajados.setEmpleadoCodigo(empleadoCodigo);
            }
            em.persist(diastrabajados);
            if (periodoIdperiodo != null) {
                periodoIdperiodo.getDiastrabajadosList().add(diastrabajados);
                periodoIdperiodo = em.merge(periodoIdperiodo);
            }
            if (empleadoCodigo != null) {
                empleadoCodigo.getDiastrabajadosList().add(diastrabajados);
                empleadoCodigo = em.merge(empleadoCodigo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Diastrabajados diastrabajados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Diastrabajados persistentDiastrabajados = em.find(Diastrabajados.class, diastrabajados.getIddiastrabajados());
            Periodo periodoIdperiodoOld = persistentDiastrabajados.getPeriodoIdperiodo();
            Periodo periodoIdperiodoNew = diastrabajados.getPeriodoIdperiodo();
            Empleado empleadoCodigoOld = persistentDiastrabajados.getEmpleadoCodigo();
            Empleado empleadoCodigoNew = diastrabajados.getEmpleadoCodigo();
            if (periodoIdperiodoNew != null) {
                periodoIdperiodoNew = em.getReference(periodoIdperiodoNew.getClass(), periodoIdperiodoNew.getIdperiodo());
                diastrabajados.setPeriodoIdperiodo(periodoIdperiodoNew);
            }
            if (empleadoCodigoNew != null) {
                empleadoCodigoNew = em.getReference(empleadoCodigoNew.getClass(), empleadoCodigoNew.getCodigo());
                diastrabajados.setEmpleadoCodigo(empleadoCodigoNew);
            }
            diastrabajados = em.merge(diastrabajados);
            if (periodoIdperiodoOld != null && !periodoIdperiodoOld.equals(periodoIdperiodoNew)) {
                periodoIdperiodoOld.getDiastrabajadosList().remove(diastrabajados);
                periodoIdperiodoOld = em.merge(periodoIdperiodoOld);
            }
            if (periodoIdperiodoNew != null && !periodoIdperiodoNew.equals(periodoIdperiodoOld)) {
                periodoIdperiodoNew.getDiastrabajadosList().add(diastrabajados);
                periodoIdperiodoNew = em.merge(periodoIdperiodoNew);
            }
            if (empleadoCodigoOld != null && !empleadoCodigoOld.equals(empleadoCodigoNew)) {
                empleadoCodigoOld.getDiastrabajadosList().remove(diastrabajados);
                empleadoCodigoOld = em.merge(empleadoCodigoOld);
            }
            if (empleadoCodigoNew != null && !empleadoCodigoNew.equals(empleadoCodigoOld)) {
                empleadoCodigoNew.getDiastrabajadosList().add(diastrabajados);
                empleadoCodigoNew = em.merge(empleadoCodigoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = diastrabajados.getIddiastrabajados();
                if (findDiastrabajados(id) == null) {
                    throw new NonexistentEntityException("The diastrabajados with id " + id + " no longer exists.");
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
            Diastrabajados diastrabajados;
            try {
                diastrabajados = em.getReference(Diastrabajados.class, id);
                diastrabajados.getIddiastrabajados();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The diastrabajados with id " + id + " no longer exists.", enfe);
            }
            Periodo periodoIdperiodo = diastrabajados.getPeriodoIdperiodo();
            if (periodoIdperiodo != null) {
                periodoIdperiodo.getDiastrabajadosList().remove(diastrabajados);
                periodoIdperiodo = em.merge(periodoIdperiodo);
            }
            Empleado empleadoCodigo = diastrabajados.getEmpleadoCodigo();
            if (empleadoCodigo != null) {
                empleadoCodigo.getDiastrabajadosList().remove(diastrabajados);
                empleadoCodigo = em.merge(empleadoCodigo);
            }
            em.remove(diastrabajados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Diastrabajados> findDiastrabajadosEntities() {
        return findDiastrabajadosEntities(true, -1, -1);
    }

    public List<Diastrabajados> findDiastrabajadosEntities(int maxResults, int firstResult) {
        return findDiastrabajadosEntities(false, maxResults, firstResult);
    }

    private List<Diastrabajados> findDiastrabajadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Diastrabajados.class));
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

    public Diastrabajados findDiastrabajados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Diastrabajados.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiastrabajadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Diastrabajados> rt = cq.from(Diastrabajados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
