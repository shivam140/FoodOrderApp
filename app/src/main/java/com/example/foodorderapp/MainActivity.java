package com.example.foodorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodorderapp.Adapters.MainAdapter;
import com.example.foodorderapp.Models.MainModel;
import com.example.foodorderapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.burger, "Burger", "5","A burger without cheese is like a hug without a squeeze."));
        list.add(new MainModel(R.drawable.pizza, "Pizza", "10","It will be light, crisp and good. If it is full of dough and heavy, however, it means that the pizza has not been leavened properly."));
        list.add(new MainModel(R.drawable.vdapav, "Vada pav", "25","Vada pav is a savory dinner roll stuffed with fried mashed and spiced potato fritters."));
        list.add(new MainModel(R.drawable.sandwich, "Sandwich", "12","A large sandwich consisting of a long roll split lengthwise and filled with layers of various ingredients such as meat, cheese, tomatoes, lettuce, and condiments."));
        list.add(new MainModel(R.drawable.avbhaji, "PavBhaji", "45","A ragout of boiled, mashed and slow simmered vegetables spiked with a tantalising array of spices and served to be lapped up with crusty pan fried bread, a dash of lemon and a lashing of sweet red onions"));


        MainAdapter adapter = new MainAdapter(list,this);
        binding.recyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}