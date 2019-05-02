package br.unb.meau.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.unb.meau.R;
import br.unb.meau.activity.PerfilActivity;
import br.unb.meau.model.Usuario;

public class InteressadoAdapter extends RecyclerView.Adapter<InteressadoAdapter.InteressadoViewHolder> {
    private Context context;
    private List<Usuario> usuarios;

    public InteressadoAdapter(List<Usuario> usuarios, Context context ) {
        this.context = context;
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public InteressadoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(context);
        view = mInflater.inflate(R.layout.card_interessado, viewGroup,false);
        return new InteressadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final InteressadoViewHolder interessadoViewHolder, int i) {
        interessadoViewHolder.nome.setText(usuarios.get(i).getNome());
        interessadoViewHolder.idade.setText(usuarios.get(i).getIdade()+" anos");
        Uri uriFotoAnimal = Uri.parse(usuarios.get(i).getPicPath());
        Glide.with(context).load(uriFotoAnimal).into(interessadoViewHolder.imgPerfil);

        interessadoViewHolder.imgPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cardPerfil = new Intent(v.getContext(), PerfilActivity.class);
                cardPerfil.putExtra("perfilSelecionado",usuarios.get(interessadoViewHolder.getAdapterPosition()));
                context.startActivity(cardPerfil);
            }
        });


    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public static class InteressadoViewHolder extends RecyclerView.ViewHolder {
        TextView nome, idade;
        ImageView imgPerfil;


        public InteressadoViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.nomeInteressado);
            idade = itemView.findViewById(R.id.idadeInteressado);
            imgPerfil = itemView.findViewById(R.id.imgInteressado);

        }
    }
}
