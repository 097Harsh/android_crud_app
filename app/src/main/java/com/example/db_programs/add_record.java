package com.example.db_programs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class add_record extends AppCompatActivity {

    EditText name_text, email_text;
    Button submit_button;
    MyDatabaseHelper db_connect;

    String id = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        name_text = findViewById(R.id.name);
        email_text = findViewById(R.id.email);
        submit_button = findViewById(R.id.submit);

        db_connect = new MyDatabaseHelper(this);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        if (id != null) {
            name_text.setText(intent.getStringExtra("name"));
            email_text.setText(intent.getStringExtra("email"));
            submit_button.setText("Update");
        }

        submit_button.setOnClickListener(v -> {

            String name = name_text.getText().toString().trim();
            String email = email_text.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this,
                        "Please Enter All Data",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (id != null) {

                boolean updated = db_connect.updateData(id, name, email);

                if (updated)
                    Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();

            } else {

                boolean inserted = db_connect.insertData(name, email);

                if (inserted)
                    Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Insert Failed", Toast.LENGTH_SHORT).show();
            }

            finish();
        });
    }
}
