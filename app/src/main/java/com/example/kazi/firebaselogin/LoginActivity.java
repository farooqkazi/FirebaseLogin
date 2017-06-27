package com.example.kazi.firebaselogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
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

/**
 * Created by Kazi on 26/June/17.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmailLogin,txtPasswordLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmailLogin = (EditText) findViewById(R.id.txtEmailLogin);
        txtPasswordLogin = (EditText) findViewById(R.id.txtPasswordLogin);
        firebaseAuth = firebaseAuth.getInstance();
    }
    public void btnLoginUser_Click(View v){

        final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this,"Please Wait ...", "Processing ...", true);
        (firebaseAuth.createUserWithEmailAndPassword(txtEmailLogin.getText().toString(), txtPasswordLogin.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){

                            Toast.makeText(LoginActivity.this, "Login Successful",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
                            i.putExtra("Email",firebaseAuth.getCurrentUser().getEmail());
                            startActivity(i);
                        }
                        else{
                            Log.e("ERROR",task.getException().toString());
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();

                        }

                    }
                });

    }
}
