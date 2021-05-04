package es.unican.is2.practica4test;


import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.unican.is2.practica4.SegurosGUI;


public class SegurosGUITest {

	private FrameFixture demo;

	@Before
	public void setUp() {
		SegurosGUI gui = new SegurosGUI();
		demo = new FrameFixture(gui);
		gui.setVisible(true);	
	}

	@After
	public void tearDown() {
		demo.cleanUp();
	}

	@Test
	public void test() {

		// Comprobamos que la interfaz tiene el aspecto deseado
		demo.button("btnCalcular").requireText("CALCULAR");
		demo.radioButton("btnMinusvalia").requireText("Minusvalía");

		// PRIMER CASO

		// Escribimos una fecha
		demo.textBox("txtFechaUltimoSiniestro").deleteText();
		demo.textBox("txtFechaUltimoSiniestro").enterText("03-05-2017");
		// seleccionamos cobertura
		demo.comboBox("comboCobertura").selectItem("TODORIESGO");
		// introducimos potencia
		demo.textBox("txtPotencia").deleteText();
		demo.textBox("txtPotencia").enterText("111");
		// le metemos minusvalia
		demo.radioButton("btnMinusvalia").check();

		// Pulsamos el botón
		demo.button("btnCalcular").click();

		// Comprobamos la salida
		demo.textBox("txtPrecio").requireText("900.0");

		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// SEGUNDO CASO

		// Escribimos una fecha
		demo.textBox("txtFechaUltimoSiniestro").deleteText();
		demo.textBox("txtFechaUltimoSiniestro").enterText("03-05-2020");
		// seleccionamos cobertura
		demo.comboBox("comboCobertura").selectItem("TERCEROSLUNAS");
		// introducimos potencia
		demo.textBox("txtPotencia").deleteText();
		demo.textBox("txtPotencia").enterText("90");
		// le metemos minusvalia
		demo.radioButton("btnMinusvalia").check();

		// Pulsamos el botón
		demo.button("btnCalcular").click();

		// Comprobamos la salida
		demo.textBox("txtPrecio").requireText("510.0");

		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// TERCER CASO

		// Escribimos una fecha
		demo.textBox("txtFechaUltimoSiniestro").deleteText();
		demo.textBox("txtFechaUltimoSiniestro").enterText("03-05-2019");
		// seleccionamos cobertura
		demo.comboBox("comboCobertura").selectItem("TERCEROS");
		// introducimos potencia
		demo.textBox("txtPotencia").deleteText();
		demo.textBox("txtPotencia").enterText("110");
		// le metemos minusvalia
		demo.radioButton("btnMinusvalia").uncheck();

		// Pulsamos el botón
		demo.button("btnCalcular").click();

		// Comprobamos la salida
		demo.textBox("txtPrecio").requireText("470.0");

		// Sleep para visualizar como se realiza el test
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
