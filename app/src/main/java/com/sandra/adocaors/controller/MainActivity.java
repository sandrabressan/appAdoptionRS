package com.sandra.adocaors.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Toast;
import com.sandra.adocaors.model.DataClass;
import com.sandra.adocaors.R;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;
    DataClass androidData;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataList = new ArrayList<>();

        androidData = new DataClass("Canoas/RS", "É uma gata muito dócil e que gosta de receber carinho!", "Lilica", R.drawable.lilica);
        dataList.add(androidData);

        androidData = new DataClass("Porto Alegre/RS", "É uma gata  muito preguiçosa. Não se preocupe, não vai estragar o seu sofá!", "Mia", R.drawable.mia);
        dataList.add(androidData);

        androidData = new DataClass("Gramado/RS", "É um cachorro muito amigo e que gosta de brincar com crianças!", "Pinduca", R.drawable.pinduca);
        dataList.add(androidData);

        androidData = new DataClass("Caxias do Sul/RS", "Muito bom para vigiar o seu lar. Durma tranquilo!", "Ronda", R.drawable.ronda);
        dataList.add(androidData);

        androidData = new DataClass("Lajeado/RS", "Um cachorro muito fofo e que está querendo um lar!", "Ted", R.drawable.ted);
        dataList.add(androidData);

        androidData = new DataClass("Novo Hamburgo/RS", "Ele gosta bastante de brincar. Um lar com crianças seria muito bom!", "Tobi", R.drawable.tobi);
        dataList.add(androidData);

        adapter = new MyAdapter(MainActivity.this, dataList);
        recyclerView.setAdapter(adapter);
    }

    private void searchList(String text){
        List<DataClass> dataSearchList = new ArrayList<>();
        for (DataClass data : dataList){
            if (data.getDataLocal().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()){
            Toast.makeText(this, "Não Encontrado", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }
    }
}