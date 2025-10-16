
package Dominio;

import java.io.Serializable;


public class Dueno implements Serializable{
    private String nombre;
    private int edad;
    private int experiencia;
    
    public Dueno(){
        
    }
    
    public Dueno(String nombre, int edad, int experiencia){
        this.setNombre(nombre);
        this.setEdad(edad);
        this.setExperiencia(experiencia);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
    
    @Override 
    public String toString(){
        return this.getNombre();
    }
    
    
}

