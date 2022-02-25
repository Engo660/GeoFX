package dad.geofx.ip;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Ipapi {

	private static final String BASE_URL = "https://ipapi.com/";
	private IpapiInterface service;

	public Ipapi() {

		ConnectionPool con = new ConnectionPool(1, 1, TimeUnit.SECONDS);

		OkHttpClient client = new OkHttpClient.Builder().connectionPool(con).build();

		Retrofit r = new Retrofit.Builder().baseUrl(BASE_URL).client(client)
				.addConverterFactory(GsonConverterFactory.create()).build();

		service = r.create(IpapiInterface.class);
	}
	
	public IpapiGetSet message(String ip) throws IOException {
		
		Response<IpapiGetSet> response = service.ipInfo(ip).execute();
		
		IpapiGetSet message = response.body();
		
		return message;
		
	}
}
