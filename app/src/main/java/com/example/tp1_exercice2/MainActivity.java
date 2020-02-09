package com.example.tp1_exercice2;

        /*
        Application réalisée par: Patrick Lainesse
        Matricule: 740302
        Dans le cadre du cours IFT 1155, Hiver 2020

        Application conçue et testée sur un émulateur Nexus 7, API 28 AVD et un cellulaire Motorola MotoG3
        Le thème material design a été utilisé pour la réalisation de cet exercice.

        Ressources utilisées:
        Icônes: https://material.io/resources/icons/?style=baseline
        Navigation Drawer: https://abhiandroid.com/materialdesign/navigation-drawer#Drawer_Layout_In_Android
        TVAC, tutoriel material design: https://www.youtube.com/watch?v=VD3YItr9nMg
        RecyclerView et Cards: https://www.binpress.com/android-recyclerview-cardview-guide/


        À faire:
        - penser à un active text overlay (pour l'activité courante highlightée)
        - utiliser l'outil palette bitmap
        - tout bogue quand la liste est vide
        */

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<Membre> listeMembres;
    //????
    private ArrayList<Membre> listMembres;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // on récupère le intent contenant l'ArrayList de membres du fragmentA (ajout membres)
        // pour le passer au fragmentB (enregistrer en .txt)
        intent = getIntent();
        listeMembres = intent.getParcelableArrayListExtra("cle_listeMembres");

        listMembres = new ArrayList<Membre>();
        listMembres = intent.getParcelableArrayListExtra("cle_listMembres");

        // appel de la méthode pour mettre les choix dans le navdrawer
        setDrawerLayout();
    }

    // méthode qui ajoute les options au navigation drawer
    protected void setDrawerLayout() {

        Toolbar toolbar = findViewById(R.id.barre_navigation);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.navigation_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.ouvertureNav,
                R.string.fermetureNav
        );

        // on ajoute un écouteur d'événement sur le drawer
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    // méthode qui réagit à un clic sur une option du navdrawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // on crée un arraylist pour stocker les membres ajoutés au fragmentA
        // ainsi qu'un bundle pour le passer au fragmentB
        /*ArrayList<Membre> listMembres = new ArrayList<Membre>();
        listMembres = intent.getParcelableArrayListExtra("cle_listMembres");*/
        Bundle bundle = new Bundle();

        if(listMembres == null){
            Toast.makeText(this, "vide!", Toast.LENGTH_LONG).show();
            //Toast.makeText(this, listMembres.get(2).getNom().toString(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, listMembres.get(0).getNom(), Toast.LENGTH_LONG).show();
        }

        bundle.putParcelableArrayList("cle_listeMain", (ArrayList<? extends Parcelable>) listeMembres);
        bundle.putParcelableArrayList("cle_listMembres", (ArrayList<? extends Parcelable>) listMembres);

        Fragment monFragment = null;

        // on vérifie l'option sélectionnée sur le drawer
        int idChoix = item.getItemId();

        switch(idChoix) {
            case R.id.menu_ajouter_membres:
                monFragment = new FragmentA();
                break;

            case R.id.menu_enregistrer:
                monFragment = new FragmentB();
                break;

            case R.id.menu_lister:
                monFragment = new FragmentC();
                bundle.putChar("char_choix", 'C');
                break;

            case R.id.menu_rechercher:
                monFragment = new FragmentC();
                bundle.putChar("char_choix", 'D');
                break;

            case R.id.menu_liste_sexe:
                monFragment = new FragmentC();
                bundle.putChar("char_choix", 'E');
                break;

            case R.id.menu_liste_fem:
                monFragment = new FragmentC();
                bundle.putChar("char_choix", 'F');
                break;

                // par défaut, on montre les membres qui n'ont pas encore été ajoutés au fichier txt
                default:
                    monFragment = new FragmentB();
        }

        // placer le layout correspondant à la sélection dans le fragment
        monFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_fragment_layout, monFragment);
        fragmentTransaction.commit();


        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
