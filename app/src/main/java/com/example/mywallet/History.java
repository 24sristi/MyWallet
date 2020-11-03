package com.example.mywallet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class History extends AppCompatActivity {
    DatabaseHandler dbHandler;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        dbHandler = new DatabaseHandler(this, null, null, 2);

        textView=(TextView) findViewById(R.id.textView);
        String dbstring = dbHandler.databaseToString();
        textView.setText(dbstring);
    }
}