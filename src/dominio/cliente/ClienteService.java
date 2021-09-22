package dominio.cliente;

import daos.ClienteDAO;

import java.util.Collection;

public class ClienteService {
   
    ClienteDAO dao = new ClienteDAO();

    public Cliente create(Cliente c) throws Exception {
        if(c.getDni() == null){
            throw new Exception("El DNI no puede ser nulo");
        }
        if(dao.findByDni(c.getDni()) != null){
            throw new Exception("El cliente ya existe");
        }
        return dao.create(c);
    }

    public void delete(Cliente c) {
        dao.delete(c);
    }

    public void update(Cliente c) throws Exception {
        if (dao.findByDni(c.getDni()) == null) {
            throw new Exception("El cliente no existe");
        }
        dao.update(c);
    }

    public void showList() {
        Collection<Cliente> clientes = dao.findAll();
        clientes.forEach((cliente) -> {
            System.out.println(cliente.toString());
        });
    }

    public Cliente findByDni(Long dni) throws Exception {
        Cliente c = dao.findByDni(dni);
        if (c == null) {
            throw new Exception("El Cliente no existe");
        }
        return c;
    }
}
