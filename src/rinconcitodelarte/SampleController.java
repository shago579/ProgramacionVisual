package rinconcitodelarte;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 *
 * @author LuisSantiago
 */
public class SampleController implements Initializable {
    @FXML
    private MenuBar toolbar;
    @FXML
    private MenuItem AgregNueAlum;
    @FXML
    private MenuItem AgregarProfItem;
    @FXML
    private MenuItem AgregarAdmItem;
    @FXML
    private MenuItem MenItemCreditos;
    
    private Conexion con = null;
    
    @FXML
    private void agregarAlum(ActionEvent event){
        System.out.println("Presiono Agregar Nuevo Alumno");
        Datos datos = new Datos();
        //System.out.println("Agrego nuevo alumno correctamente? " + con.insertar("INSERT INTO `alumnos` (`id`,`nombres`,`apellidos`) VALUES("+textfield1.getText()+",'"+textfield2.getText()+"','"+textfield3.getText()+"');"));

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            con = new rinconcitodelarte.Conexion(rinconcitodelarte.Conexion.usrBD, rinconcitodelarte.Conexion.passBD, "practica");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}