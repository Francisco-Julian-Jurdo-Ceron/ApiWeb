package edu.unicauca.practice_layout;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity{

    private ListView listMenu;
    private ImageButton scannerQr;
    private ImageButton fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String[] list = getResources().getStringArray(R.array.array_menu);
        listMenu = (ListView)findViewById(R.id.listM);
        scannerQr = (ImageButton)findViewById(R.id.bt_qr);
        fav=(ImageButton)findViewById(R.id.bt_fav);

        ArrayAdapter <String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, list);
        listMenu.setAdapter(arrayAdapter);

    }

}
