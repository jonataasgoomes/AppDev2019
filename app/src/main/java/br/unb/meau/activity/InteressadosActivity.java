package br.unb.meau.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
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
import br.unb.meau.adapter.InteressadoAdapter;
import br.unb.meau.model.Usuario;

public class InteressadosActivity extends AppCompatActivity {
    private RecyclerView recyclerInteressado;
    private FirebaseFirestore db;
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interessados);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Interessados");
        toolbar.setBackgroundResource(R.color.colorAmarelo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        db = FirebaseFirestore.getInstance();

        recuperarinteressados();




    }

    public void recuperarinteressados() {
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<Usuario> usuarios = new ArrayList<>();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                usuarios.add( document.toObject(Usuario.class));
                            }
                            recyclerInteressado = findViewById(R.id.recyclerInteressado);
                            RecyclerView.LayoutManager layoutManager;
                            layoutManager = new GridLayoutManager(getApplicationContext(), 2);
                            recyclerInteressado.setLayoutManager(layoutManager);
                            InteressadoAdapter adapter = new InteressadoAdapter(usuarios,
                                    getApplicationContext());
                            recyclerInteressado.setAdapter(adapter);


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
