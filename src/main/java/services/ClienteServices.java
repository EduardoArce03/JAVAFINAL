package services;

import java.util.List;

import business.GestionClientes;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Cliente;

@Path("clientes")
public class ClienteServices {
	
	@Inject
	private GestionClientes gestionClientes;
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Cliente cliente) {
		try {
			gestionClientes.guardarClientes(cliente);
			return Response.ok(cliente).build();
		}catch(Exception e){
			ErrorMessage em = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(em)
					.build();
		}
	}
	
	 @POST
	    @Path("/{telefono}/{monto}")
	    public Response realizarRecarga(@PathParam("telefono") String telefono, @PathParam("monto") double montoRecarga) {
	        try {
	            gestionClientes.recargarSaldo(telefono, montoRecarga);
	            return Response.ok(telefono).build();
	        } catch (Exception e) {
	        	ErrorMessage em = new ErrorMessage(99, e.getMessage());
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
						.entity(em)
						.build();
	        }
	    }
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("obtener/{telefono}")
	public Response getClientePorCedula(@PathParam("telefono")String telefono) {
		try {
			Cliente cliente = gestionClientes.getClientePorTelefono(telefono);
			return Response.ok(cliente).build();
		}catch(Exception e){
			ErrorMessage em = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(em)
					.build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String borrar(@QueryParam("id")int codigo) {
		try {
			gestionClientes.borrarCliente(codigo);
			return "OK";
		} catch (Exception e) {
			// TODO: handle exception
			return "ERROR";
		}
	}
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response leer(@QueryParam("dni")String cedula) {
		
		try {
			Cliente ge = gestionClientes.getClienteporCedula(cedula);
			return Response.ok(ge).build();
		} catch (Exception e) {
			// TODO: handle exception
			ErrorMessage em = new ErrorMessage(4, "Cliente no existe ");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(em)
					.build();
		}
	}*/
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getClientes(){
		List <Cliente> lista = gestionClientes.getClientes();
		if(lista.size()>0) {
			return Response.ok(lista).build();
		}
		ErrorMessage em = new ErrorMessage(6, "No se registran clientes");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(em)
				.build();
	}
}
