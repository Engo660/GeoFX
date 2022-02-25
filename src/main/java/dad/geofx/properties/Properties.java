package dad.geofx.properties;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Properties {
	

	private StringProperty ipAddressProperty = new SimpleStringProperty();
	private StringProperty latitudProperty = new SimpleStringProperty();
	private StringProperty longitudeProperty = new SimpleStringProperty();
	private StringProperty locationProperty = new SimpleStringProperty();
	private StringProperty cityProperty = new SimpleStringProperty();
	private StringProperty zipProperty = new SimpleStringProperty();
	private StringProperty lenguageProperty = new SimpleStringProperty();
	private StringProperty timeProperty = new SimpleStringProperty();
	private StringProperty callingProperty = new SimpleStringProperty();
	private StringProperty currencyProperty = new SimpleStringProperty();
	private StringProperty ispProperty = new SimpleStringProperty();
	private StringProperty typeProperty = new SimpleStringProperty();
	private StringProperty asnProperty = new SimpleStringProperty();
	private StringProperty hostProperty = new SimpleStringProperty();
	private ObjectProperty<Image> flagProperty = new SimpleObjectProperty<>();
	private BooleanProperty proxyProperty = new SimpleBooleanProperty();
	private BooleanProperty torProperty = new SimpleBooleanProperty();
	private BooleanProperty crawlerProperty = new SimpleBooleanProperty();
	private StringProperty securityProperty = new SimpleStringProperty();
	private StringProperty threatsProperty = new SimpleStringProperty();
	private StringProperty threatTypesProperty = new SimpleStringProperty();
	

	public StringProperty getIpAddressProperty() {
		return ipAddressProperty;
	}
	public final void setIpAddressProperty(final String ipAddressProperty) {
		this.ipAddressProperty.set(ipAddressProperty);
	}
	public StringProperty getLatitudProperty() {
		return latitudProperty;
	}
	public final void setLatitudProperty(final String latitudProperty) {
		this.latitudProperty.set(latitudProperty);
	}
	public StringProperty getLongitudeProperty() {
		return longitudeProperty;
	}
	public final void setLongitudeProperty(final String longitudeProperty) {
		this.longitudeProperty.set(longitudeProperty);
	}
	public StringProperty getLocationProperty() {
		return locationProperty;
	}
	public final void setLocationProperty(final String locationProperty) {
		this.locationProperty.set(locationProperty);
	}
	public StringProperty getCityProperty() {
		return cityProperty;
	}
	public final void setCityProperty(final String cityProperty) {
		this.cityProperty.set(cityProperty);
	}
	public StringProperty getZipProperty() {
		return zipProperty;
	}
	public final void setZipProperty(final String zipProperty) {
		this.zipProperty.set(zipProperty);
	}
	public StringProperty getLenguageProperty() {
		return lenguageProperty;
	}
	public final void setLenguageProperty(final String lenguageProperty) {
		this.lenguageProperty.set(lenguageProperty);
	}
	public StringProperty getTimeProperty() {
		return timeProperty;
	}
	public final void setTimeProperty(final String timeProperty) {
		this.timeProperty.set(timeProperty);
	}
	public StringProperty getCallingProperty() {
		return callingProperty;
	}
	public final void setCallingProperty(final String callingProperty) {
		this.callingProperty.set(callingProperty);
	}
	public StringProperty getCurrencyProperty() {
		return currencyProperty;
	}
	public final void setCurrencyProperty(final String currencyProperty) {
		this.currencyProperty.set(currencyProperty);
	}
	public StringProperty getIspProperty() {
		return ispProperty;
	}
	public final void setIspProperty(final String ispProperty) {
		this.ispProperty.set(ispProperty);
	}
	public StringProperty getTypeProperty() {
		return typeProperty;
	}
	public final void setTypeProperty(final String typeProperty) {
		this.typeProperty.set(typeProperty);
	}
	public StringProperty getAsnProperty() {
		return asnProperty;
	}
	public final void setAsnProperty(final String asnProperty) {
		this.asnProperty.set(asnProperty);
	}
	public StringProperty getHostProperty() {
		return hostProperty;
	}
	public final void setHostProperty(final String hostProperty) {
		this.hostProperty.set(hostProperty);
	}
	public ObjectProperty<Image> getFlagProperty() {
		return flagProperty;
	}
	public final void setFlagProperty(final Image flagProperty) {
		this.flagProperty.set(flagProperty);
	}
	public BooleanProperty getProxyProperty() {
		return proxyProperty;
	}
	public final void setProxyProperty(final boolean proxyProperty) {
		this.proxyProperty.set(proxyProperty);
	}
	public BooleanProperty getTorProperty() {
		return torProperty;
	}
	public final void setTorProperty(final boolean torProperty) {
		this.torProperty.set(torProperty);
	}
	public BooleanProperty getCrawlerProperty() {
		return crawlerProperty;
	}
	public final void setCrawlerProperty(final boolean crawlerProperty) {
		this.crawlerProperty.set(crawlerProperty);
	}
	public StringProperty getSecurityProperty() {
		return securityProperty;
	}
	public final void setSecurityProperty(final String securityProperty) {
		this.securityProperty.set(securityProperty);
	}
	public StringProperty getThreatsProperty() {
		return threatsProperty;
	}
	public final void setThreatsProperty(final String threatsProperty) {
		this.threatsProperty.set(threatsProperty);
	}
	public StringProperty getThreatTypesProperty() {
		return threatTypesProperty;
	}
	public final void setThreatTypesProperty(final String threatTypesProperty) {
		this.threatTypesProperty.set(threatTypesProperty);
	}
	
	
	

}
