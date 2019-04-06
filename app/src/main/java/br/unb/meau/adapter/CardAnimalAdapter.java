package br.unb.meau.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.unb.meau.R;
import br.unb.meau.model.Animal;

public class CardAnimalAdapter extends RecyclerView.Adapter<CardAnimalAdapter.AnimalViewHolder> {
    private List<Animal> animais;
    public CardAnimalAdapter(List<Animal> listaAnimais) {
        this.animais = listaAnimais ;
    }

    @NonNull
        @Override
        public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_animal,viewGroup, false);
            return new AnimalViewHolder(itemLista);
        }

        @Override
        public void onBindViewHolder(@NonNull AnimalViewHolder animalViewHolder, int i) {
            Animal animal = animais.get(i);
            animalViewHolder.textNomeAnimal.setText(animal.getNome());
            animalViewHolder.textIdade.setText(animal.getIdade());
            animalViewHolder.textPorte.setText(animal.getPorte());
            animalViewHolder.textEspecie.setText(animal.getEspecie());
            animalViewHolder.textLocalizacao.setText(animal.getLocalizacao());
            animalViewHolder.imageAnimal.setImageResource(animal.getImagem());

        }

        @Override
        public int getItemCount() {
            return animais.size();
        }

        public class AnimalViewHolder extends RecyclerView.ViewHolder{

            private TextView textNomeAnimal;
            private ImageView imageAnimal;
            private TextView textLocalizacao;
            private TextView textEspecie;
            private TextView textPorte;
            private TextView textIdade;

            public AnimalViewHolder(@NonNull View itemView) {
                super(itemView);
                textNomeAnimal = itemView.findViewById(R.id.textNomeAnimal);
                imageAnimal = itemView.findViewById(R.id.imageAnimal);
                textLocalizacao = itemView.findViewById(R.id.textLocalização);
                textEspecie = itemView.findViewById(R.id.textEspecie);
                textPorte = itemView.findViewById(R.id.textPorte);
                textIdade = itemView.findViewById(R.id.textIdade);


            }
        }

}