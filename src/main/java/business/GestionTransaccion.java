package business;

import dao.TransaccionDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
@Stateless
public class GestionTransaccion {
	@Inject
	private TransaccionDAO transaccionDAO;
	
	
	
}
