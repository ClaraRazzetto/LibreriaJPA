package dominio.prestamo;

import daos.PrestamoDAO;
import java.text.SimpleDateFormat;

import java.util.Collection;

public class PrestamoService {

    private PrestamoDAO dao = new PrestamoDAO();
    private SimpleDateFormat convertir = new SimpleDateFormat("dd/MM/yyyy");
    

    public Prestamo create(Prestamo p) throws Exception {
        
        if (dao.findById(p.getId()) != null) {
            throw new Exception("El prestamo ya existe");
        }
        return dao.create(p);
    }

    public void delete(Prestamo p) {
        dao.delete(p);
    }

    public void update(Prestamo p) throws Exception {
        if (dao.findById(p.getId()) == null) {
            throw new Exception("El prestamo no existe");
        }
        dao.update(p);
    }

    public void findAll(){
        Collection<Prestamo> prestamos = dao.findAll();
        showList(prestamos);
    }
    
    public void showListByCliente(Long dni) throws Exception{
      Collection<Prestamo> prestamos = dao.findByCliente(dni);
        if (prestamos == null) {
            throw new Exception("El cliente no tiene prestamos");
        }
      showList(prestamos);
    }
    
    public void showList(Collection<Prestamo> prestamos) {
        prestamos.forEach((prestamo) -> {
            System.out.println(prestamo.toString());
        });
    }
}
