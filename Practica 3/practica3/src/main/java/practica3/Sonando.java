package practica3;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * @author Borja Cuevas y Adrian Garcia
 *
 */
public class Sonando extends ControladorAlarmaState {
	
	protected ApagaMelodiaTask apagaMelodiaTask;
	protected Timer timer = new Timer();

	public void entryAction (ControladorAlarma contexto) {
		contexto.activarMelodia();
		// programar el evento temporizado
		apagaMelodiaTask = new ApagaMelodiaTask(contexto);
		timer.schedule(apagaMelodiaTask, contexto.intervaloSonar());
	}
	
	
	public void exitAction (ControladorAlarma contexto) {
		contexto.desactivarMelodia();
		Alarma al = contexto.alarmaMasProxima();
		System.out.println("Alarma id: " + al.id() + " desactivada");
		contexto.eliminaAlarma(contexto.alarmaMasProxima());
	}
	
	
	public void apagar (ControladorAlarma contexto) {
		// cancela el evento temporizado
		apagaMelodiaTask.cancel();
		
		ControladorAlarmaState estadoSonando = getEstadoSonando();
		estadoSonando.exitAction(contexto);
		ControladorAlarmaState estadoProgramada = getEstadoProgramado();
		contexto.setState(estadoProgramada);
		estadoProgramada.entryAction(contexto);
		
	}
	
	
	/**
	 * 
	 * @author Borja Cuevas y Adrian Garcia
	 *
	 */
	public class ApagaMelodiaTask extends TimerTask {
		
		private ControladorAlarma contexto;
		ControladorAlarmaState estadoSonando = getEstadoSonando();
		ControladorAlarmaState estadoProgramada = getEstadoDesprogramado();
		
		/**
		 * Constructor.
		 * @param c estado actual del controlador.
		 */
		public ApagaMelodiaTask (ControladorAlarma c) {
			contexto = c;
		}
		
		/**
		 * Metodo run de la clase.
		 */
		public void run () {
			estadoSonando.exitAction(contexto);
			contexto.setState(estadoProgramada);
			estadoProgramada.entryAction(contexto);
		}
	}
}
