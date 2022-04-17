package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Treballador;
import model.TreballadorDAO;

public class TreballadorController{
	//Objecte per gestionar la persistÃ¨ncia de les dades
	private TreballadorDAO treballadorDAO;
	//Objecte per gestionar el objecte actual
	private Treballador treballador = null;
	//indicador de nou registre
	private boolean nouRegistre = false;

	//Elements grÃ fics de la UI
	@FXML
	private AnchorPane anchorPane;
	private Stage ventana;
	@FXML private TextField idField;
	
	ZoneId defaultZoneId = ZoneId.systemDefault();
	
	private ValidationSupport vs;

	public void setConexionBD(EntityManager em) {	
		//Crear objecte DAO de persones
		treballadorDAO = new TreballadorDAO(em);
	}

	/**
	 * Inicialitza la classe. JAVA l'executa automÃ ticament desprÃ©s de carregar el fitxer fxml amb loader.load()
	 * i abans de rebre l'objecte conexionBD o qualsevol altre que pasem des del IniciMenuController
	 */
	@FXML private void initialize() {
		//idField.setVisible(isDatosValidos());
				//Validació dades
				//https://github.com/controlsfx/controlsfx/issues/1148
				//produeix error si no posem a les VM arguments això: --add-opens=javafx.graphics/javafx.scene=ALL-UNNAMED
				vs = new ValidationSupport();
				vs.registerValidator(idField, true, Validator.createEmptyValidator("ID obligatori"));
	}

	public Stage getVentana() {
		return ventana;
	}

	public void setVentana(Stage ventana) {
		this.ventana = ventana;
	}

	@FXML
	void onActionEliminar(ActionEvent event) {
		if(isDatosValidos()){
			// Mostrar missatge confirmació
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Vol esborrar el producte " + idField.getText() + "?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				if(treballadorDAO.delete(Integer.parseInt(idField.getText()))){ 
					limpiarFormulario();
					treballadorDAO.showAll();
				}
			}
		}
	}

	@FXML private void onActionSortir(ActionEvent e) throws IOException {
		sortir();
		//tancar el formulari
		((BorderPane)anchorPane.getParent()).setCenter(null);
	}

	@FXML private void onActionGuardarEntrada(ActionEvent e) throws IOException {
		//verificar si les dades són vàlides				
		if(isDatosValidos()){
			Treballador t = new Treballador(Integer.parseInt(idField.getText()),Date.from(java.time.LocalDate.now().atStartOfDay(defaultZoneId).toInstant()), null);
			treballadorDAO.saveEntrada(t);
			limpiarFormulario();
			treballadorDAO.showAll();
			System.out.println(t.toString());
		}
	}
	
	@FXML private void onActionGuardarSalida(ActionEvent e) throws IOException {
		//verificar si les dades són vàlides				
		if(isDatosValidos()){
			Treballador t = new Treballador(Integer.parseInt(idField.getText()),Date.from(java.time.LocalDate.now().atStartOfDay(defaultZoneId).toInstant()),Date.from(java.time.LocalDate.now().atStartOfDay(defaultZoneId).toInstant()));

			treballadorDAO.saveSortida(t);
			limpiarFormulario();
			treballadorDAO.showAll();
			System.out.println(t.toString());
		}
	}

	public void sortir(){
		treballadorDAO.showAll();
	}
	private boolean isDatosValidos() {

		//Comprovar si totes les dades sÃ³n vÃ lides
		if (vs.isInvalid()) {
			String errors = vs.getValidationResult().getMessages().toString();
			// Mostrar finestra amb els errors
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(ventana);
			alert.setTitle("Camps incorrectes");
			alert.setHeaderText("Corregeix els camps incorrectes");
			alert.setContentText(errors);
			alert.showAndWait();

			return false;
		}
		return true;
	}

	@FXML private void onActionListar(ActionEvent e) throws IOException {
		//verificar si les dades són vàlides				
		System.out.println(treballadorDAO.getList().toString());
	}


	private void limpiarFormulario(){
		idField.setText("");
	}
}
