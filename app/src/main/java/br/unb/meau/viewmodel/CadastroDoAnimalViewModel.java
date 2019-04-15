package br.unb.meau.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.widget.Button;

import br.unb.meau.model.Animal;

public class CadastroDoAnimalViewModel extends ViewModel {
    private boolean adopting = false, helping = false, providing = false;

    public boolean getAdopting() {
        return adopting;
    }

    public void setAdopting(boolean adopting) {
        this.adopting = adopting;
    }

    public boolean getHelping() {
        return helping;
    }

    public void setHelping(boolean helping) {
        this.helping = helping;
    }

    public boolean getProviding() {
        return providing;
    }

    public void setProviding(boolean providing) {
        this.providing = providing;
    }
}
