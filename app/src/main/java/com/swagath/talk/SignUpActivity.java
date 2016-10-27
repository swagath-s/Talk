package com.swagath.talk;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener mAuthListener;

    private FirebaseAuth mAuth;
    private static final String TAG = "SignUpActivity";

    private EditText uNameFld;
    private EditText pwd1Fld;
    private EditText pwd2Fld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
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

    public void createAccount(View view) {
        uNameFld = (EditText)findViewById(R.id.uName);
        pwd1Fld = (EditText)findViewById(R.id.pwd1);
        pwd2Fld = (EditText)findViewById(R.id.pwd);
        String uName = uNameFld.getText().toString();
        String pwd1 = pwd1Fld.getText().toString();



        mAuth.createUserWithEmailAndPassword(uName, pwd1)
              .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                       // If sign in fails, display a message to the user. If sign in succeeds
                       // the auth state listener will be notified and logic to handle the
                       // signed in user can be handled in the listener.
                       if (!task.isSuccessful()) {
                           Toast.makeText(SignUpActivity.this, R.string.auth_failed,
                                   Toast.LENGTH_SHORT).show();
                       }
                      // ...
                   }
             });


    }
}
