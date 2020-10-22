package com.example.mywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Credit extends AppCompatActivity {

    EditText creditamount;
    EditText creditreason;
    DatabaseHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        creditamount= (EditText) findViewById(R.id.creditamount);
        creditreason= (EditText) findViewById(R.id.creditreason);
        dbHandler = new DatabaseHandler(this, null, null, 2);
        Button submit = (Button) findViewById(R.id.submitcredit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                dbHandler.addcredit(creditamount.getText().toString(),creditreason.getText().toString());
            }
        });

    }
}