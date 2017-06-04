package com.example.arbildo.proyecto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager= CallbackManager.Factory.create();
        loginButton=(LoginButton) findViewById(R.id.login_button);
        info=(TextView) findViewById(R.id.tv1);

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        String id=loginResult.getAccessToken().getUserId();
                        info.setText("ID del usuario: "+loginResult.getAccessToken().getUserId()+ "\n"+"Token: "+loginResult.getAccessToken().getToken() );

                        ProfilePictureView profilePictureView;
                        profilePictureView = (ProfilePictureView) findViewById(R.id.friendProfilePicture);
                        profilePictureView.setProfileId(id);
                    }

                    @Override
                    public void onCancel() {

                       Toast.makeText(getApplicationContext(), "Error en logueo. Por favor, intente nuevamente", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(getApplicationContext(), "Error en logueo. Por favor, intente nuevamente", Toast.LENGTH_LONG).show();
                    }

                });

    }
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

     }


}
