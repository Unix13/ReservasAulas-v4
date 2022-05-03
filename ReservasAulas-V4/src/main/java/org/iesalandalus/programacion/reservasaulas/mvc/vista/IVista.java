/**
 * 
 */
package org.iesalandalus.programacion.reservasaulas.mvc.vista;

import org.iesalandalus.programacion.reservasaulas.mvc.controlador.IControlador;

/**
 * @author vivas
 *
 */
public interface IVista {

	public void setControlador(IControlador controlador);

	public void comenzar();

	public void salir();

	public void insertarAula();

	public void borrarAula();

	public void buscarAula();

	public void listarAulas();

	public void insertarProfesor();

	public void borrarProfesor();

	public void buscarProfesor();

	public void listarProfesores();

	public void realizarReserva();

	public void anularReserva();

	public void listarReservas();

	public void listarReservasAula();

	public void listarReservasProfesor();

	public void consultarDisponibilidad();

}
