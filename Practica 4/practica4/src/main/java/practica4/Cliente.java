package practica4;

/**
 * Clase del objeto cliente.
 * @author Borja Cuevas Cuesta y Adrian García Cubas
 *
 */
public class Cliente {
	private String nombre;
	private String dni;
	private boolean minusvalia;
	
	/**
	 * Constructor del Objeto cliente.
	 * @param nombre nombre del cliente
	 * @param dni dni del cliente
	 * @param minusvalia indica si el cliente tiene alguna minusvalia
	 */
	public Cliente(String nombre, String dni, boolean minusvalia) {
		this.nombre = nombre;
		this.dni = dni;
		this.minusvalia = minusvalia;
	}
	
	/**
	 * Metodo que retorna el nombre del cliente.
	 * @return nombre del cliente
	 */
	public String nombre() {
		return nombre;
	}
	
	/**
	 * Metodo que retorna el dni del cliente.
	 * @return dni del cliente
	 */
	public String dni() {
		return dni;
	}
	
	/**
	 * Metodo que indica si un cliente tiene alguna minusvalia
	 * @return true=minusvalia, false=no minusvalia
	 */
	public boolean minusvalia() {
		return minusvalia;
	}
}
