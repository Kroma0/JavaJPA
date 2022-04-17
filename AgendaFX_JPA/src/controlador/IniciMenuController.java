package controlador;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class IniciMenuController extends Application {

	private EntityManager em;

	@FXML
	private BorderPane borderPane;

	public IniciMenuController () {
		super();
		try{

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgendaFX_JPA_2"); 
			em = emf.createEntityManager(); 

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		//Carrega el fitxer amb la interficie d'usuari inicial (Scene)
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/IniciMenuView.fxml"));
		Scene fm_inici = new Scene(loader.load());

		//Li assigna la escena a la finestra inicial (primaryStage) i la mostra
		primaryStage.setScene(fm_inici);
		primaryStage.setTitle("Agenda");
		//primaryStage.setMaximized(true);
		primaryStage.show();
	}


	@FXML
	void onActionMenuItemPersones(ActionEvent event) throws IOException {
		//Carrega el fitxer amb la interficie d'usuari
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/PersonesView.fxml"));
		Pane panell = (AnchorPane)loader.load();
		//Crear un objecte de la clase PersonasController ja que necessitarem accedir al mètodes d'aquesta classe
		PersonesController personesControler = (PersonesController)loader.getController();
		personesControler.setConexionBD(em);

		borderPane.setCenter(panell); 
	}
	
	@FXML
	void onActionMenuItemClients(ActionEvent event) throws IOException {
		//Carrega el fitxer amb la interficie d'usuari
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ClientsView.fxml"));
		Pane panell = (AnchorPane)loader.load();
		//Crear un objecte de la clase PersonasController ja que necessitarem accedir al mètodes d'aquesta classe
		PersonesController clientsControler = (PersonesController)loader.getController();
		clientsControler.setConexionBD(em);

		borderPane.setCenter(panell); 
	}

	@FXML
	void onActionMenuItemProductes(ActionEvent event) throws IOException {
		//Carrega el fitxer amb la interficie d'usuari
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ProductesView.fxml"));
		Pane panell = (AnchorPane)loader.load();
		//Crear un objecte de la clase PersonasController ja que necessitarem accedir al mètodes d'aquesta classe
		ProductesController productesControler = (ProductesController)loader.getController();
		productesControler.setConexionBD(em);

		borderPane.setCenter(panell); 
	}
	
	@FXML
	void onActionMenuItemFitxar(ActionEvent event) throws IOException {
		//Carrega el fitxer amb la interficie d'usuari
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/FitxarView.fxml"));
		Pane panell = (AnchorPane)loader.load();
		//Crear un objecte de la clase PersonasController ja que necessitarem accedir al mètodes d'aquesta classe
		TreballadorController treballadorControler = (TreballadorController)loader.getController();
		treballadorControler.setConexionBD(em);

		borderPane.setCenter(panell); 
	}


	@FXML
	void onActionMenuItemSortir(ActionEvent event) {
		Platform.exit();
	}


	@Override
	public void stop() throws Exception {
		super.stop();

		if (em != null) em.close();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
