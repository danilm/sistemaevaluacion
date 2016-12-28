package com.ucavila.sisevaulacion.comm;


import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.ucavila.sisevaulacion.model.Tienda;
import com.ucavila.sisevaulacion.model.Vendedor;

public class ClienteSocket {

	private String address;
	private int port;
	private Socket socket;
		
	public void conectar(String address, int port){
		try {
			this.address=address;
			this.port=port;
			this.socket = new Socket(getAddress(), getPort());
			
		} catch (UnknownHostException e){
			System.out.println("Error de host: " + getAddress());
		} catch (IOException io) {
			System.out.println("Error de comunicacion con el host " + getAddress());
		} catch (NullPointerException ne) {
			System.out.println("Error, no se ha especificado un host válido");
		}
	}
	
	 public boolean obtenerListaVendedores(String password){
		  try {
			System.out.println("Obteniendo Lista de Vendedores Usando Socket " + this.socket.getPort());
			
			ObjectOutputStream pw = new ObjectOutputStream(this.socket.getOutputStream());
			
			//Obtiene el flujo de entrada asociado al socket:
			ObjectInputStream br = new ObjectInputStream(this.socket.getInputStream());
			
			
			//Enviamos la cadena de identificacion:
			pw.writeObject("ClienteSiS");
			String respuesta = (String)br.readObject();
			System.out.println("Respuesta Servidor:" + respuesta);
			if (respuesta.equals("ServidorSiS-passw")){
				//Nos responde el servidor, le enviamos el password para que lo valide y nos devuelva el listado
				pw.writeObject(password);
				respuesta = (String)br.readObject();
				//Lo primero que nos devolverá será un OK, caso contrario, algo ha pasado
				if (respuesta.equals("OK")){
					System.out.println("OBTENIENDO DATOS DE LA TIENDA");
					Tienda tienda= (Tienda) br.readObject();
					tienda.mostrarTienda();
					
				} else {
					System.out.println("Error, el servidor no acepta el password enviado");
				}
			} else {
				System.out.println("Error, el servidor no ha devuelto la respuesta esperada");
			}
			
			br.close();
			pw.close();
			this.socket.close();
		} catch (IOException | ClassNotFoundException  e) {
			System.out.println("Error de comunicacion con el host " + getAddress());
			System.out.println(e.getMessage());
			try {
				
				this.socket.close();
			} catch (IOException e1) {
				System.out.println(e.getMessage());
				return false;
			}
			return false;
			
		}
		 return true;
	 }
	 
	 public void enviarExcepcion(Vendedor vendedor){
		 try {
				ObjectOutputStream pw = new ObjectOutputStream(this.socket.getOutputStream());
				ObjectInputStream br = new ObjectInputStream(this.socket.getInputStream());
				
				pw.writeObject("ClienteSiS-excepcion");
				String respuesta = (String)br.readObject();
				System.out.println("Respuesta Servidor:" + respuesta);
				if (respuesta.equals("ServidorSiS-enviarExcepcion")){
					//Obtenemos la IP local y se la añadimos a nuestro vendedor:
					InetAddress inet = this.getSocket().getLocalAddress();
					String ip=inet.getHostAddress();
					vendedor.setIp(ip);
					
					//Enviamos el vendedor al servidor para que lo procese
					pw.writeObject(vendedor);
				} else {
					System.out.println("Error, el servidor no ha devuelto la respuesta esperada");
				}
				br.close();
				pw.close();
				this.socket.close();
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("Error de comunicacion con el host " + getAddress());
				System.out.println(e.getMessage());
				try {
					this.socket.close();
				} catch (IOException e1) {
					System.out.println(e.getMessage());
					
				}
				
				
			}
	 }

	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	
}
