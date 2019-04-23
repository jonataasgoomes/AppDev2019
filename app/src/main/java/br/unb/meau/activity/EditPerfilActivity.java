package br.unb.meau.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import br.unb.meau.R;
import br.unb.meau.helper.ConfigFirebase;
import br.unb.meau.helper.UserFirebase;
import br.unb.meau.model.Usuario;

public class EditPerfilActivity extends AppCompatActivity {


    private Usuario usuarioLogado;
    private AlertDialog dialog;
    private ImageView imageEditPerfil, imagePerfil, imageMenu;
    private TextView textAlterarFoto;
    private TextInputEditText editNomeCompletoPerfil,
            editIdadePerfil,
            editEmailPerfil,
            editCidPerfil,
            editEndPerfil,
            editTelPerfil,
            editNomeUsuarioPerfil,
            editEstPerfil;
    private Button btnSalvarEdit;
    private static final int SELECTION_GALERY = 200;
    private StorageReference storageRef;
    private FirebaseFirestore dataBaseRef;
    private DocumentReference userRef;
    private String TAG = "Error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_perfil);
        usuarioLogado = UserFirebase.getAuthDadosUsuarioLogado();
        storageRef = ConfigFirebase.getFirebaseStorage();
        dataBaseRef = FirebaseFirestore.getInstance();
        userRef = dataBaseRef.collection("user").document(usuarioLogado.getId());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Editar Perfil");
        toolbar.setBackgroundResource(R.color.colorBtnLogin);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        //iniciando componentes;
        initCampos();

        //Dados do Usuário;
        if (!(UserFirebase.getUsuarioAtual() == null)) {
            editNomeCompletoPerfil.setText(usuarioLogado.getNome());
            editEmailPerfil.setText(usuarioLogado.getEmail());
            String caminhoFoto = usuarioLogado.getPicPath();
            if (caminhoFoto != null) {
                Uri url = Uri.parse(caminhoFoto);
                Glide.with(EditPerfilActivity.this)
                        .load(caminhoFoto)
                        .into(imageEditPerfil);
            } else {
                imageEditPerfil.setImageResource(R.drawable.user);
            }
        }


        //Salvar alterações
        btnSalvarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String nomeAtt = editNomeCompletoPerfil.getText().toString();
                String idadeAtt = editIdadePerfil.getText().toString();
                String cidadeAtt = editCidPerfil.getText().toString();
                String endAtt = editEndPerfil.getText().toString();
                String telAtt = editTelPerfil.getText().toString();
                String nomeUsuarioAtt = editNomeUsuarioPerfil.getText().toString();
                String estadoAtt = editEstPerfil.getText().toString();
                UserFirebase.attNomeUsuario(nomeAtt);

                //salvar no banco
                usuarioLogado.setNome(nomeAtt);
                usuarioLogado.setIdade(idadeAtt);
                usuarioLogado.setCidade(cidadeAtt);
                usuarioLogado.setEndereco(endAtt);
                usuarioLogado.setTelefone(telAtt);
                usuarioLogado.setUsuario(nomeUsuarioAtt);
                usuarioLogado.setEstado(estadoAtt);
                usuarioLogado.atualizar();
                Toast.makeText(getApplicationContext(), "Atualizado com sucesso", Toast.LENGTH_SHORT).show();


            }
        });

        //Configuração para mudar foto;
        textAlterarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (foto.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(foto, SELECTION_GALERY);

                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            carregamentoDialog("Carregando foto, aguarde!");
            Bitmap imagem = null;
            try {
                //Galeria de fotos
                switch (requestCode) {
                    case SELECTION_GALERY:
                        Uri localImagemSelect = data.getData();
                        imagem = MediaStore.Images.Media.getBitmap(getContentResolver(), localImagemSelect);
                        break;
                }

                //Carregar imagem
                if (imagem != null) {


                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    imagem.compress(Bitmap.CompressFormat.JPEG, 50, baos);
                    byte[] dadosImagem = baos.toByteArray();

                    //Salvar imagem no storage
                    StorageReference imgRef = storageRef.child("imagens")
                            .child("perfil")
                            .child(usuarioLogado.getId() + ".jpeg");
                    UploadTask uploadTask = imgRef.putBytes(dadosImagem);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EditPerfilActivity.this,
                                    "Erro ao carregar a imagem", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {


                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            //Recuperar local da foto
                            Task<Uri> url = taskSnapshot.getStorage().getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri url) {
                                            attPicUser(url);
                                        }
                                    });

                            Toast.makeText(EditPerfilActivity.this,
                                    "Sucesso ao carregar a imagem", Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    });
                    imageEditPerfil.setImageBitmap(imagem);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        recuperarDadosUsuario();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }

    private void attPicUser(Uri url) {
        //atualizar foto
        UserFirebase.attPicUser(url);
        //atualizar no firebase
        usuarioLogado.setPicPath(url.toString());
        usuarioLogado.atualizarFoto();
        Toast.makeText(EditPerfilActivity.this,
                "Foto atualizada", Toast.LENGTH_SHORT).show();
    }

    //Metodo para iniciar os componentes
    public void initCampos() {

        imageEditPerfil = findViewById(R.id.imageEditPerfil);
        textAlterarFoto = findViewById(R.id.textAlterarFoto);
        editNomeCompletoPerfil = findViewById(R.id.editTextNome);
        editIdadePerfil = findViewById(R.id.editTextIdade);
        editEmailPerfil = findViewById(R.id.editTextEmail);
        editEstPerfil = findViewById(R.id.editTextEstado);
        editCidPerfil = findViewById(R.id.editTextCidade);
        editEndPerfil = findViewById(R.id.editTextEndereco);
        editTelPerfil = findViewById(R.id.editTextTelefone);
        editNomeUsuarioPerfil = findViewById(R.id.editTextNomeUsuario);
        editEmailPerfil.setFocusable(false);
        btnSalvarEdit = findViewById(R.id.buttonSalvarEdit);
    }

    private void recuperarDadosUsuario() {
        userRef = dataBaseRef.collection("user").document(usuarioLogado.getId());
        userRef.addSnapshotListener(this,
                new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                        if (documentSnapshot.exists()) {
                            Usuario user = documentSnapshot.toObject(Usuario.class);
                            editNomeUsuarioPerfil.setText(user.getNome());
                            editIdadePerfil.setText(user.getIdade());
                            editNomeUsuarioPerfil.setText(user.getUsuario());
                            editEstPerfil.setText(user.getEstado());
                            editCidPerfil.setText(user.getCidade());
                            editEndPerfil.setText(user.getEndereco());
                            editTelPerfil.setText(user.getTelefone());
                            editNomeUsuarioPerfil.setText(user.getUsuario());
                        } else if (e != null) {
                            Log.w(TAG, "Got an exception");
                        }
                    }
                }
        );


    }

    private void carregamentoDialog(String titulo){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(titulo);
        alert.setCancelable(false);
        alert.setView(R.layout.carregamento);
        dialog = alert.create();
        dialog.show();

    }


}
