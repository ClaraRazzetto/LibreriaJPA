package dominio.autor;

import daos.AutorDAO;
import java.util.Collection;

public class AutorService {

    private AutorDAO dao = new AutorDAO();
    
    public Autor create(Autor a){
        return dao.createAutor(a);
    }
    
    public void delete(Autor a){
        dao.deleteAutor(a);
    }
    
    public void update(Autor a) throws Exception{
        if(dao.findByNombre(a.getNombre()) == null){
            throw new Exception("El autor no existe");
        }
        dao.updateAutor(a);
    }
    
    public void showList(){
        Collection<Autor> autores = dao.findAll();
        autores.forEach((autor) -> {
            System.out.println(autor.toString());
        });
    }
    
    public void findByName(String nombre) throws Exception{
        Autor a = dao.findByNombre(nombre);
        if(a == null){
            throw new Exception("El autor no existe");
        }
        System.out.println(a.toString());
    }
}
