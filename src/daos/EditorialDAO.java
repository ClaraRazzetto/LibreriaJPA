package daos;

import dominio.editorial.Editorial;
import java.util.Collection;


public class EditorialDAO extends DAO{

    public Editorial createEditorial(Editorial ed){
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().persist(ed);
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getEntityManager().getTransaction().rollback();
        }finally{
            em.close();
        }
        return ed;
    }
    
    public Editorial deleteEditorial(Editorial ed){
        try {
            ed = getEntityManager().find(Editorial.class, ed.getId());
            getEntityManager().getTransaction().begin();
            getEntityManager().remove(ed);
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getEntityManager().getTransaction().rollback();
        }finally {
            em.close();
        }
        return ed;
    }
    
    public Editorial updateEditorial(Editorial ed){
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().merge(ed);
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getEntityManager().getTransaction().rollback();
        }finally {
            em.close();
        }
        return ed;
    }
    
    public Collection<Editorial> findAll(){
        Collection<Editorial> editoriales = null;
        try {
            editoriales = getEntityManager().createQuery("SELECT ed FROM Editorial ed")
                      .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            em.close();
        }
        return editoriales;
    }
    
    public Editorial findByNombre(String nombre){
       Editorial ed = null;
       try {
          ed = (Editorial) getEntityManager().createQuery("SELECT ed FROM Editorial ed "
                  + "WHERE ed.nombre = :nombre").setParameter("nombre", nombre).getSingleResult();       
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            em.close();
        }
       return ed;
   }
}
