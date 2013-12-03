package edu.upc.eetac.dsa.rodrigo.sampedro.beeter.android;

import java.io.IOException;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import edu.upc.eetac.dsa.rodrigo.sampedro.beeter.android.api.BeeterAPI;
import edu.upc.eetac.dsa.rodrigo.sampedro.beeter.android.api.Sting;
import edu.upc.eetac.dsa.rodrigo.sampedro.beeter.android.api.StingCollection;
 
public class Beeter extends ListActivity {
	
	String serverAddress;
	String serverPort;
	BeeterAPI api;
	ProgressDialog pd;
	ArrayList<Sting> stingList;
	StingAdaptar adapter;
	
	//primero define un tag unidentificador, nomrlamente elnombre dela clase
	private final static String TAG = Beeter.class.toString();
	
	//datos amostrar enla lista
	private static final String[] items = { "lorem", "ipsum", "dolor", "sit",
			"amet", "consectetuer", "adipiscing", "elit", "morbi", "vel",
			"ligula", "vitae", "arcu", "aliquet", "mollis", "etiam", "vel",
			"erat", "placerat", "ante", "porttitor", "sodales", "pellentesque",
			"augue", "purus" };
	
	//esto es el adaptador entre la list ay lso datsos, usamso un adaptador de array indicandole ke son stirng.
	//private ArrayAdapter<String> adapter;
 
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {		
		//llamada ala superior, ponemos el contentview el layout
		super.onCreate(savedInstanceState);
		
		//iniciamos le log del logcat para verlo enel debug
		Log.d(TAG, "onCreate()");
		
		//assesmanager te devuevle el path de config de la aplicacion
		AssetManager assetManager = getAssets();
		//creamos un objeto de propiedades
		Properties config = new Properties();
		try {
			//cargamos las propiedades
			config.load(assetManager.open("config.properties"));
			serverAddress = config.getProperty("server.address");
			serverPort = config.getProperty("server.port");
			//printamos lo cargado	 
			Log.d(TAG, "Configured server " + serverAddress + ":" + serverPort);
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
			//finalizamos la actividad si ha hay une error
			finish();
		}
		
		Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("alicia", "alicia"
						.toCharArray());
			}
		});
		setContentView(R.layout.beeter_layout);
		//instancia el contenedor indicando el contesto, la actividad
		// Ponemos  (contexto actividad, elemnto que queremos pintar el layaout el como se muestran lso datos , los datos)
		//adapter = new ArrayAdapter<String>(this,
		//		android.R.layout.simple_list_item_1, items);
		
		stingList = new ArrayList<Sting>();
		adapter = new StingAdaptar(this, stingList);
		
		//este metodo existe porke heredados de listactivity que indicamos cual es el adaptador que aplica ala list view
		setListAdapter(adapter);
		
		api = new BeeterAPI();
		URL url = null;
		try {
			url = new URL("http://" + serverAddress + ":" + serverPort
					+ "/beeter-api/stings?&offset=0&length=20");
		} catch (MalformedURLException e) {
			Log.d(TAG, e.getMessage(), e);
			finish();
		}
		(new FetchStingsTask()).execute(url);
	}
	
	private void addStings(StingCollection stings){
		stingList.addAll(stings.getStings());
		adapter.notifyDataSetChanged();
	}
	
	
	private class FetchStingsTask extends AsyncTask<URL, Void, StingCollection>
	{

		@Override
		protected StingCollection doInBackground(URL... params) {
			StingCollection stings = api.getStings(params[0]);
			return stings;
		}
	 
//		@Override
//		protected void onPostExecute(StingCollection result) {
//			ArrayList<Sting> stings = new ArrayList<Sting>(result.getStings());
//			for (Sting s : stings) {
//				Log.d(TAG, s.getStingid() + "-" + s.getSubject());
//			}
//			if (pd != null) {
//				//cierra el dialog
//				pd.dismiss();
//			}
//		}
		
		@Override
		protected void onPostExecute(StingCollection result) {
			addStings(result);
			if (pd != null) {
				pd.dismiss();
			}
		}
	 
		@Override
		protected void onPreExecute() {
			pd = new ProgressDialog(Beeter.this);
			pd.setTitle("Searching...");
			pd.setCancelable(false);
			pd.setIndeterminate(true);
			pd.show();
		}
		
	}


}
