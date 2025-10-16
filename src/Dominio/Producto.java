
package Dominio;

import java.io.Serializable;


public class Producto implements Cloneable, Serializable{
    private String nombre;
    private String descripcion;
    private String tipo;
    private String formaVenta;
    private int stock;
    private String imagen;
    
        public Producto(String nombre, String descripcion, String tipo, String formaVenta, String imagen){
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setTipo(tipo);
        this.setFormaVenta(formaVenta);
        this.setStock(0);
        this.setImagen(imagen);
        
    }
    
    public Producto(String nombre){
        this.setNombre(nombre);
        this.setStock(0);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUnidad() {
        return formaVenta;
    }

    public void setFormaVenta(String unidad) {
        this.formaVenta = unidad;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    @Override
    public String toString(){
        return this.nombre;
    }
    
    @Override
    public Producto clone(){
        try{
            return (Producto) super.clone();
        } catch(CloneNotSupportedException e){
            return null;
        }
    }
    
}
