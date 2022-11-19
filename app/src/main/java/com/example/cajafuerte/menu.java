package com.example.cajafuerte;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        setwindowActionBar(tb);

        NavigationView nav = (NavigationView) findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id==R.id.op1){
                    Toast.makeText(getApplicationContext(), "Ver registros de caja", Toast.LENGTH_SHORT).show();
                    registros r = new registros();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,r).commit();
                }
                else if (id==R.id.op2){
                    Toast.makeText(getApplicationContext(), "Cambiar contraseña usuario", Toast.LENGTH_SHORT).show();
                    Cambiouser c= new Cambiouser();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,c).commit();
                }
                else if (id==R.id.op3){
                    Toast.makeText(getApplicationContext(), "Cambiar contraseña caja", Toast.LENGTH_SHORT).show();
                    Cambiocaja cc= new Cambiocaja();
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,cc).commit();
                }

                return false;
            }
        });
        DrawerLayout dl = (DrawerLayout) findViewById(R.id.Principal);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                dl,
                R.string.abrir_drawer,
                R.string.cerrar_drawer
        );
        dl.addDrawerListener(toggle);
        toggle.syncState();

        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dl.isDrawerOpen(GravityCompat.START)){
                    dl.closeDrawer(GravityCompat.START);
                }
                else {
                    dl.openDrawer((int) Gravity.START);
                }
            }
        });
    }

    private void setwindowActionBar(Toolbar tb) {
    }
}