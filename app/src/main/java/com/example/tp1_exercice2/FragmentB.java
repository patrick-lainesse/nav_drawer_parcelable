package com.example.tp1_exercice2;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class FragmentB extends Fragment {


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_enregistrer, container, false);

        setMessage(view);
        setCardMembre(view);

        return view;

    }

    private void setCardMembre(View view) {

        // on récupère une référence sur le recycler view pour y afficher
        // les infos des membres à ajouter au .txt
        RecyclerView recMembres = view.findViewById(R.id.fragB_recycler);
        //recMembres.setHasFixedSize(true);                           // essayer d'enlever ça???
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        //llm.setOrientation(LinearLayoutManager.VERTICAL);

        String test = this.getArguments().getString("testMain");
        List<Membre> listMembre = this.getArguments().getParcelableArrayList("cle_listeMain");

        //Membre premierMembre = new Membre("Demers", "Jacques", "Homme", "Entraîneur", "Jacques Demers a peut-être gagné la coupe Stanley, mais il ne savait pas lire. Ça ne l'a d'ailleurs pas empêché de devenir sénateur, et depuis, on attend plus ou moins patiemment l'annonce de sa mort.");

        //List<Membre> listMembre = new ArrayList<>();

/*        listMembre.add(premierMembre);
        listMembre.add(premierMembre);
        listMembre.add(premierMembre);
        listMembre.add(premierMembre);
        listMembre.add(premierMembre);*/

        MembresAdapter mAdapter = new MembresAdapter(listMembre);

        recMembres.setAdapter(mAdapter);
        recMembres.setLayoutManager(llm);

    }

    private void setMessage(View v) {

        TextView textView = v.findViewById(R.id.fragB_message);
        /*TextView textView2 = v.findViewById(R.id.fragB_message2);
        String test = this.getArguments().getString("testMain");*/

        textView.setText("Liste des membres à ajouter");
        // ??? à ajouter à String.xml

    }
}
