package com.ucavila;



public class Cliente {
	//Este password se usa para generar el fichero de excepciones desde el servidor
	private static final String PASSWORD="1qazxsw2";
	
	/**
	 * Clase principal que, segun el numero de argumentos, realizará la lectura
	 * o escritura del fichero binario de vendedores y ventas
	 * @param args
	 * @param argc
	 */
	public static void main(String[] args) {
		int argc = args.length;
		//Comprobamos los parámetros de entrada
		if (argc < 1) {
			errorArgumentos(1);
			System.exit(1);
		} else if ((argc > 1) &&(argc < 2)){
			errorArgumentos(2);
			System.exit(2);
		}
		
		//Vamos a ver qué operación queremos procesar
		if (argc == 1){
			//Comprobamos que el argumento sea un fichero, caso contrario,lanzamos error
			FicheroDatos ficheroEntrada = new FicheroDatos(args[0]);
			
			if (!ficheroEntrada.isFile()){
				errorArgumentos(3);
				System.exit(3);
			}
			Tienda tienda = ficheroEntrada.leerFichero("Mi Tienda");
			tienda.mostrarTienda(tienda);
			
			
		} else if (argc==2){
		
		//Ahora vamos a comprobar el password (caso que sea la opcion 2)
		}

	}
	
	
	
	private static void errorArgumentos(int argc){
		System.out.println("Error de argumentos, posibles usos:");
		if (argc==1) {
			System.out.println("Leer desde fichero datos de vendedores: cliente.java fichero_datos_ventas");
		} else if (argc==2){
			System.out.println("Generar fichero de excepciones de servidor: cliente.java -p password nombre_fichero");
			
		} else if (argc==3){
			System.out.println("Fichero incorrecto");
			
		}
	}
}
