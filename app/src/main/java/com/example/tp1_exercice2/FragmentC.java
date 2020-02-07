package com.example.tp1_exercice2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
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

    private List<Membre> listMembre;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragc_lister, container, false);

        lireFichier(view);
        //setTable(view);

        return view;
    }

    private void lireFichier(View view) {

        // va falloir remplacer ça par la lecture du txt ??????
        listMembre = this.getArguments().getParcelableArrayList("cle_listeMain");

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
            setMessage(view);
        } else {
            setTable(view);
        }


    }

    private void setMessage(View view) {

        MaterialTextView tv_listeVide = view.findViewById(R.id.fragC_msg_cache);
        tv_listeVide.setText(R.string.erreur_txt);
        tv_listeVide.setVisibility(View.VISIBLE);

    }

    private void setTable(View view) {

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
        mAdapter = new MembresAdapterTable(listMembre);

        recMembres.setAdapter(mAdapter);
        recMembres.setLayoutManager(llm);

    }
}
