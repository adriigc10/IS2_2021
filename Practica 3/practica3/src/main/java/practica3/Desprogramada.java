package practica3;

import java.util.Date;

/**
 * 
 * @author Borja Cuevas y Adrian Garcia
 *
 */
public class Desprogramada extends ControladorAlarmaState {



	public void nuevaAlarma(ControladorAlarma contexto, String id, Date hora) {
		ControladorAlarmaState estadoProgramada = getEstadoProgramado();
		contexto.anhadeAlarma(new Alarma(id, hora));
		contexto.setState(estadoProgramada);
		estadoProgramada.entryAction(contexto);
	};


	public void borrarAlarma(ControladorAlarma contexto, String id) {
		contexto.eliminaAlarma(contexto.alarma(id));
		contexto.setState(getEstadoDesprogramado());
	};


	public void alarmaOn(ControladorAlarma contexto, String id) {
		ControladorAlarmaState estadoProgramada = getEstadoProgramado();
		contexto.activaAlarma(contexto.alarma(id));
		contexto.setState(estadoProgramada);
		estadoProgramada.entryAction(contexto);
	};


}
