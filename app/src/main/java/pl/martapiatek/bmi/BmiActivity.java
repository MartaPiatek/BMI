package pl.martapiatek.bmi;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BmiActivity extends AppCompatActivity {

    private TextView txtViewBmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);




        txtViewBmi = findViewById(R.id.txtViewBmi);
        double bmi;

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            bmi = Double.valueOf(extras.getString("BMI"));
            txtViewBmi.setText("BMI = " + String.valueOf(bmi));

            if(bmi < 18.5)
                getWindow().getDecorView().setBackgroundColor(ContextCompat.getColor(BmiActivity.this, R.color.color1));

            else if(bmi >=18.5 && bmi <25)
                getWindow().getDecorView().setBackgroundColor(ContextCompat.getColor(BmiActivity.this, R.color.color2));
            else if(bmi >=25 )
                getWindow().getDecorView().setBackgroundColor(ContextCompat.getColor(BmiActivity.this, R.color.color3));



        }

    }
}

