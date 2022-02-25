package dad.geofx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.geofx.ip.Ipapi;
import dad.geofx.ip.IpapiGetSet;
import dad.geofx.ip.Ipify;
import dad.geofx.properties.Properties;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class SecurityController implements Initializable {
	
	Properties p=new Properties();
	Ipapi ipapi=new Ipapi();
	Ipify ipify=new Ipify();
	
	@FXML
    private Text ipThreatText;

    @FXML
    private CheckBox proxyCheckBox;

    @FXML
    private CheckBox torCheckBox;

    @FXML
    private CheckBox crawlerCheckBox;

    @FXML
    private Text threatLevelText;

    @FXML
    private Text threatTypesText;

    @FXML
    private GridPane view;
	
	public SecurityController() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SecurityView.fxml"));
		loader.setController(this);
		loader.load();
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		p.getProxyProperty().bindBidirectional(proxyCheckBox.selectedProperty());
		p.getTorProperty().bindBidirectional(torCheckBox.selectedProperty());
		p.getCrawlerProperty().bindBidirectional(crawlerCheckBox.selectedProperty());
		p.getSecurityProperty().bindBidirectional(ipThreatText.textProperty());
		p.getThreatsProperty().bindBidirectional(threatLevelText.textProperty());
		p.getThreatTypesProperty().bindBidirectional(threatTypesText.textProperty());
		
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


	
	void setInfo(String ip) {

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

				p.setProxyProperty(ipapi.getSecurity().getIsProxy());
				p.setTorProperty(ipapi.getSecurity().getIsTor());
				p.setCrawlerProperty(ipapi.getSecurity().getIsCrawler());
				p.setThreatsProperty(ipapi.getSecurity().getThreatLevel());

				if (ipapi.getSecurity().getThreatLevel().equals("low")) {
					p.setSecurityProperty("This IP is safe, No threats have been detected");
					p.setThreatTypesProperty("No threats have been detected for this IP address");
				} else {
					p.setSecurityProperty("Threads detected. Ip not safe");
					p.setThreatTypesProperty(ipapi.getSecurity().getThreatTypes().toString());
				}

			} catch (Exception e) {
				e.getMessage();
			}

		});

		new Thread(task).start();
	}
	
	
	public GridPane getView() {
		return view;
}

}
