package rinconcitodelarte;

import javafx.beans.property.SimpleStringProperty;


/**
 *
 * @author LuisSantiago
 * */

public class Tabla{
    /***Clase tabla que le da formato a la tabla***/
    private SimpleStringProperty iD;
    private SimpleStringProperty nombre;
    private SimpleStringProperty apellidos;
    private SimpleStringProperty taller;
    private SimpleStringProperty fecha;
    
    public Tabla(String niD, String nNombre, String nApellidos, String nTaller, String nFecha){
        iD = new SimpleStringProperty(niD);
        nombre = new SimpleStringProperty(nNombre);
        apellidos = new SimpleStringProperty(nApellidos);
        taller = new SimpleStringProperty(nTaller);
        fecha = new SimpleStringProperty(nFecha);
    }
    
    public String getId(){
        return iD.get();
    }
    
    public void setId(String niD){
        iD.set(niD);
    }
    
    public String getNombre() {
        return nombre.get();
    }
    public void setNombre(String fName) {
        nombre.set(fName);
    }
        
    public String getApellidos() {
        return apellidos.get();
    }
    public void setApellidos(String nApellido) {
        apellidos.set(nApellido);
    }
    
    public String getTaller() {
        return taller.get();
    }
    public void setTaller(String nTaller) {
        taller.set(nTaller);
    }
    public String getFecha() {
        return fecha.get();
    }
    public void setFecha(String dia) {
        fecha.set(dia);
    }
    //http://docs.oracle.com/javafx/2/api/javafx/scene/control/cell/PropertyValueFactory.html
}