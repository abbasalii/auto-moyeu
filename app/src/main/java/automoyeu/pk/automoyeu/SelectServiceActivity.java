package automoyeu.pk.automoyeu;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;


public class SelectServiceActivity extends ActionBarActivity {

    private ArrayList<String> order_info = new ArrayList<String>();
    private ArrayList<String> services = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_service);

        order_info = getIntent().getStringArrayListExtra("order_info");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select, menu);
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

    public void addService(View view) {

        ((ImageButton)view).setImageResource(R.drawable.cross);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeService(v);
            }
        });
        switch (view.getId()) {
            case R.id.oilChange:
                services.add("Oil Change");
                break;
            case R.id.carWash:
                services.add("Car Wash");
                break;
            case R.id.tuning:
                services.add("Tuning");
                break;
            case R.id.detailedCheckup:
                services.add("Detailed Checkup");
                break;
            case R.id.tCAssistance:
                services.add("Traffic Challan Assistance");
                break;
            case R.id.tTAssistance:
                services.add("Token Tax Assistance");
                break;
            case R.id.otherServices:
                services.add("Other Value Added Services");
                break;
        }
    }

    public void removeService(View view) {

        ((ImageButton)view).setImageResource(R.drawable.plus);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addService(v);
            }
        });
        switch (view.getId()) {
            case R.id.oilChange:
                services.remove("Oil Change");
                break;
            case R.id.carWash:
                services.remove("Car Wash");
                break;
            case R.id.tuning:
                services.remove("Tuning");
                break;
            case R.id.detailedCheckup:
                services.remove("Detailed Checkup");
                break;
            case R.id.tCAssistance:
                services.remove("Traffic Challan Assistance");
                break;
            case R.id.tTAssistance:
                services.remove("Token Tax Assistance");
                break;
            case R.id.otherServices:
                services.remove("Other Value Added Services");
                break;
        }
    }

    public void checkout(View view) {
        Intent intent = new Intent(this, DataView.class);
        intent.putStringArrayListExtra("order_info", order_info);
        intent.putStringArrayListExtra("selected_services", services);
        startActivity(intent);

    }
}