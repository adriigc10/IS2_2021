package practica4;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class SeguroTest {

	private Cliente cliente1 = new Cliente("Borja", "0001", true);
	private Cliente cliente2 = new Cliente("Adrian", "0002", false);
	
	
	@Test
	public void constructorTest() {
		Seguro seguro1 = new Seguro(1, cliente1, Cobertura.TERCEROS);
		assertTrue(seguro1 instanceof Seguro);
		
		Seguro seguro2 = new Seguro(17, cliente2, Cobertura.TODORIESGO);
		assertTrue(seguro2 instanceof Seguro);
		
		Seguro seguro3 = new Seguro(1, cliente2, Cobertura.TERCEROSLUNAS);
		assertTrue(seguro3 instanceof Seguro);
	}
	
	@Test
	public void precioTest() {
		Seguro seguro1 = new Seguro(1, cliente1, Cobertura.TERCEROS);
		seguro1.setFechaUltimoSiniestro(LocalDate.now());
		
		try {
			System.out.println(seguro1.precio());
			assertTrue(seguro1.precio() == 450);
		} catch (Exception e) {
			fail("Precio Obtenido: " + seguro1.precio() + " Precio esperado: 400\n");
		}
		
		
		Seguro seguro2 = new Seguro(89, cliente2, Cobertura.TODORIESGO);
		seguro2.setFechaUltimoSiniestro(LocalDate.now().minusDays(364));
		
		try {
			assertTrue(seguro2.precio() == 1200.00);
		} catch (Exception e) {
			fail("Precio Obtenido: " + seguro1.precio() + " Precio esperado: 1200\n");
		}
		
		
		Seguro seguro3 = new Seguro(90, cliente1, Cobertura.TERCEROSLUNAS);
		seguro3.setFechaUltimoSiniestro(LocalDate.now().minusYears(1));
		
		try {
			assertTrue(seguro3.precio() == 489.475);
		} catch (Exception e) {
			fail("Precio Obtenido: " + seguro1.precio() + " Precio esperado: 489.475\n");
		}
		
		
		Seguro seguro4 = new Seguro(110, cliente2, Cobertura.TERCEROS);
		seguro4.setFechaUltimoSiniestro(LocalDate.now().minusDays(1094));
		assertTrue(seguro4.precio() == 460.00);
		
		Seguro seguro5 = new Seguro(111, cliente1, Cobertura.TODORIESGO);
		seguro5.setFechaUltimoSiniestro(LocalDate.now().minusDays(1096));
		assertTrue(seguro5.precio() == 900.00);
	}

}
