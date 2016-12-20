package com.ucavila;

import java.util.ArrayList;

public class Tienda {

	private String nombreTienda;
	private ArrayList<Vendedor> listaVendedores = new ArrayList<Vendedor>();
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
	
	
	
}
