package practica3;

public abstract class ControladorAlarmaState {
	
	private static Desprogramada estadoDesprogramada = new Desprogramada();
	private static Programada estadoProgramda = new Programada();
	private static Sonando estadoSonando = new Sonando();
	
	// crear una alarma
	public static ControladorAlarmaState init (ControladorAlarma contexto) {
		estadoDesprogramada.entryAction(contexto);
		return estadoDesprogramada;
	}
	
	public void nuevaAlarma(ControladorAlarma contexto) {};
	public void apagar(ControladorAlarma contexto) {};
	public void alarmaOff(ControladorAlarma contexto) {};
	public void alarmaOn(ControladorAlarma contexto) {};
	public void borrarAlarma(ControladorAlarma contexto) {};
	//public void entryAction(ControladorAlarma contexto) {};
	//public void exitAction(ControladorAlarma contexto) {};
	//public void doAction(ControladorAlarma contexto) {}

	public static ControladorPuertaState getEstadoDesprogramada() {
		return estadoDesprogramada;
	}

	public static ControladorPuertaState getEstadoProgramda() {
		return estadoProgramda;
	}

	public static ControladorPuertaState getEstadoSonando() {
		return estadoSonando;
	}
	
}
