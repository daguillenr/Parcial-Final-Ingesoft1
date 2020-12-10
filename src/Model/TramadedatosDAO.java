/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 *
 * @author User
 */
public class TramadedatosDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceParcial");

    public static void insertar(Tramadedatos trama) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(trama);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public static boolean eliminar(Tramadedatos trama) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean done = false;
        try {
            em.remove(trama);
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            return done;
        }
    }

    public static Tramadedatos leerSingle(int id_s) {
        EntityManager em = emf.createEntityManager();
        Tramadedatos res = null;
        Query q = em
                .createQuery("SELECT t FROM TramaDatos t " + "WHERE  t.id_instalacion =:id_instalacion ORDER BY t.id_toma DESC")
                .setParameter("id_instalacion", id_s);

        try {
            res = (Tramadedatos) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            res = (Tramadedatos) q.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            return res;
        }
    }

    public static ArrayList<Tramadedatos> leerMultiple(int id_s) {
        EntityManager em = emf.createEntityManager();
        List<Tramadedatos> res = null;
        Query q = em
                .createQuery("SELECT t FROM TramaDatos t " + "WHERE t.id_instalacion =:id " + "ORDER BY t.id_toma DESC")
                .setParameter("id", id_s);

        try {
            res = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            ArrayList<Tramadedatos> list = new ArrayList<>(res.size());
            if (res.size() > 5) {
                for (int i = 0; i < 5; i++) {
                    list.add(res.get(i));
                }
            } else {
                list.addAll(res);
            }

            return list;
        }

    }
    
    public static ArrayList<Tramadedatos> leerPorFecha(String fecha){
        EntityManager em = emf.createEntityManager();
        List<Tramadedatos> res = null;
        Query q = em
                .createQuery("SELECT t FROM TramaDatos t " + "WHERE  t.fecha_toma LIKE :fecha_toma")
                .setParameter("fecha_toma", fecha);
        
        try {
            res = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            ArrayList<Tramadedatos> list = new ArrayList<>(res.size());
            list.addAll(res);

            return list;
        }
    }

}
