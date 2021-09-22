package main;

import dominio.autor.Autor;
import dominio.autor.AutorService;
import dominio.cliente.ClienteService;
import dominio.editorial.Editorial;
import dominio.editorial.EditorialService;
import dominio.libro.Libro;
import dominio.libro.LibroService;
import dominio.prestamo.PrestamoService;


public class Main {
 
    
    public static void main(String[] args) throws Exception {
    
    LibroService ls = new LibroService();
    AutorService as = new AutorService();
    EditorialService es = new EditorialService();
    PrestamoService ps = new PrestamoService();
    ClienteService cs = new ClienteService();
    
//    Autor a1 = as.create(new Autor("Ariosto"));
//    Editorial e1 = es.create(new Editorial("Espasa Clasicos"));
//    Libro l1 = ls.create(new Libro(12345l, "Orlando furioso", 1532, 5, a1, e1));
//   
//    Autor a2 = as.create(new Autor("Charles Dickens"));
//    Editorial e2 =es.create(new Editorial("Juventud"));
//    Libro l2 = ls.create(new Libro(12346l, "Historia de dos ciudades", 1859, 3, a2, e2));
//    
//    Autor a3 = as.create(new Autor("Margaret Mitchell"));
//    Editorial e3 = es.create(new Editorial("Kindle"));
//    Libro l3 = ls.create(new Libro(12347l, "Lo que el viento se llevo", 1936, 4, a3, e3));
//    
      MainService.menu();
    
      ls.showList();

    }

}
