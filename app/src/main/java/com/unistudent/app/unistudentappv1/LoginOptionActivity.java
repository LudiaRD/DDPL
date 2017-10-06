package com.unistudent.app.unistudentappv1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginOptionActivity extends AppCompatActivity {
    private Button emailOptionBtn;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    private SignInButton gSignInBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_option);
        checkAccount();
        mAuth = FirebaseAuth.getInstance();
        emailOptionBtn = (Button) findViewById(R.id.email_option_button);
        gSignInBtn = (SignInButton) findViewById(R.id.googleSignInButton);
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        emailOptionBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent loginEmailIntent = new Intent(LoginOptionActivity.this, EmailLoginActivity.class);
                startActivity(loginEmailIntent);
            }
        });

    }

    private void checkAccount() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    //Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    startActivity(new Intent(LoginOptionActivity.this, MainActivity.class));
                } else {
                    // User is signed out
                    //Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void signIn() {
        //Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        //startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}
