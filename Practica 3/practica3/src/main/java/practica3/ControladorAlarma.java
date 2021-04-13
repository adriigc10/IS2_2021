package practica3;

import java.util.Date;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 
 * @author Borja Cuevas y Adrian Garcia
 *
 */
public class ControladorAlarma {
	
	private ControladorAlarmaState state = null;
	private final int INTERVALO_SONAR = 10000; //ms

	private PriorityQueue<Alarma> alarmasActivadas = new PriorityQueue<Alarma>();
	private HashMap<String, Alarma> alarmasDesactivadas = new HashMap<String, Alarma>(); 
	
	/**
	 * constructor.
	 */
	public ControladorAlarma () {
		this.state = ControladorAlarmaState.init(this);
	}
	
	/**
	 * cambia el estado del controlador.
	 * @param estado estado del controlador
	 */
	public void setState (ControladorAlarmaState estado) {
		this.state = estado;
	}
	
	/**
	 * retorna la alarma con el id indicado.
	 * @param id id de la alarma que se quiera buscar.
	 * @return alarma buscada.
	 */
	public Alarma alarma(String id) {
		Alarma alarmaBuscada = null;
		
		alarmaBuscada = alarmasDesactivadas.get(id);
		
		if (alarmaBuscada == null) {
			for (Alarma a: alarmasActivadas) {
				if (a.id().equals(id)) {
					alarmaBuscada = a;
				}
			}
		}
		
		return alarmaBuscada;
	}
	
	/**
	 * anhade alarma al controlador.
	 * @param al alarma a anhadir.
	 * @return true is se consigue anhadir, false en otro caso.
	 */
	public boolean anhadeAlarma (Alarma al) {
		if (alarma(al.id()) == null) {
			alarmasActivadas.add(al);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Elimina alarma del controlador.
	 * @param al alarma a eliminar.
	 * @return true si se consigue eliminar, false en otro caso.
	 */
	public boolean eliminaAlarma (Alarma al) {
		if (alarmasActivadas.contains(al)) {
			alarmasActivadas.remove(al);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Retorna la alarma activa mas proxima del controlador.
	 * @return alarma activa mas proxima.
	 */
	public Alarma alarmaMasProxima () {
		return alarmasActivadas.peek();
	}
	
	/**
	 * Desactiva alarma indicada.
	 * @param al alarma a desactivar.
	 */
	public void desactivaAlarma (Alarma al) {
		alarmasActivadas.remove(al);
		alarmasDesactivadas.put(al.id(), al);
	}
	
	/**
	 * Activa la alarma indicada.
	 * @param al alarma a activar.
	 */
	public void activaAlarma (Alarma al) {
		alarmasDesactivadas.remove(al.id());
		alarmasActivadas.add(al);
	}
	
	/**
	 * Retorna la lista de alarmas activas.
	 * @return lista de alarmas activas.
	 */
	public PriorityQueue<Alarma> alarmasActivas () {
		return alarmasActivadas;
	}
	
	/**
	 * Activa la melodia de una alarma.
	 */
	public void activarMelodia () {
		System.out.println("Sonando alarma");
	}
	
	/**
	 * Desactiva la melodia de una alarma.
	 */
	public void desactivarMelodia () {
		System.out.println("Alarma desactivada");
	}
	
	/**
	 * Observador del tiempo durante el que suenan las alarmas.
	 * @return intervalo de duracion.
	 */
	public int intervaloSonar() {
		return INTERVALO_SONAR;
	}
	
	/**
	 * Retorna la lista de alarmas desactivadas.
	 * @return lista de alarmas desactivadas.
	 */
	public  HashMap<String, Alarma> alarmasDesactivadas() {
		return alarmasDesactivadas;
	}
	
	// señales
	
	/**
	 * Anhade una nueva alarma al controlador.
	 * @param id id de la nueva alarma.
	 * @param hora hora de la nueva alarma.
	 */
	public void nuevaAlarma(String id, Date hora) {
		state.nuevaAlarma(this, id, hora);
		System.out.println("Alarma creada");
	}
	
	/**
	 * Apaga una alarma.
	 */
	public void apagar() {
		state.apagar(this);
	}
	
	/**
	 * Desactiva alarma.
	 * @param id id de la alarma a desactivar.
	 */
	public void alarmaOff(String id) {
		state.alarmaOff(this, id);
	}
	
	/**
	 * Activa alarma.
	 * @param id de la alarma a activar.
	 */
	public void alarmaOn(String id) {
		state.alarmaOn(this, id);
	}
	
	/**
	 * Elimina alarma del controlador.
	 * @param id id de la alarma a eliminar.
	 */
	public void borrarAlarma(String id) {
		state.borrarAlarma(this, id);
	}	
}
