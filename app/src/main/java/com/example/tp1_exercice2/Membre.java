package com.example.tp1_exercice2;

import android.os.Parcel;
import android.os.Parcelable;

public class Membre implements Parcelable {

    private String nom;
    private String prenom;
    private String sexe;
    private String fonction;
    private String commentaires;

    // constructeur
    public Membre(String nom, String prenom, String sexe, String fonction, String commentaires) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.fonction = fonction;
        this.commentaires = commentaires;
    }

    // constructeur appelé lors de la lecture avec le Creator
    public Membre(Parcel in) {
        nom = in.readString();
        prenom = in.readString();
        sexe = in.readString();
        fonction = in.readString();
        commentaires = in.readString();
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public String getFonction() {
        return fonction;
    }

    public String getCommentaires() {
        return commentaires;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeString(sexe);
        dest.writeString(fonction);
        dest.writeString(commentaires);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Membre> CREATOR = new Creator<Membre>() {
        @Override
        public Membre createFromParcel(Parcel in) {
            return new Membre(in);
        }

        @Override
        public Membre[] newArray(int size) {
            return new Membre[size];
        }
    };
}
