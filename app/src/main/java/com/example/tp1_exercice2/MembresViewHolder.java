package com.example.tp1_exercice2;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;


// classe qui place les infos des membres dans les Cards à ajouter au RecyclerView

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
