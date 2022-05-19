package jandy.krystian.repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

public class JpaRepository<E,K> {

    private Class<E> className;
    private EntityManagerFactory emf;

    public JpaRepository( EntityManagerFactory emf, Class<E> className) {
        this.className = className;
        this.emf = emf;
    }

    public List<E> getAll() {
        EntityManager em = emf.createEntityManager();
        List<E> list = em.createQuery("select e from " + className.getName() + " e", className).getResultList();
        em.close();
        return list;
    }

    public void add(E obj) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(obj);
        transaction.commit();
        em.close();
    }

    public void delete(E obj) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(em.merge(obj));
        transaction.commit();
        em.close();
    }

    public E find(K id) {
        EntityManager em = emf.createEntityManager();
        E entity = null;
//        try {
//            entity = em.createQuery("select e from " + className.getName() + " e where e.name = :name", className)
//                    .setParameter("name", id)
//                    .getSingleResult();
//            em.close();
//        } catch (NoResultException ignored) {}
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        entity = em.find(className,id);
        transaction.commit();
        em.close();
        return entity;
    }

    public void update(E obj){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(obj);
        transaction.commit();
        em.close();
    }

    public void refresh(E obj){
        EntityManager em = emf.createEntityManager();
        em.refresh(em.merge(obj));
        em.close();
    }

}
