package com.example.tp1_exercice2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MembresAdapter extends RecyclerView.Adapter<MembresAdapter.MembresViewHolder> {

    private List<Membre> listeMembres;

    // constructeur pour l'adapteur
    public MembresAdapter(List<Membre> listeMembres) {
        this.listeMembres = listeMembres;
    }

    @NonNull
    @Override
    public MembresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View viewCarte = LayoutInflater.from(parent.getContext()).inflate(R.layout.membre_card, parent, false);

        return new MembresViewHolder(viewCarte);
    }

    @Override
    public void onBindViewHolder(@NonNull MembresViewHolder holder, int position) {
        Membre membre = listeMembres.get(position);
        holder.cardNom.setText(membre.getPrenom() + " " + membre.getNom());
        holder.cardSexe.setText(membre.getSexe());
        holder.cardFonction.setText(membre.getFonction());
        holder.cardCommentaires.setText(membre.getCommentaires());

    }

    @Override
    public int getItemCount() {
        return listeMembres.size();
    }



    public class MembresViewHolder extends RecyclerView.ViewHolder {

        protected MaterialTextView cardNom;
        protected MaterialTextView cardSexe;
        protected MaterialTextView cardFonction;
        protected MaterialTextView cardCommentaires;

        public MembresViewHolder(@NonNull View itemView) {
            super(itemView);

            cardNom = itemView.findViewById(R.id.card_nom);
            cardSexe = itemView.findViewById(R.id.card_sexe);
            cardFonction = itemView.findViewById(R.id.card_fonction);
            cardCommentaires = itemView.findViewById(R.id.card_commentaires);

        }
    }
}
