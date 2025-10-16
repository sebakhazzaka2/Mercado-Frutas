
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dominio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Serializacion {
    public static void serializar(Sistema sistema) throws IOException{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("salida"));
            out.writeObject(sistema);
            out.close();
    }
    
    public static Sistema deserealizar() throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("salida"));
        Sistema sistema = (Sistema)in.readObject();
        return sistema;
    }
}
