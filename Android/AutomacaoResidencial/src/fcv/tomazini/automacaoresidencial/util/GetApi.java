package fcv.tomazini.automacaoresidencial.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class GetApi {
	private static final String ENCODING = "UTF-8";
	private String location = "";
	private String port = "80";

	public GetApi(String location) {
		this.location = location;
	}

	public boolean isConnected(Context context) {
		final ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		final NetworkInfo networkInfo = connectivityManager
				.getActiveNetworkInfo();

		boolean ativo = (networkInfo != null && networkInfo.isConnected());
		if (ativo)
			return isServerActive(context);
		return false;
	}

	private boolean isServerActive(Context context) {
		try {
			URL myUrl = new URL("http://" + location + ":" + port);
			URLConnection connection = myUrl.openConnection();
			connection.setConnectTimeout(3000);
			connection.connect();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getFromApi(String action) {
		HttpClient client = new DefaultHttpClient();
		StringBuilder builder = new StringBuilder();
		HttpGet httpGet = new HttpGet("http://" + location + ":" + port + "/"
				+ action);
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content, ENCODING));

				String line;
				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
			} else {
				Log.e("Error....", "Failed to download file");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return builder.toString();
	}
}
