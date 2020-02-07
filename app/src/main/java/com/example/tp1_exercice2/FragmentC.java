package com.example.tp1_exercice2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

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

        // va falloir remplacer ça par la lecture du txt
        //listMembre = this.getArguments().getParcelableArrayList("cle_listeMain");

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



        MembresAdapter mAdapter = new MembresAdapter(listMembre);

        recMembres.setAdapter(mAdapter);
        recMembres.setLayoutManager(llm);

    }
}
