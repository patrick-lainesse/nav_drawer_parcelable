package com.example.tp1_exercice2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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


public class FragmentA extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // on crée un ArrayList pour stocker les membres créés mais non encore enregistrés au fichier
        ArrayList<Membre> listeTemp = new ArrayList<Membre>();

        // on inflate le layout de l'activité ajouter membres dans le fragment
        View view = inflater.inflate(R.layout.frag_ajout_membres, container, false);
        Activity activity = getActivity();

        // pour remplir la liste du spinner
        setAdapter(view, activity);


        String test = this.getArguments().getString("testMain");
        Toast.makeText(getActivity(), test, Toast.LENGTH_LONG).show();



        setBouton(view);        // bundle???

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("stringA", "Youppi");
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

        // on récupère les références sur les différents champs du formulaire
        final TextInputEditText nomET = v.findViewById(R.id.fragA_nom);
        final TextInputEditText prenomET = v.findViewById(R.id.fragA_prenom);
        final RadioGroup radioSexe = v.findViewById(R.id.fragA_radioSexe);
        final AutoCompleteTextView spinnerFonctions = v.findViewById(R.id.fragA_menuFonction);
        final TextInputEditText commentairesET = v.findViewById(R.id.fragA_commentaires);
        Button button = v.findViewById(R.id.fragA_bouton_envoyer);

        // ajout d'un écouteur sur le spinner ?????


        // ajout d'un écouteur d'événement sur le bouton Envoyer
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // déclaration des variables String pour recevoir chaque élément d'un objet membre
                String formNom = null;
                String formPrenom = null;
                String formSexe = null;
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

                if(commentairesET != null && !TextUtils.isEmpty(commentairesET.getText())) {
                    formComment = commentairesET.getText().toString();
                }

                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("testString", formNom);
                startActivity(intent);

                // bon code ici???
                //Toast.makeText(getActivity(), formNom + " " + formPrenom + " " + formSexe + " " + formComment, Toast.LENGTH_SHORT).show();


    /*            listeTemp.add(new Membre(nomET.getText().toString(),
                                    prenomET.getText().toString(),
                                    boutonSexe.getText().toString(),
                                    spinnerFonctions.getText().toString(),
                                    commentairesET.getText().toString()));
*/
/*
                Toast.makeText(getActivity(), nomET.getText().toString() +
                        prenomET.getText().toString() +
                        Integer.toString(choixSexe) +
                        //testc +
                        //boutonSexe.getText().toString() +
                        //spinnerFonctions.getText().toString() +
                        commentairesET.getText().toString(), Toast.LENGTH_LONG).show();

*/

            }
        });

    }
}