package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorderapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
//    ImageView subtract, add;
//    EditText quantity;
    //int quantity = 0;
    Button add, subtract;
    TextView quantity;
    int count;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        add =  findViewById(R.id.add);
        subtract = findViewById(R.id.subtract);
        quantity = findViewById(R.id.quantity);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                quantity.setText(count +"\uD83D\uDE03");
            }
        });
        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                quantity.setText(count +"\uD83D\uDE00");
            }
        });




        final DBHelper helper = new DBHelper(this);

        if (getIntent().getIntExtra("type",0) ==1) {

             final int image = getIntent().getIntExtra("image", 0);
             final int price = Integer.parseInt(getIntent().getStringExtra("price"));
             final String name = getIntent().getStringExtra("name");
             final String description = getIntent().getStringExtra("description");

            binding.detailImage.setImageResource(image);
            binding.priceLbl.setText(String.format("%d", price));
            binding.nameBoxLbl.setText(name);
            binding.detailDescription.setText(description);



            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = helper.insertOrder(
                            binding.nameBoxLbl.getText().toString(),
                            binding.nameBox.getText().toString(),
                            price,
                            image,
                            name,
                            description,
                            Integer.parseInt(binding.quantity.getText().toString()));
                    if (isInserted)
                        Toast.makeText(DetailActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();

                }
            });
        } else {

            final int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = helper.getOrderById(id);
            final int image = cursor.getInt(4);

            binding.detailImage.setImageResource(image);
            binding.priceLbl.setText(String.format("%d", cursor.getInt(3)));
            binding.nameBox.setText(cursor.getString(6));
            binding.detailDescription.setText(cursor.getString(5));

            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Boolean isUpdated = helper.updateOrder(
                            binding.nameBoxLbl.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            Integer.parseInt(binding.priceLbl.getText().toString()),
                            image,
                            binding.detailDescription.getText().toString(),
                            binding.nameBox.getText().toString(),
                            1,
                            id);
                    if (isUpdated)
                        Toast.makeText(DetailActivity.this,"updated", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this,"Failed", Toast.LENGTH_SHORT).show();


                }
            });



        }
//        add = (ImageView) findViewById(R.id.add);
//        subtract =(ImageView) findViewById(R.id.subtract);
//        quantity =(EditText) findViewById(R.id.quantity);

//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (v == add) {
//
//                }
//
//            }
//        });

//        subtract.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (v==subtract){
//
//                }
//
//            }
//        });



    }
}