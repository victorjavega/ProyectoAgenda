import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;

public class BaseDatos {
	private VentanaContactos Ventana;
	Connection conexion = null; //maneja la conexión
	Statement instruccion = null;// instrucción de consulta
	ResultSet conjuntoResultados = null;// maneja los resultados
	private JComboBox<Contactos> listadoContactos;



	public BaseDatos(JComboBox listadoDelincuentes) {
		// TODO Auto-generated constructor stub
		crearconexion();
		this.listadoContactos=listadoDelincuentes;
	}

	public void crearconexion (){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			// establece la conexión a la base de datos
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/fichacontactos","root","tonphp");

		}catch( SQLException excepcionSql ){
			excepcionSql.printStackTrace();
		}// fin de catch
		catch( ClassNotFoundException noEncontroClase )
		{
			noEncontroClase.printStackTrace();
		}
		// fin de catch
	}

	public void leerContactos (){
		try{
		// crea objeto Statement para consultar la base de datos
					instruccion = (Statement) conexion.createStatement();
					// consulta la base de datos
					conjuntoResultados = instruccion.executeQuery("SELECT nombre,numero,apellidos,direccion,iD FROM contactos");
					//Mostrar por pantalla
					while (conjuntoResultados.next())
					{
					   System.out.println("id="+conjuntoResultados.getObject("ID")+
					      ", Nombre="+conjuntoResultados.getObject("nombre"));
					   Contactos c=new Contactos((String)conjuntoResultados.getObject("nombre"),
							   							(int)conjuntoResultados.getObject("numero"),
							   							(String)conjuntoResultados.getObject("apellidos"),
							   							(String)conjuntoResultados.getObject("direccion"),
							   							(int)conjuntoResultados.getObject("iD"));


					   listadoContactos.addItem(c);
					}
					conjuntoResultados.close();	

			}
		catch( SQLException excepcionSql ){
		excepcionSql.printStackTrace();}
		}

	public int insertarContactos (String nombre, int numero, String apellidos, String direccion){
		// crea objeto Statement para consultar la base de datos
		try {
			instruccion = (Statement) conexion.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// insercion en base de datos
		try {
			String sql="INSERT INTO `fichacontactos`.`contactos` (`nombre`, `numero`, `apellidos`, `direccion`) VALUES ("
					+ 													"'"+nombre+"', '"+numero+"', '"+apellidos+"', '"+direccion+"');";
			instruccion.executeUpdate(sql);
			//PARA GUARDAR EL ID
			sql = "SELECT * FROM contactos ORDER BY ID DESC LIMIT 1";
			conjuntoResultados = instruccion.executeQuery(sql);
			int ID=-1;
			//Mostrar por pantalla
			while (conjuntoResultados.next())
			{
				ID=(int)conjuntoResultados.getObject("ID");
			}
			conjuntoResultados.close();	
			return ID;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
			}
		}

		public void modificaContactos ( int ID, String nombre, int numero, String apellidos, String direccion){
			// crea objeto Statement para consultar la base de datos
			try {
				instruccion = (Statement) conexion.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// insercion en base de datos
			try {
				String sql="UPDATE  `fichacontactos`.`contactos` SET "
						+ "`nombre` ='"+nombre+"',"
						+"`numero` =  '"+numero+"',"
						+"`apellidos` =  '"+apellidos+"',"
						+"`direccion` =  '"+direccion+"'"+
						 " WHERE  `con`.`ID` ="+ID+"";
				instruccion.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
		public void remove (int ID){
			try {
				instruccion = (Statement) conexion.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// insercion en base de datos
			try {
				String sql="DELETE FROM `fichacontactos`.`contactos` WHERE  `con`.`ID` ="+ID+"";
				instruccion.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
}
