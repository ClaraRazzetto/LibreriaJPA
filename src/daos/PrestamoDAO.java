package daos;

import dominio.prestamo.Prestamo;
import java.util.Collection;

public class PrestamoDAO extends DAO{

    public Prestamo create(Prestamo p){
        try{
            getEntityManager().getTransaction().begin();
            getEntityManager().persist(p);
            getEntityManager().getTransaction().commit();
        }catch(Exception e){
            e.fillInStackTrace();
            getEntityManager().getTransaction().rollback();
        } finally {
            em.close();
        }
        return p;
    }
    
    public Prestamo delete(Prestamo p) {
        try {
            p = getEntityManager().find(Prestamo.class, p.getId());
            getEntityManager().getTransaction().begin();
            getEntityManager().remove(p);
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.fillInStackTrace();
            getEntityManager().getTransaction().rollback();
        } finally {
            em.close();
        }
        return p;
    }
    
    public Prestamo update(Prestamo p){
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().merge(p);
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.fillInStackTrace();
            getEntityManager().getTransaction().rollback();
        } finally {
            em.close();
        }
        return p;
    }
    
    public Collection<Prestamo> findAll(){
        Collection<Prestamo> prestamos = null;
        try {
            prestamos = getEntityManager().createQuery("SELECT p FROM Prestamo p")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return prestamos;
    }
            
    public Prestamo findById(Integer id) throws Exception {
        Prestamo p = null;
        try {
            p = getEntityManager().find(Prestamo.class, id);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            em.close();
        }
        return p;
    }

    public Collection<Prestamo> findByCliente(Long dni){
        Collection<Prestamo> prestamos = null;
        try {
            prestamos = getEntityManager().createQuery("SELECT p FROM Prestamo p"
                    + " WHERE p.cliente.dni = :dni").setParameter("dni", dni).
                    getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return prestamos;
    }
}
