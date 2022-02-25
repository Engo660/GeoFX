package dad.geofx.app;

import dad.geofx.controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
	
	private MainController mc;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		mc = new MainController();

		Scene scene = new Scene(mc.getView());

		primaryStage.setTitle("GeoFx");
		primaryStage.getIcons().add(new Image("/img/Globe-icon.png"));
		primaryStage.setScene(scene);
		primaryStage.show();

	}


	public static void main(String[] args) {
		launch(args);

	}

}
