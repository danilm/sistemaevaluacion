package com.ucavila.sisevaulacion.business;

import java.io.BufferedReader;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TreeMap;

import com.ucavila.sisevaulacion.comm.ClienteSocket;
import com.ucavila.sisevaulacion.model.Tienda;
import com.ucavila.sisevaulacion.model.Vendedor;

public class FicheroDatos extends File{

	private String nombreFichero;
	private static final long serialVersionUID = 1L;
	private static final String SEPARATOR = ",";

	public FicheroDatos(String pathname) {
		super(pathname);
		this.nombreFichero = pathname;
		
	}
	
	public Tienda leerFichero(String nombre){
		Tienda tienda = new Tienda(nombre);
		TreeMap<String,Vendedor> listaVendedores = new TreeMap<String,Vendedor>();
		Integer indice = 1;
		BufferedReader fich = null;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
			fich =new BufferedReader(new FileReader(this.nombreFichero));
			String line = fich.readLine();
			while (line!=null) {
				
				String [] vendedorLinea = line.split(SEPARATOR);
				Vendedor vendedor = new Vendedor();
				vendedor.setNombre(vendedorLinea[1]);
				vendedor.setApellidos(vendedorLinea[0]);
				vendedor.setTotal(Double.parseDouble(vendedorLinea[2]));
				vendedor.setFecha(formatter.parse(vendedorLinea[3]));
				listaVendedores.put(vendedor.getApellidos(),vendedor);
				//Si el total es menor de 1000, hay que enviar los datos al servidor como expcecion
				if(Double.parseDouble(vendedorLinea[2]) < 1000){
					enviarExcepcion(vendedor);
				}
				indice++;
				line = fich.readLine();
			}
			tienda.setListaVendedores(listaVendedores);
			fich.close();
			return tienda;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		
		
		} catch (IOException io) {
			io.printStackTrace();
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	private void enviarExcepcion(Vendedor vendedor){
		System.out.println("Excepcion detectada! Enviando al vendedor " + vendedor.getApellidos() + "(" + vendedor.getTotal() + "€) al servidor de excepciones");
		ClienteSocket cliente = new ClienteSocket();
		cliente.conectar("127.0.0.1",2244);
		cliente.enviarExcepcion(vendedor);
	}

}
