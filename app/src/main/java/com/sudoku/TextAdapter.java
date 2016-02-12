package com.sudoku;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.GradientDrawable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.HashMap;

public class TextAdapter extends BaseAdapter {
    private Context mContext;
    private Integer[] grid = new Integer[81];

    public TextAdapter(Context c, InputStream inputStream) throws IOException {
        mContext = c;

        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        String line;


        int i = 0;
        while((line = in.readLine()) != null){
            String[] lineArray = line.split(" ");
            for (String element : lineArray) {
                if (element.equals("*")) {
                    grid[i] = null;
                } else {
                    grid[i] = Integer.valueOf(element);
                }
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
            textView.setPadding(8, 8, 8, 8);
            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            //textView.setInputType(InputType.TYPE_CLASS_NUMBER);


            textView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                        if (grid[position] == null) {
                            grid[position] = 0;
                            textView.setText(String.valueOf(grid[position]));
                        }
                        else {
                            if (grid[position] >= 9)
                                grid[position] = 0;
                            else
                                grid[position]++;
                            textView.setText(String.valueOf(grid[position]));
                        }
                    }
                    return true;
                }
            });


            GradientDrawable gd = new GradientDrawable();
            gd.setColor(0xFFFFFFFF); // Changes this drawbale to use a single color instead of a gradient
            gd.setCornerRadius(5);
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

