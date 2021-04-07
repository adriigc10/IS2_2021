package practica3;

import java.sql.Date;

public abstract class ControladorAlarmaState {

	private static Desprogramada estadoDesprogramada = new Desprogramada();
	private static Programada estadoProgramda = new Programada();
	private static Sonando estadoSonando = new Sonando();


	public void nuevaAlarma(ControladorAlarma contexto, String id, Date hora) {};
	public void apagar(ControladorAlarma contexto) {};
	public void alarmaOff(ControladorAlarma contexto, String id) {};
	public void alarmaOn(ControladorAlarma contexto, String id) {};
	public void borrarAlarma(ControladorAlarma contexto, String id) {};
	public void entryAction(ControladorAlarma contexto) {};
	public void exitAction(ControladorAlarma contexto) {};
	public void doAction(ControladorAlarma contexto) {}

	// crear una alarma
	//TODO: que coño es esto
	public static ControladorAlarmaState init (ControladorAlarma contexto) {
		estadoDesprogramada.entryAction(contexto);
		return estadoDesprogramada;
	}

	
	// Observadores de los estados 
	public static ControladorAlarmaState getEstadoDesprogramada() {
		return estadoDesprogramada;
	}

	public static ControladorAlarmaState getEstadoProgramada() {
		return estadoProgramda;
	}

	public static ControladorAlarmaState getEstadoSonando() {
		return estadoSonando;
	}
	
}
