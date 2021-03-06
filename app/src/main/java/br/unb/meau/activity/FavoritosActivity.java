package br.unb.meau.activity;

        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.firestore.FirebaseFirestore;
        import com.google.firebase.firestore.QueryDocumentSnapshot;
        import com.google.firebase.firestore.QuerySnapshot;

        import java.util.ArrayList;
        import java.util.List;

        import br.unb.meau.R;
        import br.unb.meau.adapter.CardAnimalAdapter;
        import br.unb.meau.model.Animal;

public class FavoritosActivity extends AppCompatActivity {
    private RecyclerView recyclerAnimal;
    private FirebaseFirestore db;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Favoritos");
        toolbar.setBackgroundResource(R.color.colorAmarelo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Configurar RecyclerView
        // Configurar RecyclerView
        db = FirebaseFirestore.getInstance();


        //Definindo layout do Animal
        recuperarAnimais();


    }

    public void recuperarAnimais() {
        db.collection("animals")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<Animal> cardsAnimal = new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                cardsAnimal.add( document.toObject(Animal.class));
                            }
                            recyclerAnimal = findViewById(R.id.recyclerAnimal);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerAnimal.setLayoutManager(layoutManager);
                            CardAnimalAdapter adapter = new CardAnimalAdapter(cardsAnimal, getApplicationContext());
                            recyclerAnimal.setAdapter(adapter);

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}
