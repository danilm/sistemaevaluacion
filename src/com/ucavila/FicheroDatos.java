package com.ucavila;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class FicheroDatos extends File{

	private String nombreFichero;
	private static final long serialVersionUID = 1L;

	public FicheroDatos(String pathname) {
		super(pathname);
		this.nombreFichero = pathname;
		
	}
	
	public Tienda leerFichero(){
		Tienda tienda = new Tienda();
		ArrayList<Vendedor> listaVendedores = new ArrayList<Vendedor>();
		ObjectInputStream fich;
		try {
			fich =new ObjectInputStream(new FileInputStream(this.nombreFichero));
			
			while (true) {
				Vendedor vendedor = (Vendedor) fich.readObject();
				listaVendedores.add(vendedor);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		
		} catch (EOFException eof){
			//Hemos llegado al final del fichero, no se hace nada más
			tienda.setListaVendedores(listaVendedores);
			
			return tienda;
		} catch (IOException io) {
			io.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
