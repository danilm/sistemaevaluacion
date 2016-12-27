package com.ucavila.sisevaulacion.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Tienda implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombreTienda;
	private ArrayList<Vendedor> listaVendedores = new ArrayList<Vendedor>();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	
	public Tienda(String nombre) {
		this.nombreTienda=nombre;
	}

		
	public String getNombreTienda() {
		return nombreTienda;
	}
	public void setNombreTienda(String nombreTienda) {
		this.nombreTienda = nombreTienda;
	}
	public ArrayList<Vendedor> getListaVendedores() {
		return listaVendedores;
	}
	public void setListaVendedores(ArrayList<Vendedor> listaVendedores) {
		this.listaVendedores = listaVendedores;
	}
	
	public void mostrarTienda(Tienda tienda){
		System.out.println("Tienda: " + tienda.getNombreTienda());
		System.out.println("Listado de vendedores (total " + tienda.listaVendedores.size() + ")");
		System.out.println("==========================================");
		int indice=1;
		for (Vendedor vendedor : tienda.listaVendedores){
			System.out.println(indice + " " + vendedor.getApellidos() + " " + vendedor.getNombre() + " " + vendedor.getTotal() + "€ " + formatter.format(vendedor.getFecha()));
			indice++;
		}
		System.out.println("==========================================");
	}
	
	
}
