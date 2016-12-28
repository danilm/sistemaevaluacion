package com.ucavila.sisevaulacion.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Tienda implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombreTienda;
	private TreeMap<String, Vendedor> listaVendedores = new TreeMap<String, Vendedor>();
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
	public TreeMap<String, Vendedor> getListaVendedores() {
		return listaVendedores;
	}
	public void setListaVendedores(TreeMap<String, Vendedor> listaVendedores) {
		this.listaVendedores = listaVendedores;
	}
	
	public void mostrarTienda(){
		System.out.println("Tienda: " + this.getNombreTienda());
		System.out.println("Listado de vendedores (total " + this.listaVendedores.size() + ")");
		System.out.println("==========================================");
		int indice=1;
		for (Map.Entry<String,Vendedor> vendedor : this.listaVendedores.entrySet()){
			System.out.println(indice + " " + vendedor.getValue().getApellidos() + " " + vendedor.getValue().getNombre() + " " + vendedor.getValue().getTotal() + "€ " + formatter.format(vendedor.getValue().getFecha()));
			indice++;
		}
		System.out.println("==========================================");
		
	}
	
}
