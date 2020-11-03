package edu.unicauca.practice_layout;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SubMenuActivity extends AppCompatActivity {

    private ListView listSubCategory;
    private TextView categories;
    private int positionMain;
    private Bundle datos;
    private String title;
    private TextView Subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submenu);

        listSubCategory = (ListView)findViewById(R.id.list_subcategory);
        categories = (TextView) findViewById(R.id.category);
        Subtitle = (TextView) findViewById(R.id.textSub);

        Intent i = getIntent();
        datos = i.getExtras();
        positionMain = datos.getInt("Category");
        String title=(datos.getString("nameCategory"));
        selection(positionMain);
    }

    private void selection (int posicion){

        if (posicion == 0){
            categories.setText(title);
            Subtitle.setText("Conductores Disponibles");
            String subCategory []= getResources().getStringArray(R.array.array_nombre_Conductores);
            ArrayAdapter <String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, subCategory);
            listSubCategory.setAdapter(arrayAdapter);
        }else if (posicion == 1){
            categories.setText(title);
            Subtitle.setText("Rutas Opcionales");
            //String subCategory []= getResources().getStringArray(R.array.array_Trigonometría);
            //ArrayAdapter <String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, subCategory);
            //listSubCategory.setAdapter(arrayAdapter);
        }else if(posicion == 2){
            categories.setText(title);
            Subtitle.setText("");
            String subCategory []= getResources().getStringArray(R.array.array_opciones);
            ArrayAdapter <String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, subCategory);
            listSubCategory.setAdapter(arrayAdapter);
        }
    }

}
