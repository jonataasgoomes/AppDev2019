package br.unb.meau.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.unb.meau.R;

public class LoginActivity extends AppCompatActivity {
    private Button btnEntrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnEntrar = findViewById(R.id.buttonEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "LOGIN EM BREVE", Toast.LENGTH_SHORT).show();
            }
        });

        /*Logar usuário
        usuario.signInWithEmailAndPassword("jonataasgoomes@gmail.com","jr12345")
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Log.i("signIn", "Sucesso ao logar usuario");
                            }
                            else{
                                Log.i("signIn", "Erro ao logar usuario");
                            }
                        }
                    });*/

        /*Deslogar usuário
        usuario.signOut();*/

        /*Verificar usuario logado
        if (usuario.getCurrentUser() != null){
            Log.i("CreateUser", "Usuário logado!");
        }else
            Log.i("CreateUser", "Usuário deslogado");*/

        /*Cadastro de um usuario
        usuario.createUserWithEmailAndPassword("jonataasgoomes@gmail.com","jr12345")
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Log.i("CreateUser", "Sucesso ao cadastrar");
                                }
                                else{
                                    Log.i("CreateUser", "Erro ao cadastrar usuario");
                                }
                            }
                        });*/

    }

    public void btnCadastrar(View view ) {
        startActivity(new Intent(this, CadastroPessoaActivity.class));
    }
}
