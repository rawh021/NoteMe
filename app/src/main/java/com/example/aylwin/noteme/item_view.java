package com.example.aylwin.noteme;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aylwin.noteme.Model.Catatan;

import java.io.Serializable;

import static com.example.aylwin.noteme.MainActivity.dbHelper;
import static com.example.aylwin.noteme.R.layout.view_item;


/**
 * Created by Aylwin on 8/9/2017.
 */

public class item_view extends AppCompatActivity {
    EditText txtNote;
    Button save, cancel, delete;
    Catatan cat;
    boolean cat_baru;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(view_item);
            txtNote = (EditText) findViewById(R.id.note);
            save = (Button) findViewById(R.id.btn_save);
            cancel = (Button) findViewById(R.id.btn_cancel);
            delete = (Button) findViewById(R.id.btn_delete);

            if (getIntent().getSerializableExtra("datane") != null) {
                cat = (Catatan) getIntent().getSerializableExtra("datane");
                setTitle(cat.getTitle());
                txtNote.setText(cat.getNote());
                cat_baru = false;
            } else {
                cat_baru=true;
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Set Your Title");
                final EditText input = new EditText(this);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String in = ""+input.getText();
                      if(in.equals("")){
                          Toast.makeText(getApplicationContext(),"Please Insert Note Title",Toast.LENGTH_LONG).show();
                          setTitle("Note");
                      }else{
                          setTitle(input.getText());
                      }
                    }
                });
                alert.show();
                delete.setEnabled(false);
            }

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               Catatan x = new Catatan(getTitle().toString(), txtNote.getText().toString());
                String Title = getTitle().toString();
                if (!(Title.equals(""))) {
                    if (cat_baru) {
                            dbHelper.insertBarang(x);
                    } else {
                        cat.setNote("" + cat.getNote());
                        int z = dbHelper.updateBarang(x, cat.getId());
                        if (z<=0){
                            Toast.makeText(getApplicationContext(),"Gagal di update",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getApplicationContext(),"Berhasil di update",Toast.LENGTH_LONG).show();
                        }
                    }
                    Intent i = new Intent(item_view.this, MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Please Insert Note Title",Toast.LENGTH_LONG).show();
                    AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());
                    alert.setTitle("Set Your Title");
                    final EditText input = new EditText(getApplicationContext());
                    alert.setView(input);
                    input.setText(getTitle());
                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            String in = ""+input.getText();
                            if(in.equals("")){
                                Toast.makeText(getApplicationContext(),"Please Insert Note Title",Toast.LENGTH_LONG).show();
                                setTitle("Note");
                            }else{
                                setTitle(input.getText());
                            }
                        }
                    });
                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    });
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (cat != null){
                    int z = dbHelper.deleteBarang(cat);
                    if (z<=0){
                        Toast.makeText(item_view.this,"Gagal di hapus - "+z,Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(item_view.this, "Berhasil di hapus", Toast.LENGTH_LONG).show();
                    }
                }else{
                   Toast.makeText(item_view.this,"Data tidak ada",Toast.LENGTH_LONG).show();
                }
                Intent i = new Intent(item_view.this,MainActivity.class);
                startActivity(i);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(item_view.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_view, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);

                alert.setTitle("Set Your Title");
                final EditText input = new EditText(this);
                alert.setView(input);
                input.setText(getTitle());

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String in = ""+input.getText();
                        if(in.equals("")){
                            Toast.makeText(getApplicationContext(),"Please Insert Note Title",Toast.LENGTH_LONG).show();
                            setTitle("Note");
                        }else{
                            setTitle(input.getText());
                        }
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });
                alert.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
