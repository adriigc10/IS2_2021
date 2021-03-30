package practica3;

import java.sql.Date;

public class Desprogramada extends ControladorAlarmaState {
	
	public void nuevaAlarma(ControladorAlarma contexto, String id, Date hora) {
		contexto.setState(estadoProgramada);
	};
	
	public void alarmaOn(ControladorAlarma contexto) {};
	public void borrarAlarma(ControladorAlarma contexto) {};

}
