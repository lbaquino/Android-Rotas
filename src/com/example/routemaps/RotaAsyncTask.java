package com.example.routemaps;

import java.util.Locale;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.maps.GeoPoint;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

public class RotaAsyncTask extends 
  AsyncTask<Double, Void, Void>{

  private ProgressDialog dialog;
  private GoogleMap mapView;
  private Context context;
  private Route rota;

  public RotaAsyncTask(Context ctx, GoogleMap mapa) {
    mapView = mapa;
    context = ctx;
  }
 
  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    //dialog = ProgressDialog.show(mapView.getContext(), "Aguarde", "Calculando rota");
  }
 
  @Override
  protected Void doInBackground(Double... params) {
	Log.d("Log: ", "params: "+params[0] + params[1] + params[2] + params[3]);
	
	// Parametros
	int p1 = (int) (params[0] * 1E6) / 10;
	int p2 = (int) (params[1] * 1E6) / 10;
	int p3 = (int) (params[2] * 1E6) / 10;
	int p4 = (int) (params[3] * 1E6) / 10;
	
	Log.d("Log int: ", "Ps: "+p1 + p2 + p3 + p4);
	
	//GeoPoint pontoGeografico1 = new GeoPoint(p1, p2);
	//GeoPoint pontoGeografico2 = new GeoPoint(p3, p4);
	// Vai chamar a rota
    rota = directions(params[0], params[1], params[2], params[3]);
    // Chamou a rota
    return null;
  }
 
  @Override
  protected void onPostExecute(Void result) {
    super.onPostExecute(result);
    PolylineOptions options = new PolylineOptions()
      .width(5)
      .color(Color.RED)
      .visible(true);

    for (LatLng latlng : rota.getPoints()) {
      options.add(latlng);
    }

    mapView.addPolyline(options);
    //dialog.dismiss();
  }
 
  private Route directions(double p1, double p2, double p3, double p4) {
  
    // Formatando a URL com a latitude e longitude  
    // de origem e destino.  
    String urlRota = String.format(Locale.US,
      "http://maps.googleapis.com/maps/api/"+
      "directions/json?origin=%f,%f&"+
      "destination=%f,%f&" +
      "sensor=true&mode=driving",   
      p1, p2, p3, p4); 
  
    GoogleParser parser;
    parser = new GoogleParser(urlRota);
    return parser.parse();
  }
}