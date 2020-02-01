package com.example.tp1_exercice2;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.fragment.app.Fragment;


public class FragmentA extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // on inflate le layout de l'activité ajouter membres dans le fragment
        View view = inflater.inflate(R.layout.frag_ajout_membres, container, false);
        Activity activity = getActivity();

        // pour remplir la liste du spinner
        setAdapter(view, activity);

        return view;
    }

    private void setAdapter(View v, Activity activity) {

        final String[] postes = new String[]{"Enseignant", "Étudiant", "Ingénieur", "Retraité", "Autre"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.item_menu_fonction, postes);

        AutoCompleteTextView menuFonctions = v.findViewById(R.id.menu_fonction);
        menuFonctions.setAdapter(adapter);
    }
}