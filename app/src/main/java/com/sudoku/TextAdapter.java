package com.sudoku;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.InputFilter;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TextAdapter extends BaseAdapter {
    private Context mContext;
    public Integer[] grid = new Integer[81];
    public Integer [][] grid2d = new Integer[9][9];

    public TextAdapter(Context c, InputStream inputStream) throws IOException {
        mContext = c;

        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String line;


        int i = 0;
        int row = 0, col = 0;
        while((line = in.readLine()) != null){
            String[] lineArray = line.split(" ");
            for (String element : lineArray) {
                if (element.equals("*")) {
                    grid[i] = null;
                    if (col % 9 == 0 && col != 0) {
                        col = 0;
                        row++;
                    }
                    grid2d[row][col] = 0;
                } else {
                    if (col % 9 == 0 && col != 0) {
                        col = 0;
                        row++;
                    }
                    grid[i] = Integer.valueOf(element);
                    grid2d[row][col] = Integer.valueOf(element);
                }
                col++;
                i++;
            }
        }
    }

    public int getCount() {
        return 81;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        final TextView textView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            textView = new EditText(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(85, 85));
            textView.setPadding(0, 0, 0, 0);
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            //textView.setInputType(InputType.TYPE_CLASS_NUMBER);


            textView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                        if (grid[position] == null) {
                            grid[position] = 1;
                            textView.setText(String.valueOf(grid[position]));
                        }
                        else {
                            if (grid[position] >= 9)
                                grid[position] = 1;
                            else
                                grid[position]++;
                            textView.setText(String.valueOf(grid[position]));
                        }
                    }
                    return true;
                }
            });


            GradientDrawable gd = new GradientDrawable();
            gd.setStroke(1, 0xFF000000);
            textView.setBackground(gd);

            InputFilter[] FilterArray = new InputFilter[1];
            FilterArray[0] = new InputFilter.LengthFilter(1);
            textView.setFilters(FilterArray);


        } else {
            textView = (EditText) convertView;
        }
        if (grid[position] != null)
            textView.setText(String.valueOf(grid[position]));

        return textView;
    }
}

