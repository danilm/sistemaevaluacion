package com.ucavila;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		DataInputStream fich = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		try {
			fich =new DataInputStream(new FileInputStream(this.nombreFichero));
			
			while (true) {
				String lineaFichero = fich.readUTF();
				String [] vendedorLinea = lineaFichero.split(",");
				Vendedor vendedor = new Vendedor();
				vendedor.setNombre(vendedorLinea[0]);
				vendedor.setApellidos(vendedorLinea[1]);
				vendedor.setTotal(Double.parseDouble(vendedorLinea[2]));
				vendedor.setFecha(formatter.parse(vendedorLinea[3]));
				listaVendedores.add(vendedor);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		
		} catch (EOFException eof){
			//Hemos llegado al final del fichero, no se hace nada más
			tienda.setListaVendedores(listaVendedores);
			try {
				fich.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return tienda;
		} catch (IOException io) {
			io.printStackTrace();
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}
