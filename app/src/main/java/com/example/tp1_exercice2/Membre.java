package com.example.tp1_exercice2;

import android.os.Parcel;
import android.os.Parcelable;

public class Membre implements Parcelable {

    private String nom;
    private String prenom;
    private int sexe;       // 1 = homme; 2 = femme
    private int fonction;   // 0: enseignant; 1: étudiant; 2: ingénieur; 3: retraité; 4: autre
    private String commentaires;

    // constructeur
    public Membre(String nom, String prenom, int sexe, int fonction, String commentaires) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.fonction = fonction;
        this.commentaires = commentaires;
    }

    // constructeur appelé lors de la lecture avec le Creator
    protected Membre(Parcel in) {
        nom = in.readString();
        prenom = in.readString();
        sexe = in.readInt();
        fonction = in.readInt();
        commentaires = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(prenom);
        dest.writeInt(sexe);
        dest.writeInt(fonction);
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
