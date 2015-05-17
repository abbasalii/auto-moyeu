package automoyeu.pk.automoyeu;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;


public class SelectMainActivity extends ActionBarActivity {

    private String contact = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_main);

        contact = getIntent().getStringExtra("contact");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_main, menu);
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

    public void placeOrder (View view) {
        Intent intent = new Intent(this, OrderFormActivity.class);
        intent.putExtra("contact", contact);
        startActivity(intent);
    }

    public void trackLocation (View view) {
        Intent intent = new Intent(this, TrackLocation.class);
        startActivity(intent);
    }

    public void mileage(View view) {

    }

    public void schedule(View view) {

    }
    public void help(View view) {

    }
    public void back(View view) {
        finish();
    }
}