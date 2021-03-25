package practica3;

public class Desprogramada extends ControladorAlarmaState {
	
	public void nuevaAlarma(ControladorAlarma contexto) {
		contexto.setState(estadoProgramada);
	};
	public void alarmaOn(ControladorAlarma contexto) {};
	public void borrarAlarma(ControladorAlarma contexto) {};

}
