/*
 * Proyecto:            Proyecto Dos
 * Clase:               DatosProyecto
 * Paquete:             source
 * Desarrollador:       Cristian Julián Andrade Murillo
 * Descripción:         La función de esta clase es buscar los archivos .java, es decir, las clases   
 * Última modificación: 29/05/2019   
*/

package source;

import java.io.File;
import java.util.HashMap;

public class BuscarClases {
	//En este método se buscan las carpetas dentro del proyecto
    public static DatosProyecto obtenerDirectorios(String ruta){
    	HashMap<String,String> rutaArchivos = new HashMap<String,String>(); //guarda nombre y ruta de las clases.
        File directorio = new File(ruta); //carga el directorio del proyecto.
        String nombreProyecto = directorio.getName(); //guarda el nombre del proyecto.
        DatosProyecto datosProyecto = new DatosProyecto(nombreProyecto, rutaArchivos);//inicializa la instancia de la clase DatosProyecto. 
        //Verifica si la ruta ingresada es un directorio.
        if(directorio.isDirectory()){
        	BuscarClases.obtenerArchivosJava(ruta, rutaArchivos);//llama el método que busca los archivos .java
        	datosProyecto.setRutaArchivos(rutaArchivos); //define la ruta de los archivos.
        }else{
        	//Muestra el error que la ruta que ingreso no es valida.
        	System.out.println("La ruta ingresada no es valida.");
        }
        return datosProyecto;
    }
    //Es el método  que busca dentro de cada carpeta los archivos .java
    private static void obtenerArchivosJava(String ruta, HashMap<String,String> rutaArchivos){
        File directorio = new File(ruta); //carga el directorio del proyecto.
        //Verifica si la ruta ingresada es un directorio.
        if(directorio.isDirectory()){
	        File listaDirectorios[] = directorio.listFiles();//lista los archivos dentro de la carpeta.
	        for(int i = 0; i<listaDirectorios.length; i++){
	            if(listaDirectorios[i].isDirectory()) {
	                obtenerArchivosJava(listaDirectorios[i].getAbsolutePath(), rutaArchivos);
	            }else{
	                if(listaDirectorios[i].getName().contains(".java")) {
	                	rutaArchivos.put(listaDirectorios[i].getName().replaceAll(".java", ""),listaDirectorios[i].getAbsolutePath());         
	                }
	            }
	        }
        }
    }

}
