package com.example.shubham.notes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Shubham on 29-07-2017.
 */

public class NoteAdapter extends ArrayAdapter<Note> {
    public NoteAdapter (Context context, int resource, ArrayList<Note> notes){
        super(context,resource,notes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.note_view,null);
        }

        Note note =getItem(position);
        if(note!=null){
            TextView title = (TextView) convertView.findViewById(R.id.note_title);
            TextView date = (TextView) convertView.findViewById(R.id.note_date);
            TextView content = (TextView) convertView.findViewById(R.id.note_content);
            title.setText(note.getTitle());
            date.setText(note.getDateTimeFormatted(getContext()));
            content.setText(note.getNote());
        }
        return convertView;
    }
}
