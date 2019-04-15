package br.unb.meau.activity;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import br.unb.meau.R;
import br.unb.meau.fragment.CadastroAdocaoFragment;
import br.unb.meau.fragment.CadastroAjudaFragment;
import br.unb.meau.fragment.CadastroApadrinhamentoFragment;
import br.unb.meau.model.Animal;
import br.unb.meau.viewmodel.CadastroDoAnimalViewModel;

public class CadastroDoAnimalActivity extends AppCompatActivity implements CadastroApadrinhamentoFragment.OnFragmentInteractionListener, CadastroAdocaoFragment.OnFragmentInteractionListener, CadastroAjudaFragment.OnFragmentInteractionListener {
    private Button adoptButton, helpButton, provideButton, saveButton;
    CadastroDoAnimalViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_do_animal);

        adoptButton = findViewById(R.id.adopt_button);
        helpButton = findViewById(R.id.help_button);
        provideButton = findViewById(R.id.provide_button);
        saveButton = findViewById(R.id.save_button);
        mViewModel = ViewModelProviders.of(this).get(CadastroDoAnimalViewModel.class);
        setupAdoptButton();
        setupProvideButton();
        setupHelpButton();
        setupSaveButton();
        adoptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.setAdopting(!mViewModel.getAdopting());
                setupAdoptButton();
                setupProvideButton();
                setupSaveButton();
            }
        });
        provideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.setProviding(!mViewModel.getProviding());
                setupAdoptButton();
                setupProvideButton();
                setupSaveButton();
            }
        });
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.setHelping(!mViewModel.getHelping());
                setupHelpButton();
                setupSaveButton();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAnimal();
            }
        });
        // Coloca o nome na acton bar
        //getSupportActionBar().setTitle("Cadastro do animal");
    }
    private void setupAdoptButton () {
        if (mViewModel.getAdopting()) {
            enableButton(adoptButton);
            selectButton(adoptButton);
            displayFragment(R.id.adopt_fragment, true);
        } else if (mViewModel.getProviding()) {
            unselectButton(adoptButton);
            disableButton(adoptButton);
            displayFragment(R.id.adopt_fragment, false);
        } else {
            unselectButton(adoptButton);
            enableButton(adoptButton);
            displayFragment(R.id.adopt_fragment, false);
        }
    }
    private void setupProvideButton () {
        if (mViewModel.getProviding()) {
            enableButton(provideButton);
            selectButton(provideButton);
            displayFragment(R.id.provide_fragment, true);
        } else if (mViewModel.getAdopting()) {
            unselectButton(provideButton);
            disableButton(provideButton);
            displayFragment(R.id.provide_fragment, false);
        } else {
            unselectButton(provideButton);
            enableButton(provideButton);
            displayFragment(R.id.provide_fragment, false);
        }
    }
    private void setupHelpButton () {
        if (mViewModel.getHelping()) {
            selectButton(helpButton);
            displayFragment(R.id.help_fragment, true);
        } else {
            unselectButton(helpButton);
            displayFragment(R.id.help_fragment, false);
        }
    }
    private void displayFragment (int frag, boolean val) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(frag);

        if (val) {
            fm.beginTransaction()
                    .show(fragment)
                    .commit();
        } else {
            fm.beginTransaction()
                    .hide(fragment)
                    .commit();
        }
    }
    private void setupSaveButton () {
        if(mViewModel.getAdopting()) {
            saveButton.setText(getResources().getText(R.string.save_animal_adopt));
            saveButton.setVisibility(View.VISIBLE);
        } else if(mViewModel.getProviding()) {
            saveButton.setText(getResources().getText(R.string.save_animal_provide));
            saveButton.setVisibility(View.VISIBLE);
        } else if(mViewModel.getHelping()) {
            saveButton.setText(getResources().getText(R.string.save_animal_help));
            saveButton.setVisibility(View.VISIBLE);
        } else {
            saveButton.setVisibility(View.GONE);
        }
    }

    private void saveAnimal () {
        EditText nameText, diseaseText, aboutText;
        RadioGroup species, gender, size, age;
        int[] temperArray = {R.id.temper_box_1, R.id.temper_box_2, R.id.temper_box_3, R.id.temper_box_4, R.id.temper_box_5, R.id.temper_box_6};
        int[] healthArray = {R.id.health_box_1, R.id.health_box_2, R.id.health_box_3, R.id.health_box_4};

        nameText = findViewById(R.id.nameText);
        diseaseText = findViewById(R.id.diseaseText);
        aboutText = findViewById(R.id.aboutText);

        species = findViewById(R.id.species_group);
        gender = findViewById(R.id.gender_group);
        size = findViewById(R.id.size_group);
        age = findViewById(R.id.age_group);

        ArrayList<String> temper = new ArrayList<String>();
        ArrayList<String> health = new ArrayList<String>();

        for(int i = 0; i < 6; i++) {
            CheckBox view = findViewById(temperArray[i]);
            if (view.isChecked()) {
                temper.add(view.getText().toString());
            }
        }

        for(int i = 0; i < 4; i++) {
            CheckBox view = findViewById(healthArray[i]);
            if (view.isChecked()) {
                health.add(view.getText().toString());
            }
        }

        new Animal(
                nameText.getText().toString(),
                getCheckedOption(age),
                getCheckedOption(species),
                "",
                getCheckedOption(size),
                temper,
                health,
                diseaseText.getText().toString(),
                aboutText.getText().toString(),
                R.drawable.cachorro1
        );
    }
    private String getCheckedOption (RadioGroup group) {
        int id = group.getCheckedRadioButtonId();
        RadioButton checked = findViewById(id);
        return checked.getText().toString();
    }
    private void disableButton (Button btn) {
        btn.setEnabled(false);
        btn.setBackgroundColor(getResources().getColor(R.color.color_unselected_button));
        btn.setTextColor(getResources().getColor(R.color.color_disabled_text));
    }
    private void enableButton (Button btn) {
        btn.setEnabled(true);
        btn.setBackgroundColor(getResources().getColor(R.color.color_unselected_button));
        btn.setTextColor(getResources().getColor(R.color.color_enabled_text));
    }
    private void selectButton (Button btn) {
        btn.setBackgroundColor(getResources().getColor(R.color.color_selected_button));
    }
    private void unselectButton (Button btn) {
        btn.setBackgroundColor(getResources().getColor(R.color.color_unselected_button));
    }

    @Override
    public void onCadastroAdocaoFragmentInteraction(Uri uri) {

    }

    @Override
    public void onCadastroAjudaFragmentInteraction(Uri uri) {

    }

    @Override
    public void onCadastroApadrinhamentoFragmentInteraction(Uri uri) {

    }
}
