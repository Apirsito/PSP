/*
 * Proyecto:            Proyecto Dos
 * Clase:               DatosProyecto
 * Paquete:             source
 * Desarrollador:       Cristian Julián Andrade Murillo
 * Descripción:         La función de esta clase es recorrer cada clase y contar las líneas y los métodos.  
 * Última modificación: 29/05/2019   
*/

package source;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Contador {
	public DatosProyecto conteoArchivos(String ruta){
	DatosProyecto proyecto = BuscarClases.obtenerDirectorios(ruta);
    try {
            ArrayList<DatosClase> listaDatosClase = new ArrayList<DatosClase>();
            Iterator iterator = proyecto.getRutaArchivos().entrySet().iterator();
            int numeroLoscTotales = 0;
            while(iterator.hasNext()){
                Map.Entry entry = (Map.Entry) iterator.next();
                int numeroLineas = 0;
                int numeroMetodos = 0;
                FileReader lectorArchivos = new FileReader((String)entry.getValue());
                BufferedReader lectorClase = new BufferedReader(lectorArchivos);
                String linea = lectorClase.readLine();
                while(linea!=null) {
                    //Numero de lineas en la clase
                    if(!linea.trim().equals("")&&!linea.trim().startsWith("//")&&!linea.trim().startsWith("*/")&&!linea.trim().startsWith("/*")&&!linea.trim().startsWith("*")) {
                    	numeroLineas++;
                    }
                    //Numero de metodos de la clase
                    if(!linea.contains("if")&&(linea.contains("public")||linea.contains("private")||linea.contains("protected"))&& linea.contains("(") && linea.contains(")") && linea.contains("{")) {
                        numeroMetodos++;
                    }
                    linea = lectorClase.readLine();
                }
                DatosClase datosClase = new DatosClase((String) entry.getKey(), numeroLineas, numeroMetodos);
                listaDatosClase.add(datosClase);
                proyecto.setDatosClase(listaDatosClase);
                lectorClase.close();
                numeroLoscTotales = numeroLoscTotales+numeroLineas;
                proyecto.setNumeroLoscTotales(numeroLoscTotales);
            }
    } catch (FileNotFoundException ex) {
        Logger.getLogger(Contador.class.getName()).log(Level.SEVERE, null, ex);
    } 
    catch (IOException ex) {
        Logger.getLogger(Contador.class.getName()).log(Level.SEVERE, null, ex);
    }
    return proyecto;
}
}

