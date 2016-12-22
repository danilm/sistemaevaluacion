package com.ucavila.sisevaulacion.comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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
		 BufferedReader br = null;
		 PrintWriter pw = null;
		 try {
			br  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream(),true);
			
			//Enviamos la cadena de identificacion:
			pw.println("ClienteSiS");
			String respuesta = br.readLine();
			if (respuesta.equals("ServidorSiS-passw")){
				//Nos responde el servidor, le enviamos el password para que lo valide y nos devuelva el listado
				pw.println(password);
				respuesta = br.readLine();
				//Lo primero que nos devolverá será un OK, caso contrario, algo ha pasado
				if (respuesta.equals("OK")){
					while (br.readLine()!=null){
						String linea=br.readLine();
						//Hay que ver cómo se trae los datos
					}
				} else {
					System.out.println("Error, el servidor no acepta el password enviado");
				}
			} else {
				System.out.println("Error, el servidor no ha devuelto la respuesta esperada");
			}
			
			br.close();
			pw.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("Error de comunicacion con el host " + getAddress());
			System.out.println(e.getMessage());
			try {
				br.close();
				pw.close();
				socket.close();
			} catch (IOException e1) {
				System.out.println(e.getMessage());
				return false;
			}
			return false;
			
		}
		 return true;
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
