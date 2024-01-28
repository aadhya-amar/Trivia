package com.aadhya.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.aadhya.quiz.databinding.ActivityResultActvityBinding;
import com.aadhya.quiz.databinding.ActivityTriviaBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

public class ResultActvity extends AppCompatActivity {

    ActivityResultActvityBinding binding;
    int POINTS =10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityResultActvityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int correctAnswers = getIntent().getIntExtra("correct",0);
        int total = getIntent().getIntExtra("total",0);

     long points=correctAnswers * POINTS;


        binding.score.setText(String.format("%d/%d",correctAnswers,total));
        binding.coins.setText(String.valueOf(points));

        FirebaseFirestore database=FirebaseFirestore.getInstance();

        database.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .update("coins", FieldValue.increment(points));
    }
}