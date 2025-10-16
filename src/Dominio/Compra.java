
package Dominio;

import java.io.Serializable;


public class Compra extends Movimiento implements Serializable {
    private Puesto puesto;
    private Mayorista mayorista;
    private Producto producto;
    private int precio;
    private int cantidad;
    private int numeroMovimiento;

    
    public Compra(Puesto puesto, Mayorista mayorista, Producto producto, 
            int precio, int cantidad){
        this.setPuesto(puesto);
        this.setMayorista(mayorista);
        this.setProducto(producto);
        this.setPrecio(precio);
        this.setCantidad(cantidad);
        this.numeroMovimiento = super.getNumeroMovimiento();        
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public Mayorista getMayorista() {
        return mayorista;
    }

    public void setMayorista(Mayorista mayorista) {
        this.mayorista = mayorista;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public int getNumeroMovimiento(){
        return numeroMovimiento;
    }
    
    public void setNumeroMovimiento(int numeroMovimiento){
        this.numeroMovimiento = numeroMovimiento;
    }
    

}
