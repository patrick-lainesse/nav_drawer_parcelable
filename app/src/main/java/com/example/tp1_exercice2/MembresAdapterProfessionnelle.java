package com.example.tp1_exercice2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class MembresAdapterProfessionnelle extends RecyclerView.Adapter<MembresAdapterProfessionnelle.MembresViewHolder> {

    private List<Membre> listeMembres;

    // constructeur pour l'adapteur
    public MembresAdapterProfessionnelle(List<Membre> listeMembres) {
        this.listeMembres = listeMembres;
    }

    @NonNull
    @Override
    public MembresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View viewCarte = LayoutInflater.from(parent.getContext()).inflate(R.layout.membre_professionnelle, parent, false);

        return new MembresViewHolder(viewCarte);
    }

    @Override
    public void onBindViewHolder(@NonNull MembresViewHolder holder, int position) {
        Membre membre = listeMembres.get(position);
        holder.cardNom.setText(membre.getPrenom() + " " + membre.getNom());
        holder.cardFonction.setText(membre.getFonction());

    }

    @Override
    public int getItemCount() {
        return listeMembres.size();
    }



    public class MembresViewHolder extends RecyclerView.ViewHolder {

        protected MaterialTextView cardNom;
        protected MaterialTextView cardFonction;

        public MembresViewHolder(@NonNull View itemView) {
            super(itemView);

            cardNom = itemView.findViewById(R.id.cardF_nom);
            cardFonction = itemView.findViewById(R.id.cardF_fonction);

        }
    }
}
