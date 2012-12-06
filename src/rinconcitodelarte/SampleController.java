package rinconcitodelarte;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

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
    /***Cosas reloj***/
    @FXML
    private Line horario;
    @FXML
    private Line minutero;
    @FXML
    private Line segundero;
    /***FinCosas Reloj***/
    /***Cosas de la tabla***/
    @FXML
    private TableView tablaAlumnos;
    @FXML
    private TableColumn columID;
    @FXML
    private TableColumn columNombres;
    @FXML
    private TableColumn columApelli;
    @FXML
    private TableColumn columTaller;
    @FXML
    private TableColumn columInscrip;
    /***Terminan las cosas de las tablas***/
    /***Cosas de los creditos***/
    @FXML
    private Pane creditos;
    /***Fin de cosas de los creditos***/
    private Conexion con = null;
    Fechas fecha = new Fechas();
    //private Date fechaAct = new Date();
    /****Pone visible el Pane para agregar nuevo alumno****/
    @FXML
    private void agregarAlum(ActionEvent event){
        System.out.println("Presiono Agregar Nuevo Alumno");
        recipienteReloj.setVisible(false);
        inicioAdmin.setVisible(false);
        newAdminPane.setVisible(false);
        creditos.setVisible(false);
        panelDatos.setVisible(true);
        pestanaReloj.setText("Agregar Alumno");
        

    }
    /****Lee los datos y los envía a la base de datos****/
    @FXML
    private void aceptarAlum(ActionEvent evt){
        System.out.println("Presiono Aceptar Alumno Nuevo");
        
        System.out.println("Esta Seleccionado "+chusTaller.getValue().toString());
        System.out.println("Agrego nuevo alumno correctamente? " + con.insertar("INSERT INTO `alumnos` (`id`,`nombres`,`apellidos`,`taller`,`fecha`) VALUES("+ultimoId()+",'"+txtNombre.getText()+"','"+txtApellido.getText()+"','"+ chusTaller.getValue().toString() +"','"+fecha.getFecha()+"');"));
        data.add(new Tabla(ultimoId()+"",txtNombre.getText(), txtApellido.getText(), chusTaller.getValue().toString(),fecha.getFecha()));
        panelDatos.setVisible(false);
        recipienteReloj.setVisible(true);
        pestanaReloj.setText("Inicio");
        
    }
    /****Metodo que pone visible el Pane para Iniciar Sesion****/
    @FXML
    private void iniciarSesion(ActionEvent evt){
        recipienteReloj.setVisible(false);
        panelDatos.setVisible(false);
        creditos.setVisible(false);
        inicioAdmin.setVisible(true);
        newAdminPane.setVisible(false);
        pestanaReloj.setText("Inicio Sesion");
    }
    
    /****Lee los datos y los compara con la base de datos para los administradores para iniciar sesion y 
     habilitar las pestañas bloqueadas y llena la tabla de los alumnos****/
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
                alumnostab.setDisable(false);
                closeSesion.setDisable(false);
                llenarTabla();
                tablaAlumnos.setItems(data);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /****Pone visible el Pane con los textfield y los passwordfield para crear un nuevo administrador****/
    @FXML
    private void nuevoAdmin(ActionEvent evt){
        recipienteReloj.setVisible(false);
        inicioAdmin.setVisible(false);
        creditos.setVisible(false);
        panelDatos.setVisible(false);
        newAdminPane.setVisible(true);
        pestanaReloj.setText("Nuevo Administrador");
        
    }
    /****Lee y envia la informacion a la base de datos para guardarla****/
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
    
    /****Aqui inicia el programa y hace la conexion a base de datos para posteriormente utilizarla****/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
            con = new rinconcitodelarte.Conexion(rinconcitodelarte.Conexion.usrBD, rinconcitodelarte.Conexion.passBD, "practica");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /****Revisa la base de datos para ver cual es el ultimo Id de Alumnos para asignar Id automaticamente sin repetir****/
    private int ultimoId(){
        int lastId=1;
        while(con.buscar("SELECT nombres FROM alumnos WHERE id=" + lastId) != null){
            lastId ++;
        }
        
        return lastId;
    }
    /****Revisa la base de datos para ver cual es el ultimo Id de Administrador para asignar Id automaticamente sin repetir****/
    private int ultimoAdmin(){
        int lastId=1;
        while(con.buscar("SELECT user FROM admin WHERE idadmin=" + lastId)!= null){
            lastId++;
        }
        return lastId;
    }
    
     private TableView<Tabla> table = new TableView<>();
     private final ObservableList<Tabla> data = FXCollections.observableArrayList(null);
     
     /****Con este metodo se lee la tabla de la base de datos y los pone en la tabla de Alumnos****/
    private void llenarTabla(){
        /***se le pone un setCellValue de la clase Tabla con tipo String***/
        columID.setCellValueFactory(new PropertyValueFactory<Tabla,String>("iD"));
        columNombres.setCellValueFactory(new PropertyValueFactory<Tabla, String>("nombre"));
        columApelli.setCellValueFactory(new PropertyValueFactory<Tabla, String>("apellidos"));
        columTaller.setCellValueFactory(new PropertyValueFactory<Tabla, String>("taller"));
        columInscrip.setCellValueFactory(new PropertyValueFactory<Tabla, String>("fecha"));
        
         java.sql.ResultSet resultado;
        for(int x=1; x<ultimoId(); x++){
            /***Recorre la tabla de la base de datos y va llenando el data con un objeto tabla***/
            resultado = con.buscar("SELECT nombres, apellidos, taller, fecha FROM alumnos WHERE id = "+ x);
            try {
                String newid = x+"";
                String newname = resultado.getString("nombres");
                //System.out.println(newname);
                String newapellido = resultado.getString("apellidos");
                //System.out.println(newapellido);
                String newtaller = resultado.getString("taller");
                //System.out.println(newtaller);
                String newfecha = resultado.getString("fecha");
                //System.out.println(newfecha);
                data.add(new Tabla(newid, newname, newapellido, newtaller, newfecha));
            } catch (SQLException ex) {
                Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
            
    }
    
     
            
   
    @FXML
        private void abrirCreditos(ActionEvent evt){
        recipienteReloj.setVisible(false);
        inicioAdmin.setVisible(false);
        creditos.setVisible(true);
        panelDatos.setVisible(false);
        newAdminPane.setVisible(false);
        pestanaReloj.setText("Créditos");
        
    
        
    }
}
