package practica3;

import java.util.Date;

public class Desprogramada extends ControladorAlarmaState {
	
	
	public void nuevaAlarma(ControladorAlarma contexto, String id, Date hora) {
		ControladorAlarmaState estadoProgramada = getEstadoProgramada();
		contexto.anhadeAlarma(new Alarma(id, hora));
		contexto.setState(estadoProgramada);
		estadoProgramada.entryAction(contexto);
	};
	
	
	public void borrarAlarma(ControladorAlarma contexto, String id) {
		contexto.eliminaAlarma(contexto.alarma(id));
		contexto.setState(getEstadoDesprogramada());
	};
	
	
	public void alarmaOn(ControladorAlarma contexto, String id) {
		ControladorAlarmaState estadoProgramada = getEstadoProgramada();
		//contexto.alarma(id).activarAlarma();
		//TODO: revision cambios
		contexto.activaAlarma(contexto.alarma(id));
		contexto.setState(estadoProgramada);
		estadoProgramada.entryAction(contexto);
	};
	

}
