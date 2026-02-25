package com.example.db_programs;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button register_button;
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout = findViewById(R.id.tableLayout);

        register_button = findViewById(R.id.register);
        register_button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, add_record.class);
            startActivity(intent);
        });

        displayDate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayDate();
    }

    public void displayDate() {

        tableLayout.removeAllViews();

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        Cursor cursor = dbHelper.getAllData();

        // Header
        TableRow headerRow = new TableRow(this);
        headerRow.addView(createTextView("ID"));
        headerRow.addView(createTextView("Name"));
        headerRow.addView(createTextView("Email"));
        headerRow.addView(createTextView("Edit"));
        headerRow.addView(createTextView("Delete"));
        tableLayout.addView(headerRow);

        while (cursor.moveToNext()) {

            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String email = cursor.getString(2);

            TableRow row = new TableRow(this);

            row.addView(createTextView(id));
            row.addView(createTextView(name));
            row.addView(createTextView(email));

            // EDIT
            Button editBtn = new Button(this);
            editBtn.setText("Edit");
            editBtn.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, add_record.class);
                intent.putExtra("id", id);
                intent.putExtra("name", name);
                intent.putExtra("email", email);
                startActivity(intent);
            });
            row.addView(editBtn);

            // DELETE
            Button deleteBtn = new Button(this);
            deleteBtn.setText("Delete");
            deleteBtn.setOnClickListener(v -> {
                dbHelper.deleteData(id);
                displayDate();
            });
            row.addView(deleteBtn);

            tableLayout.addView(row);
        }

        cursor.close();
    }

    private TextView createTextView(String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setPadding(20, 20, 20, 20);
        tv.setTextSize(16);
        return tv;
    }
}
