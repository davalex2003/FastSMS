package com.example.fastsms;

import static com.example.fastsms.MainActivity.names;
import static com.example.fastsms.MainActivity.number_of_contacts;
import static com.example.fastsms.MainActivity.numbers;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class AddActivity extends AppCompatActivity {
    private static final int VR_REQUEST = 999;
    ImageButton sound;
    EditText name, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ImageButton back = findViewById(R.id.imageButton4);
        name = findViewById(R.id.EditText1);
        number = findViewById(R.id.editText);
        sound = findViewById(R.id.imageButton3);
        sound.setOnClickListener(v -> {
            listenToSpeech();
        });
        back.setOnClickListener(v -> {
            Intent main = new Intent(AddActivity.this, MainActivity.class);
            startActivity(main);
        });
        Button add = findViewById(R.id.button5);
        add.setOnClickListener(v -> {
            names.add(name.getText().toString());
            numbers.add(number.getText().toString());
            number_of_contacts++;
            Toast.makeText(getApplicationContext(), "Добавлен контакт " + name.getText().toString(), Toast.LENGTH_LONG).show();
            MediaPlayer mediaPlayer = MediaPlayer.create(AddActivity.this, R.raw.add);
            mediaPlayer.start();
            Intent main = new Intent(AddActivity.this, MainActivity.class);
            startActivity(main);
        });
    }

    private void listenToSpeech() {
        Intent listenIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        listenIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                Objects.requireNonNull(getClass().getPackage()).getName());
        listenIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say a word!");
        listenIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        listenIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);
        startActivityForResult(listenIntent, VR_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VR_REQUEST && resultCode == RESULT_OK) {
            name.setText(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}