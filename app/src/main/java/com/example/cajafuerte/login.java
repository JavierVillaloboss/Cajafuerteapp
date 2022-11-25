package com.example.cajafuerte;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cajafuertefr-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText correo = findViewById(R.id.Correo);
        final EditText pass = findViewById(R.id.Contrasenia);

        final Button loginbtn = findViewById(R.id.Iniciarsesionbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String correoTxt = correo.getText().toString();
                final String passTxt = pass.getText().toString();
                if (correoTxt.isEmpty() || passTxt.isEmpty()){
                    Toast.makeText(login.this, "Por favor ingrese correo o contraseña", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(correoTxt)){

                                final String getpass = snapshot.child(correoTxt).child("pass").getValue(String.class);

                                if (getpass.equals(passTxt)){
                                    Toast.makeText(login.this, "Sesión iniciada con éxito!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(login.this,menu.class));
                                }
                                else {
                                    Toast.makeText(login.this, "Contraseña incorrecta :(", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(login.this, "Correo incorrecto :(", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

    }

}