package com.ucavila;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Tienda {

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
		System.out.println("Listado de vendedores");
		System.out.println("=====================");
		for (Vendedor vendedor : tienda.listaVendedores){
			System.out.println(vendedor.getApellidos() + " " + vendedor.getNombre() + " " + vendedor.getTotal() + "€ " + formatter.format(vendedor.getFecha()));
		}
	}
	
	
}
