package com.example.fastsms;

import static com.example.fastsms.MainActivity.names;
import static com.example.fastsms.MainActivity.number_of_contacts;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class DeleteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        ListView contactList = findViewById(R.id.ListView);
        contactList.setAdapter(new ArrayAdapter<>(this, R.layout.contact, R.id.textView2, names));
        ArrayList<String> names_1 = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            if (!Objects.equals(names.get(i), "")) {
                names_1.add(names.get(i));
            }
        }
        names = names_1;
        ImageButton back = findViewById(R.id.imageButton45);
        back.setOnClickListener(v -> {
            Intent main = new Intent(DeleteActivity.this, MainActivity.class);
            startActivity(main);
        });
        contactList.setOnItemClickListener((parent, itemClicked, position, id) -> {
            Toast.makeText(getApplicationContext(), "Удалён контакт " + names.get(position), Toast.LENGTH_LONG).show();
            number_of_contacts--;
            names.set(position, "");
            MediaPlayer mediaPlayer = MediaPlayer.create(DeleteActivity.this, R.raw.delete);
            mediaPlayer.start();
        });
    }
}