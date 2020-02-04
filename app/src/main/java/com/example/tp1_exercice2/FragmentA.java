package com.example.tp1_exercice2;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FragmentA extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // on crée un ArrayList pour stocker les membres créés mais non encore enregistrés au fichier ???? éliminer si je décide simplement de renvoyer les membres à l'autre activité
        //ArrayList<Membre> listeTemp = new ArrayList<Membre>();

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

        // conversion de postes en list pour utiliser la fonction contains
        //final List<String> tabPostes = Arrays.asList(postes);
        menuFonctions.setAdapter(adapter);
    }

    // fonction pour réagir au clic sur le bouton "envoyer"
    public void setBouton(View v) {

        // on récupère les références sur les différents champs du formulaire
        final TextInputEditText nomET = v.findViewById(R.id.fragA_nom);
        final TextInputEditText prenomET = v.findViewById(R.id.fragA_prenom);
        final RadioGroup radioSexe = v.findViewById(R.id.fragA_radioSexe);
        final AutoCompleteTextView spinnerFonctions = v.findViewById(R.id.fragA_menuFonction);
        final TextInputEditText commentairesET = v.findViewById(R.id.fragA_commentaires);
        Button button = v.findViewById(R.id.fragA_bouton_envoyer);

        final ArrayList<Membre> listeTemp = new ArrayList<Membre>();

        // ajout d'un écouteur sur le spinner ?????


        // ajout d'un écouteur d'événement sur le bouton Envoyer
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // déclaration des variables String pour recevoir chaque élément d'un objet membre
                String formNom = null;
                String formPrenom = null;
                String formSexe = null;
                String formFonction = null;
                String formComment = null;

                // vérification de la sélection du bouton radio ??? problème de pas capable de getText()???
                int choixSexe = radioSexe.getCheckedRadioButtonId();
                //RadioButton boutonSexe = v.findViewById(choixSexe);
                // if (selectedRadioButtonID != -1)
                //testc = boutonSexe.getText().toString();

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

                formFonction = spinnerFonctions.getText().toString();


                // vérification si le poste entré fait partie de la liste
                //    if(tabPostes.contains(formFonction)                                         ????? à ajouter, sinon peut entrer nquel poste

                if(commentairesET != null && !TextUtils.isEmpty(commentairesET.getText())) {
                    formComment = commentairesET.getText().toString();
                }

                //Toast.makeText(getActivity(), formNom + " " + formPrenom + " " + formSexe + " " + formFonction + " " + formComment, Toast.LENGTH_SHORT).show();

                if(formNom == null || formPrenom == null || formSexe == null || formFonction == null ||formComment == null) {
                    Toast.makeText(getActivity(), "Veuillez entrer toutes les cases correctement pour créer un nouveau membre.", Toast.LENGTH_LONG).show();
                }

                else{
                    listeTemp.add(new Membre(formNom, formPrenom, formSexe, formFonction, formComment));
                    Toast.makeText(getActivity(), "Le membre " + formPrenom + " " + formNom + " été correctement ajouté à la liste. "
                            + "Veuillez ne pas l'oublier de l'enregistrer dans le fichier .txt." + formFonction, Toast.LENGTH_LONG).show();
// enlever le formFonction à la fin du toast ???????
                }

                // on met l'arraylist dans un intent pour envoyer aux autres fragments???
                // on vérifie si un doublon ???
            }
        });

    }
}