package daos;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DAO {

    protected EntityManager em
            = Persistence.createEntityManagerFactory("Libreria1PU").
              createEntityManager();

    protected EntityManager getEntityManager() {
        
        if (!em.isOpen()) {
            em = Persistence.createEntityManagerFactory("Libreria1PU").
                 createEntityManager();
        }
        return em;
    }
}
