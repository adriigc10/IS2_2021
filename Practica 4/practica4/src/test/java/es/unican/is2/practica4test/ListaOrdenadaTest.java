package es.unican.is2.practica4test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import es.unican.is2.containers.ListaOrdenada;

/**
 * Clase de prueba de la clase ListaOrdenada.
 * 
 * @author Borja Cuevas y Adrian Garcia.
 *
 */
public class ListaOrdenadaTest {

	ListaOrdenada<Integer> lista;

	@Before
	public void setUp() throws Exception {
		lista = new ListaOrdenada<Integer>();
	}

	@Test
	public void testConstructor() {
		assertTrue(lista.size() == 0);
	}

	@Test
	public void testGet() {

		ListaOrdenada<Integer> listaPrueba = new ListaOrdenada<Integer>();

		// casos validos

		// obtener de lista con un elemento
		listaPrueba.add(1);
		try {
			assertTrue(listaPrueba.get(0) == 1);
			assertTrue(listaPrueba.size() == 1);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}

		// obtener valor intermedio de lista con varios elementos
		listaPrueba.add(2);
		listaPrueba.add(4);
		listaPrueba.add(3);
		try {
			assertTrue(listaPrueba.get(2) == 3);
			assertTrue(listaPrueba.size() == 4);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}

		// obtener valor limite de lista con varios elementos
		try {
			assertTrue(listaPrueba.get(listaPrueba.size() - 1) == 4);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}


		// casos no validos

		// lista vacia
		listaPrueba = new ListaOrdenada<Integer>();
		try {
			listaPrueba.get(0);
			fail("Deberia haberse lanzado excepcion");
		} 
		catch (IndexOutOfBoundsException e) {
			// funcionamiento correcto
		}

		// indice negativo
		listaPrueba.add(1);
		listaPrueba.add(2);
		try {
			listaPrueba.get(-7);
			fail("Deberia haberse lanzado excepcion");
		} 
		catch (IndexOutOfBoundsException e) {
			// funcionamiento correcto
		}

		// indice negativo
		try {
			listaPrueba.get(-1);
			fail("Deberia haberse lanzado excepcion");
		} 
		catch (IndexOutOfBoundsException e) {
			// funcionamiento correcto
		}

		// indice mayor que size-1
		try {
			listaPrueba.get(listaPrueba.size());
			fail("Deberia haberse lanzado excepcion");
		} 
		catch (IndexOutOfBoundsException e) {
			// funcionamiento correcto
		}

		// indice mayor que size-1
		try {
			listaPrueba.get(listaPrueba.size() + 2);
			fail("Deberia haberse lanzado excepcion");
		} 
		catch (IndexOutOfBoundsException e) {
			// funcionamiento correcto
		}
	}

	@Test
	public void testAdd() {

		ListaOrdenada<Integer> listaPrueba = new ListaOrdenada<Integer>();

		// casos validos

		// anhadir a lista vacia
		try {
			listaPrueba.add(1);
			assertTrue(listaPrueba.get(0) == 1);
			assertTrue(listaPrueba.size() == 1);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}

		// anhadir a lista con un elemento
		try {
			listaPrueba.add(1);
			assertTrue(listaPrueba.get(1) == 1);
			assertTrue(listaPrueba.size() == 2);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}

		// anhadir a lista con varios elementos
		listaPrueba = new ListaOrdenada<Integer>();
		listaPrueba.add(2);
		listaPrueba.add(3);
		listaPrueba.add(4);
		try {
			listaPrueba.add(1);
			assertTrue(listaPrueba.get(0) == 1);
			assertTrue(listaPrueba.size() == 4);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}

	}

	@Test
	public void testRemove() {

		ListaOrdenada<Integer> listaPrueba = new ListaOrdenada<Integer>();

		// casos validos

		// eliminar de lista con un elemento
		listaPrueba.add(1);
		try {
			listaPrueba.remove(0);
			assertTrue(listaPrueba.size() == 0);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}

		// eliminar valor intermedio de lista con varios elementos
		listaPrueba.add(1);
		listaPrueba.add(2);
		listaPrueba.add(3);
		listaPrueba.add(4);
		try {			
			listaPrueba.remove(2);
			assertTrue(listaPrueba.size() == 3);
			assertTrue(listaPrueba.get(2) == 4);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}
		
		// eliminar valor limite de lista con varios elementos
		listaPrueba.add(3);
		try {			
			listaPrueba.remove(listaPrueba.size() - 1);
			assertTrue(listaPrueba.size() == 3);
			assertTrue(listaPrueba.get(2) == 3);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}

		// casos no validos

		listaPrueba = new ListaOrdenada<Integer>();
		
		// indice negativo
		listaPrueba.add(1);
		listaPrueba.add(2);
		listaPrueba.add(3);
		listaPrueba.add(4);
		try {
			listaPrueba.remove(-7);
			fail("Deberia haberse lanzado excepcion");
		} 
		catch (IndexOutOfBoundsException e) {
			// funcionamiento correcto
		}
		
		// indice negativo
		try {
			listaPrueba.remove(-1);
			fail("Deberia haberse lanzado excepcion");
		} 
		catch (IndexOutOfBoundsException e) {
			// funcionamiento correcto
		}
		
		// indice mayor que size-1
		try {
			listaPrueba.remove(listaPrueba.size());
			fail("Deberia haberse lanzado excepcion");
		} 
		catch (IndexOutOfBoundsException e) {
			// funcionamiento correcto
		}
		
		// indice mayor que size-1
		try {
			listaPrueba.remove(listaPrueba.size() + 2);
			fail("Deberia haberse lanzado excepcion");
		} 
		catch (IndexOutOfBoundsException e) {
			// funcionamiento correcto
		}
		
		// lista vacia
		listaPrueba = new ListaOrdenada<Integer>();
		try {
			listaPrueba.remove(0);
			fail("Deberia haberse lanzado excepcion");
		} 
		catch (IndexOutOfBoundsException e) {
			// funcionamiento correcto
		}
		
	}

	@Test
	public void testSize() {

		ListaOrdenada<Integer> listaPrueba = new ListaOrdenada<Integer>();

		// casos validos

		// tamanho de lista vacia
		try {
			assertTrue(listaPrueba.size() == 0);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}

		// tamanho de lista con un elemento
		listaPrueba.add(1);
		try {
			assertTrue(listaPrueba.size() == 1);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}

		// tamanho de lista con varios elementos
		listaPrueba.add(3);
		listaPrueba.add(5);
		listaPrueba.add(7);
		try {
			assertTrue(listaPrueba.size() == 4);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}
	}

	@Test
	public void testClear() {

		ListaOrdenada<Integer> listaPrueba = new ListaOrdenada<Integer>();

		// casos validos

		// vaciar lista vacia
		try {
			listaPrueba.clear();
			assertTrue(listaPrueba.size() == 0);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}

		// vaciar lista con un elemento
		listaPrueba.add(1);
		try {
			listaPrueba.clear();
			assertTrue(listaPrueba.size() == 0);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}

		// vaciar lista con varios elementos
		listaPrueba.add(1);
		listaPrueba.add(2);
		listaPrueba.add(3);
		listaPrueba.add(4);
		try {
			listaPrueba.clear();
			assertTrue(listaPrueba.size() == 0);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}

	}

}
