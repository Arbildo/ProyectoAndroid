package com.example.arbildo.proyecto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

public class SegundoActivity extends AppCompatActivity {
    ProfilePictureView profilePictureView;
    TextView nombre, correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);

        profilePictureView = (ProfilePictureView) findViewById(R.id.friendProfilePicture);
        nombre=(TextView) findViewById(R.id.tvnombre);
        correo=(TextView) findViewById(R.id.tvcorreo);

        String id = getIntent().getStringExtra("IdUsuario");
        String jnombre= getIntent().getStringExtra("NombreUsuario");
        String jcorreo= getIntent().getStringExtra("EmailUsuario");


        profilePictureView.setProfileId(id);
        nombre.setText(jnombre);
        correo.setText(jcorreo);
    }



}