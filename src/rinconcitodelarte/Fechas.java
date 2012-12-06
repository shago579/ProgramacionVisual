/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rinconcitodelarte;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author LuisSantiago
 */
public class Fechas {
    
    private Calendar Cal = new GregorianCalendar();
    private String fecha;
    
    public Fechas(){
        fecha = Cal.get(Calendar.DAY_OF_MONTH) +"/"+ Cal.get(Calendar.MONTH) +"/"+ Cal.get(Calendar.YEAR);
    }

    public String getFecha(){
        return fecha;
    }
    
    public int gethoras(){
        return Cal.get(Calendar.HOUR);
    }
    
    public int getminutos(){
        return Cal.get(Calendar.MINUTE);
    }    
    
    public int getsegundos(){
        return Cal.get(Calendar.SECOND);
    }
}
