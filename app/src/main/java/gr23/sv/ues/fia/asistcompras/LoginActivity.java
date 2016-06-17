package gr23.sv.ues.fia.asistcompras;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import gr23.sv.ues.fia.asistcompras.Modelos.ControlDB;


public class LoginActivity extends Activity {

    LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookSDKInitialize();
        setContentView(R.layout.activity_login);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        getLoginDetails(loginButton);
    }

    /**
    ** Inicializa facebook sdk
    ** se inicializa en onCreate()
    **/
    private void facebookSDKInitialize() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    private void getLoginDetails(LoginButton login_button) {
        // Callback registration
        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult login_result) {
                Intent inte = new Intent(LoginActivity.this, LugarInsertarActivity.class);
                startActivity(inte);
                Toast.makeText(LoginActivity.this,"Ha iniciado sesión con exito",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                // code for cancellation
            }

            @Override
            public void onError(FacebookException exception) {
                //  code to handle error
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}