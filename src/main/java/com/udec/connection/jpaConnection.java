package com.udec.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Marco Gonzalez
 *
 */
public final class jpaConnection {

    private static EntityManagerFactory emf;

    public static void createEntityManagerFactory() {

        try {
            emf = Persistence.createEntityManagerFactory("com.udec_nomina_jar_1.0-SNAPSHOTPU");
            // System.out.println("N O T A: EMF CREADO");
        } catch (Exception e) {
            System.err.println("E R R O R: ERROR AL CREAR EMF");
        }

    }

    public static EntityManager getEntityManager() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            // System.out.println("N O T A: EM CREADO");
        } catch (Exception e) {
            System.err.println("E R R O R:  ERROR AL CREAR EM");
        }
        return em;
    }
}
