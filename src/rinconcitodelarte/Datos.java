package rinconcitodelarte;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author LuisSantiago
 */
public class Datos implements Initializable{
    
    @FXML
    private TextField textName;
    @FXML
    private TextField textApellidos;
    @FXML
    private ComboBox taller;
    @FXML
    private Button acept;
    
    @FXML
    private void agregarDatos(ActionEvent event){
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet.");
           
    }
    
}
