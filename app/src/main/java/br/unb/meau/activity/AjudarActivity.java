package br.unb.meau.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import br.unb.meau.R;
import br.unb.meau.adapter.CardAnimalAdapter;
import br.unb.meau.model.Animal;

public class AjudarActivity extends AppCompatActivity {
    private RecyclerView recyclerAnimal;
    private List<Animal> cardsAnimais = new ArrayList<>();
    private CardAnimalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajudar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ajudar");
        toolbar.setBackgroundResource(R.color.colorAmarelo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Coloca o nome na acton bar
        //getSupportActionBar().setTitle("Ajudar");
        recyclerAnimal = findViewById(R.id.recyclerAnimal);

        //Definindo layout do Animal
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerAnimal.setLayoutManager(layoutManager);

        //Definindo adaptador
        carregarCards();
        adapter = new CardAnimalAdapter(cardsAnimais, this);
        recyclerAnimal.setAdapter(adapter);

    }

    public void carregarCards() {

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}
