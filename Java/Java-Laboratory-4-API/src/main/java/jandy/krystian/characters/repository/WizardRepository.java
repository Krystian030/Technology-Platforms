package jandy.krystian.characters.repository;

import jandy.krystian.characters.entity.Wizard;
import jandy.krystian.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Comparator;

public class WizardRepository extends JpaRepository<Wizard,String> {

    EntityManagerFactory emf;

    public WizardRepository(EntityManagerFactory emf) {
        super(emf, Wizard.class);
        this.emf = emf;
    }

    public ArrayList<Wizard> getBiggerThanLvl(int level){
        EntityManager em = emf.createEntityManager();
        ArrayList<Wizard> wizards = (ArrayList<Wizard>) em.createQuery("select m from Wizard m where m.level >= :level", Wizard.class)
                .setParameter("level",level)
                .getResultList();
        em.close();
        return wizards;
    }

    public ArrayList<Wizard> getMageBiggerThanLvlFromTower(int level, String towerName) {
        EntityManager em = emf.createEntityManager();
        ArrayList<Wizard> wizards = (ArrayList<Wizard>) em.createQuery("select m from Wizard m where m.level >= :level and m.school.name = :tower", Wizard.class)
                        .setParameter("level",level)
                        .setParameter("tower",towerName)
                        .getResultList();
        em.close();
        return wizards;
    }
    public ArrayList<Wizard> getMageBiggerThanLvlFromSchool(int level, String schoolName) {
        EntityManager em = emf.createEntityManager();
        ArrayList<Wizard> wizards = (ArrayList<Wizard>) em.createQuery("select m from Wizard m where m.level >= :level and m.school.name = :school order by m.name", Wizard.class)
                .setParameter("level",level)
                .setParameter("school",schoolName)
                .getResultList();
        em.close();

        return wizards;
    }

}

