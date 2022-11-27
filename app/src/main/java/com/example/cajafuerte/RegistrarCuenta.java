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

public class RegistrarCuenta extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cajafuertefr-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cuenta);
        final EditText usuario = findViewById(R.id.usuarioR);
        final EditText correoR = findViewById(R.id.correo);
        final EditText passR = findViewById(R.id.contrasenia);
        final EditText Cpass = findViewById(R.id.Ccontrasenia);

        final Button registrobtn = findViewById(R.id.registrarbtn);

        registrobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usuarioTxt = usuario.getText().toString();
                final String correoRTxt = correoR.getText().toString();
                final String passRTxt = passR.getText().toString();
                final String CpassTxt = Cpass.getText().toString();

                if (usuarioTxt.isEmpty() || correoRTxt.isEmpty() || passRTxt.isEmpty() || CpassTxt.isEmpty()){
                    Toast.makeText(RegistrarCuenta.this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                }
                else if(!passRTxt.equals(CpassTxt)){
                    Toast.makeText(RegistrarCuenta.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(correoRTxt)){
                                Toast.makeText(RegistrarCuenta.this, "Este correo ya esta registrado", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                databaseReference.child("Usuarios").child(correoRTxt).child("usuario").setValue(usuarioTxt);
                                databaseReference.child("Usuarios").child(correoRTxt).child("correo").setValue(correoRTxt);
                                databaseReference.child("Usuarios").child(correoRTxt).child("pass").setValue(passRTxt);

                                Toast.makeText(RegistrarCuenta.this, "Usuario registrado con éxito!", Toast.LENGTH_SHORT).show();
                                finish();
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

    public void menu(View v){
        Intent i = new Intent(this,menu.class);
        startActivity(i);
    }
}