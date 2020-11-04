package edu.unicauca.practice_layout;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private ListView listMenu;
    private ImageButton scannerQr;
    private Button buttonInicio;
    private TextView labelRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] list = getResources().getStringArray(R.array.array_menu);
        listMenu = (ListView)findViewById(R.id.listM);
        scannerQr = (ImageButton)findViewById(R.id.bt_qr);
        buttonInicio = (Button)findViewById(R.id.btn_login);
        labelRegistro = (TextView)findViewById(R.id.labelRegistro);

        ArrayAdapter <String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, list);
        listMenu.setAdapter(arrayAdapter);

        listMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                if(position==0){
                    Intent i = new Intent(view.getContext(), MapsActivity.class);
                    startActivity(i);
                }else {

                    String listChoice = (listMenu.getItemAtPosition(position)).toString();
                    Intent i = new Intent(view.getContext(), SubMenuActivity.class);
                    i.putExtra("Category", position);
                    i.putExtra("nameCategory", listChoice);
                    startActivity(i);
                }
            }
        });

        buttonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), InicioSActivity.class);
                startActivity(i);
            }
        });

        labelRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        scannerQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQR();
            }
        });
    }

    private void addQR() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
            if (scanResult != null) {
                Intent i = new Intent(getApplicationContext(), ScannerqrActivity.class);
                i.putExtra("textqr", scanResult.getContents());
                startActivity(i);
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, R.string.scan_canceled, Toast.LENGTH_SHORT).show();
        }
    }

}
