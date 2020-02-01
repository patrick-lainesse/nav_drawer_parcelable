package com.example.tp1_exercice2;

        /*
        Application réalisée par: Patrick Lainesse
        Matricule: 740302
        Dans le cadre du cours IFT 1155, Hiver 2020

        Le thème material design a été utilisé pour la réalisation de cet exercice.

        Ressources utilisées:
        Icônes: https://material.io/resources/icons/?style=baseline
        TVAC, tutoriel material design: https://www.youtube.com/watch?v=VD3YItr9nMg


        À faire:
        - penser à un active text overlay (pour l'activité courante highlightée)
        - utiliser l'outil palette bitmap
        */

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;


import android.app.FragmentManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        Fragment monFragment = null;

        // on vérifie l'option sélectionnée sur le drawer
        int idChoix = item.getItemId();

        switch(idChoix) {
            case R.id.menu_ajouter_membres:
                monFragment = new FragmentA();
                break;

                default:
                    monFragment = null;
        }

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
