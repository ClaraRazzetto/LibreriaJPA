package dominio.libro;

import daos.LibroDAO;
import java.util.Collection;
import java.util.Objects;

public class LibroService {
    
    private LibroDAO dao = new LibroDAO();
    
    public Libro create(Libro l) throws Exception {
        if (l.getIsbn() == null) {
            throw new Exception("El ISBN no puede ser nulo");
        }
        if (dao.findeByIsbn(l.getIsbn()) != null) {
            throw new Exception("El libro ya existe");
        }
        return dao.create(l);        
    }
    
    public void delete(Libro l) {
        dao.delete(l);
    }
    
    public void update(Libro l) throws Exception {
        if (dao.findeByIsbn(l.getIsbn()) == null) {
            throw new Exception("El libro no existe");
        }
        dao.update(l);
    }
    
    public void showList() {
        Collection<Libro> libros = dao.findAll();
        libros.forEach((libro) -> {
            System.out.println(libro.toString());
        });
    }
    
    public Libro findByTitulo(String titulo) throws Exception {
        Libro l = dao.findByTitulo(titulo);
        if (l == null) {
            throw new Exception("El libro no existe");
        }
        return l;
    }
    
    public Libro findByISBN(Long isbn) throws Exception {
        Libro l = dao.findeByIsbn(isbn);
        if (l == null) {
            throw new Exception("El libro no existe");
        }
        return l;
    }
    
    public Libro findByAutor(String nombre) throws Exception {
        Libro l = dao.findByAutor(nombre);
        if (l == null) {
            throw new Exception("El libro no existe");
        }
        return l;
    }
    
    public Libro findByEditorial(String nombre) throws Exception {
        Libro l = dao.findByEditorial(nombre);
        if (l == null) {
            throw new Exception("El libro no existe");
        }
        return l;
    }
    
    public void updateEjemplares(Libro l) throws Exception{
        if (dao.findeByIsbn(l.getIsbn()) == null) {
            throw new Exception("El libro no existe");
        }
        if(l.getEjemplaresRestantes()== 0 || Objects.equals(l.getEjemplares(), l.getEjemplaresPrestados())){
            throw new Exception("Se agotaron los ejemplares");
        }
        l.setEjemplaresPrestados(l.getEjemplaresPrestados()+1);
        l.setEjemplaresRestantes(l.getEjemplaresRestantes()-1);
        dao.update(l);
    }
}
