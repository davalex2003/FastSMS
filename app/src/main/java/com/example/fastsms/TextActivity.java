package com.example.fastsms;

import static com.example.fastsms.MainActivity.names;
import static com.example.fastsms.MainActivity.numbers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class TextActivity extends AppCompatActivity {
    EditText text;
    Button copy;
    Button send_sms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        ImageButton back = findViewById(R.id.imageButton45678);
        back.setOnClickListener(v -> {
            Intent main = new Intent(TextActivity.this, SMS.class);
            startActivity(main);
        });
        text = findViewById(R.id.editTextTextPersonName2);
        text.setText(SMS.full_name + SMS.full_text);
        copy = findViewById(R.id.button11);
        send_sms = findViewById(R.id.button12);
        copy.setOnClickListener(v -> {
            ClipboardManager myClipboard;
            myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
            ClipData myClip;
            String text_1 = text.getText().toString();
            myClip = ClipData.newPlainText("text", text_1);
            myClipboard.setPrimaryClip(myClip);
            Toast.makeText(getApplicationContext(), "Текст выше скопирован", Toast.LENGTH_LONG).show();
        });
        send_sms.setOnClickListener(v -> {
            String message = text.getText().toString();
            int a = names.indexOf(SendActivity.name);
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + numbers.get(a)));
            smsIntent.putExtra("sms_body", message);
            startActivity(smsIntent);
        });
    }
}