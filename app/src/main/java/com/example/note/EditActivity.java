package com.example.note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


public class EditActivity extends AppCompatActivity {
    Toolbar toolbar;

    EditText titleNode,detailsNode;

    Calendar c;
    String todaysDate;
    String currentTime;

    NoteDatabase db;
    Note note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent=getIntent();
        Long id=intent.getLongExtra("ID",0);

        db=new NoteDatabase(this);
        note=db.getNote(id);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(note.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        titleNode=findViewById(R.id.noteTitle);
        detailsNode=findViewById(R.id.noteDetails);

        titleNode.setText(note.getTitle());
        detailsNode.setText(note.getContent());

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
            note.setTitle(titleNode.getText().toString());
            note.setContent(detailsNode.getText().toString());
            int id=db.editNote(note);
            if (id==note.getId()){
                Toast.makeText(EditActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(EditActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
            }

            Intent intent=new Intent(EditActivity.this,Details.class);
            intent.putExtra("ID",note.getId());
            startActivity(intent);
            finish();
        }
        if (item.getItemId()==R.id.delete){
            Toast.makeText(EditActivity.this, "delete", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMain() {
        Intent intent=new Intent(EditActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}