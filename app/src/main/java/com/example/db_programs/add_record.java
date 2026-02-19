package com.example.db_programs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class add_record extends AppCompatActivity {

    TextView name_text,email_text;
    Button submit_button;
    MyDatabaseHelper db_connect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_record);

        name_text = findViewById(R.id.name);
        email_text = findViewById(R.id.email);
        submit_button = findViewById(R.id.submit);
        //load connection....
        db_connect = new MyDatabaseHelper(this);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get data into variable to insterd into db...
                String name = name_text.getText().toString();
                String email = email_text.getText().toString();


                if(name.isEmpty() && email.isEmpty())
                {
                    Toast.makeText(add_record.this,"Please Enter All the Data...",Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean inserted = db_connect.insertData(name,email);
                if(inserted){
                    Toast.makeText(add_record.this,"Data Inserted Successfully...",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(add_record.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(add_record.this,"Data Inserted Failed...",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}