package com.example.contacts;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddContact extends AppCompatActivity{

    ContactBook book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);
        getSupportActionBar().setTitle("Add Contact");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        book = (ContactBook) getIntent().getSerializableExtra("ContactBook");
        FloatingActionButton done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText text = findViewById(R.id.name_input);
                String name = text.getText().toString();

                text = findViewById(R.id.number_input);
                String number = text.getText().toString();

                text = findViewById(R.id.address_input);
                String address = text.getText().toString();

                //Check if name and number are valid
                if (name.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter a valid name !", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (number.isEmpty() || !number.matches("\\+?[0-9]+$") || number.length() < 9 || number.length() > 14) {
                    Toast.makeText(getApplicationContext(), "Enter a valid number !", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Add contact
                book.add(name, number);
                book.addAddress(name, address);

                // Code to return the updated contactBook to the MainActivity
                Intent home = new Intent();
                home.putExtra("UpdatedBook", book);
                setResult(RESULT_OK, home);
                finish();

            }
        });


    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
