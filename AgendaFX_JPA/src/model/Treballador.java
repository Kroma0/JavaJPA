package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Treballador implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer idTreballador;
	private Date entrada;
	private Date sortida;
	
	public Treballador() {
		super();
		this.idTreballador = 0;
		this.entrada = null;
		this.sortida = null;
	}
	
	public Treballador(Integer idTreballador, Date entrada, Date sortida) {
		super();
		this.idTreballador = idTreballador;
		this.entrada = entrada;
		this.sortida = sortida;
	}


	public Integer getIdTreballador() {
		return idTreballador;
	}

	public void setIdTreballador(Integer idTreballador) {
		this.idTreballador = idTreballador;
	}

	public Date getEntrada() {
		return entrada;
	}

	public void setEntrada(Date entrada) {
		this.entrada = entrada;
	}

	public Date getSortida() {
		return sortida;
	}

	public void setSortida(Date sortida) {
		this.sortida = sortida;
	}

	@Override
	public String toString() {
		return "Treballador [idTreballador=" + idTreballador + ", entrada=" + entrada + ", sortida=" + sortida + "]";
	}
	
	

	
}
