package com.sudoku;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class SudokuActivity extends AppCompatActivity {
    GridView gridview;
    AssetManager assetManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);



        assetManager = getAssets();
        gridview = (GridView) findViewById(R.id.gridView);


        try {
            InputStream inputStream = assetManager.open("puzzle1.in");
            gridview.setAdapter(new TextAdapter(this, inputStream));
        } catch (IOException e) {
            Log.i("Yo", "OH NO");
        }

//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                Toast.makeText(SudokuActivity.this, "" + position,
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sudoku, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void populateGrid(View view) {
        String id = view.getResources().getResourceName(view.getId());
        switch (id) {
            case "easy":
                try {
                    InputStream inputStream = assetManager.open("puzzle1.in");
                    gridview.setAdapter(new TextAdapter(this, inputStream));
                } catch (IOException e) {
                    Log.i("Yo", "OH NO");

                }
        }

    }

}




