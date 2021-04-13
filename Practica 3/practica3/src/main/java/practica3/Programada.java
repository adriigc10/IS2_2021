package practica3;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * @author Borja Cuevas y Adrian Garcia
 *
 */
public class Programada extends ControladorAlarmaState {
	
	protected Timer timer = new Timer();
	protected LlegoLaHoraTask llegoLaHora = new LlegoLaHoraTask(null);
	
	public void entryAction (ControladorAlarma contexto) {
		if (contexto.alarmasActivas().size() == 0) {
			contexto.setState(getEstadoDesprogramado());
		} else {
			//cancela el evento temporizado
			llegoLaHora.cancel();
			
			// programar el evento temporizado
			llegoLaHora = new LlegoLaHoraTask(contexto);
			timer.schedule(llegoLaHora, contexto.alarmaMasProxima().hora());
		}
	}

	public void nuevaAlarma (ControladorAlarma contexto, String id, Date hora) {
		ControladorAlarmaState estadoProgramada = getEstadoProgramado();
		contexto.anhadeAlarma(new Alarma(id, hora));
		System.out.println("Programada: alarma creada");
		contexto.setState(estadoProgramada);
		estadoProgramada.entryAction(contexto);
	}
	
	
	public void borrarAlarma (ControladorAlarma contexto, String id) {
		ControladorAlarmaState estadoProgramada = getEstadoProgramado();
		contexto.eliminaAlarma(contexto.alarma(id));
		contexto.setState(estadoProgramada);
		estadoProgramada.entryAction(contexto);
	}
	
	
	public void alarmaOn (ControladorAlarma contexto, String id) {
		ControladorAlarmaState estadoProgramada = getEstadoProgramado();
		contexto.activaAlarma(contexto.alarma(id));
		contexto.setState(estadoProgramada);
		estadoProgramada.entryAction(contexto);
	}
	
	
	public void alarmaOff (ControladorAlarma contexto, String id) {
		ControladorAlarmaState estadoProgramada = getEstadoProgramado();
		contexto.desactivaAlarma(contexto.alarma(id));
		contexto.setState(estadoProgramada);
		estadoProgramada.entryAction(contexto);
	}
	
	
	/**
	 * 
	 * @author Borja Cuevas y Adrian Garcia
	 *
	 */
	public class LlegoLaHoraTask extends TimerTask {
		
		private ControladorAlarma contexto;
		ControladorAlarmaState estadoSonando = getEstadoSonando();
		
		/**
		 * Constructor.
		 * @param c estado actual del controlador.
		 */
		public LlegoLaHoraTask (ControladorAlarma c) {
			contexto = c;
		}

		/**
		 * Metodo run de la clase.
		 */
		public void run () {
			contexto.setState(estadoSonando);
			estadoSonando.entryAction(contexto);
		}

	}
}
