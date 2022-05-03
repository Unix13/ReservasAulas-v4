package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import java.util.Iterator;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

public class Vista implements IVista {

	private static final String ERROR = "ERROR: ";
	private static final String NOMBRE_VALIDO = "Nombre válido, el profesor está registrado en el sistema.";
	private static final String CORREO_VALIDO = "Correo válido, el correo está registrado en el sistema.";

	private IControlador controlador;

	public Vista() {

		Opcion.setVista(this);
	}

	@Override
	public void setControlador(IControlador controlador) {

		this.controlador = controlador;
	}

	@Override
	public void comenzar() {

		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOridnal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}

	@Override
	public void salir() {

		controlador.terminar();
	}

	@Override
	public void insertarAula() {

		Consola.mostrarCabecera("Insertar Aula");
		try {
			controlador.insertarAula(Consola.leerAula());
			System.out.println("Aula insertada correctamente.");

		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void borrarAula() {

		Consola.mostrarCabecera("Borrar Aula");
		try {
			controlador.borrarAula(Consola.leerAulaFicticia());
			System.out.println("Aula borrada correctamente.");

		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void buscarAula() {
		Consola.mostrarCabecera("Buscar Aula");
		Aula aula;
		try {
			aula = controlador.buscarAula(Consola.leerAulaFicticia());
			String mensaje = (aula != null) ? aula.toString() : "Aula no registrada en el sistema.";
			System.out.println(mensaje);

		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void listarAulas() {

		Consola.mostrarCabecera("Listado de Aulas");

		List<String> aulas = controlador.representarAulas();
		if (aulas.size() > 0) {
			for (Iterator<String> it = aulas.iterator(); it.hasNext();) {
				String aula = it.next();
				System.out.println(aula);
			}

		} else {
			System.out.println(ERROR + "No hay aulas que listar.");
		}

	}

	@Override
	public void insertarProfesor() {

		Consola.mostrarCabecera("Insertar Profesor");
		try {
			controlador.insertarProfesor(Consola.leerProfesor());
			System.out.println("Profesor insertado correctamente.");

		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void borrarProfesor() {

		Consola.mostrarCabecera("Borrar Profesor");
		try {
			controlador.borrarProfesor(Consola.leerProfesorFicticio());
			System.out.println("Profesor borrado correctamente.");

		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void buscarProfesor() {

		Consola.mostrarCabecera("Buscar Profesor");
		Profesor profesor;
		try {
			profesor = controlador.buscarProfesor(Consola.leerProfesorFicticio());
			String mensaje = (profesor != null) ? profesor.toString()
					: ERROR + "El profesor no está registrado en el sistema.";
			System.out.println(mensaje);

		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void listarProfesores() {

		Consola.mostrarCabecera("Listado de Profesores");

		List<String> profesores = controlador.representarProfesores();

		if (profesores.size() > 0) {
			for (Iterator<String> it = profesores.iterator(); it.hasNext();) {
				String profesor = it.next();
				System.out.println(profesor);
			}
		} else {
			System.out
					.println(ERROR + "No hay profesores que listar.");
		}
	}

	@Override
	public void realizarReserva() {

		try {
			Profesor profesor = Consola.leerProfesorFicticio();
			Profesor profesorRegistrado = controlador.buscarProfesor(profesor);

			if (profesorRegistrado != null) {

				Aula aula = Consola.leerAulaFicticia();
				Aula aulaRegistrada = controlador.buscarAula(aula);

				if (aulaRegistrada != null) {

					Permanencia permanencia = Consola.leerPermanencia();
					Reserva reserva = new Reserva(profesorRegistrado, aulaRegistrada, permanencia);

					controlador.realizarReserva(reserva);

					System.out.println("Reserva realizada correctamente.\n" + NOMBRE_VALIDO + "\n" + CORREO_VALIDO);
				} else {
					System.out.println(ERROR + "El aula " + aula.getNombre() + ", no está registrada en el sistema.");
				}
			} else {
				System.out.println(ERROR + "El correo " + profesor.getCorreo() + ", no está registrado en el sistema.");
			}

		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void anularReserva() {

		Consola.mostrarCabecera("Anular Reserva");

		try {

			controlador.anularReserva(Consola.leerReservaFicticia());
			System.out.println("Reserva anulada correctamente.");
		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void listarReservas() {

		Consola.mostrarCabecera("Listado de Reservas");

		List<String> reservas = controlador.representarReservas();

		if (reservas.size() > 0) {
			for (Iterator<String> it = reservas.iterator(); it.hasNext();) {
				String reserva = it.next();
				System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas que listar.");
		}
	}

	@Override
	public void listarReservasAula() {

		Consola.mostrarCabecera("Listado de Reservas por Aula");
		List<Reserva> reservas = controlador.getReservasAula(Consola.leerAulaFicticia());
		if (reservas.size() > 0) {
			for (Iterator<Reserva> it = reservas.iterator(); it.hasNext();) {
				Reserva reserva = it.next();

				System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas, para este aula.");
		}
	}

	@Override
	public void listarReservasProfesor() {

		Consola.mostrarCabecera("Listado de Reservas por Profesor");
		List<Reserva> reservas = controlador.getReservasProfesor(Consola.leerProfesorFicticio());
		if (reservas.size() > 0) {
			for (Iterator<Reserva> it = reservas.iterator(); it.hasNext();) {
				Reserva reserva = it.next();

				System.out.println(reserva);
			}
		} else {
			System.out.println("No hay reservas, para este profesor.");
		}
	}

	@Override
	public void consultarDisponibilidad() {
		
		try {
			Profesor profesor = Consola.leerProfesorFicticio();
			Profesor profesorRegistrado = controlador.buscarProfesor(profesor);

			if (profesorRegistrado != null) {

				Aula aula = Consola.leerAulaFicticia();
				Aula aulaRegistrada = controlador.buscarAula(aula);

				if (aulaRegistrada != null) {

					Permanencia permanencia = Consola.leerPermanencia();
					Reserva reserva = new Reserva(profesorRegistrado, aulaRegistrada, permanencia);

					controlador.realizarReserva(reserva);

					System.out.println("Reserva realizada correctamente.\n" + NOMBRE_VALIDO + "\n" + CORREO_VALIDO);
				} else {
					System.out.println(ERROR + "El aula " + aula.getNombre() + ", no está registrada en el sistema.");
				}
			} else {
				System.out.println(ERROR + "El correo " + profesor.getCorreo() + ", no está registrado en el sistema.");
			}

		} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}
}