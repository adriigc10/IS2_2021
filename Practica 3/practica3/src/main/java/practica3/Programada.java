package practica3;

import java.sql.Date;

public class Programada extends ControladorAlarmaState {

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
	
	
	public void entryAction (ControladorAlarma contexto) {
		if (contexto.alarmasActivas().size() == 0) {
			contexto.setState(getEstadoDesprogramada());
		} else {
			//TODO timer
		}
	}
}
