package com.example.shubham.notes;

import android.content.Context;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Shubham on 22-06-2017.
 */

public class Note implements Serializable {
    private long dateTime;
    private String title;
    private String note;

    public Note(long dateTime, String title, String note) {
        this.dateTime = dateTime;
        this.title = title;
        this.note = note;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDateTimeFormatted(Context context) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd, yyyy kk:mm",context.getResources().getConfiguration().locale);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(new Date(dateTime));
    }
}
