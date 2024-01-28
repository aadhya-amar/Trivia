package com.aadhya.quiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.aadhya.quiz.databinding.ActivityMainBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import me.ibrahimsn.lib.OnItemSelectedListener;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseFirestore database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content,new HomeFragment());
        transaction.commit();

        binding.bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                switch (i){
                    case 0:
                        transaction.replace(R.id.content,new HomeFragment());
                        transaction.commit();

                      //  Toast.makeText(MainActivity.this,"Home",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        transaction.replace(R.id.content,new LeaderboardFragment());
                        transaction.commit();

                    //    Toast.makeText(MainActivity.this,"Rank",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        transaction.replace(R.id.content,new WalletFragment());
                        transaction.commit();

                        // Toast.makeText(MainActivity.this,"Home",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        transaction.replace(R.id.content,new ProfileFragment());
                        transaction.commit();

                        // Toast.makeText(MainActivity.this,"Home",Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
        //database=FirebaseFirestore.getInstance();

//    final  ArrayList<CategoryModel> categories=new ArrayList<>();
//
//
//
//        //String img_st = String.valueOf(R.drawable.sports1);
////        categories.add(new CategoryModel("","Mythology","https://i.pinimg.com/564x/78/a4/24/78a42479c03ea97778940ea1cea240df.jpg"));
////        categories.add(new CategoryModel("","Sports","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfirbKIZTqfmD7P5mWR5q0BU0bZ_iVSji4cfXb_GXrkG0z9_XOW_sokhdh2eeIpts3E7Q&usqp=CAU"));
////        categories.add(new CategoryModel("","Movies","https://wallpaperaccess.com/full/3230187.jpg"));
////        categories.add(new CategoryModel("","GK","https://wallpaperaccess.com/full/1811320.jpg"));
//
//
//      final CategoryAdapter adapter=new CategoryAdapter(this,categories);
//        database.collection("categories")
//                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                            @Override
//                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                                categories.clear();
//                                for (DocumentSnapshot snapshot : value.getDocuments()){
//                                    CategoryModel model=snapshot.toObject(CategoryModel.class);
//                                    model.setCategoryId(snapshot.getId());
//                                    categories.add(model);
//                                }
//                                adapter.notifyDataSetChanged();
//                            }
//                        });
//
//
//
//        binding.categoryList.setLayoutManager(new GridLayoutManager(this,2));
//        binding.categoryList.setAdapter(adapter);


    }
}