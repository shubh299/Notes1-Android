package com.example.shubham.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView notesListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesListView = (ListView) findViewById(R.id.list_notes);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add_note :
                Intent intent = new Intent(MainActivity.this , NoteActivity.class);
                startActivity(intent);
                break;
            default : break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        notesListView.setAdapter(null);
        ArrayList<Note> notes = NoteOperations.getAllSavedNotes(this);
        if(notes == null || notes.size()==0){
            //Toast.makeText(this,"No notes saved",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            NoteAdapter noteAdapter = new NoteAdapter(this,R.layout.note_view,notes);
            notesListView.setAdapter(noteAdapter);
            notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String fileName = ((Note)notesListView.getItemAtPosition(position)).getDateTime() + NoteOperations.fileExtension;
                    Intent viewNoteIntent = new Intent(getApplicationContext(),NoteActivity.class);
                    viewNoteIntent.putExtra("NOTE_FILE",fileName);
                    startActivity(viewNoteIntent);
                }
            });
        }
    }
}
