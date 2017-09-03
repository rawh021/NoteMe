package com.example.aylwin.noteme;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.aylwin.noteme.Database.DBHelper;
import com.example.aylwin.noteme.Model.Catatan;

import java.util.List;


public class MainActivity extends AppCompatActivity  {
    public static DBHelper dbHelper;
    private List<Catatan> data;
    private ListView list;
    ArrayAdapter<String> adapter;
    private FloatingActionButton btnTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        list = (ListView) findViewById(R.id.list);
        btnTambah = (FloatingActionButton) findViewById(R.id.Add);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,item_view.class));
            }
        });

        this.data = dbHelper.getAllBarang(this);
        final String[] arrayString = new String[data.size()];
        int i = 0;
        for (Catatan b : data){
            arrayString[i] = b.getTitle();
            i++;
        }

        adapter = new ArrayAdapter<String> (this, R.layout.item_list, R.id.item_infor, arrayString);

        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Intent intent = new Intent(MainActivity.this, item_view.class);
                    intent.putExtra("datane", data.get(position));
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e+"",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
    moveTaskToBack(true);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editing, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        android.widget.SearchView searchView = (android.widget.SearchView)item.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);


        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
