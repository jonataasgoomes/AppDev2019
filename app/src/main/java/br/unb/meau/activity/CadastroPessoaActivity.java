package br.unb.meau.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import br.unb.meau.R;
import br.unb.meau.helper.ConfigFirebase;
import br.unb.meau.model.Usuario;

public class CadastroPessoaActivity extends AppCompatActivity {

    private EditText campoNome, campoIdade, campoEmail, campoEstado,
            campoCidade, campoEndereco, campoTelefone,
            campoUsuario, campoSenha, campoSenhaConf;
    private Button btnCadastrar, btnPic;

    private Usuario usuario;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);
        //Inicializa e personaliza a toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cadastro Pessoal");
        toolbar.setBackgroundResource(R.color.colorBtnLogin);

        //Inicializar campos
        initCampos();


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textNome = campoNome.getText().toString();
                String textUsuario = campoUsuario.getText().toString();
                String textEmail = campoEmail.getText().toString();
                String textSenha = campoSenha.getText().toString();
                String textSenhaConf = campoSenhaConf.getText().toString();

                if (!textNome.isEmpty()){
                    if (!textUsuario.isEmpty()){
                        if (!textEmail.isEmpty()){
                            if (!textSenha.isEmpty()){
                                if (!textSenhaConf.isEmpty()){
                                    if (textSenha.equals(textSenhaConf)){


                                        usuario = new Usuario();
                                        usuario.setNome(textNome);
                                        usuario.setUsuario(textUsuario);
                                        usuario.setEmail(textEmail);
                                        usuario.setSenha(textSenha);
                                        cadastrar(usuario);




                                    }else {
                                        Toast.makeText(CadastroPessoaActivity.this,
                                                "Senhas diferentes",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    Toast.makeText(CadastroPessoaActivity.this,
                                            "Confirme a Senha",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(CadastroPessoaActivity.this,
                                        "Preencha a Senha",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(CadastroPessoaActivity.this,
                                    "Preencha o E-mail",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(CadastroPessoaActivity.this,
                                "Preencha o Nome de usuário",
                                Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(CadastroPessoaActivity.this,
                            "Preencha o nome",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        //Cadastro de um usuario



    }
    //Método responsável por cadastrar o usuário
    public void cadastrar(Usuario usuario){
        auth = ConfigFirebase.getFirebaseAuth();
        auth.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(
                this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(CadastroPessoaActivity.this,
                                    "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();

                        }else{
                            String error = "";
                            try{
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                error = "Digite uma senha mais forte!";
                            }catch (FirebaseAuthInvalidCredentialsException e){
                                error = "Digite um e-mail válido";
                            }catch (FirebaseAuthUserCollisionException e){
                                error = "email já cadastrado";
                            }catch (Exception e){
                                error = "ao cadastrar usuário:" + e.getMessage();
                                e.printStackTrace();
                            }
                            Toast.makeText(CadastroPessoaActivity.this,
                                    "Erro: "+ error, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );


    }

    //Metodo para iniciar os componentes
    public void initCampos(){
        campoNome = findViewById(R.id.editTextNome);
        campoIdade = findViewById(R.id.editTextIdade);
        campoEmail = findViewById(R.id.editTextEmail);
        campoEstado = findViewById(R.id.editTextEstado);
        campoCidade = findViewById(R.id.editTextCidade);
        campoEndereco = findViewById(R.id.editTextEndereco);
        campoTelefone = findViewById(R.id.editTextTelefone);
        campoUsuario = findViewById(R.id.editTextNomeUsuario);
        campoSenha = findViewById(R.id.editTextPass);
        campoSenhaConf = findViewById(R.id.editTextPassConf);
        btnCadastrar = findViewById(R.id.buttonFazerCadastro);
        btnPic = findViewById(R.id.buttonAddPic);
    }


}
