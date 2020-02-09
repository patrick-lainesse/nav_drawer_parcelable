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

    private ArrayList<Membre> listMembre;

    // on récupère les références sur les différents champs du formulaire
    private TextInputEditText nomET;
    private TextInputEditText prenomET;
    private RadioGroup radioSexe;
    private AutoCompleteTextView spinnerFonctions;
    private TextInputEditText commentairesET;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // on récupère le ArrayList de l'activité principale
        Intent intent = getActivity().getIntent();
        listMembre = intent.getParcelableArrayListExtra("cle_listeMembres");

        // on vérifie si la liste est vide ou si elle contient déjà quelques membres
        // si vide, on l'initialise
        if(listMembre == null) {
            listMembre = new ArrayList<Membre>();
        }

        // on inflate le layout de l'activité ajouter membres dans le fragment
        View view = inflater.inflate(R.layout.frag_ajout_membres, container, false);
        Activity activity = getActivity();

        // pour remplir la liste du spinner
        setAdapter(view, activity);
        setBouton(view);

        return view;
    }

    // fonction qui remplit le spinner
    private void setAdapter(View v, Activity activity) {

        final String[] postes = new String[]{"Enseignant", "Étudiant", "Ingénieur", "Retraité", "Autre"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.item_menu_fonction, postes);

        AutoCompleteTextView menuFonctions = v.findViewById(R.id.fragA_menuFonction);
        menuFonctions.setAdapter(adapter);
    }

    // fonction pour réagir au clic sur le bouton "envoyer"
    private void setBouton(View v) {

        // on récupère les références sur les différents champs du formulaire
        nomET = v.findViewById(R.id.fragA_nom);
        prenomET = v.findViewById(R.id.fragA_prenom);
        radioSexe = v.findViewById(R.id.fragA_radioSexe);
        spinnerFonctions = v.findViewById(R.id.fragA_menuFonction);
        commentairesET = v.findViewById(R.id.fragA_commentaires);
        Button button = v.findViewById(R.id.fragA_bouton_envoyer);

        // ajout d'un écouteur d'événement sur le bouton Envoyer
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // déclaration des variables String pour recevoir chaque élément du formulaire
                String formNom = null;
                String formPrenom = null;
                String formSexe = null;
                String formFonction = null;
                String formComment = null;


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

                Membre troisMembre = new Membre("Saint-Pierre-Plamondon", "Louis-Paul", "Homme", "Politicien", "Jacques Demers a peut-être gagné la coupe Stanley, mais il ne savait pas lire. Ça ne l'a d'ailleurs pas empêché de devenir sénateur, et depuis, on attend plus ou moins patiemment l'annonce de sa mort.");

                // Ajout des membres créés à la liste ????
                //listMembre.add(new Membre(formNom, formPrenom, formSexe, formFonction, formComment));

                listMembre.add(premierMembre);
                listMembre.add(deuxMembre);
                listMembre.add(troisMembre);

                // if pas null... tester si un champ null et sinon afficher message d'erreur???????

                Intent intent = new Intent(getActivity(), MainActivity.class);

                // on passe la liste à l'activité principale et on la démarre
                intent.putParcelableArrayListExtra("cle_listeMembres", listMembre);
                startActivity(intent);
                Toast.makeText(getActivity(), getResources().getString(R.string.membre_ajoute_temp), Toast.LENGTH_LONG).show();
            }
        });

    }
}