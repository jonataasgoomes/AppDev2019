package br.unb.meau.adapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.unb.meau.R;
import br.unb.meau.model.Animal;


public class CardAnimalAdapter extends RecyclerView.Adapter<CardAnimalAdapter.AnimalViewHolder> {
    private Context context;
    private List<Animal> animais;


    public CardAnimalAdapter (List<Animal> listaAnimais, final Context context) {
        this.animais = listaAnimais ;
        this.context = context;
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
            animalViewHolder.imagemAnimal.setImageResource(animal.getImagem());
            animalViewHolder.imagemAnimal.setBackgroundResource(animal.getImagem());
        }
        @Override
        public int getItemCount() {
            return animais.size();
        }

        public class AnimalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private TextView textNomeAnimal;
            private ImageView imagemAnimal;
            private TextView textLocalizacao;
            private TextView textEspecie;
            private TextView textPorte;
            private TextView textIdade;
            private Button  buttonCurtir;

            public AnimalViewHolder(@NonNull View itemView) {

                super(itemView);
                textNomeAnimal = itemView.findViewById(R.id.textNomeAnimal);
                imagemAnimal = itemView.findViewById(R.id.imageAnimal);
                textLocalizacao = itemView.findViewById(R.id.textLocalização);
                textEspecie = itemView.findViewById(R.id.textEspecie);
                textPorte = itemView.findViewById(R.id.textPorte);
                textIdade = itemView.findViewById(R.id.textIdade);
                buttonCurtir = itemView.findViewById(R.id.buttonCurtir);
//                //Chamando o perfil atráves da Imagem no card.
//                imagemAnimal.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Toast.makeText(v.getContext(),"PERFIL EM BREVE", Toast.LENGTH_SHORT).show();
//                    }
//                });
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
