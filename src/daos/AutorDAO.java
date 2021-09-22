package daos;

import dominio.autor.Autor;
import java.util.Collection;


public class AutorDAO extends DAO{

    public Autor createAutor(Autor a){
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().persist(a);
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getEntityManager().getTransaction().rollback();
        }finally{
            em.close();
        }
        return a;
    }
    
    public Autor deleteAutor(Autor a){
        try {
            a = getEntityManager().find(Autor.class, a.getId());
            getEntityManager().getTransaction().begin();
            getEntityManager().remove(a);
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getEntityManager().getTransaction().rollback();
        }finally {
            em.close();
        }
        return a;
    }
    
    public Autor updateAutor(Autor a){
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().merge(a);
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getEntityManager().getTransaction().rollback();
        }finally {
            em.close();
        }
        return a;
    }
    
    public Collection<Autor> findAll(){
        Collection<Autor> autores = null;
        try {
            autores = getEntityManager().createQuery("SELECT a FROM Autor a")
                      .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            em.close();
        }
        return autores;
    }
    
   public Autor findByNombre(String nombre){
       Autor a = null;
       try {
          a = (Autor) getEntityManager().createQuery("SELECT a FROM Autor a "
                  + "WHERE a.nombre = :nombre").setParameter("nombre", nombre).getSingleResult();       
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            em.close();
        }
       return a;
   }
    
}
