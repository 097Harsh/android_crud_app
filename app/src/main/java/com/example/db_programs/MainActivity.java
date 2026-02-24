package com.example.db_programs;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button register_button;
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tableLayout = findViewById(R.id.tableLayout);

        /* MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase(); */

        //fetching record from database....
        displayDate();

        //intentviews
        register_button = findViewById(R.id.register);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, add_record.class);
                startActivity(intent);


            }
        });




    }

    public void displayDate(){
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        //SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = dbHelper.getAllData();

            // Table Header
            TableRow headerRow = new TableRow(this);
            headerRow.addView(createTextView("ID"));
            headerRow.addView(createTextView("Name"));
            headerRow.addView(createTextView("Email"));
            tableLayout.addView(headerRow);


            // Table Data
            while (cursor.moveToNext()) {
                TableRow row = new TableRow(this);

                row.addView(createTextView(cursor.getString(0)));
                row.addView(createTextView(cursor.getString(1)));
                row.addView(createTextView(cursor.getString(2)));

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