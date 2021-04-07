package practica3;

import java.util.HashMap;
import java.util.PriorityQueue;


public class ControladorAlarma {
	
	private ControladorAlarmaState state = null;
	private final int INTERVALO_SONAR = 10;

	private PriorityQueue<Alarma> alarmasActivadas = new PriorityQueue<Alarma>();
	private HashMap<String, Alarma> alarmasDesactivadas = new HashMap<String, Alarma>(); 
	
	//TODO: que coño es esto
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
	
	// TODO revisar
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
		al.desactivarAlarma();
	}
	
	public void activaAlarma (Alarma al) {
		al.activarAlarma();
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
	
}
