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
import com.udec.modelo.Concepto;
import com.udec.modelo.Grupoconcepto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;


/**
 *
 * @author Oscar
 */
public class GrupoconceptoJpaController implements Serializable {

    public GrupoconceptoJpaController( ) {
      
    }
   

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Grupoconcepto grupoconcepto) {
        if (grupoconcepto.getConceptoList() == null) {
            grupoconcepto.setConceptoList(new ArrayList<Concepto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Concepto> attachedConceptoList = new ArrayList<Concepto>();
            for (Concepto conceptoListConceptoToAttach : grupoconcepto.getConceptoList()) {
                conceptoListConceptoToAttach = em.getReference(conceptoListConceptoToAttach.getClass(), conceptoListConceptoToAttach.getIdconcepto());
                attachedConceptoList.add(conceptoListConceptoToAttach);
            }
            grupoconcepto.setConceptoList(attachedConceptoList);
            em.persist(grupoconcepto);
            for (Concepto conceptoListConcepto : grupoconcepto.getConceptoList()) {
                Grupoconcepto oldGrupoconceptoIdgrupoconceptoOfConceptoListConcepto = conceptoListConcepto.getGrupoconceptoIdgrupoconcepto();
                conceptoListConcepto.setGrupoconceptoIdgrupoconcepto(grupoconcepto);
                conceptoListConcepto = em.merge(conceptoListConcepto);
                if (oldGrupoconceptoIdgrupoconceptoOfConceptoListConcepto != null) {
                    oldGrupoconceptoIdgrupoconceptoOfConceptoListConcepto.getConceptoList().remove(conceptoListConcepto);
                    oldGrupoconceptoIdgrupoconceptoOfConceptoListConcepto = em.merge(oldGrupoconceptoIdgrupoconceptoOfConceptoListConcepto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grupoconcepto grupoconcepto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupoconcepto persistentGrupoconcepto = em.find(Grupoconcepto.class, grupoconcepto.getIdgrupoconcepto());
            List<Concepto> conceptoListOld = persistentGrupoconcepto.getConceptoList();
            List<Concepto> conceptoListNew = grupoconcepto.getConceptoList();
            List<Concepto> attachedConceptoListNew = new ArrayList<Concepto>();
            for (Concepto conceptoListNewConceptoToAttach : conceptoListNew) {
                conceptoListNewConceptoToAttach = em.getReference(conceptoListNewConceptoToAttach.getClass(), conceptoListNewConceptoToAttach.getIdconcepto());
                attachedConceptoListNew.add(conceptoListNewConceptoToAttach);
            }
            conceptoListNew = attachedConceptoListNew;
            grupoconcepto.setConceptoList(conceptoListNew);
            grupoconcepto = em.merge(grupoconcepto);
            for (Concepto conceptoListOldConcepto : conceptoListOld) {
                if (!conceptoListNew.contains(conceptoListOldConcepto)) {
                    conceptoListOldConcepto.setGrupoconceptoIdgrupoconcepto(null);
                    conceptoListOldConcepto = em.merge(conceptoListOldConcepto);
                }
            }
            for (Concepto conceptoListNewConcepto : conceptoListNew) {
                if (!conceptoListOld.contains(conceptoListNewConcepto)) {
                    Grupoconcepto oldGrupoconceptoIdgrupoconceptoOfConceptoListNewConcepto = conceptoListNewConcepto.getGrupoconceptoIdgrupoconcepto();
                    conceptoListNewConcepto.setGrupoconceptoIdgrupoconcepto(grupoconcepto);
                    conceptoListNewConcepto = em.merge(conceptoListNewConcepto);
                    if (oldGrupoconceptoIdgrupoconceptoOfConceptoListNewConcepto != null && !oldGrupoconceptoIdgrupoconceptoOfConceptoListNewConcepto.equals(grupoconcepto)) {
                        oldGrupoconceptoIdgrupoconceptoOfConceptoListNewConcepto.getConceptoList().remove(conceptoListNewConcepto);
                        oldGrupoconceptoIdgrupoconceptoOfConceptoListNewConcepto = em.merge(oldGrupoconceptoIdgrupoconceptoOfConceptoListNewConcepto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = grupoconcepto.getIdgrupoconcepto();
                if (findGrupoconcepto(id) == null) {
                    throw new NonexistentEntityException("The grupoconcepto with id " + id + " no longer exists.");
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
            Grupoconcepto grupoconcepto;
            try {
                grupoconcepto = em.getReference(Grupoconcepto.class, id);
                grupoconcepto.getIdgrupoconcepto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupoconcepto with id " + id + " no longer exists.", enfe);
            }
            List<Concepto> conceptoList = grupoconcepto.getConceptoList();
            for (Concepto conceptoListConcepto : conceptoList) {
                conceptoListConcepto.setGrupoconceptoIdgrupoconcepto(null);
                conceptoListConcepto = em.merge(conceptoListConcepto);
            }
            em.remove(grupoconcepto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Grupoconcepto> findGrupoconceptoEntities() {
        return findGrupoconceptoEntities(true, -1, -1);
    }

    public List<Grupoconcepto> findGrupoconceptoEntities(int maxResults, int firstResult) {
        return findGrupoconceptoEntities(false, maxResults, firstResult);
    }

    private List<Grupoconcepto> findGrupoconceptoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Grupoconcepto.class));
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

    public Grupoconcepto findGrupoconcepto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grupoconcepto.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoconceptoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Grupoconcepto> rt = cq.from(Grupoconcepto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
