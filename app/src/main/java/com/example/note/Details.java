package com.example.note;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.note.databinding.ActivityDetailsBinding;

public class Details extends AppCompatActivity {

    Toolbar toolbar;
    NoteDatabase db;
    Note note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent=getIntent();
        Long id=intent.getLongExtra("ID",0);

        db=new NoteDatabase(this);
        note=db.getNote(id);
        getSupportActionBar().setTitle(note.getTitle());
        TextView details=findViewById(R.id.textView_ID);
        details.setText(note.getContent());
        details.setMovementMethod(new ScrollingMovementMethod());



        FloatingActionButton fab=findViewById(R.id.fab);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteNote(note.getId());
                Toast.makeText(Details.this, "Note is deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.edit_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.edit_ID){
            Intent intent=new Intent(Details.this,EditActivity.class);
            intent.putExtra("ID",note.getId());
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}