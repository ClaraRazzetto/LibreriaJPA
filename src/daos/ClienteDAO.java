package daos;

import dominio.cliente.Cliente;
import java.util.Collection;

public class ClienteDAO extends DAO{

    public Cliente create(Cliente c){
        try{
            getEntityManager().getTransaction().begin();
            getEntityManager().persist(c);
            getEntityManager().getTransaction().commit();
        }catch(Exception e){
            e.fillInStackTrace();
            getEntityManager().getTransaction().rollback();
        } finally {
            em.close();
        }
        return c;
    }
    
    public Cliente delete(Cliente c) {
        try {
            c = getEntityManager().find(Cliente.class, c.getId());
            getEntityManager().getTransaction().begin();
            getEntityManager().remove(c);
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.fillInStackTrace();
            getEntityManager().getTransaction().rollback();
        } finally {
            em.close();
        }
        return c;
    }
    
    public Cliente update(Cliente c){
        try {
            getEntityManager().getTransaction().begin();
            getEntityManager().merge(c);
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            e.fillInStackTrace();
            getEntityManager().getTransaction().rollback();
        } finally {
            em.close();
        }
        return c;
    }
    
    public Collection<Cliente> findAll(){
        Collection<Cliente> clientes = null;
        try {
            clientes = getEntityManager().createQuery("SELECT c FROM Cliente c")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return clientes;
    }
            
    public Cliente findByDni(Long dni) throws Exception {
        Cliente c = null;
        try {
            c = (Cliente) getEntityManager().createQuery("SELECT c FROM Cliente c WHERE c.dni = :dni").
                    setParameter("dni", dni).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return c;
    }

}
