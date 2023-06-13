package com.example.fastsms;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.fio) {
            AlertDialog.Builder dialog = new
                    AlertDialog.Builder(MainActivity.this);
            dialog.setMessage("Итоговое домашнее задание \n\nПриложение FastSMS.\n\nАвтор - Давиденко Алексей Ильич, БПИ214.");
            dialog.setTitle("О программе");
            dialog.setNeutralButton("OK", (dialog1, which) -> dialog1.dismiss());
            dialog.setIcon(R.drawable.icon);
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();

        }
        return super.onOptionsItemSelected(item);
    }

    public static int number_of_contacts = 0;
    public static ArrayList<String> names = new ArrayList<>();
    public static ArrayList<String> numbers = new ArrayList<>();
    Button add;
    Button delete;
    Button write;
    Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exit = findViewById(R.id.button4);
        exit.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setMessage("Вы точно хотите выйти?");
            dialog.setCancelable(false);
            dialog.setPositiveButton("Да", (dialog1, which) -> finishAffinity());
            dialog.setNegativeButton("Нет", (dialog12, which) -> dialog12.cancel());
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();
        });
        add = findViewById(R.id.button);
        add.setOnClickListener(v -> {
            Intent intent_add = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent_add);
        });
        delete = findViewById(R.id.button2);
        delete.setOnClickListener(v -> {
            Intent intent_delete = new Intent(MainActivity.this, DeleteActivity.class);
            startActivity(intent_delete);
        });
        write = findViewById(R.id.button3);
        write.setOnClickListener(v -> {
            Intent intent_send = new Intent(MainActivity.this, SendActivity.class);
            startActivity(intent_send);
        });
    }
}