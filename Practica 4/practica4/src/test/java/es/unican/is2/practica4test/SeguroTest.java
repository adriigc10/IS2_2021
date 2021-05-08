package es.unican.is2.practica4test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import es.unican.is2.practica4.Cliente;
import es.unican.is2.practica4.Cobertura;
import es.unican.is2.practica4.DatoIncorrectoException;
import es.unican.is2.practica4.Seguro;

/**
 * Clase de prueba de la clase Seguro
 * @author Borja Cuevas y Adrian Garcia
 *
 */
public class SeguroTest {

	private Cliente clienteNoMinusvalido = new Cliente("Borja", "1234567B", false);
	private Cliente clienteMinusvalido = new Cliente("Adrian", "7654321A", true);
	private Seguro seguro;

	@Before
	public void setUp() throws Exception {
		seguro = new Seguro(20, clienteMinusvalido, Cobertura.TERCEROS);
	}


	@Test
	public void constructorTest() {

		// casos validos

		try {
			seguro = new Seguro(1, clienteMinusvalido, Cobertura.TERCEROS);
			assertTrue(seguro.potencia() == 1);
			assertTrue(seguro.tomadorSeguro() == clienteMinusvalido);
			assertTrue(seguro.cobertura() == Cobertura.TERCEROS);
		} catch (DatoIncorrectoException e) {
			fail("No se deberia haber lanzado excepcion");
		}


		try {
			seguro = new Seguro(17, clienteNoMinusvalido, Cobertura.TODORIESGO);
			assertTrue(seguro.potencia() == 17);
			assertTrue(seguro.tomadorSeguro() == clienteNoMinusvalido);
			assertTrue(seguro.cobertura() == Cobertura.TODORIESGO);
		} catch (DatoIncorrectoException e) {
			fail("No se deberia haber lanzado la excepcion");
		}


		try {
			seguro = new Seguro(1, clienteNoMinusvalido, Cobertura.TERCEROSLUNAS);
			assertTrue(seguro.potencia() == 1);
			assertTrue(seguro.tomadorSeguro() == clienteNoMinusvalido);
			assertTrue(seguro.cobertura() == Cobertura.TERCEROSLUNAS);
		} catch (DatoIncorrectoException e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// casos no validos

		// potencia 0

		try {
			seguro = new Seguro(0, clienteNoMinusvalido, Cobertura.TERCEROS);
			fail("Deberia lanzarse excepcion por potencia <= 0");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}

		// potencia negativa
		try {
			seguro = new Seguro(-5, clienteNoMinusvalido, Cobertura.TODORIESGO);
			fail("Deberia lanzarse excepcion por potencia <= 0");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}

		// cobertura nula
		try {
			seguro = new Seguro(1, clienteNoMinusvalido, null);
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
			seguro = new Seguro(1, clienteMinusvalido, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now());
			assertTrue(seguro.precio() == 450.0);
		} catch (DatoIncorrectoException e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// dos
		try {
			seguro = new Seguro(65, clienteNoMinusvalido, Cobertura.TODORIESGO);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(50));
			assertTrue(seguro.precio() == 1200.0);
		} catch (DatoIncorrectoException e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// tres
		try {
			seguro = new Seguro(89, clienteMinusvalido, Cobertura.TODORIESGO);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusYears(1));
			assertTrue(seguro.precio() == 900);
		} catch (DatoIncorrectoException e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// cuatro
		try {
			seguro = new Seguro(90, clienteMinusvalido, Cobertura.TERCEROSLUNAS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(366));
			assertTrue(seguro.precio() == 510.0);
		} catch (DatoIncorrectoException e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// cinco
		try {
			seguro = new Seguro(105, clienteMinusvalido, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(500));
			assertTrue(seguro.precio() == 352.5);
		} catch (DatoIncorrectoException e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// seis
		try {
			seguro = new Seguro(110, clienteNoMinusvalido, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(1094));
			assertTrue(seguro.precio() == 470.0);
		} catch (DatoIncorrectoException e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// siete
		try {
			seguro = new Seguro(111, clienteMinusvalido, Cobertura.TODORIESGO);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(1095));
			assertTrue(seguro.precio() == 900.0);
		} catch (DatoIncorrectoException e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// ocho
		try {
			seguro = new Seguro(725, clienteNoMinusvalido, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(2000));
			assertTrue(seguro.precio() == 480.0);
		} catch (DatoIncorrectoException e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// nueve
		try {
			seguro = new Seguro(725, clienteNoMinusvalido, Cobertura.TODORIESGO);
			seguro.setFechaUltimoSiniestro(null);
			assertTrue(seguro.precio() == 1200.0);
		} catch (DatoIncorrectoException e) {
			fail("No se deberia haber lanzado la excepcion");
		}

		// casos no validos
		
		// fecha posterior a hoy
		try {
			seguro = new Seguro(1, clienteMinusvalido, Cobertura.TERCEROS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().plusDays(1));
			fail("Deberia lanzarse excepcion por fecha posterior a hoy");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}

		// fecha posterior a hoy
		try {
			seguro = new Seguro(50, clienteNoMinusvalido, Cobertura.TERCEROSLUNAS);
			seguro.setFechaUltimoSiniestro(LocalDate.now().plusDays(50));
			fail("Deberia lanzarse excepcion por fecha posterior a hoy");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}

		// potencia cero
		try {
			seguro = new Seguro(0, clienteNoMinusvalido, Cobertura.TERCEROSLUNAS);
			seguro.setFechaUltimoSiniestro(LocalDate.now());
			fail("Deberia lanzarse excepcion por potencia <= 0");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}

		// potencia negativa
		try {
			seguro = new Seguro(-5, clienteMinusvalido, Cobertura.TERCEROSLUNAS);
			seguro.setFechaUltimoSiniestro(LocalDate.now());
			fail("Deberia lanzarse excepcion por potencia <= 0");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}


		// cobertura nula
		try {
			seguro = new Seguro(90, clienteMinusvalido, null);
			seguro.setFechaUltimoSiniestro(LocalDate.now().minusDays(1096));
			fail("No se ha lanzado la excepcion por cobertura nula");
		} catch (DatoIncorrectoException e) {
			// funcionamiento correcto
		}
	}

}
