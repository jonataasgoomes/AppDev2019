package br.unb.meau.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import br.unb.meau.R;
import br.unb.meau.helper.ConfigFirebase;
import br.unb.meau.helper.UserFirebase;
import br.unb.meau.model.Usuario;

public class AdicionarFotoActivity extends AppCompatActivity {
    private Usuario usuarioLogado;
    private StorageReference storageRef;
    private static final int SELECTION_GALERY = 200;
    private ImageView txtAlterarFoto;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_foto);
        usuarioLogado = UserFirebase.getAuthDadosUsuarioLogado();
        storageRef = ConfigFirebase.getFirebaseStorage();


        txtAlterarFoto = findViewById(R.id.txtAdicionarFoto);

        //Configuração para mudar foto;
        txtAlterarFoto.setOnClickListener(new View.OnClickListener() {
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
                            Toast.makeText(AdicionarFotoActivity.this,
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

                            Toast.makeText(AdicionarFotoActivity.this,
                                    "Sucesso ao carregar a imagem", Toast.LENGTH_SHORT).show();




                        }
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

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

    private void attPicUser(Uri url) {
        //atualizar foto
        UserFirebase.attPicUser(url);
        //atualizar no firebase
        usuarioLogado.setPicPath(url.toString());
        usuarioLogado.atualizarFoto();
        finish();
        Intent fim = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(fim);
        dialog.cancel();
        Toast.makeText(AdicionarFotoActivity.this,
                "Seja bem vindo ao MeaU", Toast.LENGTH_SHORT).show();
    }
}
