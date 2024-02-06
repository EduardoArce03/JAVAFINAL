package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


public class Transaccion {
	    private int codigo;
	    private double montoRecarga;
	    private Cliente cuentaARecargar;

	    // Constructor, getters y setters

	    public Transaccion(double montoRecarga, Cliente cuentaARecargar) {
	        this.montoRecarga = montoRecarga;
	        this.cuentaARecargar = cuentaARecargar;
	    }

	    public void realizarRecarga(double montoRecarga) {
	        
	    	cuentaARecargar.ingresarSaldo(montoRecarga);
	        
	    }
}
