
package Dominio;

import java.io.Serializable;


public class Venta extends Movimiento implements Serializable{
    private Puesto puesto;
    private Producto producto;
    private int cantidad;
    private int precioUnit;
    private int numeroMovimiento;
    
    public Venta(Puesto puesto, Producto producto, int cantidad, int precioUnit) {
        this.puesto = puesto;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnit = precioUnit;
        this.numeroMovimiento = super.getNumeroMovimiento();
    }
    

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(int precioUnit) {
        this.precioUnit = precioUnit;
    }
    
    public int getNumeroMovimiento(){
        return numeroMovimiento;
    }
    
    public void setNumeroMovimiento(int numeroMovimiento){
        this.numeroMovimiento = numeroMovimiento;
    }
    
    
}
