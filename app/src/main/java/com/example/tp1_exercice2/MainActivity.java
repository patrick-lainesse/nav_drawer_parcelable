package com.example.tp1_exercice2;

        /*
        Application réalisée par: Patrick Lainesse
        Matricule: 740302
        Dans le cadre du cours IFT 1155, Hiver 2020

        Le thème material design a été utilisé pour la réalisation de cet exercice.

        Ressources utilisées:
        Icônes: https://material.io/resources/icons/?style=baseline
        TVAC, tutoriel material design: https://www.youtube.com/watch?v=VD3YItr9nMg

        Ajouter les fragments au manifest????

        Application testée sur un appareil Nexus 7, API 28 AVD


        À faire:
        - penser à un active text overlay (pour l'activité courante highlightée)
        - utiliser l'outil palette bitmap
        */

import android.content.Intent;
import android.os.Bundle;
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

    private Toolbar toolbar;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    //private Bundle fragBundle;      // à nettoyer probablement
    private String fragString = "bye-bye";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // on récupère le intent contenant l'ArrayList de membres ajoutés qui ne sont pas encore enregistrés en .txt
        Intent intent = getIntent();
        fragString = intent.getStringExtra("testString");       // à transformer en arraylist

        // à nettoyer
        Toast.makeText(this, fragString, Toast.LENGTH_LONG).show();

        // appel de la méthode pour remplir le drawer
        setDrawerLayout();
    }

    protected void setDrawerLayout() {

        toolbar = findViewById(R.id.barre_navigation);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.navigation_layout);
        navigationView = findViewById(R.id.nav_view);

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // https://abhiandroid.com/materialdesign/navigation-drawer#Drawer_Layout_In_Android

        // bundle et fragments array list
        // https://stackoverflow.com/questions/39867847/android-passing-arraylistmodel-to-fragment-from-activity
        final ArrayList<Membre> membresTemp = new ArrayList<Membre>();
        Bundle bundle = new Bundle();

        // test de bundle
        bundle.putString("testMain", fragString);
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

                default:
                    monFragment = null;
                    // si le temps: rajouter un fragment "accueuil" qui imprime un message et/ou le nombre de membres non enregistrés au fichier encore
                    // ça pourrait être le fragmentB pour montrer les membres en "cards" qui ne sont pas encore ajoutés à la liste
        }

        // suite test
        monFragment.setArguments(bundle);

        // placer le layout correspondant à la sélection dans le fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.activity_main_fragment_layout, monFragment);
        fragmentTransaction.commit();


        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
