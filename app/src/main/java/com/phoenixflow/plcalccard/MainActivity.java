package com.phoenixflow.plcalccard;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //Array to hold count of Discs
    TextView out25;
    TextView out20;
    TextView out15;
    TextView out10;
    TextView out5;
    TextView out2_5;
    TextView out1_25;
    TextView out0_5;
    TextView out0_25;
    CheckBox collars;
    int discCount[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    private TextView infoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        out25 = (TextView) findViewById(R.id.textView2);
        out20 = (TextView) findViewById(R.id.textView4);
        out15 = (TextView) findViewById(R.id.textView6);
        out10 = (TextView) findViewById(R.id.textView8);
        out5 = (TextView) findViewById(R.id.textView10);
        out2_5 = (TextView) findViewById(R.id.textView12);
        out1_25 = (TextView) findViewById(R.id.textView14);
        out0_5 = (TextView) findViewById(R.id.textView14);
        out0_25 = (TextView) findViewById(R.id.textView16);
        collars = (CheckBox) findViewById(R.id.checkBox);

        infoView = (TextView) findViewById(R.id.infoView);

        countReset();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info: {
                //Perform your click operation
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Info on PL Equipment");
                builder.setMessage("Standard Barbell Weight = 20KG\nUSAPL Collar Weight = 5KG (Total)");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        infoView.setVisibility(View.VISIBLE);
                    }
                });
                builder.show();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void test(View view){
        EditText inputWeight = (EditText) findViewById(R.id.weight);
        String rawWT = inputWeight.getText().toString();
        if (rawWT.matches("")){
            Toast.makeText(this, "Please enter a valid weight", Toast.LENGTH_SHORT).show();
            return;
        }
        else
            countReset();
        Arrays.fill(discCount, 0);
        calculate(Double.parseDouble(inputWeight.getText().toString()));
    }

    public void calculate(double wt){
        if (wt <= 25){
            Toast.makeText(this, "No Plates", Toast.LENGTH_SHORT).show();
            return;
        }
        if (wt % 0.5 != 0){
            Toast.makeText(this, "Weight must be a multiple of 0.5KG", Toast.LENGTH_SHORT).show();
            return;
        }
        double netWT = wt - 20;
        if (collars.isChecked()){
            netWT -= 5;
        }

        while (netWT > 0){
            if (netWT - 25*2 >= 0){
                discCount[0]++;
                netWT = netWT - 25*2;
            }
            else if (netWT - 20*2 >= 0){
                discCount[1]++;
                netWT = netWT - 20*2;
            }
            else if (netWT - 15*2 >= 0){
                discCount[2]++;
                netWT = netWT - 15*2;
            }
            else if (netWT - 10*2 >= 0){
                discCount[3]++;
                netWT = netWT - 10*2;
            }
            else if (netWT - 5*2 >= 0){
                discCount[4]++;
                netWT = netWT - 5*2;
            }
            else if (netWT - 2.5*2 >= 0){
                discCount[5]++;
                netWT = netWT - 2.5*2;
            }
            else if (netWT - 1.25*2 >= 0){
                discCount[6]++;
                netWT = netWT - 1.25*2;
            }
            else if (netWT - 0.5*2 >= 0){
                discCount[7]++;
                netWT = netWT - 0.5*2;
            }
            else if (netWT - 0.25*2 >= 0){
                discCount[8]++;
                netWT = netWT - 0.25*2;
            }
        }
        out25.setText("x".concat(Integer.toString(discCount[0])));
        out20.setText("x".concat(Integer.toString(discCount[1])));
        out15.setText("x".concat(Integer.toString(discCount[2])));
        out10.setText("x".concat(Integer.toString(discCount[3])));
        out5.setText("x".concat(Integer.toString(discCount[4])));
        out2_5.setText("x".concat(Integer.toString(discCount[5])));
        out1_25.setText("x".concat(Integer.toString(discCount[6])));
        out0_5.setText("x".concat(Integer.toString(discCount[7])));
        out0_25.setText("x".concat(Integer.toString(discCount[8])));
    }

    public void countReset(){
        out25.setText("x0");
        out20.setText("x0");
        out15.setText("x0");
        out10.setText("x0");
        out5.setText("x0");
        out2_5.setText("x0");
        out1_25.setText("x0");
        out0_5.setText("x0");
        out0_25.setText("x0");
    }
}

