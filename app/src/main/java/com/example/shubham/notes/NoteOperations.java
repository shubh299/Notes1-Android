package com.example.shubham.notes;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Shubham on 23-06-2017.
 */

public class NoteOperations {

    public static final String fileExtension = ".bin";

    public static boolean saveNote (Context context, Note note) {
        String fileName = String.valueOf(note.getDateTime())+fileExtension;
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;

        try{
            fileOutputStream = context.openFileOutput(fileName, context.MODE_PRIVATE);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(note);
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ArrayList<Note> getAllSavedNotes (Context context) {
        ArrayList<Note> notes = new ArrayList<>();

        File filesDir = context.getFilesDir();
        ArrayList<String> noteFiles = new ArrayList<>();

        for(String file : filesDir.list()){
            if(file.endsWith(fileExtension))
                noteFiles.add(file);
        }

        FileInputStream fis;
        ObjectInputStream ois;

        for(int i=0;i<noteFiles.size();i++){
           try {
               fis = context.openFileInput(noteFiles.get(i));
               ois = new ObjectInputStream(fis);
               notes.add((Note)ois.readObject());
               fis.close();
               ois.close();
           }
           catch (Exception e){
               e.printStackTrace();
               return null;
           }
        }
        Collections.reverse(notes);
        return notes;
    }

    public static Note getNoteByName(Context context,String fileName){
        File file = new File(context.getFilesDir(),fileName);
        Note note;
        if (file.exists()){
            FileInputStream fis;
            ObjectInputStream ois;

            try{
                fis = context.openFileInput(fileName);
                ois = new ObjectInputStream(fis);
                note = (Note) ois.readObject();
                fis.close();
                ois.close();
            }
            catch (Exception e){
                e.printStackTrace();
                return null;
            }
            return note;
        }
        return null;
    }

    public static void deleteNote(Context context, String fileName){
        File dir = context.getFilesDir();
        File file = new File(dir,fileName);
        if(file.exists()){
            file.delete();
        }
    }
}
