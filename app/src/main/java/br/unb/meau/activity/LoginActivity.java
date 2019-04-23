package br.unb.meau.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import br.unb.meau.R;
import br.unb.meau.helper.ConfigFirebase;
import br.unb.meau.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button btnEntrar;
    private Usuario usuario;
    private FirebaseAuth auth;
    private AlertDialog dialog;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UsuarioLogado();
        //configurando a barra de navegação
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");
        toolbar.setBackgroundResource(R.color.colorBtnLogin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById( R.id.editSenha);
        btnEntrar = findViewById(R.id.buttonEntrar);

        //botão entrada para o Login.
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pegando os campos para verificar se estão vazios
                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

               if (!textoEmail.isEmpty()){
                   if (!textoSenha.isEmpty()){

                       usuario = new Usuario();
                       usuario.setEmail(textoEmail);
                       usuario.setSenha(textoSenha);
                       carregamentoDialog("Realizando o login");
                       validarLogin();

                   }else
                       Toast.makeText(LoginActivity.this, "Preencha a senha!",
                               Toast.LENGTH_SHORT).show();
               }else
                   Toast.makeText(LoginActivity.this, "Preencha o email!",
                           Toast.LENGTH_SHORT).show();


            }
        });



    }


    public void btnCadastrar(View view ) {
        startActivity(new Intent(this, CadastroPessoaActivity.class));
    }

    public void validarLogin(){
        auth = ConfigFirebase.getFirebaseAuth();
        auth.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
                ).addOnCompleteListener
                (new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if ( task.isSuccessful() ){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    dialog.cancel();
                    Toast.makeText(LoginActivity.this, "Login realizado", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    String error = "";
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        dialog.cancel();
                        error = "Email ou senha incorretos";
                    }catch (FirebaseAuthInvalidUserException e){
                        dialog.cancel();
                        error = "Usuário não cadastrado";
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, error,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void UsuarioLogado(){
        auth = ConfigFirebase.getFirebaseAuth();
        auth.signOut();
        if (auth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }

    private void carregamentoDialog(String titulo) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(titulo);
        alert.setCancelable(false);
        alert.setView(R.layout.carregamento);
        dialog = alert.create();
        dialog.show();

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }
}
