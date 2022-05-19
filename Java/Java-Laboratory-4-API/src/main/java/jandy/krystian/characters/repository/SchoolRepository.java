package jandy.krystian.characters.repository;

import jandy.krystian.characters.entity.School;
import jandy.krystian.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;

public class SchoolRepository extends JpaRepository<School,String> {
    EntityManagerFactory emf;
    public SchoolRepository(EntityManagerFactory emf) {
        super(emf, School.class);
        this.emf = emf;
    }

    public ArrayList<School> getLowerThan(int influence){
        EntityManager em = emf.createEntityManager();
        ArrayList<School> schools = (ArrayList<School>) em.createQuery("select t from School t where t.influence <= :influence", School.class)
                .setParameter("influence",influence)
                .getResultList();
        em.close();
        return schools;
    }
    public ArrayList<School> getBiggerThan(int influence){
        EntityManager em = emf.createEntityManager();
        ArrayList<School> schools = (ArrayList<School>) em.createQuery("select t from School t where t.influence > :influence", School.class)
                .setParameter("influence",influence)
                .getResultList();
        em.close();
        return schools;
    }

}
