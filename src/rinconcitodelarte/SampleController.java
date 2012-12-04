package rinconcitodelarte;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
    @FXML
    private Pane panelDatos;
    @FXML
    private Pane recipienteReloj;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private ComboBox chusTaller;
    @FXML
    private Tab pestanaReloj;
    @FXML
    private TextField txtusername;
    @FXML
    private PasswordField passContra;
    @FXML
    private Pane inicioAdmin;
    @FXML
    private Tab alumnostab;
    @FXML
    private Label confirm;
    @FXML
    private MenuItem closeSesion;
    @FXML
    private TextField nameAdmin;
    @FXML
    private PasswordField passAdmin;
    @FXML
    private Pane newAdminPane;
    @FXML
    private Label correcto;
    @FXML
    private Button okButt;
    
    private Conexion con = null;
    Fechas fecha = new Fechas();
    //private Date fechaAct = new Date();
    
    @FXML
    private void agregarAlum(ActionEvent event){
        System.out.println("Presiono Agregar Nuevo Alumno");
        recipienteReloj.setVisible(false);
        panelDatos.setVisible(true);
        pestanaReloj.setText("Agregar Alumno");
        

    }
    
    @FXML
    private void aceptarAlum(ActionEvent evt){
        System.out.println("Presiono Aceptar Alumno Nuevo");
        System.out.println("Esta Seleccionado "+chusTaller.getValue().toString());
        System.out.println("Agrego nuevo alumno correctamente? " + con.insertar("INSERT INTO `alumnos` (`id`,`nombres`,`apellidos`,`taller`,`fecha`) VALUES("+ultimoId()+",'"+txtNombre.getText()+"','"+txtApellido.getText()+"','"+ chusTaller.getValue().toString() +"','"+fecha.getFecha()+"');"));
        
        panelDatos.setVisible(false);
        recipienteReloj.setVisible(true);
        pestanaReloj.setText("Reloj");
        
    }
    
    @FXML
    private void iniciarSesion(ActionEvent evt){
        recipienteReloj.setVisible(false);
        inicioAdmin.setVisible(true);
        newAdminPane.setVisible(false);
        pestanaReloj.setText("Inicio Sesion");
    }
    
    @FXML
    private void confirDatos(ActionEvent evt){
        java.sql.ResultSet resultado;
        resultado = con.buscar("SELECT * FROM admin WHERE user ='"+txtusername.getText()+"'");
        try {
            System.out.println(resultado.getString(3));
            if(passContra.getText().equals(resultado.getString("pass"))){
                confirm.setText("Inicio de sesion correcto");
                confirm.setTextFill(Color.BLUE);
                AgregNueAlum.setDisable(false);
                AgregarProfItem.setDisable(false);
                alumnostab.setDisable(false);
                closeSesion.setDisable(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void nuevoAdmin(ActionEvent evt){
        recipienteReloj.setVisible(false);
        inicioAdmin.setVisible(false);
        newAdminPane.setVisible(true);
        pestanaReloj.setText("Nuevo Administrador");
        
    }
    
    @FXML
    private void confirmnewAdmin(ActionEvent evt){
        if(con.insertar("INSERT INTO `admin` (`idadmin`, `user`,`pass`) VALUES("+ultimoAdmin()+",'"+nameAdmin.getText()+"','"+passAdmin.getText()+"');")){
            okButt.setVisible(true);
            correcto.setVisible(true);
            
        }else{
            System.out.println("Contraseña Incorrecta");
            correcto.setText("No se pudo agregar nuevo Administrador");
            correcto.setVisible(true);
        }
    } 
    
    @FXML
    private void entendido(ActionEvent evt){
        pestanaReloj.setText("Reloj");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            con = new rinconcitodelarte.Conexion(rinconcitodelarte.Conexion.usrBD, rinconcitodelarte.Conexion.passBD, "practica");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private int ultimoId(){
        int lastId=1;
        while(con.buscar("SELECT nombres FROM alumnos WHERE id=" + lastId) != null){
            lastId ++;
        }
        
        return lastId;
    }
    
    private int ultimoAdmin(){
        int lastId=1;
        while(con.buscar("SELECT user FROM admin WHERE idadmin=" + lastId)!= null){
            lastId++;
        }
        return lastId;
    }
}