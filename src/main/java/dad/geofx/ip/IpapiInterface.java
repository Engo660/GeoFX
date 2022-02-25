package dad.geofx.ip;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IpapiInterface {
	
	@GET("ip_api.php")
	public Call<IpapiGetSet> ipInfo(@Query("ip") String ip);
		
}
