package com.sudoku;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class SudokuActivity extends AppCompatActivity {
    GridView gridview;
    AssetManager assetManager;
    int puzzleLevel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        assetManager = getAssets();
        gridview = (GridView) findViewById(R.id.gridView);


        try {
            InputStream inputStream = assetManager.open("easy_1.in");
            gridview.setAdapter(new TextAdapter(this, inputStream));
        } catch (IOException e) {
            Log.i("Yo", "OH NO");
        }

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

        String id = view.getResources().getResourceEntryName(view.getId());
        switch (id) {
            case "easy":
                try {
                    InputStream inputStream = assetManager.open("easy_1.in");
                    gridview.setAdapter(new TextAdapter(this, inputStream));
                    puzzleLevel = 0;
                } catch (IOException e) {
                    Log.i("Yo", "OH NO");

                }
                break;
            case "medium":
                try {
                    InputStream inputStream = assetManager.open("medium_1.in");
                    gridview.setAdapter(new TextAdapter(this, inputStream));
                    puzzleLevel = 1;
                } catch (IOException e) {
                    Log.i("Yo", "OH NO");

                }
                break;
            case "hard":
                try {
                    InputStream inputStream = assetManager.open("hard_1.in");
                    gridview.setAdapter(new TextAdapter(this, inputStream));
                    puzzleLevel = 2;
                } catch (IOException e) {
                    Log.i("Yo", "OH NO");

                }
                break;
        }

    }

    public void solvePuzzle(View view) {
        TextAdapter textAdapter = (TextAdapter) gridview.getAdapter();
        SudokuSolver sudokuSolver = new SudokuSolver();
        sudokuSolver.solve(0, 0, textAdapter.grid2d);
        int x = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextView textView = (TextView) gridview.getChildAt(x);
                textView.setText(String.valueOf(textAdapter.grid2d[i][j]));
                x++;
            }
        }

        checkSolution(view);
    }

    public void checkSolution(View view) {
        boolean win = true;

        int[] userArray = new int[81];
        int[] solvedArray = new int[81];

        TextAdapter textAdapter = (TextAdapter) gridview.getAdapter();

        for (int i = 0; i < 81; i++) {
            EditText textView = (EditText) gridview.getChildAt(i);
            String text = String.valueOf(textView.getText());
            if (text.equals("")){
                userArray[i] = 0;
            } else {
                userArray[i] = Integer.valueOf(text);
            }
        }

        SudokuSolver sudokuSolver = new SudokuSolver();

        sudokuSolver.solve(0, 0, textAdapter.grid2d);
        int x = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                solvedArray[x] = textAdapter.grid2d[i][j];
                x++;
            }
        }

        for (int i = 0; i < 81; i++) {
            EditText textView = (EditText) gridview.getChildAt(i);

            if(textView.isEnabled()) {
                if (solvedArray[i] != userArray[i]) {
                    textView.setTextColor(Color.RED);
                    win = false;
                } else {
                    textView.setTextColor(Color.BLACK);
                }
            }
        }


        if(win) {
            Toast.makeText(view.getContext(), "You Win!",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(view.getContext(), "Wrong!!",
                    Toast.LENGTH_LONG).show();
        }
    }
}




