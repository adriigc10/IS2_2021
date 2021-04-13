package practica3;

import java.util.Date;

/**
 * 
 * @author Borja Cuevas y Adrian Garcia
 *
 */
public abstract class ControladorAlarmaState {

	private static Desprogramada estadoDesprogramado = new Desprogramada();
	private static Programada estadoProgramado = new Programada();
	private static Sonando estadoSonando = new Sonando();

	/**
	 * Crea nueva alarma.
	 * @param contexto estado actual del controlador.
	 * @param id id de la nueva alarma. 
	 * @param hora hora de la nueva alarma.
	 */
	public void nuevaAlarma(ControladorAlarma contexto, String id, Date hora) {};
	
	/**
	 * Apaga la alarma.
	 * @param contexto estado actual del controlador.
	 */
	public void apagar(ControladorAlarma contexto) {};
	
	/**
	 * Desactiva alarma.
	 * @param contexto estado actual del controlador.
	 * @param id id de la alarma a desactivar.
	 */
	public void alarmaOff(ControladorAlarma contexto, String id) {};
	
	/**
	 * Activa alarma.
	 * @param contexto estado actual del controlador.
	 * @param id id de la alarma a activar
	 */
	public void alarmaOn(ControladorAlarma contexto, String id) {};
	
	/**
	 * Elimina una alarma del controlador.
	 * @param contexto estado actual del controlador.
	 * @param id id de la alarma a eliminar.
	 */
	public void borrarAlarma(ControladorAlarma contexto, String id) {};
	
	/**
	 * Accion a realizar al entrar en un nuevo estado.
	 * @param contexto estado actual del controlador.
	 */
	public void entryAction(ControladorAlarma contexto) {};
	
	/**
	 * Accion a realizar al salir de un estado.
	 * @param contexto estado actual del controlador.
	 */
	public void exitAction(ControladorAlarma contexto) {};
	
	/**
	 * 
	 * @param contexto
	 */
	public void doAction(ControladorAlarma contexto) {}

	/**
	 * Constructor del estado del controlador.
	 * @param contexto estado actual del controlador.
	 * @return
	 */
	public static ControladorAlarmaState init (ControladorAlarma contexto) {
		return estadoDesprogramado;
	}

	
	/**
	 * Retorna el estado desprogramada.
	 * @return estado desprogramado.
	 */
	public static ControladorAlarmaState getEstadoDesprogramado() {
		return estadoDesprogramado;
	}

	/**
	 * Retorna estado programado.
	 * @return estado programado.
	 */
	public static ControladorAlarmaState getEstadoProgramado() {
		return estadoProgramado;
	}

	/**
	 * Retorna estado sonando.
	 * @return sonando.
	 */
	public static ControladorAlarmaState getEstadoSonando() {
		return estadoSonando;
	}
	
}
