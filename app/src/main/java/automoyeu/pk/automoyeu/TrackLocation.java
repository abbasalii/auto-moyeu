package automoyeu.pk.automoyeu;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

public class TrackLocation extends ActionBarActivity {

    private LocationManager locationMangaer=null;
    private LocationListener locationListener=null;
    private ProgressBar pb =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_location);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        pb = (ProgressBar) findViewById(R.id.prog_bar_wait);
        pb.setVisibility(View.VISIBLE);
        locationMangaer = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();

        locationMangaer.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        //5000 = 5 second = Time between location updates
        //10 = 10 meter = Distance the device should move to calculate coordinates
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_track_location, menu);
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

    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location loc) {

            double longitude = loc.getLongitude();
            double latitude = loc.getLatitude();
            locationMangaer.removeUpdates(this);
            Intent intent = new Intent(TrackLocation.this, MapActivity.class);
            intent.putExtra("Longitude", longitude);
            intent.putExtra("Latitude", latitude);
            TrackLocation.this.startActivity(intent);
            finish();
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub
        }
    }
}