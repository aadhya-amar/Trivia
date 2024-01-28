package com.aadhya.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aadhya.quiz.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    FirebaseAuth auth;
    FirebaseFirestore database;
    ProgressDialog dialog;

    //FirebaseFirestore database;
    // ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database=FirebaseFirestore.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Time to unlock your strategies...");

        //database = FirebaseFirestore.getInstance();

        // dialog = new ProgressDialog(this);
        //dialog.setMessage("We're creating new account...");

        binding.submitbtn.setOnClickListener(new View.OnClickListener() {
       // binding.submitbtn.setOnClickListener((v)->);
            @Override
            public void onClick(View v) {
                String email, pass,name,referCode;

                email = binding.emailbox.getText().toString();
                pass = binding.passwordbox.getText().toString();
                name=binding.namebox.getText().toString();
                referCode=binding.referbox.getText().toString();

              final User user = new User(name,email,pass,referCode);
              dialog.show();

                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {


                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String uid=task.getResult().getUser().getUid();

                            database
                                    .collection("users")
                                            .document(uid)
                                                    .set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                dialog.dismiss();
                                                startActivity(new Intent(SignupActivity.this,MainActivity.class));
                                                finish();
                                            }
                                            else{
                                                Toast.makeText(SignupActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                                            }

                                        }
                                    });
//                            Toast.makeText(SignupActivity.this, "Success", Toast.LENGTH_SHORT).show();

                        } else {
                            dialog.dismiss();
                            Toast.makeText(SignupActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }
        });
    }


}
