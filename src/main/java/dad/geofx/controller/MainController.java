package dad.geofx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.geofx.ip.Ipapi;
import dad.geofx.ip.IpapiGetSet;
import dad.geofx.ip.Ipify;
import dad.geofx.properties.Properties;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {
	
	LocationController lc=new LocationController();
	ConnectionController cc=new ConnectionController();
	SecurityController sc=new SecurityController();
	Properties p=new Properties();
	Ipapi ipapi=new Ipapi();
	Ipify ipify=new Ipify();

    @FXML
    private BorderPane root;

    @FXML
    private Button checkIpButton;

    @FXML
    private Tab connectionTab;

    @FXML
    private TextField ipTextField;

    @FXML
    private Tab locationTab;

    @FXML
    private Tab securityTab;
	
	public MainController() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		locationTab.setContent(lc.getView());
		connectionTab.setContent(cc.getView());
		securityTab.setContent(sc.getView());
		p.getIpAddressProperty().bindBidirectional(ipTextField.textProperty());
		
		Task<String> task = new Task<String>() {
			@Override
			protected String call() throws Exception {
				Thread.sleep(500L);
				return ipify.getIP();
			}
		};

		task.setOnSucceeded(event -> {
			p.setIpAddressProperty(task.getValue());
			setInfo(p.getIpAddressProperty().get());
		});
		new Thread(task).start();
	}


	
	private void setInfo(String ip) {

		Task<IpapiGetSet> task = new Task<IpapiGetSet>() {

			@Override
			protected IpapiGetSet call() throws Exception {
				Thread.sleep(500L);
				return ipapi.message(ip);
			}
		};

		task.setOnSucceeded(event -> {

			try {
				IpapiGetSet ipapi = task.get();
				p.setIpAddressProperty(ipapi.getIp());
			}catch (Exception e) {
					e.getMessage();
				}

			});
			new Thread(task).start();
		}
	
	 @FXML
	    void onCheckButtonAction(ActionEvent event) {
		 cc.setInfo(p.getIpAddressProperty().get());
		 lc.setInfo(p.getIpAddressProperty().get());
		 sc.setInfo(p.getIpAddressProperty().get());
	    }
	public BorderPane getView() {
		return root;
	 }

}
