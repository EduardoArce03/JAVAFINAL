package dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import model.Cliente;
@Stateless 
public class ClienteDAO {
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Cliente cliente) {
		em.persist(cliente);
	}
	
	public void update(Cliente cliente) {
		em.merge(cliente);
	}
	
	public void remove(int codigo) {
		Cliente cliente = em.find(Cliente.class, codigo);
		em.remove(cliente);
	}
	
	public Cliente read(int codigo) {
		Cliente cliente = em.find(Cliente.class, codigo);
		return cliente;
	}
	
	public List<Cliente> getAll(){
		String jpql = "SELECT c FROM Cliente c";
		Query q = em.createQuery(jpql, Cliente.class);
		return q.getResultList();
	}
	
	public Cliente getClienteporTelefono(String cedula) {
		String jpql = "SELECT c FROM Cliente c WHERE c.telefono = :cedula";
		Query q = em.createQuery(jpql, Cliente.class);
		q.setParameter("cedula", cedula);
		List<Cliente> clientes = q.getResultList();
		if(clientes.size()>0) {
			return clientes.get(0);
		}
		return null;
	}
	
	 public void recargarSaldo(int codigoCliente, double montoRecarga) {
	        Cliente cliente = em.find(Cliente.class, codigoCliente);
	        if (cliente != null) {
	            cliente.ingresarSaldo(montoRecarga);
	            em.merge(cliente); 
	        } else {
	           System.out.println("No se ha realizado");
	        }
	    }
	
	
}
