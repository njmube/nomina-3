/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.controlador;

import com.udec.connection.jpaConnection;
import com.udec.controlador.exceptions.IllegalOrphanException;
import com.udec.controlador.exceptions.NonexistentEntityException;
import com.udec.modelo.Concepto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.udec.modelo.Nomina;
import java.util.ArrayList;
import java.util.List;
import com.udec.modelo.Novedadxconcepto;
import javax.persistence.EntityManager;


/**
 *
 * @author Oscar
 */
public class ConceptoJpaController implements Serializable {

    public ConceptoJpaController() {
       
    }


    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();

    }

    public void create(Concepto concepto) {
        if (concepto.getNominaList() == null) {
            concepto.setNominaList(new ArrayList<Nomina>());
        }
        if (concepto.getNovedadxconceptoList() == null) {
            concepto.setNovedadxconceptoList(new ArrayList<Novedadxconcepto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Nomina> attachedNominaList = new ArrayList<Nomina>();
            for (Nomina nominaListNominaToAttach : concepto.getNominaList()) {
                nominaListNominaToAttach = em.getReference(nominaListNominaToAttach.getClass(), nominaListNominaToAttach.getIdnomina());
                attachedNominaList.add(nominaListNominaToAttach);
            }
            concepto.setNominaList(attachedNominaList);
            List<Novedadxconcepto> attachedNovedadxconceptoList = new ArrayList<Novedadxconcepto>();
            for (Novedadxconcepto novedadxconceptoListNovedadxconceptoToAttach : concepto.getNovedadxconceptoList()) {
                novedadxconceptoListNovedadxconceptoToAttach = em.getReference(novedadxconceptoListNovedadxconceptoToAttach.getClass(), novedadxconceptoListNovedadxconceptoToAttach.getIdnovedad());
                attachedNovedadxconceptoList.add(novedadxconceptoListNovedadxconceptoToAttach);
            }
            concepto.setNovedadxconceptoList(attachedNovedadxconceptoList);
            em.persist(concepto);
            for (Nomina nominaListNomina : concepto.getNominaList()) {
                Concepto oldConceptoIdconceptoOfNominaListNomina = nominaListNomina.getConceptoIdconcepto();
                nominaListNomina.setConceptoIdconcepto(concepto);
                nominaListNomina = em.merge(nominaListNomina);
                if (oldConceptoIdconceptoOfNominaListNomina != null) {
                    oldConceptoIdconceptoOfNominaListNomina.getNominaList().remove(nominaListNomina);
                    oldConceptoIdconceptoOfNominaListNomina = em.merge(oldConceptoIdconceptoOfNominaListNomina);
                }
            }
            for (Novedadxconcepto novedadxconceptoListNovedadxconcepto : concepto.getNovedadxconceptoList()) {
                Concepto oldConceptoIdconceptoOfNovedadxconceptoListNovedadxconcepto = novedadxconceptoListNovedadxconcepto.getConceptoIdconcepto();
                novedadxconceptoListNovedadxconcepto.setConceptoIdconcepto(concepto);
                novedadxconceptoListNovedadxconcepto = em.merge(novedadxconceptoListNovedadxconcepto);
                if (oldConceptoIdconceptoOfNovedadxconceptoListNovedadxconcepto != null) {
                    oldConceptoIdconceptoOfNovedadxconceptoListNovedadxconcepto.getNovedadxconceptoList().remove(novedadxconceptoListNovedadxconcepto);
                    oldConceptoIdconceptoOfNovedadxconceptoListNovedadxconcepto = em.merge(oldConceptoIdconceptoOfNovedadxconceptoListNovedadxconcepto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Concepto concepto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Concepto persistentConcepto = em.find(Concepto.class, concepto.getIdconcepto());
            List<Nomina> nominaListOld = persistentConcepto.getNominaList();
            List<Nomina> nominaListNew = concepto.getNominaList();
            List<Novedadxconcepto> novedadxconceptoListOld = persistentConcepto.getNovedadxconceptoList();
            List<Novedadxconcepto> novedadxconceptoListNew = concepto.getNovedadxconceptoList();
            List<String> illegalOrphanMessages = null;
            for (Nomina nominaListOldNomina : nominaListOld) {
                if (!nominaListNew.contains(nominaListOldNomina)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Nomina " + nominaListOldNomina + " since its conceptoIdconcepto field is not nullable.");
                }
            }
            for (Novedadxconcepto novedadxconceptoListOldNovedadxconcepto : novedadxconceptoListOld) {
                if (!novedadxconceptoListNew.contains(novedadxconceptoListOldNovedadxconcepto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Novedadxconcepto " + novedadxconceptoListOldNovedadxconcepto + " since its conceptoIdconcepto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Nomina> attachedNominaListNew = new ArrayList<Nomina>();
            for (Nomina nominaListNewNominaToAttach : nominaListNew) {
                nominaListNewNominaToAttach = em.getReference(nominaListNewNominaToAttach.getClass(), nominaListNewNominaToAttach.getIdnomina());
                attachedNominaListNew.add(nominaListNewNominaToAttach);
            }
            nominaListNew = attachedNominaListNew;
            concepto.setNominaList(nominaListNew);
            List<Novedadxconcepto> attachedNovedadxconceptoListNew = new ArrayList<Novedadxconcepto>();
            for (Novedadxconcepto novedadxconceptoListNewNovedadxconceptoToAttach : novedadxconceptoListNew) {
                novedadxconceptoListNewNovedadxconceptoToAttach = em.getReference(novedadxconceptoListNewNovedadxconceptoToAttach.getClass(), novedadxconceptoListNewNovedadxconceptoToAttach.getIdnovedad());
                attachedNovedadxconceptoListNew.add(novedadxconceptoListNewNovedadxconceptoToAttach);
            }
            novedadxconceptoListNew = attachedNovedadxconceptoListNew;
            concepto.setNovedadxconceptoList(novedadxconceptoListNew);
            concepto = em.merge(concepto);
            for (Nomina nominaListNewNomina : nominaListNew) {
                if (!nominaListOld.contains(nominaListNewNomina)) {
                    Concepto oldConceptoIdconceptoOfNominaListNewNomina = nominaListNewNomina.getConceptoIdconcepto();
                    nominaListNewNomina.setConceptoIdconcepto(concepto);
                    nominaListNewNomina = em.merge(nominaListNewNomina);
                    if (oldConceptoIdconceptoOfNominaListNewNomina != null && !oldConceptoIdconceptoOfNominaListNewNomina.equals(concepto)) {
                        oldConceptoIdconceptoOfNominaListNewNomina.getNominaList().remove(nominaListNewNomina);
                        oldConceptoIdconceptoOfNominaListNewNomina = em.merge(oldConceptoIdconceptoOfNominaListNewNomina);
                    }
                }
            }
            for (Novedadxconcepto novedadxconceptoListNewNovedadxconcepto : novedadxconceptoListNew) {
                if (!novedadxconceptoListOld.contains(novedadxconceptoListNewNovedadxconcepto)) {
                    Concepto oldConceptoIdconceptoOfNovedadxconceptoListNewNovedadxconcepto = novedadxconceptoListNewNovedadxconcepto.getConceptoIdconcepto();
                    novedadxconceptoListNewNovedadxconcepto.setConceptoIdconcepto(concepto);
                    novedadxconceptoListNewNovedadxconcepto = em.merge(novedadxconceptoListNewNovedadxconcepto);
                    if (oldConceptoIdconceptoOfNovedadxconceptoListNewNovedadxconcepto != null && !oldConceptoIdconceptoOfNovedadxconceptoListNewNovedadxconcepto.equals(concepto)) {
                        oldConceptoIdconceptoOfNovedadxconceptoListNewNovedadxconcepto.getNovedadxconceptoList().remove(novedadxconceptoListNewNovedadxconcepto);
                        oldConceptoIdconceptoOfNovedadxconceptoListNewNovedadxconcepto = em.merge(oldConceptoIdconceptoOfNovedadxconceptoListNewNovedadxconcepto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = concepto.getIdconcepto();
                if (findConcepto(id) == null) {
                    throw new NonexistentEntityException("The concepto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Concepto concepto;
            try {
                concepto = em.getReference(Concepto.class, id);
                concepto.getIdconcepto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The concepto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Nomina> nominaListOrphanCheck = concepto.getNominaList();
            for (Nomina nominaListOrphanCheckNomina : nominaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Concepto (" + concepto + ") cannot be destroyed since the Nomina " + nominaListOrphanCheckNomina + " in its nominaList field has a non-nullable conceptoIdconcepto field.");
            }
            List<Novedadxconcepto> novedadxconceptoListOrphanCheck = concepto.getNovedadxconceptoList();
            for (Novedadxconcepto novedadxconceptoListOrphanCheckNovedadxconcepto : novedadxconceptoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Concepto (" + concepto + ") cannot be destroyed since the Novedadxconcepto " + novedadxconceptoListOrphanCheckNovedadxconcepto + " in its novedadxconceptoList field has a non-nullable conceptoIdconcepto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(concepto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Concepto> findConceptoEntities() {
        return findConceptoEntities(true, -1, -1);
    }

    public List<Concepto> findConceptoEntities(int maxResults, int firstResult) {
        return findConceptoEntities(false, maxResults, firstResult);
    }

    private List<Concepto> findConceptoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Concepto.class));
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

    public Concepto findConcepto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Concepto.class, id);
        } finally {
            em.close();
        }
    }

    public int getConceptoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Concepto> rt = cq.from(Concepto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
