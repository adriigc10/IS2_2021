package practica3;

import java.sql.Date;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class ControladorAlarma {
	
	private ControladorAlarmaState estado;
	private final int INTERVALO_SONAR = 10;

	private PriorityQueue<Alarma> alarmasActivas = new PriorityQueue<Alarma>();
	private HashMap<String, Alarma> alarmasDesactivadas = new HashMap<String, Alarma>(); 
	
	public ControladorAlarma () {
		this.estado = ControladorAlarmaState.init(this);
	}
	
	public void setState (ControladorAlarmaState estado) {
		this.estado = estado;
	}
	
	public Alarma alarma(String id) {
		Alarma alarmaEncontrada = null;
		
		alarmaEncontrada = alarmasDesactivadas.get(id);
		
		if (alarmaEncontrada == null) {
			alarmasActivas.
		}
		
		return alarmaEncontrada;
	}
	
	public boolean anhadeAlarma (Alarma al) {
		alarmas.put(al.id(), al);
	}
	
	public boolean eliminaAlarma (Alarma al) {
		return false;
	}
	
	public Alarma alarmaMasProxima () {
		return null;
	}
	
	public void desactivaAlarma (Alarma al) {
		
	}
	
	public void activaAlarma (Alarma al) {
		
	}
	
	public Alarma[] alarmasActivas () {
		return null;
	}
	
	public void activarMelodia () {
		
	}
	
	public void desactivarMelodia () {
		
	}
	
}
