package practica3;

public class Sonando extends ControladorAlarmaState {

	public void entryAction (ControladorAlarma contexto) {
		contexto.activarMelodia();
		//TODO timer
	}
	
	
	public void exitAction (ControladorAlarma contexto) {
		contexto.desactivarMelodia();
		//contexto.eliminaAlarma(al);
		//TODO timer
	}
	
	
	public void apagar (ControladorAlarma contexto) {
		ControladorAlarmaState estadoProgramada = getEstadoProgramada();
		contexto.setState(estadoProgramada);
		estadoProgramada.exitAction(contexto);
		
	}
}
