/**
 * 
 */
package org.iesalandalus.programacion.reservasaulas.mvc.modelo;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.mvc.modelo.dominio.Reserva;

/**
 * @author vivas
 *
 */
public interface IModelo {
	
	public void comenzar();

	public void terminar();

	public List<Aula> getAulas();

	public int getNumAulas();

	public List<String> representarAulas();

	public Aula buscar(Aula aula);

	public void insertar(Aula aula) throws OperationNotSupportedException;

	public void borrar(Aula aula) throws OperationNotSupportedException;

	public List<Profesor> getProfesores();

	public int getNumProfesores();

	public	List<String> representarProfesores();

	public	Profesor buscar(Profesor profesor);

	public	void insertar(Profesor profesor) throws OperationNotSupportedException;

	public	void borrar(Profesor profesor) throws OperationNotSupportedException;

	public	List<Reserva> getReservas();

	public	int getNumReservas();

	public	List<String> representarReservas();

	public	Reserva buscar(Reserva reserva);

	public	void realizarReserva(Reserva reserva) throws OperationNotSupportedException;

	public	void anularReserva(Reserva reserva) throws OperationNotSupportedException;

	public	List<Reserva> getReservasAulas(Aula aula);

	public	List<Reserva> getReservasProfesor(Profesor profesor);

	public	List<Reserva> getReservasPermanencia(Permanencia permanencia);

	public	boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) throws OperationNotSupportedException;

}
