package edu.upc.eetac.dsa.rodrigo.sampedro.beeter.android.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class BeeterAPI {

	private final static String TAG = BeeterAPI.class.toString();
	private final static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public Sting getSting(URL url) {
		Sting sting = new Sting();

		HttpURLConnection urlConnection = null;
		try {
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestProperty("Accept",
					MediaType.BEETER_API_STING);
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoInput(true);
			urlConnection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			JSONObject jsonSting = new JSONObject(sb.toString());
			sting = parseSting(jsonSting);
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
			return null;
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage(), e);
			return null;
		} catch (ParseException e) {
			Log.e(TAG, e.getMessage(), e);
			return null;
		} finally {
			if (urlConnection != null)
				urlConnection.disconnect();
		}

		return sting;
	}
	public Sting createSting(URL url, String subject, String content) {
		Sting sting = new Sting();
		sting.setSubject(subject);
		sting.setContent(content);
		
		HttpURLConnection urlConnection = null;
		try {
			JSONObject jsonSting = createJsonSting(sting);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestProperty("Accept",
					MediaType.BEETER_API_STING);
			urlConnection.setRequestProperty("Content-Type",
					MediaType.BEETER_API_STING);
			urlConnection.setRequestMethod("POST");
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			urlConnection.connect();
		
			PrintWriter writer = new PrintWriter(
					urlConnection.getOutputStream());
			writer.println(jsonSting.toString());
			writer.close();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		
			jsonSting = new JSONObject(sb.toString());
			sting = parseSting(jsonSting);
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage(), e);
			return null;
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
			return null;
		} catch (ParseException e) {
			Log.e(TAG, e.getMessage(), e);
			return null;
		} finally {
			if (urlConnection != null)
				urlConnection.disconnect();
		}
		
		return sting;
	}
	 
	private JSONObject createJsonSting(Sting sting) throws JSONException {
		JSONObject jsonSting = new JSONObject();
		jsonSting.put("subject", sting.getSubject());
		jsonSting.put("content", sting.getContent());
	 
		return jsonSting;
	}
	// le pasaremos la url que sera la de beeter-api/stings?0 && etc...
	public StingCollection getStings(URL url) {

		StingCollection stings = new StingCollection();

		HttpURLConnection urlConnection = null;
		try {
			// iniciamso el objeto de la conexion http
			urlConnection = (HttpURLConnection) url.openConnection();

			// aceptamos la conexion con el media type indicado
			urlConnection.setRequestProperty("Accept",
					MediaType.BEETER_API_STING_COLLECTION);
			// realizamso el metodo GET
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoInput(true);
			// realizamos la conexion
			urlConnection.connect();

			// preparamos lso buffer par ala respuesta http
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));

			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			// obtenemos el jsond ela respuestaen el Sting Builder

			// pasamos el Sting buldier a Json mirarse org.json
			JSONObject jsonObject = new JSONObject(sb.toString());

			// indicmaos que de la parte entre corchetes de json me coja el
			// array denominado links
			JSONArray jsonLinks = jsonObject.getJSONArray("links");
			// me pone lso elementos del array en el arraylist definido en sting
			// collectionpara loslinks
			parseLinks(jsonLinks, stings.getLinks());

			// de jsonde la respuesta dame el array de os stings
			JSONArray jsonStings = jsonObject.getJSONArray("stings");
			for (int i = 0; i < jsonStings.length(); i++) {
				// para cada uno de lso elemento lso parseo a Sting
				JSONObject jsonSting = jsonStings.getJSONObject(i);
				Sting sting = parseSting(jsonSting);

				// lo allado a el array list de stings
				stings.addSting(sting);
			}
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
			return null;
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage(), e);
			return null;
		} catch (ParseException e) {
			Log.e(TAG, e.getMessage(), e);
			return null;
		} finally {
			if (urlConnection != null)
				urlConnection.disconnect();
		}

		return stings;
	}

	private void parseLinks(JSONArray source, List<Link> links)
			throws JSONException {
		for (int i = 0; i < source.length(); i++) {
			JSONObject jsonLink = source.getJSONObject(i);
			Link link = new Link();
			link.setRel(jsonLink.getString("rel"));
			link.setTitle(jsonLink.getString("title"));
			link.setType(jsonLink.getString("type"));
			link.setUri(jsonLink.getString("uri"));
			links.add(link);
		}
	}

	private Sting parseSting(JSONObject source) throws JSONException,
			ParseException {
		Sting sting = new Sting();
		sting.setAuthor(source.getString("author"));
		if (source.has("content"))
			sting.setContent(source.getString("content"));
		// se carga la T por que no tiene que estar
		String tsLastModified = source.getString("lastModified").replace("T",
				" ");
		sting.setLastModified(sdf.parse(tsLastModified));
		sting.setStingid(source.getString("stingid"));
		sting.setSubject(source.getString("subject"));
		sting.setUsername(source.getString("username"));

		JSONArray jsonStingLinks = source.getJSONArray("links");
		parseLinks(jsonStingLinks, sting.getLinks());
		return sting;
	}
}
