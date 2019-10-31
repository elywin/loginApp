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

public class MainActivity extends AppCompatActivity {
TextView txtv1;
Button btn1;
EditText txt1, txt2;

FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAuth = FirebaseAuth.getInstance();
        txtv1 = findViewById(R.id.textview1);
        btn1 = findViewById(R.id.btn);
        txt1 = findViewById(R.id.text1);
        txt2 = findViewById(R.id.text2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= txt1.getText().toString();
                String  password= txt2.getText().toString();

                if (email.isEmpty()){
                    txt1.setError("Please enter email id");
                    txt1.requestFocus();
                }else if (password.isEmpty()){
                    txt2.setError("Please enter password");
                    txt2.requestFocus();
                }else if (email.isEmpty()&&password.isEmpty()){
                    Toast.makeText(MainActivity.this,"Fileds are empty!",Toast.LENGTH_SHORT).show();
                }else if (!(email.isEmpty()&&password.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"SignUp unsuccessful Try again",Toast.LENGTH_SHORT).show();

                            }else {
                                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                            }
                        }
                    });
                }else{
                    Toast.makeText(MainActivity.this,"Error Occured!",Toast.LENGTH_SHORT).show();

                }
            }
        });

        txtv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
                //startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
    }
}
