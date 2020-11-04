package edu.unicauca.practice_layout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScannerqrActivity extends AppCompatActivity {

    private TextView textQR;
    private Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scannerqr);

        textQR = (TextView)findViewById(R.id.textQr);

        Intent i = getIntent();
        datos = i.getExtras();
        textQR.setText(datos.getString("textqr"));
    }
}
