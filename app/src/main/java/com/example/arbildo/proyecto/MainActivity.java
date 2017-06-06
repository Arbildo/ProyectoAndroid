package com.example.arbildo.proyecto;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    ProfilePictureView profilePictureView;
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    private AccessToken accessToken;
    private AccessTokenTracker accessTokenTracker;



   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager= CallbackManager.Factory.create();
        loginButton=(LoginButton) findViewById(R.id.login_button);

       loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));



       profilePictureView = (ProfilePictureView) findViewById(R.id.friendProfilePicture);
        LoginManager.getInstance().registerCallback(callbackManager,new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        final String id=loginResult.getAccessToken().getUserId();

                       // info.setText("ID del usuario: "+loginResult.getAccessToken().getUserId()+ "\n"+"Token: "+loginResult.getAccessToken().getToken() );

                        //profilePictureView.setProfileId(id);

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {

                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        Log.v("Main", response.toString());

                                        try {
                                            String jemail=(object.getString("email"));
                                            String jnombre=(object.getString("name"));
                                            Intent datos = new Intent(getApplicationContext(), SegundoActivity.class);
                                            datos.putExtra("IdUsuario", id);
                                            datos.putExtra("NombreUsuario",jemail);
                                            datos.putExtra("EmailUsuario",jnombre);
                                            startActivity(datos);


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                        //CapturadeDatos(object);
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email, birthday");
                        request.setParameters(parameters);
                        request.executeAsync();





                       Intent datos = new Intent(getApplicationContext(), SegundoActivity.class);
                        datos.putExtra("IdUsuario", id);
                       startActivity(datos);




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
   /*private void CapturadeDatos(JSONObject jsonObject) {
        try {
            email.setText(jsonObject.getString("email"));
            facebookName.setText(jsonObject.getString("name"));



        } catch (JSONException e) {
            e.printStackTrace();
        }*/
     }

