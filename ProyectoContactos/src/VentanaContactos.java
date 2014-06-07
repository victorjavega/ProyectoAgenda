import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class VentanaContactos extends JFrame {

	//Elementos de la pantalla
	private JPanel contentPane;
	private JTextField nombre;
	private JTextField numero;
	private Contactos contactos;
	private BaseDatos datos;

	//ComboBox para guardar delincuentes
	private JComboBox<Contactos> con;
	private JTextField textID;
	private JTextField direccion;
	private JTextField apellidos;

	//Lanza la aplicación
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaContactos frame = new VentanaContactos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the frame.
	 */
	public VentanaContactos() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		con = new JComboBox<Contactos>();
		con.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Cojemos del comboBox la informacion y la pone en su sitio
				contactos=con.getItemAt(con.getSelectedIndex());
				if(contactos!=null)
				{
					
				nombre.setText(contactos.getNombre());
				numero.setText(String.valueOf(contactos.getNumero()));
				apellidos.setText(String.valueOf(contactos.getApellidos()));
				direccion.setText(contactos.getDireccion());
				textID.setText(String.valueOf(contactos.getId()));
				
				}
			}
		});
		con.setBounds(174, 60, 197, 20);
		contentPane.add(con);

		JLabel lblContacto = new JLabel("Contactos");
		lblContacto.setBounds(184, 11, 96, 14);
		contentPane.add(lblContacto);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(39, 35, 96, 14);
		contentPane.add(lblNombre);

		nombre = new JTextField();
		nombre.setBounds(10, 60, 107, 20);
		contentPane.add(nombre);
		nombre.setColumns(10);

		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumero.setBounds(-11, 91, 88, 14);
		contentPane.add(lblNumero);

		//Boton Guardar
		JButton botonGuardar = new JButton("Guardar");
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//Aquí realizaremos los siguientes pasos
				//1.- Comprobaremos que todos los campos están completados
				if((nombre.getText()).equals("")){
					JOptionPane.showMessageDialog(null, "Has introducido mal el Nombre");
				}
				else if((numero.getText().equals(""))){
					JOptionPane.showMessageDialog(null, "Has introducido mal el numero");
				}
				else if((apellidos.getText()).equals("")){
					JOptionPane.showMessageDialog(null, "Has introducido mal los apellidos");
				}
				else if((direccion.getText()).equals("")){
					JOptionPane.showMessageDialog(null, "Has introducido mal la direccion");
				} 
				else{
				//2.- Crearemos un nuevo objeto delincuente
				Contactos c=new Contactos();
				int ID=0;
				ID=datos.insertarDelincuentes((nombre.getText()), Integer.parseInt(numero.getText()), Integer.parseInt(direccion.getText()));
				c.setNombre(nombre.getText());
				c.setNumero(Integer.parseInt(numero.getText()));
				c.setApellidos(apellidos.getText());
				c.setDireccion(String.valueOf(direccion.getText()));
				c.setId(ID);


				//3.- Lo almacenaremos en el ComboBox
				con.addItem(c);
				
				}
			}
		});
		botonGuardar.setBounds(28, 274, 89, 23);
		contentPane.add(botonGuardar);

		numero = new JTextField();
		numero.setBounds(10, 116, 107, 20);
		contentPane.add(numero);
		numero.setColumns(10);
		//Boton Modificar
		JButton botonModificar = new JButton("Modificar");
		botonModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				datos.modificaContactos(Integer.parseInt(textID.getText()),nombre.getText(), Integer.parseInt(numero.getText()), Integer.parseInt(direccion.getText()));
				contactos.setNombre(nombre.getText());
				contactos.setNumero(Integer.parseInt(numero.getText()));
				contactos.setApellidos(apellidos.getText());
				contactos.setDireccion(String.valueOf(direccion.getText()));
				
				

			}
		});
		botonModificar.setBounds(335, 274, 89, 23);
		contentPane.add(botonModificar);

		JButton botonBorrar = new JButton("Borrar");
		botonBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Eliminamos el item seleccionado
				con.removeItem(contactos);
				datos.remove(Integer.parseInt(textID.getText()));
			}
		});
		botonBorrar.setBounds(232, 274, 89, 23);
		contentPane.add(botonBorrar);

		datos=new BaseDatos(con);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(401, 36, 46, 14);
		contentPane.add(lblId);

		textID = new JTextField();
		textID.setEditable(false);
		textID.setBounds(394, 60, 30, 20);
		contentPane.add(textID);
		textID.setColumns(10);
		
		direccion = new JTextField();
		direccion.setColumns(10);
		direccion.setBounds(10, 167, 107, 20);
		contentPane.add(direccion);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDireccion.setBounds(-29, 147, 117, 14);
		contentPane.add(lblDireccion);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellidos.setBounds(0, 198, 88, 14);
		contentPane.add(lblApellidos);
		
		apellidos = new JTextField();
		apellidos.setColumns(10);
		apellidos.setBounds(10, 218, 107, 20);
		contentPane.add(apellidos);
		datos.leerContactos();
	}
}
