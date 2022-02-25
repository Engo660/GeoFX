package dad.geofx.ip;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Ipify {

	private static final String BASE_URL = "https://api.ipify.org/";
	private IpifyInterface service;

	public Ipify() {

		ConnectionPool pool = new ConnectionPool(1, 1, TimeUnit.SECONDS);

		OkHttpClient client = new OkHttpClient.Builder().connectionPool(pool).build();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client)
				.addConverterFactory(GsonConverterFactory.create()).build();

		service = retrofit.create(IpifyInterface.class);
	}
	
	public String getIP() throws IOException {
		
		Response<IpifyGetSet> response = service.getIP().execute();
		
		IpifyGetSet message = response.body();
		
		return message.getIp();
	}

}
