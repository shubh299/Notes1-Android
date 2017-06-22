package com.example.shubham.notes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {
    EditText noteTitle;
    EditText noteContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        noteTitle = (EditText) findViewById(R.id.note_title);
        noteContent = (EditText) findViewById(R.id.note_content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                break;
            case R.id.delete_note:
                break;
            default:
                break;
        }
        return true;
    }

    private void saveNote() {
        Note note = new Note(System.currentTimeMillis() , noteTitle.getText().toString() , noteContent.getText().toString() );
        if(NoteOperations.saveNote(this , note)) {
            Toast.makeText(this , "Note Saved", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
            Toast.makeText(this , "Cannot save note, please free your disk space ", Toast.LENGTH_SHORT).show();
    }
}
