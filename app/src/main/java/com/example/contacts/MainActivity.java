package com.example.contacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    ListView list;
    ContactBook contactBook;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Contacts                                       -SreekX");

        list = findViewById(R.id.list);
        contactBook = this.loadContacts("save");


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, contactBook.getNames());
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int itemPosition, long itemId) {
                String contactName = listView.getItemAtPosition(itemPosition).toString();
                Intent i = new Intent(MainActivity.this, ViewContact.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("ContactBook", contactBook);
                i.putExtra("Name", contactName);
                startActivityForResult(i, 1);
            }
        });


        FloatingActionButton add = findViewById(R.id.add_button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddContact.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("ContactBook", contactBook);
                startActivityForResult(intent, 1);


            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        this.saveContacts("save");
        this.contactBook = this.loadContacts("save");
        adapter.clear();
        adapter.addAll(contactBook.getNames());


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                this.contactBook = (ContactBook) data.getSerializableExtra("UpdatedBook");
            }
        }
    }


    public ContactBook loadContacts(String fileName) {

        try {
            FileInputStream fis = MainActivity.this.openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            ContactBook book = (ContactBook) is.readObject();
            is.close();
            fis.close();
            return book;
        } catch (FileNotFoundException e) {
            return new ContactBook();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }


    public void saveContacts(String fileName) {

        try {
            FileOutputStream fos = MainActivity.this.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(this.contactBook);
            os.close();
            fos.close();
        } catch (Exception e) {

            e.printStackTrace();

        }


    }


}
