package com.unistudent.app.unistudentappv1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailLoginActivity extends AppCompatActivity {
    Button emailLoginBtn;
    EditText emailEt;
    EditText passEt;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_login);

        mAuth = FirebaseAuth.getInstance();
        emailLoginBtn = (Button) findViewById(R.id.login_email_button);
        emailEt = (EditText) findViewById(R.id.emial_field);
        passEt = (EditText) findViewById(R.id.password_field);


        emailLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        checkAccount();
    }

    private void signIn(){
        String email = emailEt.getText().toString();
        String pass = passEt.getText().toString();
        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(pass)){
            Toast.makeText(EmailLoginActivity.this, "Your field is empty!", Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                //Log.w(TAG, "signInWithEmail:failed", task.getException());
                                Toast.makeText(EmailLoginActivity.this, "Sign in failed!",
                                        Toast.LENGTH_SHORT).show();
                            }else{
                                startActivity(new Intent(EmailLoginActivity.this, MainActivity.class ));
                            }

                            // ...
                        }
                    });
        }
    }


    private void checkAccount(){
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    //Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Intent homeIntent  = new Intent(EmailLoginActivity.this, MainActivity.class);
                    startActivity(homeIntent);
                } else {
                    // User is signed out
                    //Log.d(TAG, "onAuthStateChanged:signed_out");
                    Toast.makeText(getApplicationContext(), "Kesalahan Login", Toast.LENGTH_LONG);
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
}
