package daos;

import dominio.libro.Libro;
import java.util.Collection;

public class LibroDAO extends DAO {

    public Libro create(Libro l) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().persist(l);
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getEntityManager().getTransaction().rollback();
        } finally {
            em.close();
        }
        return l;
    }

    public Libro delete(Libro l) {
        try {
            l = getEntityManager().find(Libro.class, l.getIsbn());
            getEntityManager().getTransaction().begin();
            getEntityManager().remove(l);
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getEntityManager().getTransaction().rollback();
        } finally {
            em.close();
        }
        return l;
    }

    public Libro update(Libro l) {
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().merge(l);
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            getEntityManager().getTransaction().rollback();
        } finally {
            em.close();
        }
        return l;
    }

    public Collection<Libro> findAll() {
        Collection<Libro> libros = null;
        try {
            libros = getEntityManager().createQuery("SELECT l FROM Libro l")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return libros;
    }

    public Libro findByTitulo(String titulo) {
        Libro l = null;
        try {
            l = (Libro) getEntityManager().createQuery("SELECT l FROM Libro l "
                    + "WHERE l.titulo = :titulo").setParameter("titulo", titulo).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return l;
    }

    public Libro findeByIsbn(Long isbn){
        Libro l = null;
        try {
            l = getEntityManager().find(Libro.class, isbn);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            em.close();
        }
        return l;
    }
    
    public Libro findByAutor(String nombre) {
        Libro l = null;
        try {
            l = (Libro) getEntityManager().createQuery("SELECT l FROM Libro l "
                    + "WHERE l.autor.nombre = :nombre").setParameter("nombre", nombre).
                    getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return l;
    }

    public Libro findByEditorial(String nombre) {
        Libro l = null;
        try {
            l = (Libro) getEntityManager().createQuery("SELECT l FROM Libro l "
                    + "WHERE l.editorial.nombre = :nombre").setParameter("nombre", nombre).
                    getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return l;
    }
}
