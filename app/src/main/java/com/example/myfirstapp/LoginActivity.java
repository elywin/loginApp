package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    TextView txtv2;
    Button btn2;
    EditText txt4, txt5;

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFirebaseAuth = FirebaseAuth.getInstance();
        txtv2 = findViewById(R.id.textview2);
        btn2 = findViewById(R.id.btn3);
        txt4 = findViewById(R.id.text4);
        txt5 = findViewById(R.id.text5);

    mAuthStateListener = new FirebaseAuth.AuthStateListener() {

        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser mFirebaseUser =mFirebaseAuth.getCurrentUser();
            if ( mFirebaseUser != null){
                Toast.makeText(LoginActivity.this,"Your logged in",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(i);
              //  startActivity(new Intent(LoginActivity.this,HomeActivity.class));
            }else{
                Toast.makeText(LoginActivity.this,"Login please",Toast.LENGTH_SHORT).show();
            }
        }
    };

    btn2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String email= txt4.getText().toString();
            String  password= txt5.getText().toString();

            if (email.isEmpty()){
                txt4.setError("Please enter email id");
                txt4.requestFocus();
            }else if (password.isEmpty()){
                txt5.setError("Please enter password");
                txt5.requestFocus();
            }else if (email.isEmpty()&&password.isEmpty()){
                Toast.makeText(LoginActivity.this,"Fileds are empty!",Toast.LENGTH_SHORT).show();
            }else if ( !(email.isEmpty()&&password.isEmpty())){
                mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Loggin failed, try again!",Toast.LENGTH_SHORT).show();
                        }else{
                                Intent intoHome = new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intoHome);
                           // startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                        }
                    }
                });
            }else{
                Toast.makeText(LoginActivity.this,"Error Occured!",Toast.LENGTH_SHORT).show();

            }

        }
    });

    txtv2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intoSignin = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intoSignin);
          //  startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    });

    }

    @Override
    protected void onStart() {
        super.onStart();
     mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

}
