package dad.geofx.ip;


import retrofit2.Call;
import retrofit2.http.GET;

public interface IpifyInterface {
	
	@GET("?format=json")
	public Call<IpifyGetSet> getIP();
}
