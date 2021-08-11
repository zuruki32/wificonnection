package com.example.connection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {
Button connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialwork();
        connect();

    }

    private void connect() {
        connection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, connection.class));

            }
        });

    }

    private void initialwork() {
        connection = (Button) findViewById(R.id.connection);
    }
}
