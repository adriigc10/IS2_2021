package practica3;

import java.sql.Date;

public class Alarma {

	private String id;
	private Date hora;
	
	public Alarma (String id, Date hora) {
		this.id = id;
		this.hora = hora;
	}
	
	public String id () {
		return id;
	}
	
	public Date hora () {
		return hora;
	}
}
