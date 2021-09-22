package main;

import dominio.autor.Autor;
import dominio.autor.AutorService;
import dominio.cliente.Cliente;
import dominio.cliente.ClienteService;
import dominio.editorial.Editorial;
import dominio.editorial.EditorialService;
import dominio.libro.Libro;
import dominio.libro.LibroService;
import dominio.prestamo.Prestamo;
import dominio.prestamo.PrestamoService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MainService {

    private static Scanner leer = new Scanner(System.in).useDelimiter("\n");
    
    private static LibroService ls = new LibroService();
    private static AutorService as = new AutorService();
    private static EditorialService es = new EditorialService();
    private static PrestamoService ps = new PrestamoService();
    private static ClienteService cs = new ClienteService();

    public static void menu() throws Exception {
        Boolean salida = true;
        do {

            System.out.println("Menú libreria");
            System.out.println("0.Salir");
            System.out.println("1.Crear libro");
            System.out.println("2.Crear prestamo");
            System.out.println("Ingrese la opción seleccionada");
            Integer op = leer.nextInt();
            
            switch (op){
                case 0: salida = false;
                        break;
                case 1: IngresarDatosLibro();
                        break;
                case 2: IngresarDatosPrestamo();
                        break;      
            }
        } while (salida);
        
    }

    private static void IngresarDatosLibro() throws Exception {
        System.out.println("Ingrese los datos del libro: ");
        System.out.println("Isbn: ");
        Long isbn = leer.nextLong();
        System.out.println("Titulo: ");
        String titulo = leer.next();
        System.out.println("Año: ");
        Integer anio = leer.nextInt();
        System.out.println("Ejemplares:");
        Integer ejemplares = leer.nextInt();
        System.out.println("Autor:");
        Autor autor = as.create(new Autor(leer.next()));
        System.out.println("Editorial: ");
        Editorial editorial = es.create(new Editorial(leer.next()));
        ls.create(new Libro(isbn,titulo,anio, ejemplares, autor, editorial));
        
    }

    private static void IngresarDatosPrestamo() throws ParseException, Exception {
        System.out.println("Ingrese datos del prestamo");
        
        System.out.println("Desea ingresar un nuevo cliente? s/n");
        String op = leer.next();
        Cliente cliente = null;
        try {
         
            if ("s".equals(op)) {
                cliente = cargarDatosCliente();
            } else {
                System.out.println("Ingrese el dni del cliente: ");
                cliente = cs.findByDni(leer.nextLong());
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        
        ls.showList();
        
        System.out.println("Titulo libro: ");
        Libro libro = ls.findByTitulo(leer.next());
        ls.updateEjemplares(libro);
  
        SimpleDateFormat convertir = new SimpleDateFormat("dd/MM/yyyy");
        
        System.out.println("Fecha devolucion 'dd/mm/yyyy': ");
        Date devolucion = convertir.parse(leer.next());
        
        ps.create(new Prestamo(devolucion, libro, cliente));
    }
    
    public static Cliente cargarDatosCliente() throws Exception{
        System.out.println("Ingrese datos del cliente");
        System.out.println("Nombre: ");
        String nombre = leer.next();
        System.out.println("Apellido: ");
        String apellido = leer.next();
        System.out.println("DNI: ");
        Long dni = leer.nextLong();
        System.out.println("Telefono: ");
        String telefono = leer.next();
        return cs.create(new Cliente(dni,nombre,apellido,telefono));
    }
}
