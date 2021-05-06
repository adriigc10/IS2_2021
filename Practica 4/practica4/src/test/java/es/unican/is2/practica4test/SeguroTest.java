package es.unican.is2.practica4test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import es.unican.is2.practica4.Cliente;
import es.unican.is2.practica4.Cobertura;
import es.unican.is2.practica4.DatoIncorrectoException;
import es.unican.is2.practica4.Seguro;

public class SeguroTest {

	private Cliente cliente = new Cliente("Borja", "0001", true);
	private Seguro seguro;

	@Before
	public void setUp() throws Exception {
		seguro = new Seguro(20, cliente, Cobertura.TERCEROS);
	}


	@Test
	public void constructorTest() {

		// casos validos

		try {
			seguro = new Seguro(1, cliente, Cobertura.TERCEROS);
			assertTrue(seguro.potencia() == 1);
			assertTrue(seguro.tomadorSeguro() == cliente);
			assertTrue(seguro.cobertura() == Cobertura.TERCEROS);
		} catch (Exception e) {
			fail("No se deberia haber lanzado excepcion");
		}


		try {
			seguro = new Seguro(17, cliente, Cobertura.TODORIESGO);
			assertTrue(seguro.potencia() == 17);
			assertTrue(seguro.tomadorSeguro() == cliente);
			assertTrue(seguro.cobertura() == Cobertura.TODORIESGO);
		} catch (Exception e) {
			fail("No se deberia haber lanzado la excepcion");
		}


		try {
			seguro = new Seguro(1, cliente, Cobertura.TERCEROSLUNAS);
			assertTrue(seguro.potencia() == 1);
			assertTrue(seguro.tomadorSeguro() == cliente);
			assertTrue(seguro.cobertura() == Cobertura.TERCEROSLUNAS);
		} catch (DatoIncorrectoException e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// casos no validos

		// potencia 0

		try {
			seguro = new Seguro(0, cliente, Cobertura.TERCEROS);
			fail("Deberia lanzarse excepcion por potencia <= 0");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}

		// potencia negativa
		try {
			seguro = new Seguro(-5, cliente, Cobertura.TODORIESGO);
			fail("Deberia lanzarse excepcion por potencia <= 0");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}

		// cobertura nula
		try {
			seguro = new Seguro(1, cliente, null);
			fail("Deberia lanzarse excepcion por cobertura nula");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}

		// cliente nulo
		try {
			seguro = new Seguro(1, null, Cobertura.TERCEROSLUNAS);
			fail("Deberia lanzarse excepcion por cliente nulo");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}
	}

	@Test
	public void precioTest() {

		// casos validos

		// uno
		try {
			seguro.setFechaUltimoSiniestro(LocalDate.now());
			seguro.setPotencia(1);
			seguro.setCobertura(Cobertura.TERCEROS);
			cliente.setMinusvalido();
			assertTrue(seguro.precio() == 450.0);
		} catch (Exception e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// dos
		try {
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(50));
			seguro.setPotencia(65);
			seguro.setCobertura(Cobertura.TODORIESGO);
			cliente.setNoMinusvalido();
			assertTrue(seguro.precio() == 1200.0);
		} catch (Exception e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// tres
		try {
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(1));
			seguro.setPotencia(89);
			seguro.setCobertura(Cobertura.TODORIESGO);
			cliente.setMinusvalido();
			assertTrue(seguro.precio() == 900);
		} catch (Exception e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// cuatro
		try {
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(366));
			seguro.setPotencia(90);
			seguro.setCobertura(Cobertura.TERCEROSLUNAS);
			assertTrue(seguro.precio() == 510.0);
		} catch (Exception e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// cinco
		try {
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(500));
			seguro.setPotencia(105);
			seguro.setCobertura(Cobertura.TERCEROS);
			assertTrue(seguro.precio() == 352.5);
		} catch (Exception e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// seis
		try {
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(1094));
			seguro.setPotencia(110);
			seguro.setCobertura(Cobertura.TERCEROS);
			cliente.setNoMinusvalido();
			assertTrue(seguro.precio() == 470.0);
		} catch (Exception e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// siete
		try {
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(1095));
			seguro.setPotencia(111);
			seguro.setCobertura(Cobertura.TODORIESGO);
			cliente.setMinusvalido();
			assertTrue(seguro.precio() == 900.0);
		} catch (Exception e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// ocho
		try {
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(2000));
			seguro.setPotencia(725);
			seguro.setCobertura(Cobertura.TERCEROS);
			cliente.setNoMinusvalido();
			assertTrue(seguro.precio() == 480.0);
		} catch (Exception e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// nueve
		try {
			seguro.setFechaUltimoSiniestro(null);
			seguro.setPotencia(725);
			seguro.setCobertura(Cobertura.TODORIESGO);
			assertTrue(seguro.precio() == 1200.0);
		} catch (Exception e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// casos no validos
		
		//TODO: hace falta especificar todos los parametros del caso de prueba no valido o solo el que va a dar error??

		// fecha posterior a hoy
		try {
			seguro.setPotencia(1);
			seguro.setCobertura(Cobertura.TERCEROS);
			cliente.setMinusvalido();
			seguro.setFechaUltimoSiniestro(LocalDate.now().plusDays(1));
			seguro.precio();
			fail("Deberia lanzarse excepcion por fecha posterior a hoy");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}

		// fecha posterior a hoy
		try {
			seguro.setPotencia(50);
			seguro.setCobertura(Cobertura.TERCEROSLUNAS);
			cliente.setNoMinusvalido();
			seguro.setFechaUltimoSiniestro(LocalDate.now().plusDays(50));
			seguro.precio();
			fail("Deberia lanzarse excepcion por fecha posterior a hoy");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}

		// potencia
		try {
			cliente.setNoMinusvalido();
			seguro.setCobertura(Cobertura.TERCEROSLUNAS);
			seguro.setFechaUltimoSiniestro(LocalDate.now());
			seguro.setPotencia(0);
			seguro.precio();
			fail("Deberia lanzarse excepcion por potencia <= 0");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}

		// potencia
		try {
			cliente.setMinusvalido();
			seguro.setCobertura(Cobertura.TERCEROSLUNAS);
			seguro.setFechaUltimoSiniestro(LocalDate.now());
			seguro.setPotencia(-5);
			seguro.precio();
			fail("Deberia lanzarse excepcion por potencia <= 0");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}


		// cobertura
		try {
			seguro.setPotencia(90);
			cliente.setMinusvalido();
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(1096));
			seguro.setCobertura(null);
			seguro.precio();
			fail("No se ha lanzado la excepcion por cobertura nula");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}

		//TODO: que hacemos con el caso no valido de la minusvalia??
		//TODO: caso no valido con cliente nulo??
	}

}
