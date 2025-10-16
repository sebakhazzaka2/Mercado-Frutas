
package Dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.beans.*;
import java.io.File;
import java.io.Serializable;



public class Sistema implements Serializable {
    private ArrayList<Puesto> listaPuestos;
    private ArrayList<Dueno> listaDueños;
    private ArrayList<Producto> listaProductos;
    private ArrayList<Mayorista> listaMayoristas;
    private ArrayList<Movimiento> listaMovimientos;
    private PropertyChangeSupport manejador;


    public Sistema() {
        this.listaPuestos = new ArrayList<>();
        this.listaDueños = new ArrayList<>();
        this.listaProductos = new ArrayList<>();
        this.listaMayoristas = new ArrayList<>();
        this.listaMovimientos = new ArrayList<>(); 
        manejador = new PropertyChangeSupport(this);
        
        
    }

    public ArrayList<Puesto> getListaPuestos() {
        return listaPuestos;
    }
    
    public void setListaPuestos(Puesto unPuesto) {
        ArrayList<Puesto> anterior = this.getListaPuestos();
        this.getListaPuestos().add(unPuesto);
        manejador.firePropertyChange("listaPuestos",anterior,
                unPuesto);
    }

    public ArrayList getListaDueños() {
        return listaDueños;
    }
    
    public void setListaDueños(Dueno unDueño) {
        ArrayList<Dueno> anterior = this.getListaDueños();
        this.getListaDueños().add(unDueño);
        manejador.firePropertyChange("listaDueños", anterior,
                unDueño);        
    }    

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }
    
    public void setListaProductos(Producto unProducto) {
        ArrayList<Producto> anterior = this.getListaProductos();
        this.getListaProductos().add(unProducto);
        manejador.firePropertyChange("listaProductos", anterior, 
                unProducto);
    }
    
    public ArrayList<Mayorista> getListaMayoristas() {
        return listaMayoristas;
    }
    
    public void setListaMayorista(Mayorista unMayorista) {
        ArrayList<Mayorista> anterior = this.getListaMayoristas();        
        this.getListaMayoristas().add(unMayorista);
        manejador.firePropertyChange("listaMayoristas",anterior,
                unMayorista);
        
    }

    public ArrayList<Movimiento> getListaMovimientos(){
        return listaMovimientos;
    }
    
    public void setListaMovimientos(Movimiento unMovimiento){
        ArrayList<Movimiento> anterior = this.getListaMovimientos();
        this.getListaMovimientos().add(unMovimiento);
        manejador.firePropertyChange("listaMovimientos",anterior,
                unMovimiento);
    }

    public ArrayList<Venta> getListaVentas() {
        ArrayList<Movimiento> movimientos = this.getListaMovimientos();
        ArrayList<Venta> listaVentas = new ArrayList<>();
        for(Movimiento movimiento: movimientos){
            if(movimiento instanceof Venta){
                listaVentas.add((Venta)movimiento);
            }
        }
        return listaVentas;
    }
         

    public ArrayList<Compra> getListaCompras() {
        ArrayList<Movimiento> movimientos = this.getListaMovimientos();
        ArrayList<Compra> listaCompras = new ArrayList<>();
        for(Movimiento movimiento: movimientos){
            if(movimiento instanceof Compra){
                listaCompras.add((Compra)movimiento);
            }
        }
        return listaCompras;
    }
    

    
    public void registrarProducto(String nombre, String descripcion, String tipo, String formaVenta, String imagen){
        Producto p = new Producto(nombre,descripcion,tipo,formaVenta, imagen);
        this.setListaProductos(p);
    }
    
    public void registrarDueño(String nombre, int edad, int experiencia){
        Dueno dueño = new Dueno(nombre, edad, experiencia);
        this.setListaDueños(dueño);
    }
    
    public void registrarPuesto (String identificacion, Dueno dueño, 
        String ubicacion, int cantidadEmpleados){
        Puesto puesto = new Puesto (identificacion, dueño, ubicacion, cantidadEmpleados);
        this.setListaPuestos(puesto);
    }
    
    public void registrarMayorista (String rut, String nombre, 
        String direccion, ArrayList<Producto> listaProductos){
        Mayorista mayorista = new Mayorista (rut, nombre, direccion, listaProductos);
        this.setListaMayorista(mayorista);
    }    
    
    
    public ArrayList<Producto> listaOrdenadaProducto(){  //devuelve una lista de productos ordenada alfabeticamente
    Collections.sort(this.getListaProductos(),new Comparator<Producto>(){
        public int compare(Producto p1, Producto p2){
            return p1.getNombre().toUpperCase().compareTo(p2.getNombre().toUpperCase());
        }
    });    
    return this.getListaProductos();
    }
    
    public ArrayList<Producto> listaOrdenadaProductosPorMayorista(Mayorista unMayorista){ //devuelve una lista de productos que pertenecen a un mayorista ordenada alfabeticamente
        Iterator<Mayorista> it = this.getListaMayoristas().iterator();
        while(it.hasNext()){
            Mayorista aux = it.next();
            if(aux.equals(unMayorista)){
                Collections.sort(aux.getListaProductos(),new Comparator<Producto>(){
                    public int compare(Producto p1, Producto p2){
                        return p1.getNombre().toUpperCase().compareTo(p2.getNombre().toUpperCase());
                    }
                });

            }
        }
        return unMayorista.getListaProductos();
    }
    
    public void registrarCompra(Puesto puesto, Mayorista mayorista, Producto producto, int precio, int cantidad){
//        Compra compra = new Compra(puesto,mayorista,producto,precio,cantidad,numeroMovimiento);
        Movimiento compra = new Compra(puesto,mayorista,producto,precio,cantidad);
        this.setListaMovimientos(compra);
        this.actualizarPuestoCompra(puesto, producto, cantidad);
        
    }
    
    public void actualizarPuestoCompra(Puesto puesto, Producto producto, int cantidad){ //actualiza el puesto despues de comprar
        Iterator<Puesto> it = this.getListaPuestos().iterator();
        while(it.hasNext()){
            Puesto p = it.next();
            if(p.equals(puesto)){
                p.agregarProductoCompraAPuesto(producto, cantidad);
            }
        }
    }

    public ArrayList<Producto> listaOrdenadaProductosDisponiblePorPuesto(Puesto unPuesto){ //Devuelve la lista de productos de un puesto ordenada por Tipo/Nombre
        ArrayList<Producto> lista = unPuesto.getListaProductosPuesto();
        Collections.sort(lista,new Criterio());
        ArrayList<Producto> listaDisponibles = new ArrayList<>();
        for(Producto producto: lista){
            if(producto.getStock()>0){
                listaDisponibles.add(producto);
            }
        }
        return listaDisponibles;
    }
    
    public void registrarVenta(Puesto puesto, Producto producto, int cantidad, int precioUnit){
        Movimiento venta = new Venta(puesto, producto, cantidad, precioUnit);
        this.setListaMovimientos(venta);
        puesto.actualizarStockVenta(producto, cantidad);
        
    }
    
    public int totalMontoVendidoPorProducto(Producto producto){ 
        ArrayList<Venta> lista = this.getListaVentas();
        int total = 0;
        for(Venta venta: lista){
            if(venta.getProducto().getNombre().equalsIgnoreCase(producto.getNombre())){
                total+=(venta.getCantidad()*venta.getPrecioUnit());
            }
        }
        return total;
    }
    
    public int totalMontoCompradoPorProducto(Producto producto){
        ArrayList<Compra> lista = this.getListaCompras();
        int total = 0;
        for(Compra compra: lista){
            if(compra.getProducto().getNombre().equalsIgnoreCase(producto.getNombre())){
                total+=(compra.getCantidad()*compra.getPrecio());
            }
        }
        return total;
    }

    public int totalCantidadVendidaPorProducto(Producto producto){
        ArrayList<Venta> lista = this.getListaVentas();
        int total = 0;
        for(Venta venta: lista){
            if(venta.getProducto().getNombre().equalsIgnoreCase(producto.getNombre())){
                total+=venta.getCantidad();
            }
        }
        return total;
    }
    
    
    
    public int totalCantidadCompradaPorProducto(Producto producto){
        ArrayList<Compra> lista = this.getListaCompras();
        int total = 0;
        for(Compra compra: lista){
            if(compra.getProducto().getNombre().equalsIgnoreCase(producto.getNombre())){
                total+=compra.getCantidad();
            }
        }
        return total;
    }      
    
    public int precioMinVendidoPorProducto(Producto producto){
        ArrayList<Venta> lista = this.getListaVentas();
        int min = Integer.MAX_VALUE;
        if(lista.size()>0){
            for(Venta venta: lista){
                if(venta.getProducto().getNombre().equalsIgnoreCase(producto.getNombre())){
                    if(venta.getPrecioUnit()<min){
                        min=venta.getPrecioUnit();
                    }
                }
            }
        } else{
            min = 0;
        }    
        return min;
    }

    public int precioMaxVendidoPorProducto(Producto producto){
        ArrayList<Venta> lista = this.getListaVentas();
        int max = Integer.MIN_VALUE;
        if(lista.size()>0){
            for(Venta venta: lista){
                if(venta.getProducto().getNombre().equalsIgnoreCase(producto.getNombre())){
                    if(venta.getPrecioUnit()>max){
                        max=venta.getPrecioUnit();
                    }
                }
            }
        } else{
            max = 0;
        }    
        return max;
    }

    public ArrayList<Puesto> listaPuestosConMenorPrecio(Producto producto){
        ArrayList<Puesto> listaPuestos = new ArrayList<>();
        ArrayList<Venta> listaVentas = this.getListaVentas();
        for(Venta venta: listaVentas){
            if(venta.getProducto().getNombre().equalsIgnoreCase(
                    producto.getNombre()) && venta.getPrecioUnit() 
                    == precioMinVendidoPorProducto(producto) && 
                    !listaPuestos.contains(venta.getPuesto())){
                listaPuestos.add(venta.getPuesto());
            }
        }
        return listaPuestos;
    }

    public ArrayList<Puesto> listaPuestosConMayorPrecio(Producto producto){
        ArrayList<Puesto> listaPuestos = new ArrayList<>();
        ArrayList<Venta> listaVentas = this.getListaVentas();
        for(Venta venta: listaVentas){
            if(venta.getProducto().getNombre().equalsIgnoreCase(
                    producto.getNombre()) && venta.getPrecioUnit() 
                    == precioMaxVendidoPorProducto(producto) && 
                    !listaPuestos.contains(venta.getPuesto())){
                listaPuestos.add(venta.getPuesto());
            }
        }
        return listaPuestos;
    }
    
    public void generarArchivoComprasVentas(String nombre, int inicio, int hasta, 
            ArrayList<Puesto> puestos){
        ArchivoGrabacion arch =new ArchivoGrabacion(nombre+".txt");
        ArrayList<Movimiento> movimientos = this.getListaMovimientos();
        String linea = "";
        int cantidadRegistros = 0;
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fechaHoraActualFormato = fechaActual.format(formato);
        arch.grabarLinea(fechaHoraActualFormato);
        for(Movimiento movimiento: movimientos){
            if(movimiento instanceof Compra){
                Compra compra = (Compra)movimiento;
                int numeroC = compra.getNumeroMovimiento();
                Puesto puestoC = compra.getPuesto();
                if(numeroC>=inicio && numeroC<=hasta && puestos.contains(puestoC)){
                    linea = numeroC+"#"+compra.getProducto().getNombre()+"#"+
                            puestoC.getIdentificacion()+"#C#"+compra.getCantidad()+
                            "#"+compra.getPrecio();
                    arch.grabarLinea(linea);
                    cantidadRegistros++;
                }
            }    
            if(movimiento instanceof Venta){
                Venta venta = (Venta)movimiento;
                int numeroV = venta.getNumeroMovimiento();
                Puesto puestoV = venta.getPuesto();
                if(numeroV>=inicio && numeroV<=hasta && puestos.contains(puestoV)){
                    linea = numeroV+"#"+venta.getProducto().getNombre()+"#"+
                            puestoV.getIdentificacion()+"#V#"+venta.getCantidad()+
                            "#"+venta.getPrecioUnit();
                    arch.grabarLinea(linea);
                    cantidadRegistros++;
                }                   
            }
        }
        arch.grabarLinea("Total de movimientos incluidos: "+cantidadRegistros);
        arch.cerrar();
    }
    
    public void generarArchivoVentas(String nombre, int inicio, int hasta, 
            ArrayList<Puesto> puestos){
        ArchivoGrabacion arch =new ArchivoGrabacion(nombre+".txt");
        ArrayList<Movimiento> movimientos = this.getListaMovimientos();
        String linea = "";
        int cantidadRegistros = 0;
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fechaHoraActualFormato = fechaActual.format(formato);
        arch.grabarLinea(fechaHoraActualFormato);        
        for(Movimiento movimiento: movimientos){  
            if(movimiento instanceof Venta){
                Venta venta = (Venta)movimiento;
                int numeroV = venta.getNumeroMovimiento();
                Puesto puestoV = venta.getPuesto();
                if(numeroV>=inicio && numeroV<=hasta && puestos.contains(puestoV)){
                    linea = numeroV+"#"+venta.getProducto().getNombre()+"#"+
                            puestoV.getIdentificacion()+"#V#"+venta.getCantidad()+
                            "#"+venta.getPrecioUnit();
                    arch.grabarLinea(linea);
                    cantidadRegistros++;
                }                   
            }
        }
        arch.grabarLinea("Total de movimientos incluidos: "+cantidadRegistros);
        arch.cerrar();
    }
    
    public void generarArchivoCompras(String nombre, int inicio, int hasta, 
            ArrayList<Puesto> puestos){
        ArchivoGrabacion arch =new ArchivoGrabacion(nombre+".txt");
        ArrayList<Movimiento> movimientos = this.getListaMovimientos();
        String linea = "";
        int cantidadRegistros = 0;
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fechaHoraActualFormato = fechaActual.format(formato);
        arch.grabarLinea(fechaHoraActualFormato);        
        for(Movimiento movimiento: movimientos){
            if(movimiento instanceof Compra){
                Compra compra = (Compra)movimiento;
                int numeroC = compra.getNumeroMovimiento();
                Puesto puestoC = compra.getPuesto();
                if(numeroC>=inicio && numeroC<=hasta && puestos.contains(puestoC)){
                    linea = numeroC+"#"+compra.getProducto().getNombre()+"#"+
                            puestoC.getIdentificacion()+"#C#"+compra.getCantidad()+
                            "#"+compra.getPrecio();
                    arch.grabarLinea(linea);
                    cantidadRegistros++;
                }
            }    
        }
        arch.grabarLinea("Total de movimientos incluidos: "+cantidadRegistros);
        arch.cerrar();
    }
    
    public boolean existeMayorista(String rut) {
    for (Mayorista mayorista : listaMayoristas) {
        if (mayorista.getRut().equals(rut)) {
            return true;
        }
    }
    return false;
    }
    
    public boolean existeDueno(String nombre) {
        for (Dueno dueno : listaDueños) {
            if (dueno.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean existePuesto(String identificacion) {
    for (Puesto puesto : this.getListaPuestos()) {
        if (puesto.getIdentificacion().equalsIgnoreCase(identificacion)) {
            return true;
        }
    }
    return false;
    }
    
    public boolean existeProducto(String nombre) {
    for (Producto producto : this.getListaProductos()) {
        if (producto.getNombre().equalsIgnoreCase(nombre)) {
            return true;
        }
    }
    return false;
    }
    
    public void cargarProductosArchivo(){
        String rutaArchivo = System.getProperty("user.dir");
        rutaArchivo += File.separator+"productos.txt";
        ArchivoLectura arch = new ArchivoLectura(rutaArchivo);
        while(arch.hayMasLineas()){
            String[] productos = arch.linea().split("@");
            String nombre = productos[0];
            String descripcion = productos[1];
            String tipo = productos[2];
            String formaVenta = productos[3];
            String rutaImagen = System.getProperty("user.dir");
            rutaImagen += File.separator+"ImagenesOblig/"+productos[4];
            this.registrarProducto(nombre, descripcion, tipo, formaVenta, rutaImagen);
        }
        arch.cerrar();
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        manejador.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(PropertyChangeListener listener){
        manejador.removePropertyChangeListener(listener);
    }
    
}    
    
