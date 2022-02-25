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
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ConnectionController implements Initializable {
	
		Ipapi ipapi=new Ipapi();
		Ipify ipify=new Ipify();
		Properties p=new Properties();
	
	    @FXML
	    private Text asnText;

	    @FXML
	    private Text hostNameText;

	    @FXML
	    private Text ipAdressText;
	    

	    @FXML
	    private Text registeredIspText;

	    @FXML
	    private Text typeText;

	    @FXML
	    private GridPane view;

	public ConnectionController() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConnectionView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		p.getAsnProperty().bindBidirectional(asnText.textProperty());
		p.getHostProperty().bindBidirectional(hostNameText.textProperty());
		p.getIpAddressProperty().bindBidirectional(ipAdressText.textProperty());
		p.getIspProperty().bindBidirectional(registeredIspText.textProperty());
		p.getTypeProperty().bindBidirectional(typeText.textProperty());
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
				p.setIpAddressProperty(ipapi.getIp());
				p.setIspProperty(ipapi.getConnection().getIsp());
				p.setTypeProperty(ipapi.getType());
				p.setAsnProperty(ipapi.getConnection().getAsn().toString());
				p.setHostProperty(ipapi.getHostname());	

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
