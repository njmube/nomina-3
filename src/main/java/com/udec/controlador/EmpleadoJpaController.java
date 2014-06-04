/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.controlador;

import com.udec.connection.jpaConnection;
import com.udec.controlador.exceptions.IllegalOrphanException;
import com.udec.controlador.exceptions.NonexistentEntityException;
import com.udec.controlador.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.udec.modelo.Afp;
import com.udec.modelo.Eps;
import com.udec.modelo.Cargo;
import com.udec.modelo.Banco;
import com.udec.modelo.Nomina;
import java.util.ArrayList;
import java.util.List;
import com.udec.modelo.Novedadxconcepto;
import com.udec.modelo.Diastrabajados;
import com.udec.modelo.Empleado;
import javax.persistence.EntityManager;

/**
 *
 * @author Oscar
 */
public class EmpleadoJpaController implements Serializable {


    public EmpleadoJpaController() {

    }

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();

    }

    public List<Empleado> findByList(String property, Object m) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Empleado.class));
        return getEntityManager().createQuery("SELECT c FROM " + Empleado.class.getSimpleName() + " c WHERE c." + property + " = :name", Empleado.class).setParameter("name", m).getResultList();
    }

    public void create(Empleado empleado) throws PreexistingEntityException, Exception {
        if (empleado.getNominaList() == null) {
            empleado.setNominaList(new ArrayList<Nomina>());
        }
        if (empleado.getNovedadxconceptoList() == null) {
            empleado.setNovedadxconceptoList(new ArrayList<Novedadxconcepto>());
        }
        if (empleado.getDiastrabajadosList() == null) {
            empleado.setDiastrabajadosList(new ArrayList<Diastrabajados>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Afp afpIdafp = empleado.getAfpIdafp();
            if (afpIdafp != null) {
                afpIdafp = em.getReference(afpIdafp.getClass(), afpIdafp.getIdafp());
                empleado.setAfpIdafp(afpIdafp);
            }
            Eps epsIdeps = empleado.getEpsIdeps();
            if (epsIdeps != null) {
                epsIdeps = em.getReference(epsIdeps.getClass(), epsIdeps.getIdeps());
                empleado.setEpsIdeps(epsIdeps);
            }
            Cargo cargoCargoid = empleado.getCargoCargoid();
            if (cargoCargoid != null) {
                cargoCargoid = em.getReference(cargoCargoid.getClass(), cargoCargoid.getCargoid());
                empleado.setCargoCargoid(cargoCargoid);
            }
            Banco bancoIdbanco = empleado.getBancoIdbanco();
            if (bancoIdbanco != null) {
                bancoIdbanco = em.getReference(bancoIdbanco.getClass(), bancoIdbanco.getIdbanco());
                empleado.setBancoIdbanco(bancoIdbanco);
            }
            List<Nomina> attachedNominaList = new ArrayList<Nomina>();
            for (Nomina nominaListNominaToAttach : empleado.getNominaList()) {
                nominaListNominaToAttach = em.getReference(nominaListNominaToAttach.getClass(), nominaListNominaToAttach.getIdnomina());
                attachedNominaList.add(nominaListNominaToAttach);
            }
            empleado.setNominaList(attachedNominaList);
            List<Novedadxconcepto> attachedNovedadxconceptoList = new ArrayList<Novedadxconcepto>();
            for (Novedadxconcepto novedadxconceptoListNovedadxconceptoToAttach : empleado.getNovedadxconceptoList()) {
                novedadxconceptoListNovedadxconceptoToAttach = em.getReference(novedadxconceptoListNovedadxconceptoToAttach.getClass(), novedadxconceptoListNovedadxconceptoToAttach.getIdnovedad());
                attachedNovedadxconceptoList.add(novedadxconceptoListNovedadxconceptoToAttach);
            }
            empleado.setNovedadxconceptoList(attachedNovedadxconceptoList);
            List<Diastrabajados> attachedDiastrabajadosList = new ArrayList<Diastrabajados>();
            for (Diastrabajados diastrabajadosListDiastrabajadosToAttach : empleado.getDiastrabajadosList()) {
                diastrabajadosListDiastrabajadosToAttach = em.getReference(diastrabajadosListDiastrabajadosToAttach.getClass(), diastrabajadosListDiastrabajadosToAttach.getIddiastrabajados());
                attachedDiastrabajadosList.add(diastrabajadosListDiastrabajadosToAttach);
            }
            empleado.setDiastrabajadosList(attachedDiastrabajadosList);
            em.persist(empleado);
            if (afpIdafp != null) {
                afpIdafp.getEmpleadoList().add(empleado);
                afpIdafp = em.merge(afpIdafp);
            }
            if (epsIdeps != null) {
                epsIdeps.getEmpleadoList().add(empleado);
                epsIdeps = em.merge(epsIdeps);
            }
            if (cargoCargoid != null) {
                cargoCargoid.getEmpleadoList().add(empleado);
                cargoCargoid = em.merge(cargoCargoid);
            }
            if (bancoIdbanco != null) {
                bancoIdbanco.getEmpleadoList().add(empleado);
                bancoIdbanco = em.merge(bancoIdbanco);
            }
            for (Nomina nominaListNomina : empleado.getNominaList()) {
                Empleado oldEmpleadoCodigoOfNominaListNomina = nominaListNomina.getEmpleadoCodigo();
                nominaListNomina.setEmpleadoCodigo(empleado);
                nominaListNomina = em.merge(nominaListNomina);
                if (oldEmpleadoCodigoOfNominaListNomina != null) {
                    oldEmpleadoCodigoOfNominaListNomina.getNominaList().remove(nominaListNomina);
                    oldEmpleadoCodigoOfNominaListNomina = em.merge(oldEmpleadoCodigoOfNominaListNomina);
                }
            }
            for (Novedadxconcepto novedadxconceptoListNovedadxconcepto : empleado.getNovedadxconceptoList()) {
                Empleado oldEmpleadoCodigoOfNovedadxconceptoListNovedadxconcepto = novedadxconceptoListNovedadxconcepto.getEmpleadoCodigo();
                novedadxconceptoListNovedadxconcepto.setEmpleadoCodigo(empleado);
                novedadxconceptoListNovedadxconcepto = em.merge(novedadxconceptoListNovedadxconcepto);
                if (oldEmpleadoCodigoOfNovedadxconceptoListNovedadxconcepto != null) {
                    oldEmpleadoCodigoOfNovedadxconceptoListNovedadxconcepto.getNovedadxconceptoList().remove(novedadxconceptoListNovedadxconcepto);
                    oldEmpleadoCodigoOfNovedadxconceptoListNovedadxconcepto = em.merge(oldEmpleadoCodigoOfNovedadxconceptoListNovedadxconcepto);
                }
            }
            for (Diastrabajados diastrabajadosListDiastrabajados : empleado.getDiastrabajadosList()) {
                Empleado oldEmpleadoCodigoOfDiastrabajadosListDiastrabajados = diastrabajadosListDiastrabajados.getEmpleadoCodigo();
                diastrabajadosListDiastrabajados.setEmpleadoCodigo(empleado);
                diastrabajadosListDiastrabajados = em.merge(diastrabajadosListDiastrabajados);
                if (oldEmpleadoCodigoOfDiastrabajadosListDiastrabajados != null) {
                    oldEmpleadoCodigoOfDiastrabajadosListDiastrabajados.getDiastrabajadosList().remove(diastrabajadosListDiastrabajados);
                    oldEmpleadoCodigoOfDiastrabajadosListDiastrabajados = em.merge(oldEmpleadoCodigoOfDiastrabajadosListDiastrabajados);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmpleado(empleado.getCodigo()) != null) {
                throw new PreexistingEntityException("Empleado " + empleado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getCodigo());
            Afp afpIdafpOld = persistentEmpleado.getAfpIdafp();
            Afp afpIdafpNew = empleado.getAfpIdafp();
            Eps epsIdepsOld = persistentEmpleado.getEpsIdeps();
            Eps epsIdepsNew = empleado.getEpsIdeps();
            Cargo cargoCargoidOld = persistentEmpleado.getCargoCargoid();
            Cargo cargoCargoidNew = empleado.getCargoCargoid();
            Banco bancoIdbancoOld = persistentEmpleado.getBancoIdbanco();
            Banco bancoIdbancoNew = empleado.getBancoIdbanco();
            List<Nomina> nominaListOld = persistentEmpleado.getNominaList();
            List<Nomina> nominaListNew = empleado.getNominaList();
            List<Novedadxconcepto> novedadxconceptoListOld = persistentEmpleado.getNovedadxconceptoList();
            List<Novedadxconcepto> novedadxconceptoListNew = empleado.getNovedadxconceptoList();
            List<Diastrabajados> diastrabajadosListOld = persistentEmpleado.getDiastrabajadosList();
            List<Diastrabajados> diastrabajadosListNew = empleado.getDiastrabajadosList();
            List<String> illegalOrphanMessages = null;
            for (Nomina nominaListOldNomina : nominaListOld) {
                if (!nominaListNew.contains(nominaListOldNomina)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Nomina " + nominaListOldNomina + " since its empleadoCodigo field is not nullable.");
                }
            }
            for (Novedadxconcepto novedadxconceptoListOldNovedadxconcepto : novedadxconceptoListOld) {
                if (!novedadxconceptoListNew.contains(novedadxconceptoListOldNovedadxconcepto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Novedadxconcepto " + novedadxconceptoListOldNovedadxconcepto + " since its empleadoCodigo field is not nullable.");
                }
            }
            for (Diastrabajados diastrabajadosListOldDiastrabajados : diastrabajadosListOld) {
                if (!diastrabajadosListNew.contains(diastrabajadosListOldDiastrabajados)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Diastrabajados " + diastrabajadosListOldDiastrabajados + " since its empleadoCodigo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (afpIdafpNew != null) {
                afpIdafpNew = em.getReference(afpIdafpNew.getClass(), afpIdafpNew.getIdafp());
                empleado.setAfpIdafp(afpIdafpNew);
            }
            if (epsIdepsNew != null) {
                epsIdepsNew = em.getReference(epsIdepsNew.getClass(), epsIdepsNew.getIdeps());
                empleado.setEpsIdeps(epsIdepsNew);
            }
            if (cargoCargoidNew != null) {
                cargoCargoidNew = em.getReference(cargoCargoidNew.getClass(), cargoCargoidNew.getCargoid());
                empleado.setCargoCargoid(cargoCargoidNew);
            }
            if (bancoIdbancoNew != null) {
                bancoIdbancoNew = em.getReference(bancoIdbancoNew.getClass(), bancoIdbancoNew.getIdbanco());
                empleado.setBancoIdbanco(bancoIdbancoNew);
            }
            List<Nomina> attachedNominaListNew = new ArrayList<Nomina>();
            for (Nomina nominaListNewNominaToAttach : nominaListNew) {
                nominaListNewNominaToAttach = em.getReference(nominaListNewNominaToAttach.getClass(), nominaListNewNominaToAttach.getIdnomina());
                attachedNominaListNew.add(nominaListNewNominaToAttach);
            }
            nominaListNew = attachedNominaListNew;
            empleado.setNominaList(nominaListNew);
            List<Novedadxconcepto> attachedNovedadxconceptoListNew = new ArrayList<Novedadxconcepto>();
            for (Novedadxconcepto novedadxconceptoListNewNovedadxconceptoToAttach : novedadxconceptoListNew) {
                novedadxconceptoListNewNovedadxconceptoToAttach = em.getReference(novedadxconceptoListNewNovedadxconceptoToAttach.getClass(), novedadxconceptoListNewNovedadxconceptoToAttach.getIdnovedad());
                attachedNovedadxconceptoListNew.add(novedadxconceptoListNewNovedadxconceptoToAttach);
            }
            novedadxconceptoListNew = attachedNovedadxconceptoListNew;
            empleado.setNovedadxconceptoList(novedadxconceptoListNew);
            List<Diastrabajados> attachedDiastrabajadosListNew = new ArrayList<Diastrabajados>();
            for (Diastrabajados diastrabajadosListNewDiastrabajadosToAttach : diastrabajadosListNew) {
                diastrabajadosListNewDiastrabajadosToAttach = em.getReference(diastrabajadosListNewDiastrabajadosToAttach.getClass(), diastrabajadosListNewDiastrabajadosToAttach.getIddiastrabajados());
                attachedDiastrabajadosListNew.add(diastrabajadosListNewDiastrabajadosToAttach);
            }
            diastrabajadosListNew = attachedDiastrabajadosListNew;
            empleado.setDiastrabajadosList(diastrabajadosListNew);
            empleado = em.merge(empleado);
            if (afpIdafpOld != null && !afpIdafpOld.equals(afpIdafpNew)) {
                afpIdafpOld.getEmpleadoList().remove(empleado);
                afpIdafpOld = em.merge(afpIdafpOld);
            }
            if (afpIdafpNew != null && !afpIdafpNew.equals(afpIdafpOld)) {
                afpIdafpNew.getEmpleadoList().add(empleado);
                afpIdafpNew = em.merge(afpIdafpNew);
            }
            if (epsIdepsOld != null && !epsIdepsOld.equals(epsIdepsNew)) {
                epsIdepsOld.getEmpleadoList().remove(empleado);
                epsIdepsOld = em.merge(epsIdepsOld);
            }
            if (epsIdepsNew != null && !epsIdepsNew.equals(epsIdepsOld)) {
                epsIdepsNew.getEmpleadoList().add(empleado);
                epsIdepsNew = em.merge(epsIdepsNew);
            }
            if (cargoCargoidOld != null && !cargoCargoidOld.equals(cargoCargoidNew)) {
                cargoCargoidOld.getEmpleadoList().remove(empleado);
                cargoCargoidOld = em.merge(cargoCargoidOld);
            }
            if (cargoCargoidNew != null && !cargoCargoidNew.equals(cargoCargoidOld)) {
                cargoCargoidNew.getEmpleadoList().add(empleado);
                cargoCargoidNew = em.merge(cargoCargoidNew);
            }
            if (bancoIdbancoOld != null && !bancoIdbancoOld.equals(bancoIdbancoNew)) {
                bancoIdbancoOld.getEmpleadoList().remove(empleado);
                bancoIdbancoOld = em.merge(bancoIdbancoOld);
            }
            if (bancoIdbancoNew != null && !bancoIdbancoNew.equals(bancoIdbancoOld)) {
                bancoIdbancoNew.getEmpleadoList().add(empleado);
                bancoIdbancoNew = em.merge(bancoIdbancoNew);
            }
            for (Nomina nominaListNewNomina : nominaListNew) {
                if (!nominaListOld.contains(nominaListNewNomina)) {
                    Empleado oldEmpleadoCodigoOfNominaListNewNomina = nominaListNewNomina.getEmpleadoCodigo();
                    nominaListNewNomina.setEmpleadoCodigo(empleado);
                    nominaListNewNomina = em.merge(nominaListNewNomina);
                    if (oldEmpleadoCodigoOfNominaListNewNomina != null && !oldEmpleadoCodigoOfNominaListNewNomina.equals(empleado)) {
                        oldEmpleadoCodigoOfNominaListNewNomina.getNominaList().remove(nominaListNewNomina);
                        oldEmpleadoCodigoOfNominaListNewNomina = em.merge(oldEmpleadoCodigoOfNominaListNewNomina);
                    }
                }
            }
            for (Novedadxconcepto novedadxconceptoListNewNovedadxconcepto : novedadxconceptoListNew) {
                if (!novedadxconceptoListOld.contains(novedadxconceptoListNewNovedadxconcepto)) {
                    Empleado oldEmpleadoCodigoOfNovedadxconceptoListNewNovedadxconcepto = novedadxconceptoListNewNovedadxconcepto.getEmpleadoCodigo();
                    novedadxconceptoListNewNovedadxconcepto.setEmpleadoCodigo(empleado);
                    novedadxconceptoListNewNovedadxconcepto = em.merge(novedadxconceptoListNewNovedadxconcepto);
                    if (oldEmpleadoCodigoOfNovedadxconceptoListNewNovedadxconcepto != null && !oldEmpleadoCodigoOfNovedadxconceptoListNewNovedadxconcepto.equals(empleado)) {
                        oldEmpleadoCodigoOfNovedadxconceptoListNewNovedadxconcepto.getNovedadxconceptoList().remove(novedadxconceptoListNewNovedadxconcepto);
                        oldEmpleadoCodigoOfNovedadxconceptoListNewNovedadxconcepto = em.merge(oldEmpleadoCodigoOfNovedadxconceptoListNewNovedadxconcepto);
                    }
                }
            }
            for (Diastrabajados diastrabajadosListNewDiastrabajados : diastrabajadosListNew) {
                if (!diastrabajadosListOld.contains(diastrabajadosListNewDiastrabajados)) {
                    Empleado oldEmpleadoCodigoOfDiastrabajadosListNewDiastrabajados = diastrabajadosListNewDiastrabajados.getEmpleadoCodigo();
                    diastrabajadosListNewDiastrabajados.setEmpleadoCodigo(empleado);
                    diastrabajadosListNewDiastrabajados = em.merge(diastrabajadosListNewDiastrabajados);
                    if (oldEmpleadoCodigoOfDiastrabajadosListNewDiastrabajados != null && !oldEmpleadoCodigoOfDiastrabajadosListNewDiastrabajados.equals(empleado)) {
                        oldEmpleadoCodigoOfDiastrabajadosListNewDiastrabajados.getDiastrabajadosList().remove(diastrabajadosListNewDiastrabajados);
                        oldEmpleadoCodigoOfDiastrabajadosListNewDiastrabajados = em.merge(oldEmpleadoCodigoOfDiastrabajadosListNewDiastrabajados);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleado.getCodigo();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
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
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Nomina> nominaListOrphanCheck = empleado.getNominaList();
            for (Nomina nominaListOrphanCheckNomina : nominaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Nomina " + nominaListOrphanCheckNomina + " in its nominaList field has a non-nullable empleadoCodigo field.");
            }
            List<Novedadxconcepto> novedadxconceptoListOrphanCheck = empleado.getNovedadxconceptoList();
            for (Novedadxconcepto novedadxconceptoListOrphanCheckNovedadxconcepto : novedadxconceptoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Novedadxconcepto " + novedadxconceptoListOrphanCheckNovedadxconcepto + " in its novedadxconceptoList field has a non-nullable empleadoCodigo field.");
            }
            List<Diastrabajados> diastrabajadosListOrphanCheck = empleado.getDiastrabajadosList();
            for (Diastrabajados diastrabajadosListOrphanCheckDiastrabajados : diastrabajadosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Diastrabajados " + diastrabajadosListOrphanCheckDiastrabajados + " in its diastrabajadosList field has a non-nullable empleadoCodigo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Afp afpIdafp = empleado.getAfpIdafp();
            if (afpIdafp != null) {
                afpIdafp.getEmpleadoList().remove(empleado);
                afpIdafp = em.merge(afpIdafp);
            }
            Eps epsIdeps = empleado.getEpsIdeps();
            if (epsIdeps != null) {
                epsIdeps.getEmpleadoList().remove(empleado);
                epsIdeps = em.merge(epsIdeps);
            }
            Cargo cargoCargoid = empleado.getCargoCargoid();
            if (cargoCargoid != null) {
                cargoCargoid.getEmpleadoList().remove(empleado);
                cargoCargoid = em.merge(cargoCargoid);
            }
            Banco bancoIdbanco = empleado.getBancoIdbanco();
            if (bancoIdbanco != null) {
                bancoIdbanco.getEmpleadoList().remove(empleado);
                bancoIdbanco = em.merge(bancoIdbanco);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
