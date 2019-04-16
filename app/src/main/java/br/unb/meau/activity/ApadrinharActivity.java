package br.unb.meau.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.unb.meau.R;
import br.unb.meau.adapter.CardAnimalAdapter;
import br.unb.meau.model.Animal;

public class ApadrinharActivity extends AppCompatActivity {
    private RecyclerView recyclerAnimal;
    private List<Animal> cardsAnimais = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apadrinhar);

        recyclerAnimal = findViewById(R.id.recyclerAnimal);

        //Definindo layout do Animal
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerAnimal.setLayoutManager(layoutManager);

        //Definindo adaptador
        carregarCards();
        CardAnimalAdapter adapter = new CardAnimalAdapter(cardsAnimais,this);
        recyclerAnimal.setAdapter(adapter);
    }

    public void carregarCards() {
        Animal animal = new Animal(
                "STAR",
                "ADULTO",
                "MACHO",
                "SANTA MARIA - DF",
                "GRANDE",
                R.drawable.cachorro11);
        this.cardsAnimais.add(animal);

        animal = new Animal(
                "KITANNA",
                "ADULTO",
                "FEMÊA",
                "GAMA - DF",
                "GRANDE",
                R.drawable.cachorro12);
        this.cardsAnimais.add(animal);

        animal = new Animal(
                "THOR",
                "FILHOTE",
                "MACHO",
                "SOBRADINHO - DF",
                "GRANDE",
                R.drawable.cachorro13);
        this.cardsAnimais.add(animal);

        animal = new Animal(
                "CHETTOS",
                "ADULTO",
                "MACHO",
                "TAGUATINGA -DF",
                "GRANDE",
                R.drawable.cachorro14);
        this.cardsAnimais.add(animal);

    }
}


