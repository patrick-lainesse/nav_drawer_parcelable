package com.example.tp1_exercice2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


// classe appellée pour enregistrer en txt le tableau de membres ajoutés au fragmentA
public class FragmentB extends Fragment {

    private ArrayList<Membre> listMembre;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_enregistrer, container, false);

        setCardMembre(view);
        setBouton(view);

        return view;

    }

    private void setCardMembre(View view) {

        // récupération d'une référence sur le recyclerview pour y afficher
        // les infos des membres à ajouter au .txt
        RecyclerView recMembres = view.findViewById(R.id.fragB_recycler);
        LinearLayoutManager llm = new LinearLayoutManager(view.getContext());

        listMembre = this.getArguments().getParcelableArrayList("cle_listeMain");

        // vérification que la liste est vide. Si oui, initialisation et affichage du message correspondant
        if(listMembre == null) {
            listMembre = new ArrayList<Membre>();
            setMessage(view, true);
        } else {
            setMessage(view, false);
        }

        MembresAdapter mAdapter = new MembresAdapter(listMembre);

        recMembres.setAdapter(mAdapter);
        recMembres.setLayoutManager(llm);
    }

    private void setBouton(final View view) {

        MaterialButton boutonVider = view.findViewById(R.id.fragB_bouton_vider);
        MaterialButton boutonEnregistrer = view.findViewById(R.id.fragB_bouton_enregistrer);
        final ArrayList<Membre> listeMembre = this.getArguments().getParcelableArrayList("cle_listeMain");

        boutonVider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vider();
            }
        });

        boutonEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // vérifier si le fichier existe déjà????

                // création du fichier pour écriture
                //ObjectOutputStream fichier = null;

                try {

                    FileOutputStream fichierEcrit = view.getContext().openFileOutput("membres.txt", Context.MODE_PRIVATE);
                    ObjectOutputStream fichier = new ObjectOutputStream(fichierEcrit);
/*

                    // test réussi d'un mot simple
                    Writer out = new OutputStreamWriter(fichierEcrit);
                    out.write("allo");
                    out.close();
*/

                    //fichier = new ObjectOutputStream(new FileOutputStream("membres.txt"));


                    // on commence par écrire le nombre d'éléments au total dans le fichier
                    fichier.writeInt(listMembre.size());
                    fichier.writeChar(';');

                    for (int i=0; i<listMembre.size(); i++) {
                        fichier.writeObject(listMembre.get(i));
                        fichier.writeChar(';');

/*

                        fichier.writeObject(listMembre.get(i).getNom());
                        fichier.writeObject(";");
                        fichier.writeObject(listMembre.get(i).getPrenom());
                        fichier.writeObject(";");
                        fichier.writeObject(listMembre.get(i).getSexe());
                        fichier.writeObject(";");
                        fichier.writeObject(listMembre.get(i).getFonction());
                        fichier.writeObject(";");
                        fichier.writeObject(listMembre.get(i).getCommentaires());
                        fichier.writeObject(";");
*//*


                        //fichier.flush();
                        //fichier.close(); */
                    }

                    //Toast.makeText(getActivity(), getResources().getString(R.string.txt_enregistre), Toast.LENGTH_LONG).show();
                    fichier.flush();
                    fichier.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                // liste vide plante????
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putParcelableArrayListExtra("cle_listMembres", (ArrayList<? extends Parcelable>) listeMembre);
                vider();
                startActivity(intent);
            }

            // pas oublier de mettre ici le vider????
        });
    }

    private void vider() {

        listMembre.clear();
        Intent intent = new Intent(getActivity(), MainActivity.class);

        // j'ai fait un cast sur listMembre, car la classe Membre implements Parcelable (comme dans les notes de cours)
        // et la méthode putParcelableArrayListExtra requiert un ArrayList qui extends Parcelable comme argument
        intent.putParcelableArrayListExtra("cle_listeMembres", (ArrayList<? extends Parcelable>) listMembre);
        Toast.makeText(getActivity(), getResources().getString(R.string.liste_videe), Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    private void setMessage(View v, Boolean listeNulle) {

        TextView textView = v.findViewById(R.id.fragB_message);

        if(!listeNulle) {
            textView.setText(R.string.titre_ajouter);
        } else {
            textView.setText(R.string.titre_rien_a_ajouter);
        }
    }
}
