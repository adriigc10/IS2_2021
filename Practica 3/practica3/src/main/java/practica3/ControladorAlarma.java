package practica3;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;


public class ControladorAlarma {
	
	private ControladorAlarmaState state = null;
	private final int INTERVALO_SONAR = 10000; //ms

	private PriorityQueue<Alarma> alarmasActivadas = new PriorityQueue<Alarma>();
	private HashMap<String, Alarma> alarmasDesactivadas = new HashMap<String, Alarma>(); 
	
	//TODO: que co�o es esto
	public ControladorAlarma () {
		this.state = ControladorAlarmaState.init(this);
	}
	
	public void setState (ControladorAlarmaState estado) {
		this.state = estado;
	}
	
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
	
	public boolean anhadeAlarma (Alarma al) {
		if (alarma(al.id()) == null) {
			alarmasActivadas.add(al);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean eliminaAlarma (Alarma al) {
		if (alarmasActivadas.contains(al)) {
			alarmasActivadas.remove(al);
			return true;
		} else if (alarmasDesactivadas.containsKey(al.id())) {
			alarmasDesactivadas.remove(al.id());
			return true;
		} else {
			return false;
		}
	}
	
	public Alarma alarmaMasProxima () {
		return alarmasActivadas.peek();
	}
	
	
	public void desactivaAlarma (Alarma al) {
		//al.desactivarAlarma();
		alarmasActivadas.remove(al);
		alarmasDesactivadas.put(al.id(), al);
	}
	
	public void activaAlarma (Alarma al) {
		//al.activarAlarma();
		alarmasDesactivadas.remove(al.id());
		alarmasActivadas.add(al);
	}
	
	public PriorityQueue<Alarma> alarmasActivas () {
		return alarmasActivadas;
	}
	
	public void activarMelodia () {
		System.out.println("Me reporto en tu zonaaaaaaaa");
	}
	
	public void desactivarMelodia () {
		System.out.println("Silencio");
	}
	
	public int intervaloSonar() {
		return INTERVALO_SONAR;
	}
	
	public  HashMap<String, Alarma> alarmasDesactivadas() {
		return alarmasDesactivadas;
	}
	
	// se�ales
	
	public void nuevaAlarma(String id, Date hora) {
		state.nuevaAlarma(this, id, hora);
		System.out.println("Alarma creada");
		System.out.println("Alarmas activas: " + alarmasActivadas.size());
		System.out.println("Alarmas desactivadas: " + alarmasDesactivadas.size());
	}
	
	public void apagar() {
		state.apagar(this);
	}
	
	public void alarmaOff(String id) {
		state.alarmaOff(this, id);
	}
	
	public void alarmaOn(String id) {
		state.alarmaOn(this, id);
	}
	
	public void borrarAlarma(String id) {
		state.borrarAlarma(this, id);
	}	
}
