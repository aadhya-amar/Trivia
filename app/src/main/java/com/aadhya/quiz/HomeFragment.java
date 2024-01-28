package com.aadhya.quiz;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aadhya.quiz.databinding.FragmentHomeBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class HomeFragment extends Fragment {



    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    FragmentHomeBinding binding;
    FirebaseFirestore database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentHomeBinding.inflate(inflater,container,false);

        database= FirebaseFirestore.getInstance();

        final ArrayList<CategoryModel> categories=new ArrayList<>();



        //String img_st = String.valueOf(R.drawable.sports1);
//        categories.add(new CategoryModel("","Mythology","https://i.pinimg.com/564x/78/a4/24/78a42479c03ea97778940ea1cea240df.jpg"));
//        categories.add(new CategoryModel("","Sports","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfirbKIZTqfmD7P5mWR5q0BU0bZ_iVSji4cfXb_GXrkG0z9_XOW_sokhdh2eeIpts3E7Q&usqp=CAU"));
//        categories.add(new CategoryModel("","Movies","https://wallpaperaccess.com/full/3230187.jpg"));
//        categories.add(new CategoryModel("","GK","https://wallpaperaccess.com/full/1811320.jpg"));


        final CategoryAdapter adapter=new CategoryAdapter(getContext(),categories);
        database.collection("categories")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        categories.clear();
                        for (DocumentSnapshot snapshot : value.getDocuments()){
                            CategoryModel model=snapshot.toObject(CategoryModel.class);
                            model.setCategoryId(snapshot.getId());
                            categories.add(model);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });



        binding.categoryList.setLayoutManager(new GridLayoutManager(getContext(),2));
        binding.categoryList.setAdapter(adapter);

        binding.spinwheel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SpinnerActivity.class));

            }
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}