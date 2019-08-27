package com.example.contacts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewContact extends AppCompatActivity {

    String name;
    ContactBook book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_contact);

        book = (ContactBook) getIntent().getSerializableExtra("ContactBook");
        name = getIntent().getStringExtra("Name");

        getSupportActionBar().setTitle("View Contact - " + name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //View Name
        TextView view = findViewById(R.id.view_name);
        view.setText("\n" + name);
        //View Number
        view = findViewById(R.id.view_number);
        view.setText("Number - " + this.book.getNumber(name));
        //View Address
        view = findViewById(R.id.view_address);
        if (book.getAddress(name) == null) view.setText("Address not available");
        else view.setText(book.getAddress(name));


        findViewById(R.id.call_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:" + book.getNumber(name))));
            }
        });

        findViewById(R.id.message_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_SENDTO).setData(Uri.parse("smsto:" + book.getNumber(name))));
            }
        });

        findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
    }


    private void delete() {

        book.delete(name);
        Intent home = new Intent();
        home.putExtra("UpdatedBook", book);
        setResult(RESULT_OK, home);
        finish();


    }


    @Override
    public void onBackPressed() {
        finish();
    }


}
