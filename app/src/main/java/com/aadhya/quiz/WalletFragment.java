package com.aadhya.quiz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aadhya.quiz.databinding.FragmentWalletBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class WalletFragment extends Fragment {



    public WalletFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }
        FragmentWalletBinding binding;
    FirebaseFirestore database;
    User user;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentWalletBinding.inflate(inflater,container,false);
        database=FirebaseFirestore.getInstance();
        database.collection("users")
                .document(FirebaseAuth.getInstance().getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                         user=documentSnapshot.toObject(User.class);
                        binding.currentcoins.setText(String.valueOf(user.getCoins()));




                    }
                });
binding.sendrequest.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(user.getCoins()>100){
            String uid=FirebaseAuth.getInstance().getUid();
            String paypal=binding.mailbox.getText().toString();
            WithdrawRequest request=new WithdrawRequest(uid,paypal,user.getName());

            database
                    .collection("withdraws")
                    .document(uid)
                    .set(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getContext(),"Request sent successfully",Toast.LENGTH_SHORT).show();

                        }
                    });
        }else{
            Toast.makeText(getContext(), "You need more coins to withdraw!", Toast.LENGTH_SHORT).show();
        }


    }
});

       return binding.getRoot();
    }
}