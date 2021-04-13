package practica3;

import java.util.Date;

/**
 * 
 * @author Borja Cuevas y Adrian Garcia
 *
 */
public class Alarma implements Comparable<Alarma>{

	private String id;
	private Date hora;
	
	/**
	 * Constructor de la alarma.
	 * @param id id de la alarma.
	 * @param hora hora de sonar de la alarma.
	 */
	public Alarma (String id, Date hora) {
		this.id = id;
		this.hora = hora;
	}
	
	/**
	 * Observador del id.
	 * @return id.
	 */
	public String id () {
		return id;
	}
	
	/**
	 * Observador de la hora.
	 * @return hora.
	 */
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
