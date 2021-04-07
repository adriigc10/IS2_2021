package practica3;

import java.sql.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Programada extends ControladorAlarmaState {
	
	protected Timer timer = new Timer();
	protected LlegoLaHoraTask llegoLaHora;
	
	public void entryAction (ControladorAlarma contexto) {
		if (contexto.alarmasActivas().size() == 0) {
			contexto.setState(getEstadoDesprogramada());
		} else {
			//TODO timer
			// programar el evento temporizado
			// activar melodia de la alarma mas proxima
			llegoLaHora = new LlegoLaHoraTask(contexto);
			timer.schedule(llegoLaHora, contexto.alarmaMasProxima().hora());
		}
	}

	public void nuevaAlarma (ControladorAlarma contexto, String id, Date hora) {
		ControladorAlarmaState estadoProgramada = getEstadoProgramada();
		contexto.anhadeAlarma(new Alarma(id, hora));
		contexto.setState(estadoProgramada);
		estadoProgramada.entryAction(contexto);
	}
	
	
	public void borrarAlarma (ControladorAlarma contexto, String id) {
		ControladorAlarmaState estadoProgramada = getEstadoProgramada();
		contexto.eliminaAlarma(contexto.alarma(id));
		contexto.setState(estadoProgramada);
		estadoProgramada.entryAction(contexto);
	}
	
	
	public void alarmaOn (ControladorAlarma contexto, String id) {
		ControladorAlarmaState estadoProgramada = getEstadoProgramada();
		contexto.alarma(id).activarAlarma();
		contexto.setState(estadoProgramada);
		estadoProgramada.entryAction(contexto);
	}
	
	
	public void alarmaOff (ControladorAlarma contexto, String id) {
		ControladorAlarmaState estadoProgramada = getEstadoProgramada();
		contexto.alarma(id).desactivarAlarma();
		contexto.setState(estadoProgramada);
		estadoProgramada.entryAction(contexto);
	}
	
	
	
	public class LlegoLaHoraTask extends TimerTask {
		
		private ControladorAlarma contexto;
		ControladorAlarmaState estadoSonando = getEstadoSonando();
		
		public LlegoLaHoraTask (ControladorAlarma c) {
			contexto = c;
		}


		public void run () {
			contexto.setState(estadoSonando);
			estadoSonando.entryAction(contexto);
		}

	}
}
