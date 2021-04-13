package practica3;

import java.util.Date;

public class Alarma implements Comparable<Alarma>{

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
	
	@Override
	public String toString() {
		return "Alarma id: " + id + ", hora: " + hora;
	}
	
	public int compareTo(Alarma al) {
		return hora.compareTo(al.hora());
	}
}
