
package Dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;


public class Puesto implements Serializable {
    private String identificacion;
    private Dueno dueño;
    private String ubicacion;
    private int cantidadEmpleados;
    private ArrayList<Producto> listaProductosPuesto;

    public Puesto(String identificacion, Dueno dueño, String ubicacion, 
            int cantidadEmpleados){
        this.setIdentificacion(identificacion);
        this.setDueño(dueño);
        this.setUbicacion(ubicacion);
        this.setCantidadEmpleados(cantidadEmpleados);
        this.listaProductosPuesto = new ArrayList<>();
        
    }
    
    
    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Dueno getDueño() {
        return dueño;
    }

    public void setDueño(Dueno dueño) {
        this.dueño = dueño;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCantidadEmpleados() {
        return cantidadEmpleados;
    }

    public void setCantidadEmpleados(int cantidadEmpleados) {
        this.cantidadEmpleados = cantidadEmpleados;
    }
    
    public ArrayList<Producto> getListaProductosPuesto(){
        return this.listaProductosPuesto;
    }
    
    public void agregarProductoCompraAPuesto(Producto producto, int cantidad){
        boolean nuevo = true;
        Iterator <Producto> it = this.getListaProductosPuesto().iterator();
        while(it.hasNext()){
            Producto p = it.next();
           if(p.getNombre().equals(producto.getNombre())){
                nuevo = false;
            }
        }           
        if(nuevo){
            this.getListaProductosPuesto().add(producto.clone());
            this.actualizarStockCompra(producto, cantidad);
        } else {
            this.actualizarStockCompra(producto,cantidad);
        }
    }
    
    public void actualizarStockCompra(Producto producto, int cantidad){
        Iterator <Producto> it = this.getListaProductosPuesto().iterator();
        while(it.hasNext()){
            Producto p = it.next();
            if(p.getNombre().equals(producto.getNombre())){
                p.setStock(p.getStock()+cantidad);
            }
        }        
    }
    
    public void actualizarStockVenta(Producto producto, int cantidad) {
        Iterator<Producto> iterator = this.getListaProductosPuesto().iterator();
        while (iterator.hasNext()) {
            Producto p = iterator.next();
            if (p.getNombre().equals(producto.getNombre())) {
                if (p.getStock() >= cantidad) {
                    p.setStock(p.getStock() - cantidad);
                }
            }
        }
    }
    
    @Override
    public String toString(){
        return this.getIdentificacion();
    }
    
    @Override
    public boolean equals(Object obj){
        Puesto p = (Puesto)obj;
        if(this.getIdentificacion().equals(p.getIdentificacion())){
            return true;
        } else {
            return false;
        }
    }
    
    
}
