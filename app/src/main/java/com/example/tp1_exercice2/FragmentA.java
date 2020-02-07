package com.example.tp1_exercice2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;


public class FragmentA extends Fragment {

    // déclaration des variables pour recevoir chaque élément du formulaire et créer une liste de membres
    private String formNom = null;
    private String formPrenom = null;
    private String formSexe = null;
    private String formFonction = null;
    private String formComment = null;
    private ArrayList<Membre> listMembre;

    // on récupère les références sur les différents champs du formulaire
    private TextInputEditText nomET;
    private TextInputEditText prenomET;
    private RadioGroup radioSexe;
    private AutoCompleteTextView spinnerFonctions;
    private TextInputEditText commentairesET;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // on récupère le ArrayList de l'activité principale
        Intent intent = getActivity().getIntent();
        listMembre = intent.getParcelableArrayListExtra("clé_listeMembres");

        // on vérifie si la liste est vide, si oui, on l'initialise
        if(listMembre == null) {
            listMembre = new ArrayList<Membre>();
        }

        // on inflate le layout de l'activité ajouter membres dans le fragment
        View view = inflater.inflate(R.layout.frag_ajout_membres, container, false);
        Activity activity = getActivity();

        // pour remplir la liste du spinner
        setAdapter(view, activity);

        setBouton(view);

        if(savedInstanceState != null) {
            remplirFormulaire(savedInstanceState);
        }

        return view;
    }

    private void remplirFormulaire(Bundle savedInstanceState) {
/*
        nomET.setText(savedInstanceState.getString("part_nom"));
        prenomET.setText(savedInstanceState.getString("part_prenom"));
        commentairesET.setText(savedInstanceState.getString("part_comment"));*/
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

/*        // on récupère les informations du formulaire non enregistrées pour les récupérer au retour
        outState.putString("part_nom", formNom);
        outState.putString("part_prenom", formPrenom);
        outState.putString("part_sexe", formSexe);
        outState.putString("part_fonction", formFonction);
        outState.putString("part_comment", formComment);*/
    }

    // fonction qui remplit le spinner
    private void setAdapter(View v, Activity activity) {

        final String[] postes = new String[]{"Enseignant", "Étudiant", "Ingénieur", "Retraité", "Autre"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.item_menu_fonction, postes);

        //AutoCompleteTextView menuFonctions = v.findViewById(R.id.fragA_menuFonction);  ???? problème ici quand saisit texte dans la boîte
        AutoCompleteTextView menuFonctions = v.findViewById(R.id.fragA_menuFonction);
        menuFonctions.setAdapter(adapter);
    }

    // fonction pour réagir au clic sur le bouton "envoyer"
    public void setBouton(View v) {

/*        // on récupère les références sur les différents champs du formulaire
        final TextInputEditText nomET = v.findViewById(R.id.fragA_nom);
        final TextInputEditText prenomET = v.findViewById(R.id.fragA_prenom);
        final RadioGroup radioSexe = v.findViewById(R.id.fragA_radioSexe);
        final AutoCompleteTextView spinnerFonctions = v.findViewById(R.id.fragA_menuFonction);
        final TextInputEditText commentairesET = v.findViewById(R.id.fragA_commentaires);
        Button button = v.findViewById(R.id.fragA_bouton_envoyer);*/


        // on récupère les références sur les différents champs du formulaire
        nomET = v.findViewById(R.id.fragA_nom);
        prenomET = v.findViewById(R.id.fragA_prenom);
        radioSexe = v.findViewById(R.id.fragA_radioSexe);
        spinnerFonctions = v.findViewById(R.id.fragA_menuFonction);
        commentairesET = v.findViewById(R.id.fragA_commentaires);
        button = v.findViewById(R.id.fragA_bouton_envoyer);

        // ajout d'un écouteur d'événement sur le bouton Envoyer
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*                // déclaration des variables String pour recevoir chaque élément du formulaire
                String formNom = null;
                String formPrenom = null;
                String formSexe = null;
                String formFonction = null;
                String formComment = null;*/


                // vérification de la sélection du bouton radio ??? problème de pas capable de getText()???
                int choixSexe = radioSexe.getCheckedRadioButtonId();

                if (choixSexe == R.id.fragA_radioFemme) {
                    formSexe = "Femme";
                }
                else if (choixSexe == R.id.fragA_radioHomme) {
                    formSexe = "Homme";
                }
                else formSexe = null;


                // vérification que les champs sont tous remplis pour éviter des nullpointer exceptions
                if(nomET != null && !TextUtils.isEmpty(nomET.getText())) {
                    formNom = nomET.getText().toString();
                }

                if(prenomET != null && !TextUtils.isEmpty(prenomET.getText())) {
                    formPrenom = prenomET.getText().toString();
                }

                if(spinnerFonctions != null && !TextUtils.isEmpty(spinnerFonctions.getText())) {
                    formFonction = spinnerFonctions.getText().toString();
                }

                if(commentairesET != null && !TextUtils.isEmpty(commentairesET.getText())) {
                    formComment = commentairesET.getText().toString();
                }


                Membre premierMembre = new Membre("Demers", "Jacques", "Homme", "Entraîneur", "Jacques Demers a peut-être gagné la coupe Stanley, mais il ne savait pas lire. Ça ne l'a d'ailleurs pas empêché de devenir sénateur, et depuis, on attend plus ou moins patiemment l'annonce de sa mort.");

                Membre deuxMembre = new Membre("Lacerte-Coulee", "Lyne", "Femme", "Autre", "Jacques Demers a peut-être gagné la coupe Stanley, mais il ne savait pas lire. Ça ne l'a d'ailleurs pas empêché de devenir sénateur, et depuis, on attend plus ou moins patiemment l'annonce de sa mort.");

                //List<Membre> listMembre = new ArrayList<Membre>();
                //listMembre = new ArrayList<Membre>();

                // remplacer Test pour fonction!!!???
                //listMembre.add(new Membre(formNom, formPrenom, formSexe, formFonction, formComment));

                listMembre.add(premierMembre);
                listMembre.add(deuxMembre);
                listMembre.add(premierMembre);

                Intent intent = new Intent(getActivity(), MainActivity.class);

                // j'ai fait un cast sur listMembre, car la classe Membre implements Parcelable (comme dans les notes de cours)
                // et la méthode putParcelableArrayListExtra requiert un ArrayList qui extends Parcelable comme argument
                intent.putParcelableArrayListExtra("clé_listeMembres", (ArrayList<? extends Parcelable>) listMembre);
                startActivity(intent);


            }
        });

    }
}