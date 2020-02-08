package com.example.tp1_exercice2;

import android.opengl.Visibility;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FragmentC extends Fragment {

    private ArrayList<Membre> listMembre;
    private ArrayList<Membre> listChoix;
    private char choixDrawer;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragc_lister, container, false);

        lireFichier(view);
        //setTable(view);

        return view;
    }

    private void lireFichier(View view) {

        MaterialTextView titreMTV = view.findViewById(R.id.fragC_titre);

        // va falloir remplacer ça par la lecture du txt ??????
        listMembre = this.getArguments().getParcelableArrayList("cle_listeMain");
        choixDrawer = this.getArguments().getChar("char_choix");
        listChoix = null;

        try {


            ObjectInputStream fichierIn = new ObjectInputStream(new FileInputStream(new File("membres.txt")));

            /*
            // test mot simple réussi
            FileInputStream fis = view.getContext().openFileInput("membres.txt");
            BufferedReader r = new BufferedReader(new InputStreamReader(fis));
            String line = r.readLine();
            r.close();
            Toast.makeText(getActivity(), line, Toast.LENGTH_LONG).show();
*/

            // on récupère le nombre de membres dans le fichier
            int nbMembres = fichierIn.readInt();
            char separateur = fichierIn.readChar();

            for (int i=0; i<nbMembres; i++)
            {
                listMembre.add((Membre) fichierIn.readObject());
                separateur = fichierIn.readChar();
            }


            fichierIn.close();

        } catch (FileNotFoundException e)
        {
            Toast.makeText(getActivity(), "Le fichier membres.txt n'existe pas.", Toast.LENGTH_LONG).show();
        }
        catch (IOException e)
        {
            Toast.makeText(getActivity(), "Erreur d'entrée/sortie de fichier.", Toast.LENGTH_LONG).show();
        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // on vérifie si la liste est vide, si oui, on l'initialise
        if(listMembre == null) {
            listMembre = new ArrayList<Membre>();
            setMessage(view, Integer.toString(R.string.erreur_txt));
        } else if (choixDrawer == 'C'){
            setTable(view, listMembre);
            titreMTV.setText(R.string.liste_des_membres);
        } else if (choixDrawer == 'D') {
            setNomPrenom(view);
            titreMTV.setText(R.string.rechercher_membre);
        } else if (choixDrawer == 'E') {
            setRadio(view);
            titreMTV.setText(R.string.recherche_sexe);
            setMessage(view, Integer.toString(R.string.choix_sexe_afficher));
        } else if (choixDrawer == 'F') {

            titreMTV.setText(R.string.liste_femmes_fonction);
            for(int i=0; i<listMembre.size(); i++)
            {
                if(listMembre.get(i).getSexe() == "Femme")
                {
                    listChoix.add(listMembre.get(i));
                }
            }
            setTable(view, listChoix);
        }


    }

    // fonction qui fait apparaître les text input entrer le nom à rechercher
    private void setNomPrenom(final View view) {

        TextInputLayout layoutNom = view.findViewById(R.id.fragD_nom_layout);
        TextInputLayout layoutPrenom = view.findViewById(R.id.fragD_prenom_layout);
        MaterialButton boutonLister = view.findViewById(R.id.fragD_bouton);

        setMessage(view, getResources().getString(R.string.entrez_nom_cherche));

        layoutNom.setVisibility(View.VISIBLE);
        layoutPrenom.setVisibility(View.VISIBLE);
        boutonLister.setVisibility(View.VISIBLE);

        boutonLister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextInputEditText nomTIET = v.findViewById(R.id.fragD_nom);
                TextInputEditText prenomTIET = v.findViewById(R.id.fragD_prenom);

                String nomCherche = null;
                String prenomCherche = null;

                if(nomTIET != null && !TextUtils.isEmpty(nomTIET.getText())) {
                    nomCherche = nomTIET.getText().toString();
                }

                if(prenomTIET != null && !TextUtils.isEmpty(prenomTIET.getText())) {
                    prenomCherche = prenomTIET.getText().toString();
                }

                listChoix = null;

                for(int i=0; i<listMembre.size(); i++) {
                    if(listMembre.get(i).getNom().equals(nomCherche)) {
                        if(listMembre.get(i).getPrenom().equals(prenomCherche)) {
                            listChoix.add(listMembre.get(i));
                        }
                    }
                }

                setTable(view, listChoix);
            }
        });
    }

    // fonction qui fait apparaître les radiobutton pour le choix d'affichage par sexe
    private void setRadio(final View view) {

        final RadioGroup rg = view.findViewById(R.id.fragE_radioSexe);
        rg.setVisibility(View.VISIBLE);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int choixSexe = rg.getCheckedRadioButtonId();
                listChoix = new ArrayList<Membre>();

                if (choixSexe == R.id.fragA_radioFemme)
                {
                    for(int i=0; i<listMembre.size(); i++)
                    {
                        if(listMembre.get(i).getSexe() == "Femme")
                        {
                            listChoix.add(listMembre.get(i));
                        }
                    }
                } else
                    {
                        for(int i=0; i<listMembre.size(); i++)
                        {
                            if(listMembre.get(i).getSexe() == "Homme")
                            {
                                listChoix.add(listMembre.get(i));
                            }
                        }
                    }
                setTable(view, listChoix);
            }
        });
    }

    private void setMessage(View view, String message) {

        MaterialTextView tv_listeVide = view.findViewById(R.id.fragC_msg_cache);
        tv_listeVide.setText(message);
        tv_listeVide.setVisibility(View.VISIBLE);

    }

    private void setTable(View view, ArrayList<Membre> liste) {

        // on récupère une référence sur le recycler view pour y afficher
        // les infos des membres à ajouter au .txt
        RecyclerView recMembres = view.findViewById(R.id.fragC_recycler);
        //recMembres.setHasFixedSize(true);                           // essayer d'enlever ça???
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        //llm.setOrientation(LinearLayoutManager.VERTICAL);

        // on crée un en-tête
        Membre membreEnTete = new Membre("Nom", "Prenom", "Sexe", "Fonction", "Commentaires");
        List<Membre> listEnTete = new ArrayList<>();

        listEnTete.add(membreEnTete);

        MembresAdapterTable mAdapter =  new MembresAdapterTable(listEnTete);
        recMembres.setAdapter(mAdapter);
        //recMembres.setLayoutManager(llm);


        //MembresAdapterTable mAdapter = new MembresAdapterTable(listMembre);
        mAdapter = new MembresAdapterTable(liste);

        recMembres.setAdapter(mAdapter);
        recMembres.setLayoutManager(llm);

    }
}
