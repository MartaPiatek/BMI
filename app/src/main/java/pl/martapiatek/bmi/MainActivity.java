package pl.martapiatek.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextMass, editTextHeight;
    private Button btnCount;
    private TextView txtViewMass, txtViewHeigth, txtViewResult;

    private BMICounter mBmiCounter;
    double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextHeight = (EditText) findViewById(R.id.editTextHeight);
        editTextMass = (EditText) findViewById(R.id.editTextMass);

        txtViewHeigth = (TextView) findViewById(R.id.textViewHeight);
        txtViewMass = (TextView) findViewById(R.id.textViewMass);
        txtViewResult = (TextView) findViewById(R.id.textViewResult);

        btnCount = (Button) findViewById(R.id.btnCount);


        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double mass = 0 ;
                double height = 0;
                try {
                    mass = Double.valueOf(editTextMass.getText().toString());
                    height = Double.valueOf(editTextHeight.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();

                }


                mBmiCounter = new BMICounterForKgM(mass, height);

                if (!mBmiCounter.isMassValid()) {
                    editTextMass.setError(getString(R.string.err_wrongMass));
                }
                result = mBmiCounter.count();

                txtViewResult.setText(String.valueOf(result));


            }
        });

        txtViewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this
                        , BmiActivity.class);
                i.putExtra("BMI", String.valueOf(result));
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
