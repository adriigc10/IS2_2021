package es.unican.is2.practica4test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import es.unican.is2.containers.ListaOrdenada;




//import es.unican.is2.containers.ListaOrdenada;

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
	public void testGetAdd() {
		
		ListaOrdenada<Integer> listaPrueba = new ListaOrdenada<Integer>();
		
		// caso no valido
		
		try {
			listaPrueba.get(0);
			fail("Deberia haberse lanzado excepcion");
		} catch (Exception e) {
			// funcionamiento correcto
		}
		
		// casos validos
		
		try {
			listaPrueba.add(1);
			assertTrue(listaPrueba.get(0) == 1);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}
		
		try {
			listaPrueba.add(2);
			assertTrue(listaPrueba.size() == 2);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}
		
		try {
			listaPrueba.add(3);
			listaPrueba.add(4);
			assertTrue(listaPrueba.get(2) == 3);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}
		
	}
	
	
	@Test
	public void testRemove() {
		
		ListaOrdenada<Integer> listaPrueba = new ListaOrdenada<Integer>();
		
		// casos no validos
		
		try {
			listaPrueba.remove(0);
			fail("Deberia haberse lanzado excepcion");
		} catch (Exception e) {
			// funcionamiento correcto
		}
		
		// casos validos
		
		try {
			listaPrueba.add(1);
			listaPrueba.remove(0);
			assertTrue(listaPrueba.size() == 0);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}
		
		try {
			listaPrueba.add(1);
			listaPrueba.add(2);
			listaPrueba.add(3);
			listaPrueba.add(4);
			listaPrueba.remove(2);
			assertTrue(listaPrueba.size() == 3);
			assertTrue(listaPrueba.get(2) == 4);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}
	}
	
	@Test
	public void testSize() {
		
		ListaOrdenada<Integer> listaPrueba = new ListaOrdenada<Integer>();
		
		// casos validos
		
		try {
			assertTrue(listaPrueba.size() == 0);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}
		
		try {
			listaPrueba.add(1);
			assertTrue(listaPrueba.size() == 1);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}
		
		try {
			listaPrueba.add(2);
			listaPrueba.add(3);
			listaPrueba.add(4);
			assertTrue(listaPrueba.size() == 4);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}
	}
	
	@Test
	public void testClear() {
		
		ListaOrdenada<Integer> listaPrueba = new ListaOrdenada<Integer>();
		
		// casos validos
		
		try {
			listaPrueba.clear();
			assertTrue(listaPrueba.size() == 0);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}
		
		try {
			listaPrueba.add(1);
			listaPrueba.clear();
			assertTrue(listaPrueba.size() == 0);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}
		
		try {
			listaPrueba.add(1);
			listaPrueba.add(2);
			listaPrueba.add(3);
			listaPrueba.add(4);
			listaPrueba.clear();
			assertTrue(listaPrueba.size() == 0);
		} catch (Exception e) {
			fail("No deberia haberse lanzado la excepcion");
		}
		
	}

}
