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

    // LinearLayout layout; // nécessaire ????

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_enregistrer, container, false);

        setMessage(view);


        // on récupère une référence sur le recycler view pour y afficher
        // les infos des membres à ajouter au .txt
        RecyclerView recMembres = view.findViewById(R.id.fragB_recycler);
        //recMembres.setHasFixedSize(true);                           // essayer d'enlever ça???
        //LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
// ou getActivity ??????
        //llm.setOrientation(LinearLayoutManager.VERTICAL);
        //recMembres.setLayoutManager(llm);         // déplacée plus bas



        Membre premierMembre = new Membre("Demers", "Jacques", "Homme", "Entraîneur", "Jacques Demers a peut-être gagné la coupe Stanley, mais il ne savait pas lire. Ça ne l'a d'ailleurs pas empêché de devenir sénateur, et depuis, on attend plus ou moins patiemment l'annonce de sa mort.");

        List<Membre> listMembre = new ArrayList<>();

        listMembre.add(premierMembre);
        listMembre.add(premierMembre);
        listMembre.add(premierMembre);
        listMembre.add(premierMembre);
        listMembre.add(premierMembre);

        MembresAdapter mAdapter = new MembresAdapter(listMembre);

        recMembres.setAdapter(mAdapter);

        recMembres.setLayoutManager(llm);


        //ViewGroup monVG = view.findViewById(R.id.layout_enregistrer);




        /*View premMembre = LayoutInflater.from(view.getContext()).inflate(R.layout.membre_card, null);
        monVG.addView(premMembre);

*//*
        View premMembre2 = LayoutInflater.from(view.getContext()).inflate(R.layout.membre_card, null);
        monVG.addView(premMembre2);
*//*

        MaterialTextView tv1 = monVG.findViewById(R.id.card_nom);
        MaterialTextView tv2 = monVG.findViewById(R.id.card_sexe);
        MaterialTextView tv3 = monVG.findViewById(R.id.card_fonction);
        MaterialTextView tv4 = monVG.findViewById(R.id.card_commentaires);


        tv1.setText("Jacques Demers");
        tv2.setText("Homme");
        tv3.setText("Entraîneur");
        tv4.setText("Jacques Demers a peut-être gagné la coupe Stanley, mais il ne savait pas lire. Ça ne l'a d'ailleurs pas empêché de devenir sénateur, et depuis, on attend plus ou moins patiemment l'annonce de s amort");*/

        // voir p.33 des adapters, il faut faire un simple adapter pour ce projet


        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.item_menu_fonction, postes);

        //layout = view.findViewById(R.id.layout_enregistrer);
        //setCardMembre(view);


        //MaterialCardView card = getActivity().findViewById(R.id.frabB_card);

        /*fonctionne
        MaterialTextView tvNom = view.findViewById(R.id.card_nom);
        tvNom.setText("Le nom");*/


/*        MaterialCardView card = new MaterialCardView(getActivity());
        //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //card.setLayoutParams(params);

        TextView tv = new TextView(getActivity().getApplicationContext());
        //tv.setLayoutParams(params);
        String test = "test";
        tv.setText(test);

        card.addView(tv);
        layout.addView(card);*/

        return view;

    }

/*    private void setCardMembre(View v) {

        MaterialCardView card = new MaterialCardView(v.getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        card.setLayoutParams(params);


        TextView tv = new TextView(v.getContext());
        tv.setLayoutParams(params);
        String test = "test";
        tv.setText(test);

        card.addView(tv);


        layout.addView(card);
    }*/

    private void setMessage(View v) {

        TextView textView = v.findViewById(R.id.fragB_message);
        TextView textView2 = v.findViewById(R.id.fragB_message2);
        String test = this.getArguments().getString("testMain");

        textView.setText("Voici la liste des membres à ajouter");
        textView2.setText(test);

    }
}
