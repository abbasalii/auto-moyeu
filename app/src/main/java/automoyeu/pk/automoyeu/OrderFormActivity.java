package automoyeu.pk.automoyeu;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import java.util.ArrayList;


public class OrderFormActivity extends ActionBarActivity {

    EditText name = null;
    EditText contact_no = null;
    EditText address = null;
    EditText pref_time = null;
    EditText comments = null;
    private ArrayList<String> order_info = new ArrayList<String>();
    private String contact = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);

        name = (EditText) findViewById(R.id.name);
        contact_no = (EditText) findViewById(R.id.contact_no);
        address = (EditText) findViewById(R.id.address);
        pref_time = (EditText) findViewById(R.id.pref_time);
        comments = (EditText) findViewById(R.id.comments);
        contact = getIntent().getStringExtra("contact");
        contact_no.setText(contact);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order_form, menu);
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

    public void next (View view) {
        order_info.clear();

        if (name.getText().toString().length() == 0) {
            name.setError(Html.fromHtml("<font color='white'>Name is Required</font>"));
            name.requestFocus();
        }
        else if (contact_no.getText().toString().length() == 0) {
            contact_no.setError(Html.fromHtml("<font color='white'>Contact is Required</font>"));
            contact_no.requestFocus();
        }
        else if (pref_time.getText().toString().length() == 0) {
            pref_time.setError(Html.fromHtml("<font color='white'>Enter Your Prefered Time</font>"));
            pref_time.requestFocus();
        }
        else if (address.getText().toString().length() == 0) {
            address.setError(Html.fromHtml("<font color='white'>Address is Required</font>"));
            address.requestFocus();
        }
        else {
            order_info.add(name.getText().toString());
            order_info.add(contact_no.getText().toString());
            order_info.add(pref_time.getText().toString());
            order_info.add(address.getText().toString());
            order_info.add(comments.getText().toString());
            Intent intent = new Intent(this, SelectServiceActivity.class);
            intent.putStringArrayListExtra("order_info", order_info);
            startActivity(intent);
        }

    }
}