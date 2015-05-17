package automoyeu.pk.automoyeu;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;


public class MapActivity extends FragmentActivity {

    private GoogleMap map;
    private View popup = null;
    private String[][] workshops = {{"Technique Autos", "+92 51 2204022"}, {"Hi-Tech Automobile Engineers", "+92 321 9717772"}, {"Fast Automotives", "+92 51 4442241"}, {"Daud Auto Workshop", "+92 51 4562376"}, {"Auto Care Workshop", "+92 300 6621556"}};
    private double[][] loc = {{33.700420, 73.067960}, {33.695843, 73.050467}, {33.660740, 73.044725}, {33.597805, 73.054766}, {33.651334, 72.974725}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("Latitude", 0);
        double longitude = intent.getDoubleExtra("Longitude", 0);
        map = ((SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        Marker mark = map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Your Current Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mark.showInfoWindow();
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 11));
        popup = getLayoutInflater().inflate(R.layout.custom_info_win, null);
        map.setMyLocationEnabled(true);
        for(int i=0; i<5; i++) {
            map.addMarker(new MarkerOptions().position(new LatLng(loc[i][0], loc[i][1])).title(i+""));
        }

        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                if(marker.getTitle().compareTo("Your Current Location")==0)
                    return null;
                int pos = Integer.parseInt(marker.getTitle());
                TextView tv = (TextView)popup.findViewById(R.id.wtitle);
                tv.setText(workshops[pos][0]);

                tv = (TextView)popup.findViewById(R.id.winfo);
                String str = "Contact: "+workshops[pos][1]+"\nOperating Hours: 9AM-10PM";
                tv.setText(str);
                tv = (TextView) popup.findViewById(R.id.wservices);
                str = "1. Oil Change\n2. AC Repair\n3. Detailed Checkup";
                tv.setText(str);
                return popup;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*public boolean onMarkerClick(Marker marker)
    {
        Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
        marker.remove();
        return true;
    }*/
}