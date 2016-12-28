package com.ucavila.sisevaulacion.model;

import java.io.Serializable;
import java.util.Date;

public class Vendedor implements Serializable,Comparable<Vendedor>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String apellidos;
	private double total=0.0;
	private Date fecha;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public int compareTo(Vendedor v) {
		return apellidos.compareTo(v.getApellidos());
	}
	
	@Override
	public boolean equals(Object o){
		if (apellidos.compareTo(((Vendedor) o).getApellidos()) == 0) 
			return true;
		else 
			return false;
	}

}
