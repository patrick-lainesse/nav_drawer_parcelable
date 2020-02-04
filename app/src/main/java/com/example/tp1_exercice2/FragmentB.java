package com.example.tp1_exercice2;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment {
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_ajout_membres, container, false);
        //Activity activity = getActivity();

        setMessage(view);

        return view;

    }

    private void setMessage(View v) {

        TextView textView = v.findViewById(R.id.fragB_message);


    }
}
