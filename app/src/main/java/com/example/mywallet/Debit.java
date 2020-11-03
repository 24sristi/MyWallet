package com.example.mywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Debit extends AppCompatActivity {

    EditText debitamount;
    EditText debitreason;
    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit);
        debitamount= (EditText) findViewById(R.id.debitamount);
        debitreason= (EditText) findViewById(R.id.debitreason);
        dbHandler = new DatabaseHandler(this, null, null, 2);
        Button submit = (Button) findViewById(R.id.submitdebit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                dbHandler.addEntry("debit",debitamount.getText().toString(),debitreason.getText().toString());
                debitamount.setText("");
                debitreason.setText("");
            }
        });
    }
}