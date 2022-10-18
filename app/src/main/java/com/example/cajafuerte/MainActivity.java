package com.example.cajafuerte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    Button btnregistrar;

    EditText etNombre, etApellido, etEdad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnregistrar();
        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);
        etEdad = (EditText) findViewById(R.id.etEdad);

    }

    public void llamadoBD(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Alumno").child("r1");

        myRef.setValue("Javier");

    }
    public void login(View v){
        Intent i = new Intent(this,login.class);
        startActivity(i);
    }

    public void Ccuenta(View v){
        Intent i = new Intent(this,RegistrarCuenta.class);
        startActivity(i);
    }
    public void btnregistrar(){
        btnregistrar = (Button) findViewById(R.id.btnregistrar);
        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String edad = etEdad.getText().toString();
                String key = UUID.randomUUID().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Alumno").child(key);

                myRef.child("nombre").setValue(nombre);
                myRef.child("apellido").setValue(apellido);
                myRef.child("edad").setValue(edad);
            }
        });
    }
}