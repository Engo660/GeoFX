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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class LocationController implements Initializable {
	
	Properties p=new Properties();
	Ipapi ipapi=new Ipapi();
	Ipify ipify=new Ipify();
	
	   @FXML
	    private Text callingCodeText;

	    @FXML
	    private Text cityStateText;

	    @FXML
	    private Text currencyText;

	    @FXML
	    private Text ipLocationText;

	    @FXML
	    private Text languageText;

	    @FXML
	    private Text latitudeText;

	    @FXML
	    private Text longitudeText;

	    @FXML
	    private Text timeZoneText;

	    @FXML
	    private GridPane view;

	    @FXML
	    private Text zipCodeText;
	    @FXML
	    private ImageView flagImageView;
	
	public LocationController() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LocationView.fxml"));
		loader.setController(this);
		loader.load();
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		p.getLatitudProperty().bindBidirectional(latitudeText.textProperty());
		p.getLongitudeProperty().bindBidirectional(longitudeText.textProperty());
		p.getLocationProperty().bindBidirectional(ipLocationText.textProperty());
		p.getFlagProperty().bindBidirectional(flagImageView.imageProperty());
		p.getCityProperty().bindBidirectional(cityStateText.textProperty());
		p.getZipProperty().bindBidirectional(zipCodeText.textProperty());
		p.getLenguageProperty().bindBidirectional(languageText.textProperty());
		p.getTimeProperty().bindBidirectional(timeZoneText.textProperty());
		p.getCallingProperty().bindBidirectional(callingCodeText.textProperty());
		p.getCurrencyProperty().bindBidirectional(currencyText.textProperty());
		
		Task<String> task1 = new Task<String>() {

			@Override
			protected String call() throws Exception {
				Thread.sleep(500L);
				return ipify.getIP();
			}
		};
		task1.setOnSucceeded(event -> {
			p.setIpAddressProperty(task1.getValue());
			setInfo(p.getIpAddressProperty().get());
		});
		new Thread(task1).start();
	}

	
	void setInfo(String ip) {

		Task<IpapiGetSet> task2 = new Task<IpapiGetSet>() {
			@Override
			protected IpapiGetSet call() throws Exception {
				Thread.sleep(500L);
				return ipapi.message(ip);
			}
		};
		task2.setOnSucceeded(event -> {

			try {

				IpapiGetSet ipapi = task2.get();
				p.setIpAddressProperty(ipapi.getIp());
				p.setLatitudProperty(ipapi.getLatitude().toString());
				p.setLongitudeProperty(ipapi.getLongitude().toString());
				p.setLocationProperty(ipapi.getCountryName().concat(" (").concat(ipapi.getCountryCode().concat(")")));
				p.setFlagProperty((new Image("/flags/" + ipapi.getCountryCode() + ".png")));
				p.setCityProperty(ipapi.getCity().concat(" (").concat(ipapi.getRegionName()).concat(")"));
				p.setZipProperty(ipapi.getZip());
				p.setLenguageProperty(ipapi.getLocation().getLanguages().get(0).getName().concat(" (")
						.concat(ipapi.getCountryCode().concat(")")));
				p.setTimeProperty(ipapi.getTimeZone().getCode());
				p.setCallingProperty(ipapi.getLocation().getCallingCode());
				p.setCurrencyProperty(ipapi.getCurrency().getName().concat(" (").concat(ipapi.getCurrency().getSymbol().concat(")")));


			} catch (Exception e) {
				e.getMessage();
			}

		});

		new Thread(task2).start();
	}
	public GridPane getView() {
		return view;
}

}
