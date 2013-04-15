package com.example.routemaps;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.google.android.gms.common.*;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SupportMapFragment fragment = 
			      (SupportMapFragment)
			        getSupportFragmentManager()
			          .findFragmentById(R.id.map);

			    GoogleMap map = fragment.getMap();

			    LatLng latLng = new LatLng(-8.102739,-34.89917);
			  
			    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));
			  
			    new RotaAsyncTask(this, map).execute(
			      // Latitude, Logintude de Origem
			      latLng.latitude, latLng.longitude,    
			      // Latitude, Longitude de Destino
			      -23.604262,-46.676513);  
			    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
