package com.example.mywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button credit = (Button) findViewById(R.id.credit);
        Button debit = (Button) findViewById(R.id.debit);
        credit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //open credit page
                Intent intent = new Intent(getApplicationContext(), Credit.class);
                startActivity(intent);
            }
        });
        debit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //open debit page
                Intent intent = new Intent(getApplicationContext(), Debit.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onResume()
    {  // this code will be run After a pause OR at startup
        super.onResume();
        //Refreshing amount here
        dbHandler = new DatabaseHandler(MainActivity.this, null, null, 2);
        String amount = dbHandler.getTotal();
        TextView statementText = (TextView) findViewById(R.id.statement);
        statementText.setText("Current amount in wallet is ₹ "+amount);
    }
}