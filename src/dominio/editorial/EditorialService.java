package dominio.editorial;

import daos.EditorialDAO;
import java.util.Collection;


public class EditorialService {
    
    private EditorialDAO dao = new EditorialDAO();
    
    public Editorial create(Editorial ed){
        return dao.createEditorial(ed);
    }
    
    public void delete(Editorial ed){
        dao.deleteEditorial(ed);
    }
    
    public void update(Editorial ed) throws Exception{
        if(dao.findByNombre(ed.getNombre()) == null){
            throw new Exception("La editorial no existe");
        }
        dao.updateEditorial(ed);
    }
    
    public void showList(){
        Collection<Editorial> editoriales = dao.findAll();
        for (Editorial editorial : editoriales) {
            System.out.println(editorial.toString());
        }
    }
    
    public void findByNombre(String nombre) throws Exception{
        Editorial ed = dao.findByNombre(nombre);
        if(ed == null){
            throw new Exception("La editorial no existe");
        }
        System.out.println(ed.toString());
    }

}
