package business;

import java.util.List;

import dao.ClienteDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import model.Cliente;

@Stateless
public class GestionClientes {
	@Inject
	private ClienteDAO clienteDAO;
	public void guardarClientes(Cliente cliente) {
		Cliente cli = clienteDAO.read(cliente.getCodigo());
		if(cli != null) {
			clienteDAO.update(cliente);
		}else {
			clienteDAO.insert(cliente);
		}
	}
	
	public void actualizarCliente(Cliente cliente) throws Exception {
		Cliente cli = clienteDAO.read(cliente.getCodigo());
		if(cli != null) {
			clienteDAO.update(cliente);
		}else {
			throw new Exception("El cliente no existe");
		}
	}
	
	/*public Cliente getClienteporCedula(String cedula) throws Exception {
		if(cedula.length()!=10) 
			throw new Exception("Cedula incorrecta");
		
		return clienteDAO.getClienteporCedula(cedula);
	}*/
	
	public void borrarCliente(int codigo) {
		clienteDAO.remove(codigo);
	}
	
	public List<Cliente> getClientes(){
		return clienteDAO.getAll();
	}
	
	 public void recargarSaldo(String telefono, double montoRecarga) {
	        Cliente cliente = clienteDAO.getClienteporTelefono(telefono);
	        if (cliente != null) {
	            cliente.ingresarSaldo(montoRecarga);
	            clienteDAO.update(cliente);
	        } else {
	            System.out.println("No se ha realizado");
	        }
	    }
	 
	 public Cliente getClientePorTelefono(String telefono) {
		 Cliente cliente = clienteDAO.getClienteporTelefono(telefono);
		 if(cliente != null) {
			 return cliente;
		 }else {
			 return null;
		 }
	 }
	 
	 
	
	
}
