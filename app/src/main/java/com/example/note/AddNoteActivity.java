package com.example.note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {
    Toolbar toolbar;

    EditText titleNode,detailsNode;

    Calendar c;
    String todaysDate;
    String currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Node");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titleNode=findViewById(R.id.noteTitle);
        detailsNode=findViewById(R.id.noteDetails);






        titleNode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()!=0){
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });

        c=Calendar.getInstance();
        todaysDate=c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH);

        currentTime=pad(c.get(Calendar.HOUR))+":"+pad(c.get(Calendar.MINUTE));

        Log.d("calender: ", "Date and Time "+todaysDate+" and "+currentTime);



    }




    private String pad(int i) {
        if (i<10){
            return "0"+i;
        }

        return String.valueOf(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.save_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.save){



            Note note=new Note(titleNode.getText().toString(),detailsNode.getText().toString(),todaysDate,currentTime);

            NoteDatabase db=new NoteDatabase(this);
            db.addNote(note);
            Toast.makeText(AddNoteActivity.this, "Save", Toast.LENGTH_SHORT).show();
            onBackPressed();
            goToMain();
        }
        if (item.getItemId()==R.id.delete){
            Toast.makeText(AddNoteActivity.this, "delete", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }







    private void goToMain() {
        Intent intent=new Intent(AddNoteActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}