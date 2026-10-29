package com.example.listaisrael;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Los datos: al menos 8 elementos
        ArrayList<Freestyler> freestylers = new ArrayList<>();
        freestylers.add(new Freestyler("Wos", "Campeón · Temporada 1 (2018)", R.drawable.lapiz));
        freestylers.add(new Freestyler("Trueno", "Campeón · Temporada 2 (2019)", R.drawable.letras));
        freestylers.add(new Freestyler("Papo", "Subcampeón · Temporada 2 (2019)", R.drawable.mas));
        freestylers.add(new Freestyler("Stuart", "Campeón · Temporada 3 (2020/21)", R.drawable.vientoo));
        freestylers.add(new Freestyler("Papo", "Subcampeón · Temporada 3 (2020/21)", R.drawable.mas));
        freestylers.add(new Freestyler("Papo", "Campeón · Temporada 4 (2022/23)", R.drawable.lapiz));
        freestylers.add(new Freestyler("Mecha", "Subcampeón · Temporada 4 (2022/23)", R.drawable.vientoo));
        freestylers.add(new Freestyler("Larrix", "Campeón · Temporada 5 (2023)", R.drawable.letras));

        // 2. La lista: cómo se colocan las filas y quién las pinta
        RecyclerView rvLista = findViewById(R.id.rvLista);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvLista.setAdapter(new AdaptadorFreestylers(freestylers));
    }
}