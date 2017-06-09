package com.example.arbildo.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

public class SegundoActivity extends AppCompatActivity{
    ProfilePictureView profilePictureView;
    TextView nombre, correo;
    Button botoncontinuar;
    String id, jnombre, jcorreo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);

        profilePictureView = (ProfilePictureView) findViewById(R.id.friendProfilePicture);
        nombre=(TextView) findViewById(R.id.tvnombre);
        correo=(TextView) findViewById(R.id.tvcorreo);
        id = getIntent().getStringExtra("IdUsuario");
        jnombre= getIntent().getStringExtra("NombreUsuario");
        jcorreo= getIntent().getStringExtra("EmailUsuario");
        botoncontinuar=(Button) findViewById(R.id.btnContinuar);
        botoncontinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inav = new Intent(getApplicationContext(), Navegacion.class);
                inav.putExtra("IdUsuario", id);
                inav.putExtra("NombreUsuario",jcorreo);
                inav.putExtra("EmailUsuarion",jnombre);
                startActivity(inav);
            }
        });

        profilePictureView.setProfileId(id);
        nombre.setText(jnombre);
        correo.setText(jcorreo);
    }

}