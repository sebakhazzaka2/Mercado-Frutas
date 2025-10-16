
package Dominio;

import java.io.Serializable;
import java.util.Comparator;


public class Criterio implements Comparator <Producto>,Serializable{
    public int compare(Producto p1, Producto p2){
        int retorno = p1.getTipo().toUpperCase().compareTo(p2.getTipo().toUpperCase());
        if (retorno ==0){
            retorno = p1.getNombre().toUpperCase().compareTo(p2.getNombre().toUpperCase());
        }
        
        return retorno;
        
    }    
}
