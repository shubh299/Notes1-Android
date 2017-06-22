package com.example.shubham.notes;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

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
}
