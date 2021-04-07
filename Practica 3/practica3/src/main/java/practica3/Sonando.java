package practica3;

import java.util.Timer;
import java.util.TimerTask;

public class Sonando extends ControladorAlarmaState {
	
	protected ApagaMelodiaTask apagaMelodiaTask;
	protected Timer timer = new Timer();

	public void entryAction (ControladorAlarma contexto) {
		contexto.activarMelodia();
		//TODO timer
		// programar el evento temporizado
		apagaMelodiaTask = new ApagaMelodiaTask(contexto);
		timer.schedule(apagaMelodiaTask, contexto.intervaloSonar());
	}
	
	
	public void exitAction (ControladorAlarma contexto) {
		contexto.desactivarMelodia();
		//contexto.eliminaAlarma(al);
	}
	
	
	public void apagar (ControladorAlarma contexto) {
		// cancela el evento temporizado
		apagaMelodiaTask.cancel();
		
		ControladorAlarmaState estadoSonando = getEstadoSonando();
		estadoSonando.exitAction(contexto);
		ControladorAlarmaState estadoProgramada = getEstadoProgramada();
		contexto.setState(estadoProgramada);
		estadoProgramada.entryAction(contexto);
		
	}
	
	
	
	public class ApagaMelodiaTask extends TimerTask {
		
		private ControladorAlarma contexto;
		ControladorAlarmaState estadoSonando = getEstadoSonando();
		ControladorAlarmaState estadoProgramada = getEstadoDesprogramada();
		
		public ApagaMelodiaTask (ControladorAlarma c) {
			contexto = c;
		}
		
		public void run () {
			estadoSonando.exitAction(contexto);
			contexto.setState(estadoProgramada);
			estadoProgramada.entryAction(contexto);
		}
	}
}
