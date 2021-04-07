package practica3;

import java.sql.Date;

public class Alarma {

	private String id;
	private Date hora;
	private boolean activada;
	
	public Alarma (String id, Date hora) {
		this.id = id;
		this.hora = hora;
		activada = true;
	}
	
	public String id () {
		return id;
	}
	
	public Date hora () {
		return hora;
	}
	
	public boolean activada () {
		return activada;
	}
	
	public void activarAlarma () {
		activada = true;
	}
	
	public void desactivarAlarma () {
		activada = false;
	}
}
