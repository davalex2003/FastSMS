package com.example.fastsms;

import static com.example.fastsms.MainActivity.names;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class SendActivity extends AppCompatActivity {
    public static String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        ListView contactList = findViewById(R.id.ListView2);
        contactList.setAdapter(new ArrayAdapter<>(this, R.layout.contact, R.id.textView2, names));
        ImageButton back = findViewById(R.id.imageButton456);
        back.setOnClickListener(v -> {
            Intent main = new Intent(SendActivity.this, MainActivity.class);
            startActivity(main);
        });
        contactList.setOnItemClickListener((parent, itemClicked, position, id) -> {
            name = names.get(position);
            Intent sms = new Intent(SendActivity.this, SMS.class);
            startActivity(sms);
        });
    }
}