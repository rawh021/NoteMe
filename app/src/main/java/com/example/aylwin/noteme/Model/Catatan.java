package com.example.aylwin.noteme.Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * Created by Aylwin on 8/9/2017.
 */

public class Catatan implements Serializable {

    public Catatan(){

    }


    public Catatan(String Title, String Note){
        this.setTitle(Title);
        this.setNote(Note);
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    private String Title,Note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

}
