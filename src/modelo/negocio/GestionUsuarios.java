package modelo.negocio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import modelo.dominio.usuarios.Usuario;
import GUI.observables.Observable;
import GUI.observables.UsuariosObserver;
import dao.DAO;
import ficheros.CargarDatos;
import ficheros.GuardarDatos;
import ficheros.excepciones.ExceptionLeerUsuarios;

/**
 * Clase encargada de gestionar los usuarios
 * @author David Bolanios
 */
public class GestionUsuarios extends Observable<UsuariosObserver>{
	
	private DAO<Usuario> daoUsuarios;

	/**
	 * Contructora por defecto
	 */
	public GestionUsuarios(){
		daoUsuarios = new DAO<Usuario>();
	}
	
	/**
	 * Añade un usuario al dao de usuarios. Si se ha insertado, se notifica con mensaje de exito al 
	 * usuario. En caso contrario, error porque ya existe el usuario y se notifica de error.
	 * @param Usuario el usuario a insertar
	 */
	public void nuevoUsuario(Usuario usuario) {
		if(!daoUsuarios.contains(new Usuario(usuario.getDni()))){
			if(daoUsuarios.add(usuario))
				avisarObservadores("Exito", "El usuario con ID "+ usuario.getDni() + " se ha creado correctamente.");
		}else
			errorProducido("Error", "Ya existe un usuario con ID "+ usuario.getDni() + ".");
	}
	
	/**
	 * Elimina un usuario del dao de usuarios. Si se ha eliminado, se notifica con mensaje de exito al 
	 * usuario. En caso contrario, error porque no existe el usuario, por lo que se notifica de error.
	 * @param DNI del usuario a eliminar
	 */
	public void bajaUsuario(String DNI) {
		if(daoUsuarios.eliminarElemento(new Usuario(DNI)))
			avisarObservadores("Exito", "El usuario con ID "+ DNI + "se ha eliminado correctamente.");
		
		else
			errorProducido("Error", "El usuario con ID "+ DNI + " no se ha encontrado.");
	}
	
	/**
	 * Busca un usuario a partir del DNI en el DAO de usuarios 
	 * @param DNI del usuario
	 * @return el usuario. Null si no se encuentra
	 */
	public Usuario buscarUsuario(String DNI) {
		return (daoUsuarios.cogerElemento(new Usuario(DNI)));
	}
	
	/**
	 * Modifica un usuario del DAO de usuarios
	 * @param usuario a modificar
	 */
	public void modificarUsuario(Usuario usuario){
		if (daoUsuarios.contains(new Usuario(usuario.getDni()))) {
			daoUsuarios.eliminarElemento(usuario);
			daoUsuarios.add(usuario);
			avisarObservadores("Exito", "El usuario con ID " + usuario.getDni() + " se ha modificado correctamente.");
		} else
			errorProducido("Error",
					"Ya existe un usuario con ID " + usuario.getDni());
	}

	/**
	 * Método encargado de comprobar que algun usuario existe en el DAO de usuario y cumple los requisitos:
	 * que el usuario que se pasa tenga el mismo DNI y contraseña que alguna del DAO.
	 * Si se encuentra, se avisa a los observadores para que inicie la ventana adecuada, si no,
	 * se envia un mensaje de error.
	 * @param usuario a loguear
	 */
	public void login(Usuario usuario) {
		Usuario user = daoUsuarios.cogerElemento(usuario);
		if(user != null && usuario.equals(user)){
			iniciarVentana(user.getDni(), user.getRol());
		}
		else{
			errorProducido("Error de login", "El usuario no existe o la contraseña es incorrecta.");
		}
	}

	/**
	 * Metodo que avisa a los observadores con la vista de usuario que tiene que cargar
	 * @param usuario el DNI del usuario
	 * @param rol el rol del usuario
	 */
	private void iniciarVentana(String usuario, String rol) {
		Iterator<UsuariosObserver> it = super.iterator();
		if(it.hasNext())
			it.next().abrirVentanaUsuario(usuario, rol);
	}

	/**
	 * Metodo que avisa a los observadores con un mensaje de informacion
	 * @param titulo El titulo del mensaje
	 * @param mensaje el mensaje
	 */
	private void avisarObservadores(String type, String message) {
		Iterator<UsuariosObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().mensajeInfo(type, message);
		}
	}
	
	/**
	 * Metodo que avisa a los observadores de un error
	 * @param error El titulo del error
	 * @param mensaje el mensaje de error
	 */
	public void errorProducido(String error, String mensaje) {
		Iterator<UsuariosObserver> it = super.iterator();
		while(it.hasNext()){
			it.next().showError(error, mensaje);;
		}
	}
	
	/**
	 * Método encargado de ejecutar la lectura de los usuarios
	 * @throws IOException si se produce algun error
	 * @throws ExceptionLeerUsuarios si hay incongruencias en los usuarios
	 */
	public void leerUsuarios() throws IOException, ExceptionLeerUsuarios {
		FileInputStream usuarios = null;
		
		try {
			usuarios = new FileInputStream("AutosoftDatos/Usuarios.txt");
			daoUsuarios = CargarDatos.leerUsuarios(usuarios);
		} 
		catch (FileNotFoundException e1) {
			System.err.println("Fichero Usuarios.txt no encontrado. Se creará un usuario por defecto...");
			daoUsuarios.add(new Usuario("1234", "11111111A", "administrador"));
		}
		catch (IOException | ExceptionLeerUsuarios e) {
			throw new ExceptionLeerUsuarios("Error en la lectura de los usuarios. Revise el formato de los datos.");
		}
		finally{
			if(usuarios != null)
				usuarios.close();
		}
	}

	/**
	 * Metodo que llama al metodo escribirUsuarios de la clase GuardarDatos, para guardar en el 
	 * fichero el condenedor de los usuarios DAO&ltUsuario&gt
	 * @throws IOException
	 */
	public void guardarUsuarios() throws IOException/*, ExceptionEscribirAlumnos*/{
		FileOutputStream usuarios = null;
		
		try {
			usuarios = new FileOutputStream("AutosoftDatos/Usuarios.txt");
			GuardarDatos.escribirEnFichero("EmpiezanUsuarios", "FinUsuarios", usuarios,daoUsuarios);
		} 
		catch (FileNotFoundException e1) {
			System.err.println("Fichero Usuarios.txt no encontrado. Se crea el fichero Usuarios.txt.");
		}
		finally{
			if(usuarios != null)
				usuarios.close();
		}
	}
	
	/**
	 * Registra un observador de esta clase.
	 * @param observer El observador que se quiere registrar.
	 */
	public void addUsuariosObserver(UsuariosObserver observer) {
		this.addObserver(observer);
	}

	/**
	 * Elimina un observador de esta clase.
	 * @param observer El observador que se quiere eliminar.
	 */
	public void removeUsuariosObserver(UsuariosObserver observer) {
		this.removeObserver(observer);
	}

	/**
	 * Elimina todos los observadores de esta clase
	 */
	public void removeAllObservers() {
		super.removeAllObservers();
	}
}
