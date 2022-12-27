package com.example.cajafuerte;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class menu extends AppCompatActivity {
    EditText passcf;
    Button btnA;
    Button abrir;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        passcf = findViewById(R.id.cajapass);
        btnA = findViewById(R.id.btnAbrir);

        boolean abierto = false, cerrado = false;

        databaseReference = FirebaseDatabase.getInstance().getReference().child("passCF");

        Switch servor=(Switch)findViewById(R.id.abrir);
         database = FirebaseDatabase.getInstance();
         myRef = database.getReference("servor");
        myRef.setValue("hola");

        servor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    myRef.setValue(1);
                }else{
                    myRef.setValue(0);
                }
            }
        });


        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passcajafuerte();
            }


        });


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



    private void passcajafuerte() {
        String passCf = passcf.getText().toString();

        pass pas = new pass(passCf);

        databaseReference.push().setValue(pas);
        Toast.makeText(this, "Contraseña ingresada", Toast.LENGTH_SHORT).show();
    }
}