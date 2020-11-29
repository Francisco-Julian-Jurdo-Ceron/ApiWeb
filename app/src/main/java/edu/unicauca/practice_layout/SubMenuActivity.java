package edu.unicauca.practice_layout;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import edu.unicauca.api.ApiConductor;
import edu.unicauca.models.Conductor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubMenuActivity extends AppCompatActivity {

    private ListView listSubCategory;
    private TextView categories;
    private int positionMain;
    private Bundle datos;
    private String title;
    private TextView Subtitle;
    private ImageView imageConductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submenu);

        listSubCategory = (ListView)findViewById(R.id.list_subcategory);
        categories = (TextView) findViewById(R.id.category);
        Subtitle = (TextView) findViewById(R.id.textSub);
        imageConductor = (ImageView) findViewById(R.id.imageConductor);
        imageConductor.setVisibility(View.INVISIBLE);

        Intent i = getIntent();
        datos = i.getExtras();
        positionMain = datos.getInt("Category");
        title=(datos.getString("nameCategory"));

        if (positionMain == 1){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiConductor conductor = retrofit.create(ApiConductor.class);
            Call<Conductor> call = conductor.getData();
            call.enqueue(new Callback<Conductor>() {
                @Override
                public void onResponse(Call<Conductor> call, Response<Conductor> response) {
                    if(response.code() != 200) {
                        Subtitle.setText("check connection");
                    }
                    String data = "";
                    data = "\n Id: " + response.body().getId() +
                            "\n Nombre: " + response.body().getName() +
                            "\n Genero: " + response.body().getGender();
                    categories.setText(title);
                    Subtitle.append("Conductor");
                    Subtitle.append(data);
                    imageConductor.setVisibility(View.VISIBLE);
                    String urlImage = response.body().getImage();
                    Picasso.get()
                            .load(urlImage)
                            .placeholder(R.drawable.hukicon)
                            .into(imageConductor);
                }

                @Override
                public void onFailure(Call<Conductor> call, Throwable t) {

                }
            });

        }else if(positionMain == 2){
            categories.setText(title);
            Subtitle.setText("");
            String subCategory []= getResources().getStringArray(R.array.array_opciones);
            ArrayAdapter <String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, subCategory);
            listSubCategory.setAdapter(arrayAdapter);
        }
    }

}
