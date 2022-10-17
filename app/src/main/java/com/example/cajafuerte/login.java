package com.example.cajafuerte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void recuperar(View v){
        EditText campo1 = this.findViewById(R.id.Correo);
        String correo = campo1.getText().toString();
        EditText campo2 = this.findViewById(R.id.Contrasenia);
        String contrasenia = campo2.getText().toString();

        if (correo.equals("c1") && contrasenia.equals("123")){
            Intent i = new Intent(this,menu.class);
            startActivity(i);
        }else {
            Toast.makeText(this, "Error en las credenciales", Toast.LENGTH_SHORT).show();
        }
    }
}