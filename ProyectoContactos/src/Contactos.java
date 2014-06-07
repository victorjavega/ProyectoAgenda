public class Contactos {
	//Falta por crear
	//Atributos para guardar
	//Tienen que ser los atributos de la pantalla
	private String nombre;
	private int numero;
	private String apellidos;
	private String direccion;
	private int iD;
	public Contactos() {
		nombre="";
		numero=0;
		apellidos="";
		direccion="";
		iD=0;
	}
	public Contactos(String Nombre, int Numero, String Apellidos, String Direccion, int ID) {
		nombre=nombre;
		numero=numero;
		apellidos=apellidos;
		direccion=direccion;
		iD=ID;
	}
	//Falta por crear
	//Los métodos de guardado y recuperación
	public void setNombre(String CojeNombre) {
		nombre=CojeNombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNumero(int cogeNumero) {
		numero=cogeNumero;
	}
	public int getNumero() {
		return numero;
	}
	public void setApellidos(String cogeApellidos) {
		apellidos=cogeApellidos;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setDireccion(String cogeDireccion) {
		direccion=cogeDireccion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setId(int id) {
		iD=id;
	}
	public int getId() {
		return iD;
	}
	//Falta por crear
	//El método toString para que aparezca en el comboBox
	public String toString(){
		return nombre+" "+ "("+iD+")";
	}

}