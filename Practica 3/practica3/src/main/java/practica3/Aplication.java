package practica3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.JTextPane;

/**
 * 
 * @author Borja Cuevas y Adrian Garcia
 *
 */
public class Aplication {

	private JFrame frame;
	private ControladorAlarma despertador = new ControladorAlarma();
	private JTextField txtIdAlarma;
	private JSpinner spnHoraAlarma;
	private Alarma al;
	private JTextPane txtAlarmasActivas;
	private JTextPane txtAlarmasDesactivadas;
	private JTextField txtIdAlarmaInteractuar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplication window = new Aplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Aplication() {
		initialize();
		nuevaAlarma();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}

	private void nuevaAlarma() {

		// alarmas activadas 
		txtAlarmasActivas = new JTextPane();
		txtAlarmasActivas.setBounds(294, 49, 143, 85);
		frame.getContentPane().add(txtAlarmasActivas);
		txtAlarmasActivas.setEditable(false);

		// alarmas desactivadas
		txtAlarmasDesactivadas = new JTextPane();
		txtAlarmasDesactivadas.setBounds(294, 172, 143, 85);
		frame.getContentPane().add(txtAlarmasDesactivadas);
		txtAlarmasDesactivadas.setEditable(false);

		// id alarma
		txtIdAlarma = new JTextField();
		txtIdAlarma.setBounds(124, 49, 101, 20);
		frame.getContentPane().add(txtIdAlarma);
		txtIdAlarma.setColumns(10);

		spnHoraAlarma = new JSpinner();
		spnHoraAlarma.setModel(new SpinnerDateModel(new Date(1617832800000L), null, null, Calendar.DAY_OF_YEAR));
		spnHoraAlarma.setBounds(124, 80, 101, 20);
		frame.getContentPane().add(spnHoraAlarma);


		// etiquetas

		// id alarma
		JLabel lblIdAlarma = new JLabel("Id Alarma nueva");
		lblIdAlarma.setBounds(10, 52, 104, 14);
		frame.getContentPane().add(lblIdAlarma);

		// hora alarma
		JLabel lblHoraAlarma = new JLabel("Hora Alarma");
		lblHoraAlarma.setBounds(10, 83, 109, 14);
		frame.getContentPane().add(lblHoraAlarma);




		// nueva alarma
		JButton btnNuevaAlarma = new JButton("Nueva alarma");
		btnNuevaAlarma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Date hora = ((Date) spnHoraAlarma.getValue());
				al = new Alarma(txtIdAlarma.getText(), hora);
				despertador.nuevaAlarma(al.id(), al.hora());
				txtIdAlarma.setText(null);

				mostrarAlarmasActivas();
			}
		});
		btnNuevaAlarma.setBounds(10, 111, 215, 39);
		frame.getContentPane().add(btnNuevaAlarma);

		// apagar alarma
		JButton btnApagar = new JButton("Apagar");
		btnApagar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				despertador.apagar();
				mostrarAlarmasDesactivadas();
				mostrarAlarmasActivas();
			}
		});
		btnApagar.setBounds(10, 172, 215, 85);
		frame.getContentPane().add(btnApagar);

		JLabel lblAlarmasActivas = new JLabel("Alarmas activadas");
		lblAlarmasActivas.setBounds(294, 24, 116, 14);
		frame.getContentPane().add(lblAlarmasActivas);

		JLabel lblAlarmasDesactivadas = new JLabel("Alarmas desactivadas");
		lblAlarmasDesactivadas.setBounds(294, 147, 161, 14);
		frame.getContentPane().add(lblAlarmasDesactivadas);
		
		// alarma para interactuar
		txtIdAlarmaInteractuar = new JTextField();
		txtIdAlarmaInteractuar.setBounds(294, 278, 143, 20);
		frame.getContentPane().add(txtIdAlarmaInteractuar);
		txtIdAlarmaInteractuar.setColumns(10);
		
		// alarma on
		JButton btnAlarmOn = new JButton("ON");
		btnAlarmOn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				despertador.alarmaOn(txtIdAlarmaInteractuar.getText());
				
				mostrarAlarmasActivas();		
				mostrarAlarmasDesactivadas();
				
				txtIdAlarmaInteractuar.setText(null);
				System.out.println("Alarmas activas: " + despertador.alarmasActivas().size());
				System.out.println("Alarmas desactivadas: " + despertador.alarmasDesactivadas().size());
			}
		});
		btnAlarmOn.setBounds(294, 315, 61, 20);
		frame.getContentPane().add(btnAlarmOn);
		
		// alarma off
		JButton btnAlarmaOff = new JButton("OFF");
		btnAlarmaOff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				despertador.alarmaOff(txtIdAlarmaInteractuar.getText());
				
				mostrarAlarmasActivas();
				mostrarAlarmasDesactivadas();
				
				txtIdAlarmaInteractuar.setText(null);
				System.out.println("Alarmas activas: " + despertador.alarmasActivas().size());
				System.out.println("Alarmas desactivadas: " + despertador.alarmasDesactivadas().size());
			}
		});
		btnAlarmaOff.setBounds(376, 315, 61, 20);
		frame.getContentPane().add(btnAlarmaOff);
		
		JLabel lblNewLabel = new JLabel("Id alarma on/off");
		lblNewLabel.setBounds(190, 281, 94, 14);
		frame.getContentPane().add(lblNewLabel);

	}

	/**
	 * Muestra las alarmas activas del controlador.
	 */
	private void mostrarAlarmasActivas() {
		
		String alAct = "";

		for (Alarma a: despertador.alarmasActivas()) {
			alAct = alAct + a.id() + "\n";
		}
		
		txtAlarmasActivas.setText(alAct);


	}

	/**
	 * Muestra las alarmas desactivadas del controlador. 
	 */
	private void mostrarAlarmasDesactivadas() {
		
		String alDes = "";

		for (Alarma a: despertador.alarmasDesactivadas().values()) {
			alDes = alDes + a.id() + "\n";
		}
		
		txtAlarmasDesactivadas.setText(alDes);

	}
}