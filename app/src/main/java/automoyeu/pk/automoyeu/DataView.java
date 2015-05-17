package automoyeu.pk.automoyeu;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DataView extends ActionBarActivity {

    private ArrayList<String> order_info = new ArrayList<String>();
    private ArrayList<String> services = new ArrayList<String>();
    private float scale;
    private String[] labels = {"Name:  ", "Contact:  ", "Prefered Time:  ", "Address:  ", "Comments:  "};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);

        order_info = getIntent().getStringArrayListExtra("order_info");
        services = getIntent().getStringArrayListExtra("selected_services");
        scale = getResources().getDisplayMetrics().density;
        int padding_in_px = (int) (10 * scale + 0.5f);

        LinearLayout layout = (LinearLayout) findViewById(R.id.view_data);

        TextView head_text_view = (TextView) getLayoutInflater().inflate(R.layout.text_view, null);
        View horizontal_line = new View(this);
        String ser_head = "Required Service(s)";
        SpannableString ss = new SpannableString(ser_head);
        ss.setSpan(new TextAppearanceSpan(getApplicationContext(), R.style.services_head), 0, ser_head.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        head_text_view.setText(ss, TextView.BufferType.SPANNABLE);
        head_text_view.setPadding(0, padding_in_px, 0, padding_in_px);
        horizontal_line.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
        horizontal_line.setBackgroundColor(getResources().getColor(R.color.app_color));



        for(int i=0; i<order_info.size(); i++) {
            TextView tv = (TextView) getLayoutInflater().inflate(R.layout.text_view, null);
            View line = new View(this);
            line.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
            line.setBackgroundColor(getResources().getColor(R.color.app_color));
            String str = labels[i]+order_info.get(i);
            SpannableString text = new SpannableString(str);
            text.setSpan(new TextAppearanceSpan(getApplicationContext(), R.style.label), 0, labels[i].length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setSpan(new TextAppearanceSpan(getApplicationContext(), R.style.text), labels[i].length(), str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setText(text, TextView.BufferType.SPANNABLE);
            tv.setPadding(0, padding_in_px, 0, padding_in_px);
            layout.addView(tv);
            layout.addView(line);
        }

        layout.addView(head_text_view);
        layout.addView(horizontal_line);

        for(int i=0; i<services.size(); i++) {
            TextView tv = (TextView) getLayoutInflater().inflate(R.layout.text_view, null);
            View line = new View(this);
            line.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
            line.setBackgroundColor(getResources().getColor(R.color.app_color));
            String label = (i+1)+".  ";
            String str = label+services.get(i);
            SpannableString text = new SpannableString(str);
            text.setSpan(new TextAppearanceSpan(getApplicationContext(), R.style.label), 0, label.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            text.setSpan(new TextAppearanceSpan(getApplicationContext(), R.style.services_text), label.length(), str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv.setText(text, TextView.BufferType.SPANNABLE);
            tv.setPadding(0, padding_in_px, 0, padding_in_px);
            layout.addView(tv);
            layout.addView(line);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_data_view, menu);
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

    public void placeOrder(View view) {
        int width_in_px = (int) (250 * scale + 0.5f);
        View popupConfirm = getLayoutInflater().inflate(R.layout.popup_confirm, null);
        final PopupWindow popupWindowConfirm = new PopupWindow(
                popupConfirm,
                width_in_px,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);
        Button btnCancel = (Button)popupConfirm.findViewById(R.id.cancel);
        Button btnConfirm = (Button)popupConfirm.findViewById(R.id.confirm);

        btnCancel.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowConfirm.dismiss();
            }
        });

        btnConfirm.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindowConfirm.dismiss();
                int width_in_px = (int) (250 * scale + 0.5f);
                View popupSuccess = getLayoutInflater().inflate(R.layout.popup_success, null);
                final PopupWindow popupWindowSuccess = new PopupWindow(
                        popupSuccess,
                        width_in_px,
                        LinearLayout.LayoutParams.WRAP_CONTENT, true);
                Button btnOk = (Button)popupSuccess.findViewById(R.id.btnOk);
                btnOk.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindowSuccess.dismiss();
                        Intent intent = new Intent(DataView.this, SelectMainActivity.class);
                        DataView.this.startActivity(intent);
                        finish();
                    }
                });

                String[] TO = {"abbasaliraana@gmail.com"};
                //String[] CC = {"12beseaali@seecs.edu.pk"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("message/rfc822");
                String msg = "";
                for(int i=0; i<order_info.size();i++) {
                    msg = msg + labels[i]+order_info.get(i)+"\n";
                }
                msg = msg + "Required Service(s):\n";
                for(int i=0; i<services.size();i++) {
                    msg = msg + (i+1) +".  " + services.get(i)+"\n";
                }

                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                //emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Auto Moyeu (New Order)");
                emailIntent.putExtra(Intent.EXTRA_TEXT, msg);

                try {
                    SmsManager sms = SmsManager.getDefault();
                    msg = "\nAuto Moyeu (New Order)\n" + msg;
                    ArrayList<String> parts = sms.divideMessage(msg);
                    sms.sendMultipartTextMessage("+923217684390", null, parts, null, null);
                    Toast.makeText(getApplicationContext(), "Sending SMS", Toast.LENGTH_LONG).show();
                    Intent send_mail = Intent.createChooser(emailIntent, "Send Mail");

                    PackageManager packageManager = getPackageManager();
                    List<ResolveInfo> activities = packageManager.queryIntentActivities(send_mail, 0);
                    boolean isIntentSafe = activities.size() > 0;

                    if (isIntentSafe) {
                        Intent intent = new Intent(DataView.this, SelectMainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        DataView.this.startActivity(intent);
                        startActivity(send_mail);
                    }
                }
                catch (Exception e) {
                    //Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }

                //popupWindowSuccess.showAtLocation(popupSuccess, Gravity.CENTER, 0, 0);
            }
        });

        popupWindowConfirm.showAtLocation(popupConfirm, Gravity.CENTER, 0, 0);
    }

    public void edit(View view) {

    }
}