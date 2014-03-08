/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.controlador;

import com.udec.connection.jpaConnection;
import com.udec.controlador.exceptions.IllegalOrphanException;
import com.udec.controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.udec.modelo.Nomina;
import java.util.ArrayList;
import java.util.List;
import com.udec.modelo.Diastrabajados;
import com.udec.modelo.Periodo;
import javax.persistence.EntityManager;


/**
 *
 * @author Oscar
 */
public class PeriodoJpaController implements Serializable {

    public PeriodoJpaController( ) {
        
    }
   

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();

    }

    public void create(Periodo periodo) {
        if (periodo.getNominaList() == null) {
            periodo.setNominaList(new ArrayList<Nomina>());
        }
        if (periodo.getDiastrabajadosList() == null) {
            periodo.setDiastrabajadosList(new ArrayList<Diastrabajados>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Nomina> attachedNominaList = new ArrayList<Nomina>();
            for (Nomina nominaListNominaToAttach : periodo.getNominaList()) {
                nominaListNominaToAttach = em.getReference(nominaListNominaToAttach.getClass(), nominaListNominaToAttach.getIdnomina());
                attachedNominaList.add(nominaListNominaToAttach);
            }
            periodo.setNominaList(attachedNominaList);
            List<Diastrabajados> attachedDiastrabajadosList = new ArrayList<Diastrabajados>();
            for (Diastrabajados diastrabajadosListDiastrabajadosToAttach : periodo.getDiastrabajadosList()) {
                diastrabajadosListDiastrabajadosToAttach = em.getReference(diastrabajadosListDiastrabajadosToAttach.getClass(), diastrabajadosListDiastrabajadosToAttach.getIddiastrabajados());
                attachedDiastrabajadosList.add(diastrabajadosListDiastrabajadosToAttach);
            }
            periodo.setDiastrabajadosList(attachedDiastrabajadosList);
            em.persist(periodo);
            for (Nomina nominaListNomina : periodo.getNominaList()) {
                Periodo oldPeriodoIdperiodoOfNominaListNomina = nominaListNomina.getPeriodoIdperiodo();
                nominaListNomina.setPeriodoIdperiodo(periodo);
                nominaListNomina = em.merge(nominaListNomina);
                if (oldPeriodoIdperiodoOfNominaListNomina != null) {
                    oldPeriodoIdperiodoOfNominaListNomina.getNominaList().remove(nominaListNomina);
                    oldPeriodoIdperiodoOfNominaListNomina = em.merge(oldPeriodoIdperiodoOfNominaListNomina);
                }
            }
            for (Diastrabajados diastrabajadosListDiastrabajados : periodo.getDiastrabajadosList()) {
                Periodo oldPeriodoIdperiodoOfDiastrabajadosListDiastrabajados = diastrabajadosListDiastrabajados.getPeriodoIdperiodo();
                diastrabajadosListDiastrabajados.setPeriodoIdperiodo(periodo);
                diastrabajadosListDiastrabajados = em.merge(diastrabajadosListDiastrabajados);
                if (oldPeriodoIdperiodoOfDiastrabajadosListDiastrabajados != null) {
                    oldPeriodoIdperiodoOfDiastrabajadosListDiastrabajados.getDiastrabajadosList().remove(diastrabajadosListDiastrabajados);
                    oldPeriodoIdperiodoOfDiastrabajadosListDiastrabajados = em.merge(oldPeriodoIdperiodoOfDiastrabajadosListDiastrabajados);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Periodo periodo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Periodo persistentPeriodo = em.find(Periodo.class, periodo.getIdperiodo());
            List<Nomina> nominaListOld = persistentPeriodo.getNominaList();
            List<Nomina> nominaListNew = periodo.getNominaList();
            List<Diastrabajados> diastrabajadosListOld = persistentPeriodo.getDiastrabajadosList();
            List<Diastrabajados> diastrabajadosListNew = periodo.getDiastrabajadosList();
            List<String> illegalOrphanMessages = null;
            for (Nomina nominaListOldNomina : nominaListOld) {
                if (!nominaListNew.contains(nominaListOldNomina)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Nomina " + nominaListOldNomina + " since its periodoIdperiodo field is not nullable.");
                }
            }
            for (Diastrabajados diastrabajadosListOldDiastrabajados : diastrabajadosListOld) {
                if (!diastrabajadosListNew.contains(diastrabajadosListOldDiastrabajados)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Diastrabajados " + diastrabajadosListOldDiastrabajados + " since its periodoIdperiodo field is not nullable.");
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
            periodo.setNominaList(nominaListNew);
            List<Diastrabajados> attachedDiastrabajadosListNew = new ArrayList<Diastrabajados>();
            for (Diastrabajados diastrabajadosListNewDiastrabajadosToAttach : diastrabajadosListNew) {
                diastrabajadosListNewDiastrabajadosToAttach = em.getReference(diastrabajadosListNewDiastrabajadosToAttach.getClass(), diastrabajadosListNewDiastrabajadosToAttach.getIddiastrabajados());
                attachedDiastrabajadosListNew.add(diastrabajadosListNewDiastrabajadosToAttach);
            }
            diastrabajadosListNew = attachedDiastrabajadosListNew;
            periodo.setDiastrabajadosList(diastrabajadosListNew);
            periodo = em.merge(periodo);
            for (Nomina nominaListNewNomina : nominaListNew) {
                if (!nominaListOld.contains(nominaListNewNomina)) {
                    Periodo oldPeriodoIdperiodoOfNominaListNewNomina = nominaListNewNomina.getPeriodoIdperiodo();
                    nominaListNewNomina.setPeriodoIdperiodo(periodo);
                    nominaListNewNomina = em.merge(nominaListNewNomina);
                    if (oldPeriodoIdperiodoOfNominaListNewNomina != null && !oldPeriodoIdperiodoOfNominaListNewNomina.equals(periodo)) {
                        oldPeriodoIdperiodoOfNominaListNewNomina.getNominaList().remove(nominaListNewNomina);
                        oldPeriodoIdperiodoOfNominaListNewNomina = em.merge(oldPeriodoIdperiodoOfNominaListNewNomina);
                    }
                }
            }
            for (Diastrabajados diastrabajadosListNewDiastrabajados : diastrabajadosListNew) {
                if (!diastrabajadosListOld.contains(diastrabajadosListNewDiastrabajados)) {
                    Periodo oldPeriodoIdperiodoOfDiastrabajadosListNewDiastrabajados = diastrabajadosListNewDiastrabajados.getPeriodoIdperiodo();
                    diastrabajadosListNewDiastrabajados.setPeriodoIdperiodo(periodo);
                    diastrabajadosListNewDiastrabajados = em.merge(diastrabajadosListNewDiastrabajados);
                    if (oldPeriodoIdperiodoOfDiastrabajadosListNewDiastrabajados != null && !oldPeriodoIdperiodoOfDiastrabajadosListNewDiastrabajados.equals(periodo)) {
                        oldPeriodoIdperiodoOfDiastrabajadosListNewDiastrabajados.getDiastrabajadosList().remove(diastrabajadosListNewDiastrabajados);
                        oldPeriodoIdperiodoOfDiastrabajadosListNewDiastrabajados = em.merge(oldPeriodoIdperiodoOfDiastrabajadosListNewDiastrabajados);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = periodo.getIdperiodo();
                if (findPeriodo(id) == null) {
                    throw new NonexistentEntityException("The periodo with id " + id + " no longer exists.");
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
            Periodo periodo;
            try {
                periodo = em.getReference(Periodo.class, id);
                periodo.getIdperiodo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The periodo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Nomina> nominaListOrphanCheck = periodo.getNominaList();
            for (Nomina nominaListOrphanCheckNomina : nominaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Periodo (" + periodo + ") cannot be destroyed since the Nomina " + nominaListOrphanCheckNomina + " in its nominaList field has a non-nullable periodoIdperiodo field.");
            }
            List<Diastrabajados> diastrabajadosListOrphanCheck = periodo.getDiastrabajadosList();
            for (Diastrabajados diastrabajadosListOrphanCheckDiastrabajados : diastrabajadosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Periodo (" + periodo + ") cannot be destroyed since the Diastrabajados " + diastrabajadosListOrphanCheckDiastrabajados + " in its diastrabajadosList field has a non-nullable periodoIdperiodo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(periodo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Periodo> findPeriodoEntities() {
        return findPeriodoEntities(true, -1, -1);
    }

    public List<Periodo> findPeriodoEntities(int maxResults, int firstResult) {
        return findPeriodoEntities(false, maxResults, firstResult);
    }

    private List<Periodo> findPeriodoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Periodo.class));
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

    public Periodo findPeriodo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Periodo.class, id);
        } finally {
            em.close();
        }
    }

    public int getPeriodoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Periodo> rt = cq.from(Periodo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
