package business;

import dao.ClienteDAO;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import model.Cliente;

@Singleton
@Startup
public class GestionDatos {
	@Inject
	private ClienteDAO daoCliente;
	
	@PostConstruct
	public void init() {
		Cliente cliente1 = new Cliente();
		cliente1.setCodigo(1);
		cliente1.setDni("1150704672");
		cliente1.setDireccion("Ricaurte");
		cliente1.setNombre("Pablo Bravo");
		cliente1.setTelefono("0994030867");
		cliente1.setSaldo(10);
		daoCliente.insert(cliente1);
	}
}
