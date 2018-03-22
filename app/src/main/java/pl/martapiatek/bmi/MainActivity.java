package pl.martapiatek.bmi;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextMass, editTextHeight;
    private Button btnCount;
    private TextView txtViewMass, txtViewHeigth, txtViewResult;

    private BMICounter mBmiCounter;
    double resultBmi;

    private String heightUnit;
    private String massUnit;

    private String[] heightUnits;
    private String[] massUnits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editTextHeight = findViewById(R.id.editTextHeight);
        editTextMass = findViewById(R.id.editTextMass);

        txtViewHeigth = findViewById(R.id.textViewHeight);
        txtViewMass = findViewById(R.id.textViewMass);
        txtViewResult = findViewById(R.id.textViewResult);

        btnCount = findViewById(R.id.btnCount);

        heightUnits = new String[]{"m", "in"};
        massUnits = new String[]{"kg", "lb"};

        heightUnit = heightUnits[0];
        massUnit = massUnits[0];
        txtViewHeigth.setText("Height ["+heightUnit+"]");
        txtViewMass.setText("Mass ["+massUnit+"]");




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

                if(heightUnit.equals(heightUnits[0]) && massUnit.equals(massUnits[0])){

                    mBmiCounter = new BMICounterForKgM(mass, height);
                }

                if(heightUnit.equals(heightUnits[1]) && massUnit.equals(massUnits[1])){

                    mBmiCounter = new BMICounterForLbIn(mass, height);
                }


                if (!mBmiCounter.isMassValid()) {
                    editTextMass.setError(getString(R.string.err_wrongMass));
                }
                resultBmi = mBmiCounter.count();

                txtViewResult.setText("BMI = " + String.valueOf(resultBmi));


                View view = getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });

        txtViewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this
                        , BmiActivity.class);
                i.putExtra("BMI", String.valueOf(resultBmi));
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_jednostka) {



            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                           ListView modeListView = new ListView(this);
                           String[] modes = new String[]{"Jednostki metryczne: kilogram i metr", "Jednostki imperialne: funt i cal"};
                            ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this,
                                            android.R.layout.simple_list_item_1, android.R.id.text1, modes);
                            modeListView.setAdapter(modeAdapter);
                            builder.setView(modeListView);
                            final Dialog dialog = builder.create();
                            dialog.show();
                            modeListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                                                                 @Override
                                                         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                                                                        if(position == 0){
                                                                                       Toast.makeText(MainActivity.this, "Wybrano jednostki: kilogram i metr " , Toast.LENGTH_SHORT).show();

                                                                                       heightUnit = heightUnits[0];
                                                                                       massUnit = massUnits[0];

                                                                                            txtViewHeigth.setText("Height ["+heightUnit+"]");
                                                                                            txtViewMass.setText("Mass ["+massUnit+"]");
                                                                                            editTextHeight.setText("");
                                                                                            editTextMass.setText("");
                                                                                            txtViewResult.setText("");


                                                                                           }else {
                                                                                       Toast.makeText(MainActivity.this, "Wybrano jednostki: funt i cal ", Toast.LENGTH_SHORT).show();
                                                                                            heightUnit = heightUnits[1];
                                                                                            massUnit = massUnits[1];

                                                                                            txtViewHeigth.setText("Height ["+heightUnit+"]");
                                                                                            txtViewMass.setText("Mass ["+massUnit+"]");
                                                                                            editTextHeight.setText("");
                                                                                            editTextMass.setText("");
                                                                                            txtViewResult.setText("");
                                                                                   }

                                                                                   dialog.dismiss();
                                                                           }
                                                    }
                                   );


            return true;
        }

        if (id == R.id.action_zapis) {
            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("Height", editTextHeight.getText().toString());
            editor.putString("Mass", editTextMass.getText().toString());
            editor.putString("BMI", String.valueOf(resultBmi));
            editor.putString("MassUnit", massUnit);
            editor.putString("HeightUnit", heightUnit);
            editor.commit();

            Toast.makeText(MainActivity.this, "Zapisano dane", Toast.LENGTH_SHORT).show();

            return true;
        }

        if (id == R.id.action_odczyt) {

            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            editTextMass.setText(sharedPref.getString("Mass", "" ) );
            editTextHeight.setText(sharedPref.getString("Height", "" ) );
            txtViewResult.setText("BMI = " + sharedPref.getString("BMI", "" ) );
            massUnit = sharedPref.getString("MassUnit", "" );
            heightUnit = sharedPref.getString("HeightUnit", "" );

            txtViewHeigth.setText("Height ["+heightUnit+"]");
            txtViewMass.setText("Mass ["+massUnit+"]");

            Toast.makeText(MainActivity.this, "Odczytano dane", Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
