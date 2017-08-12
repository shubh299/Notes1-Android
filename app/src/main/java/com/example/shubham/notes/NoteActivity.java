package com.example.shubham.notes;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {
    EditText noteTitle;
    EditText noteContent;
    String noteFileName;
    Note loadedNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        noteTitle = (EditText) findViewById(R.id.note_title);
        noteContent = (EditText) findViewById(R.id.note_content);
        noteFileName = getIntent().getStringExtra("NOTE_FILE");
        if (noteFileName!=null && !noteFileName.isEmpty()){
            loadedNote = NoteOperations.getNoteByName(this,noteFileName);
            if(loadedNote!=null) {
                noteTitle.setText(loadedNote.getTitle());
                noteContent.setText(loadedNote.getNote());
            }
        }
    }

    @Override
    public void onBackPressed() {
        saveNote();
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
                deleteNote();
                break;
            case R.id.share_note:
                shareNote();
            default:
                break;
        }
        return true;
    }

    private void deleteNote(){
        if(loadedNote == null){
            finish();
        }
        else{
            AlertDialog.Builder dialog = new AlertDialog.Builder(this).setTitle("Delete Note").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    NoteOperations.deleteNote(getApplicationContext(),loadedNote.getDateTime()+NoteOperations.fileExtension);
                    Toast.makeText(getApplicationContext(),"Note Deleted",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }).setNegativeButton("NO",null).setCancelable(true);
            dialog.show();
        }
    }

    private void saveNote() {
        Note note;

        if(noteTitle.getText().toString().isEmpty()&& noteContent.getText().toString().isEmpty()){
            finish();
            return;
        }

        if(loadedNote==null){
            note = new Note(System.currentTimeMillis() , noteTitle.getText().toString() , noteContent.getText().toString() );
        }
        else{
            note = new Note(System.currentTimeMillis() , noteTitle.getText().toString() , noteContent.getText().toString() );
            NoteOperations.deleteNote(getApplicationContext(),loadedNote.getDateTime()+NoteOperations.fileExtension);
        }

        if(NoteOperations.saveNote(this , note)) {
            //Toast.makeText(this , "Note Saved", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
            Toast.makeText(this , "Cannot save note, please free your disk space ", Toast.LENGTH_SHORT).show();
    }

    private void shareNote(){
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        String contentShare = noteTitle.getText().toString() + "\n" + noteContent.getText().toString();
        if(contentShare.isEmpty())
            Toast.makeText(this,"Nothing to share",Toast.LENGTH_SHORT).show();
        //share.putExtra(Intent.EXTRA_SUBJECT,noteTitle.getText().toString());
        //share.putExtra(Intent.EXTRA_TEXT,noteContent.getText().toString());
        else {
            share.putExtra(Intent.EXTRA_TEXT, contentShare);
            startActivity(Intent.createChooser(share, "Share Via"));
        }
    }
}
