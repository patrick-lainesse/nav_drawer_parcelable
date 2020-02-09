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
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;


// la même classe est utilisée pour toutes les fonctions qui nécessitent de lister, car seule une petite
// partie du code change selon le choix effectué sur le navigation drawer
public class FragmentC extends Fragment {

    private ArrayList<Membre> listMembre;
    private ArrayList<Membre> listChoix;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragc_lister, container, false);
        lireFichier(view);

        return view;

        // plante lorsque vide avec l'option Lister????
    }

    private void lireFichier(View view) {

        MaterialTextView titreMTV = view.findViewById(R.id.fragC_titre);

        // on récupère l'option qui a été sélectionnée sur le navigation drawer
        char choixDrawer = this.getArguments().getChar("char_choix");
        listChoix = new ArrayList<Membre>();
        listMembre = new ArrayList<Membre>();

        ObjectInputStream input;
        String nomFichier = "membres.txt";

        try {

            input = new ObjectInputStream(new FileInputStream(new File(new File(getActivity().getFilesDir(), "")+File.separator+nomFichier)));

            int nbMembres = input.readInt();

            for (int i=0; i<nbMembres; i++) {
                Membre membreLu = (Membre) input.readObject();
                listMembre.add(membreLu);
            }

            input.close();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            // mettre en string res ?????
            Toast.makeText(getActivity(), "Le fichier membres.txt n'existe pas", Toast.LENGTH_LONG).show();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Erreur d'entrée/sortie de fichier.", Toast.LENGTH_LONG).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // on vérifie si la liste est vide, si oui, on l'initialise
        if(listMembre == null) {
            listMembre = new ArrayList<Membre>();
            setMessage(view, Integer.toString(R.string.erreur_txt));
        }

        // transformer en switch?????
        if (choixDrawer == 'C'){
            setTable(view, listMembre);
            titreMTV.setText(getResources().getString(R.string.liste_des_membres));
            setMessage(view, ""); // getResources().getString(R.string.choix_sexe_afficher)
        } else if (choixDrawer == 'D') {
            setNomPrenom(view, listMembre);
            titreMTV.setText(R.string.rechercher_membre);
        } else if (choixDrawer == 'E') {
            titreMTV.setText(R.string.recherche_sexe);
            setMessage(view, getResources().getString(R.string.choix_sexe_afficher));
            setRadio(view);
        } else if (choixDrawer == 'F') {

            titreMTV.setText(R.string.liste_femmes_fonction);
            setMessage(view, ""); //getResources().getString(R.string.choix_sexe_afficher)
            for(int i=0; i<listMembre.size(); i++)
            {
                if(listMembre.get(i).getSexe().equals("Femme"))
                {
                    listChoix.add(listMembre.get(i));
                }
            }
            setProfessionnelle(view, listChoix);
        }


    }

    // fonction qui fait apparaître les text input entrer le nom à rechercher
    private void setNomPrenom(final View view, final ArrayList<Membre> listMembre) {

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

                listChoix.clear();

                if(nomCherche != null && prenomCherche != null) {
                    for (int i = 0; i < listMembre.size(); i++) {

                        if (listMembre.get(i).getNom().toUpperCase().equals(nomCherche.toUpperCase())) {
                            if (listMembre.get(i).getPrenom().toUpperCase().equals(prenomCherche.toUpperCase())) {
                                listChoix.add(listMembre.get(i));
                            }
                        }
                    }
                }

                // si on ne trouve pas, on affiche un message; sinon, on affiche les membres trouvés
                if(listChoix.size() == 0) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.membre_inexistant), Toast.LENGTH_LONG).show();
                }
                else {
                    setTable(view, listChoix);
                }
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
                listChoix.clear();

                if (choixSexe == R.id.fragE_radioFemme)
                {
                    for(int i=0; i<listMembre.size(); i++)
                    {
                        if(listMembre.get(i).getSexe().equals("Femme"))
                        {
                            listChoix.add(listMembre.get(i));
                        }
                    }
                } else
                    {
                        for(int i=0; i<listMembre.size(); i++)
                        {
                            if(listMembre.get(i).getSexe().equals("Homme"))
                            {
                                listChoix.add(listMembre.get(i));
                            }
                        }
                    }

                // si on ne trouve pas, on affiche un message; sinon, on affiche les membres trouvés
                if(listChoix.size() == 0) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.aucun_sexe), Toast.LENGTH_LONG).show();
                }
                else {
                    setTable(view, listChoix);
                }
            }
        });
    }

    private void setMessage(View view, String message) {

        MaterialTextView tv_listeVide = view.findViewById(R.id.fragC_msg_cache);
        tv_listeVide.setText(message);
        tv_listeVide.setVisibility(View.VISIBLE);

    }

    private void setTable(View view, ArrayList<Membre> liste) {

        // on récupère une référence sur le recycler view pour y afficher les infos des membres à ajouter au .txt
        RecyclerView recMembres = view.findViewById(R.id.fragC_recycler);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());

        // on crée un en-tête
        Membre membreEnTete = new Membre("Nom", "Prenom", "Sexe", "Fonction", "Commentaires");
        List<Membre> listEnTete = new ArrayList<>();

        listEnTete.add(membreEnTete);

        MembresAdapterTable mAdapter =  new MembresAdapterTable(listEnTete);
        recMembres.setAdapter(mAdapter);

        mAdapter = new MembresAdapterTable(liste);

        recMembres.setAdapter(mAdapter);
        recMembres.setLayoutManager(llm);
    }

    // méthode pour n'afficher que les noms des femmes et leur fonction
    private void setProfessionnelle(View view, ArrayList<Membre> liste) {

        view.findViewById(R.id.fragF_sexe_cache).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.fragF_commentaires_cache).setVisibility(View.INVISIBLE);

        // on récupère une référence sur le recycler view pour y afficher les infos des membres à ajouter au .txt
        RecyclerView recMembres = view.findViewById(R.id.fragC_recycler);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());

        // on crée un en-tête
        Membre membreEnTete = new Membre("Nom", "Prenom", "Sexe", "Fonction", "Commentaires");
        List<Membre> listEnTete = new ArrayList<>();

        listEnTete.add(membreEnTete);

        MembresAdapterProfessionnelle mAdapter =  new MembresAdapterProfessionnelle(listEnTete);
        recMembres.setAdapter(mAdapter);

        mAdapter = new MembresAdapterProfessionnelle(liste);

        recMembres.setAdapter(mAdapter);
        recMembres.setLayoutManager(llm);
    }
}