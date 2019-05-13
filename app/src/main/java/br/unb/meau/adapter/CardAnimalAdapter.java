package br.unb.meau.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import br.unb.meau.R;
import br.unb.meau.activity.AnimalPerfilActivity;
import br.unb.meau.model.Animal;


public class CardAnimalAdapter extends RecyclerView.Adapter<CardAnimalAdapter.AnimalViewHolder> {
    private Context context;
    private List<Animal> animais;


    public CardAnimalAdapter(List<Animal> listaAnimais, Context context) {
        this.animais = listaAnimais;
        this.context = context;
    }


    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemLista = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_animal,
                        viewGroup, false);
        return new AnimalViewHolder(itemLista);


    }

    @Override
    public void onBindViewHolder(@NonNull final AnimalViewHolder animalViewHolder, int i) {
        final Animal animal = animais.get(i);
        Uri uriFotoAnimal = Uri.parse(animal.getImagem());

        Glide.with(context).load(uriFotoAnimal).into(animalViewHolder.imagemAnimal);

        animalViewHolder.textNomeAnimal.setText(animal.getNome().toUpperCase());
        animalViewHolder.textIdade.setText(animal.getIdade().toUpperCase());
        animalViewHolder.textPorte.setText(animal.getPorte().toUpperCase());
        animalViewHolder.textSexo.setText(animal.getSexo().toUpperCase());
        animalViewHolder.textLocalizacao.setText(animal.getLocalizacao().toUpperCase());

        animalViewHolder.imagemAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cardPerfil = new Intent(v.getContext(), AnimalPerfilActivity.class);
                cardPerfil.putExtra("animalSelecionado",animais.get(animalViewHolder.getAdapterPosition()));
                context.startActivity(cardPerfil);

            }
        });

        animalViewHolder.buttonCurtir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animalViewHolder.buttonCurtir.setImageResource(R.drawable.ic_favorite_red_24dp);
                Toast.makeText(v.getContext(),  animal.getNome() + " foi curtido", Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return animais.size();
    }

    public class AnimalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textNomeAnimal;
        private ImageView imagemAnimal;
        private TextView textLocalizacao;
        private TextView textSexo;
        private TextView textPorte;
        private TextView textIdade;
        private ImageButton buttonCurtir;
        private LinearLayout qtdInteressados;

        public AnimalViewHolder(@NonNull View itemView) {

            super(itemView);
            qtdInteressados = itemView.findViewById(R.id.layoutCountInteressado);
            textNomeAnimal = itemView.findViewById(R.id.textNomeAnimal);
            imagemAnimal = itemView.findViewById(R.id.imageAnimal);
            textLocalizacao = itemView.findViewById(R.id.textLocalização);
            textSexo = itemView.findViewById(R.id.textSexo);
            textPorte = itemView.findViewById(R.id.textPorte);
            textIdade = itemView.findViewById(R.id.textIdade);
            buttonCurtir = itemView.findViewById(R.id.buttonCurtir);

                //Chamando o perfil atráves da Imagem no card.
//            imagemAnimal.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v,int position) {
//                    Animal cardSelecionado = cardsAnimais.get(position);
//                    Intent cardPerfil = new Intent(v.getContext(), AnimalPerfilActivity.class);
//                    cardPerfil.putExtra("animalSelecionado", cardSelecionado);
//                    context.startActivity(cardPerfil);
//
//
//                    Toast.makeText(v.getContext(),"PERFIL EM BREVE", Toast.LENGTH_SHORT).show();
//                }
//            });
//                //Implementação do botão curtir.
//                buttonCurtir.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(v.getContext(),"CURTIR EM BREVE" , Toast.LENGTH_SHORT).show();
//                    }
//                });

        }


        @Override
        public void onClick(View v) {

        }
    }

}
