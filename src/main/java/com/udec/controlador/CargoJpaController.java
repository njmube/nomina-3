/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.controlador;

import com.udec.connection.jpaConnection;
import com.udec.controlador.exceptions.IllegalOrphanException;
import com.udec.controlador.exceptions.NonexistentEntityException;
import com.udec.modelo.Cargo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.udec.modelo.Empleado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;


/**
 *
 * @author Oscar
 */
public class CargoJpaController implements Serializable {

    public CargoJpaController() {
       
    }
   

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();

    }

    public void create(Cargo cargo) {
        if (cargo.getEmpleadoList() == null) {
            cargo.setEmpleadoList(new ArrayList<Empleado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Empleado> attachedEmpleadoList = new ArrayList<Empleado>();
            for (Empleado empleadoListEmpleadoToAttach : cargo.getEmpleadoList()) {
                empleadoListEmpleadoToAttach = em.getReference(empleadoListEmpleadoToAttach.getClass(), empleadoListEmpleadoToAttach.getCodigo());
                attachedEmpleadoList.add(empleadoListEmpleadoToAttach);
            }
            cargo.setEmpleadoList(attachedEmpleadoList);
            em.persist(cargo);
            for (Empleado empleadoListEmpleado : cargo.getEmpleadoList()) {
                Cargo oldCargoCargoidOfEmpleadoListEmpleado = empleadoListEmpleado.getCargoCargoid();
                empleadoListEmpleado.setCargoCargoid(cargo);
                empleadoListEmpleado = em.merge(empleadoListEmpleado);
                if (oldCargoCargoidOfEmpleadoListEmpleado != null) {
                    oldCargoCargoidOfEmpleadoListEmpleado.getEmpleadoList().remove(empleadoListEmpleado);
                    oldCargoCargoidOfEmpleadoListEmpleado = em.merge(oldCargoCargoidOfEmpleadoListEmpleado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cargo cargo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo persistentCargo = em.find(Cargo.class, cargo.getCargoid());
            List<Empleado> empleadoListOld = persistentCargo.getEmpleadoList();
            List<Empleado> empleadoListNew = cargo.getEmpleadoList();
            List<String> illegalOrphanMessages = null;
            for (Empleado empleadoListOldEmpleado : empleadoListOld) {
                if (!empleadoListNew.contains(empleadoListOldEmpleado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empleado " + empleadoListOldEmpleado + " since its cargoCargoid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Empleado> attachedEmpleadoListNew = new ArrayList<Empleado>();
            for (Empleado empleadoListNewEmpleadoToAttach : empleadoListNew) {
                empleadoListNewEmpleadoToAttach = em.getReference(empleadoListNewEmpleadoToAttach.getClass(), empleadoListNewEmpleadoToAttach.getCodigo());
                attachedEmpleadoListNew.add(empleadoListNewEmpleadoToAttach);
            }
            empleadoListNew = attachedEmpleadoListNew;
            cargo.setEmpleadoList(empleadoListNew);
            cargo = em.merge(cargo);
            for (Empleado empleadoListNewEmpleado : empleadoListNew) {
                if (!empleadoListOld.contains(empleadoListNewEmpleado)) {
                    Cargo oldCargoCargoidOfEmpleadoListNewEmpleado = empleadoListNewEmpleado.getCargoCargoid();
                    empleadoListNewEmpleado.setCargoCargoid(cargo);
                    empleadoListNewEmpleado = em.merge(empleadoListNewEmpleado);
                    if (oldCargoCargoidOfEmpleadoListNewEmpleado != null && !oldCargoCargoidOfEmpleadoListNewEmpleado.equals(cargo)) {
                        oldCargoCargoidOfEmpleadoListNewEmpleado.getEmpleadoList().remove(empleadoListNewEmpleado);
                        oldCargoCargoidOfEmpleadoListNewEmpleado = em.merge(oldCargoCargoidOfEmpleadoListNewEmpleado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cargo.getCargoid();
                if (findCargo(id) == null) {
                    throw new NonexistentEntityException("The cargo with id " + id + " no longer exists.");
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
            Cargo cargo;
            try {
                cargo = em.getReference(Cargo.class, id);
                cargo.getCargoid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cargo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Empleado> empleadoListOrphanCheck = cargo.getEmpleadoList();
            for (Empleado empleadoListOrphanCheckEmpleado : empleadoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cargo (" + cargo + ") cannot be destroyed since the Empleado " + empleadoListOrphanCheckEmpleado + " in its empleadoList field has a non-nullable cargoCargoid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cargo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cargo> findCargoEntities() {
        return findCargoEntities(true, -1, -1);
    }

    public List<Cargo> findCargoEntities(int maxResults, int firstResult) {
        return findCargoEntities(false, maxResults, firstResult);
    }

    private List<Cargo> findCargoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cargo.class));
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

    public Cargo findCargo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cargo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCargoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cargo> rt = cq.from(Cargo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
