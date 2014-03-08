/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.controlador;

import com.udec.connection.jpaConnection;
import com.udec.controlador.exceptions.NonexistentEntityException;
import com.udec.modelo.Banco;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.udec.modelo.Novedadxconcepto;
import java.util.ArrayList;
import java.util.List;
import com.udec.modelo.Empleado;
import javax.persistence.EntityManager;


/**
 *
 * @author Oscar
 */
public class BancoJpaController implements Serializable {

    public BancoJpaController() {
       
    }
    

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();

    }

    public void create(Banco banco) {
        if (banco.getNovedadxconceptoList() == null) {
            banco.setNovedadxconceptoList(new ArrayList<Novedadxconcepto>());
        }
        if (banco.getEmpleadoList() == null) {
            banco.setEmpleadoList(new ArrayList<Empleado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Novedadxconcepto> attachedNovedadxconceptoList = new ArrayList<Novedadxconcepto>();
            for (Novedadxconcepto novedadxconceptoListNovedadxconceptoToAttach : banco.getNovedadxconceptoList()) {
                novedadxconceptoListNovedadxconceptoToAttach = em.getReference(novedadxconceptoListNovedadxconceptoToAttach.getClass(), novedadxconceptoListNovedadxconceptoToAttach.getIdnovedad());
                attachedNovedadxconceptoList.add(novedadxconceptoListNovedadxconceptoToAttach);
            }
            banco.setNovedadxconceptoList(attachedNovedadxconceptoList);
            List<Empleado> attachedEmpleadoList = new ArrayList<Empleado>();
            for (Empleado empleadoListEmpleadoToAttach : banco.getEmpleadoList()) {
                empleadoListEmpleadoToAttach = em.getReference(empleadoListEmpleadoToAttach.getClass(), empleadoListEmpleadoToAttach.getCodigo());
                attachedEmpleadoList.add(empleadoListEmpleadoToAttach);
            }
            banco.setEmpleadoList(attachedEmpleadoList);
            em.persist(banco);
            for (Novedadxconcepto novedadxconceptoListNovedadxconcepto : banco.getNovedadxconceptoList()) {
                Banco oldBancoIdbancoOfNovedadxconceptoListNovedadxconcepto = novedadxconceptoListNovedadxconcepto.getBancoIdbanco();
                novedadxconceptoListNovedadxconcepto.setBancoIdbanco(banco);
                novedadxconceptoListNovedadxconcepto = em.merge(novedadxconceptoListNovedadxconcepto);
                if (oldBancoIdbancoOfNovedadxconceptoListNovedadxconcepto != null) {
                    oldBancoIdbancoOfNovedadxconceptoListNovedadxconcepto.getNovedadxconceptoList().remove(novedadxconceptoListNovedadxconcepto);
                    oldBancoIdbancoOfNovedadxconceptoListNovedadxconcepto = em.merge(oldBancoIdbancoOfNovedadxconceptoListNovedadxconcepto);
                }
            }
            for (Empleado empleadoListEmpleado : banco.getEmpleadoList()) {
                Banco oldBancoIdbancoOfEmpleadoListEmpleado = empleadoListEmpleado.getBancoIdbanco();
                empleadoListEmpleado.setBancoIdbanco(banco);
                empleadoListEmpleado = em.merge(empleadoListEmpleado);
                if (oldBancoIdbancoOfEmpleadoListEmpleado != null) {
                    oldBancoIdbancoOfEmpleadoListEmpleado.getEmpleadoList().remove(empleadoListEmpleado);
                    oldBancoIdbancoOfEmpleadoListEmpleado = em.merge(oldBancoIdbancoOfEmpleadoListEmpleado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Banco banco) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Banco persistentBanco = em.find(Banco.class, banco.getIdbanco());
            List<Novedadxconcepto> novedadxconceptoListOld = persistentBanco.getNovedadxconceptoList();
            List<Novedadxconcepto> novedadxconceptoListNew = banco.getNovedadxconceptoList();
            List<Empleado> empleadoListOld = persistentBanco.getEmpleadoList();
            List<Empleado> empleadoListNew = banco.getEmpleadoList();
            List<Novedadxconcepto> attachedNovedadxconceptoListNew = new ArrayList<Novedadxconcepto>();
            for (Novedadxconcepto novedadxconceptoListNewNovedadxconceptoToAttach : novedadxconceptoListNew) {
                novedadxconceptoListNewNovedadxconceptoToAttach = em.getReference(novedadxconceptoListNewNovedadxconceptoToAttach.getClass(), novedadxconceptoListNewNovedadxconceptoToAttach.getIdnovedad());
                attachedNovedadxconceptoListNew.add(novedadxconceptoListNewNovedadxconceptoToAttach);
            }
            novedadxconceptoListNew = attachedNovedadxconceptoListNew;
            banco.setNovedadxconceptoList(novedadxconceptoListNew);
            List<Empleado> attachedEmpleadoListNew = new ArrayList<Empleado>();
            for (Empleado empleadoListNewEmpleadoToAttach : empleadoListNew) {
                empleadoListNewEmpleadoToAttach = em.getReference(empleadoListNewEmpleadoToAttach.getClass(), empleadoListNewEmpleadoToAttach.getCodigo());
                attachedEmpleadoListNew.add(empleadoListNewEmpleadoToAttach);
            }
            empleadoListNew = attachedEmpleadoListNew;
            banco.setEmpleadoList(empleadoListNew);
            banco = em.merge(banco);
            for (Novedadxconcepto novedadxconceptoListOldNovedadxconcepto : novedadxconceptoListOld) {
                if (!novedadxconceptoListNew.contains(novedadxconceptoListOldNovedadxconcepto)) {
                    novedadxconceptoListOldNovedadxconcepto.setBancoIdbanco(null);
                    novedadxconceptoListOldNovedadxconcepto = em.merge(novedadxconceptoListOldNovedadxconcepto);
                }
            }
            for (Novedadxconcepto novedadxconceptoListNewNovedadxconcepto : novedadxconceptoListNew) {
                if (!novedadxconceptoListOld.contains(novedadxconceptoListNewNovedadxconcepto)) {
                    Banco oldBancoIdbancoOfNovedadxconceptoListNewNovedadxconcepto = novedadxconceptoListNewNovedadxconcepto.getBancoIdbanco();
                    novedadxconceptoListNewNovedadxconcepto.setBancoIdbanco(banco);
                    novedadxconceptoListNewNovedadxconcepto = em.merge(novedadxconceptoListNewNovedadxconcepto);
                    if (oldBancoIdbancoOfNovedadxconceptoListNewNovedadxconcepto != null && !oldBancoIdbancoOfNovedadxconceptoListNewNovedadxconcepto.equals(banco)) {
                        oldBancoIdbancoOfNovedadxconceptoListNewNovedadxconcepto.getNovedadxconceptoList().remove(novedadxconceptoListNewNovedadxconcepto);
                        oldBancoIdbancoOfNovedadxconceptoListNewNovedadxconcepto = em.merge(oldBancoIdbancoOfNovedadxconceptoListNewNovedadxconcepto);
                    }
                }
            }
            for (Empleado empleadoListOldEmpleado : empleadoListOld) {
                if (!empleadoListNew.contains(empleadoListOldEmpleado)) {
                    empleadoListOldEmpleado.setBancoIdbanco(null);
                    empleadoListOldEmpleado = em.merge(empleadoListOldEmpleado);
                }
            }
            for (Empleado empleadoListNewEmpleado : empleadoListNew) {
                if (!empleadoListOld.contains(empleadoListNewEmpleado)) {
                    Banco oldBancoIdbancoOfEmpleadoListNewEmpleado = empleadoListNewEmpleado.getBancoIdbanco();
                    empleadoListNewEmpleado.setBancoIdbanco(banco);
                    empleadoListNewEmpleado = em.merge(empleadoListNewEmpleado);
                    if (oldBancoIdbancoOfEmpleadoListNewEmpleado != null && !oldBancoIdbancoOfEmpleadoListNewEmpleado.equals(banco)) {
                        oldBancoIdbancoOfEmpleadoListNewEmpleado.getEmpleadoList().remove(empleadoListNewEmpleado);
                        oldBancoIdbancoOfEmpleadoListNewEmpleado = em.merge(oldBancoIdbancoOfEmpleadoListNewEmpleado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = banco.getIdbanco();
                if (findBanco(id) == null) {
                    throw new NonexistentEntityException("The banco with id " + id + " no longer exists.");
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
            Banco banco;
            try {
                banco = em.getReference(Banco.class, id);
                banco.getIdbanco();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The banco with id " + id + " no longer exists.", enfe);
            }
            List<Novedadxconcepto> novedadxconceptoList = banco.getNovedadxconceptoList();
            for (Novedadxconcepto novedadxconceptoListNovedadxconcepto : novedadxconceptoList) {
                novedadxconceptoListNovedadxconcepto.setBancoIdbanco(null);
                novedadxconceptoListNovedadxconcepto = em.merge(novedadxconceptoListNovedadxconcepto);
            }
            List<Empleado> empleadoList = banco.getEmpleadoList();
            for (Empleado empleadoListEmpleado : empleadoList) {
                empleadoListEmpleado.setBancoIdbanco(null);
                empleadoListEmpleado = em.merge(empleadoListEmpleado);
            }
            em.remove(banco);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Banco> findBancoEntities() {
        return findBancoEntities(true, -1, -1);
    }

    public List<Banco> findBancoEntities(int maxResults, int firstResult) {
        return findBancoEntities(false, maxResults, firstResult);
    }

    private List<Banco> findBancoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Banco.class));
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

    public Banco findBanco(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Banco.class, id);
        } finally {
            em.close();
        }
    }

    public int getBancoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Banco> rt = cq.from(Banco.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
