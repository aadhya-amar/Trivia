package com.aadhya.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aadhya.quiz.SpinWheel.LuckyWheelView;
import com.aadhya.quiz.SpinWheel.model.LuckyItem;
import com.aadhya.quiz.databinding.ActivitySpinnerBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpinnerActivity extends AppCompatActivity {


    ActivitySpinnerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpinnerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<LuckyItem> data=new ArrayList<>();

        LuckyItem item1=new LuckyItem();
        item1.topText="5";
        item1.secondaryText="Coins";
        item1.textColor= Color.parseColor("#212121");
        item1.color=Color.parseColor("#eceff1");

        data.add(item1);

        LuckyItem item2=new LuckyItem();
        item2.topText="10";
        item2.secondaryText="Coins";
        item2.textColor= Color.parseColor("#ffffff");
        item2.color=Color.parseColor("#00cf00");
        data.add(item2);

        LuckyItem item3=new LuckyItem();
        item3.topText="15";
        item3.secondaryText="Coins";
        item3.textColor= Color.parseColor("#212121");
        item3.color=Color.parseColor("#eceff1");
        data.add(item3);

        LuckyItem item4=new LuckyItem();
        item4.topText="20";
        item4.secondaryText="Coins";
        item4.color= Color.parseColor("#7f00d9");
        item4.textColor= Color.parseColor("#ffffff");
      //  item4.textColor=Color.parseColor("#ffffff");
        data.add(item4);

        LuckyItem item5=new LuckyItem();
        item5.topText="25";
        item5.secondaryText="Coins";
        item5.textColor= Color.parseColor("#212121");
        item5.color=Color.parseColor("#eceff1");
        data.add(item5);

        LuckyItem item6=new LuckyItem();
        item6.topText="30";
        item6.secondaryText="Coins";
        item6.color= Color.parseColor("#dc0000");
        item6.textColor= Color.parseColor("#ffffff");
       // item6.textColor=Color.parseColor("#ffffff");
        data.add(item6);

        LuckyItem item7=new LuckyItem();
        item7.topText="35";
        item7.secondaryText="Coins";
        item7.textColor= Color.parseColor("#212121");
        item7.color=Color.parseColor("#eceff1");
        data.add(item7);

        LuckyItem item8=new LuckyItem();
        item8.topText="0";
        item8.secondaryText="Coins";
        item8.textColor= Color.parseColor("#ffffff");
        item8.color= Color.parseColor("#008bff");
       // item2.textColor=Color.parseColor("#ffffff");
        data.add(item8);



        binding.wheelview.setData(data);
        binding.wheelview.setRound(5);

        binding.spinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r= new Random();
                int randomNumber = r.nextInt(8);

                binding.wheelview.startLuckyWheelWithTargetIndex(randomNumber);

            }
        });
        binding.wheelview.setLuckyRoundItemSelectedListener(new LuckyWheelView.LuckyRoundItemSelectedListener() {
            @Override
            public void LuckyRoundItemSelected(int index) {
                updateCash(index);
            }
        });

    }

    void updateCash(int index){
        long cash=0;
        switch (index){
            case 0:
                cash=5;
                break;
            case 1:
                cash=10;
                break;
            case 2:
                cash=15;
                break;
            case 3:
                cash=20;
                break;
            case 4:
                cash=25;
                break;
            case 5:
                cash=30;
                break;
            case 6:
                cash=35;
                break;

            case 7:
                cash=0;
                break;


        }

        FirebaseFirestore database=FirebaseFirestore.getInstance();

        database
                .collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .update("coins", FieldValue.increment(cash)).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(SpinnerActivity.this, "Check Out your wallet..", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}
