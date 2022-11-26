package com.example.cajafuerte;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Cambiouser extends Fragment {


    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cajafuertefr-default-rtdb.firebaseio.com");


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_cambiouser, container, false);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cajafuertefr-default-rtdb.firebaseio.com");


        final EditText contrasenia = v.findViewById(R.id.contraseniaAc);
        final EditText cambiocu = v.findViewById(R.id.cambioCU);
        final EditText cambiocun = v.findViewById(R.id.cambioCUN);

        final Button btn = v.findViewById(R.id.cambioCUbtn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String contraseniaT = contrasenia.getText().toString();
                final String cambiocuT = cambiocu.getText().toString();
                final String cambiocunT = cambiocun.getText().toString();

                if (contraseniaT.isEmpty() || cambiocuT.isEmpty() || cambiocunT.isEmpty()){
                    Toast.makeText(getContext(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                }
                else if (contraseniaT.equals(cambiocunT)){
                    Toast.makeText(getContext(), "La contraseña actual no coincide", Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(contraseniaT)){
                                Toast.makeText(getContext(), "Contraseña ya registrada", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                databaseReference.child("Usuarios").child("pass").setValue(cambiocunT);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });



        return v;



    }
}