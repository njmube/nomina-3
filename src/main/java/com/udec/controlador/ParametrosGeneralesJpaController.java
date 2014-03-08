/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.controlador;

import com.udec.connection.jpaConnection;
import com.udec.controlador.exceptions.NonexistentEntityException;
import com.udec.controlador.exceptions.PreexistingEntityException;
import com.udec.modelo.ParametrosGenerales;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Oscar
 */
public class ParametrosGeneralesJpaController implements Serializable {

    public ParametrosGeneralesJpaController( ) {
        
    }
  

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();

    }

    public void create(ParametrosGenerales parametrosGenerales) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(parametrosGenerales);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findParametrosGenerales(parametrosGenerales.getIdParametrosgenerales()) != null) {
                throw new PreexistingEntityException("ParametrosGenerales " + parametrosGenerales + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ParametrosGenerales parametrosGenerales) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            parametrosGenerales = em.merge(parametrosGenerales);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = parametrosGenerales.getIdParametrosgenerales();
                if (findParametrosGenerales(id) == null) {
                    throw new NonexistentEntityException("The parametrosGenerales with id " + id + " no longer exists.");
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
            ParametrosGenerales parametrosGenerales;
            try {
                parametrosGenerales = em.getReference(ParametrosGenerales.class, id);
                parametrosGenerales.getIdParametrosgenerales();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The parametrosGenerales with id " + id + " no longer exists.", enfe);
            }
            em.remove(parametrosGenerales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ParametrosGenerales> findParametrosGeneralesEntities() {
        return findParametrosGeneralesEntities(true, -1, -1);
    }

    public List<ParametrosGenerales> findParametrosGeneralesEntities(int maxResults, int firstResult) {
        return findParametrosGeneralesEntities(false, maxResults, firstResult);
    }

    private List<ParametrosGenerales> findParametrosGeneralesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ParametrosGenerales.class));
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

    public ParametrosGenerales findParametrosGenerales(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ParametrosGenerales.class, id);
        } finally {
            em.close();
        }
    }

    public int getParametrosGeneralesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ParametrosGenerales> rt = cq.from(ParametrosGenerales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
