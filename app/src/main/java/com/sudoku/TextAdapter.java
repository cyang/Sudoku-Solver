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
                    grid[i] = -1;
                } else {
                    grid[i] = Integer.valueOf(element);
                }
                Log.i("YO", String.valueOf(grid[i]));

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
    public View getView(int position, View convertView, ViewGroup parent) {
        EditText editText;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            editText = new EditText(mContext);
            editText.setLayoutParams(new GridView.LayoutParams(85, 85));
            editText.setPadding(0, 0, 0, 0);
            editText.setGravity(Gravity.CENTER);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);

            GradientDrawable gd = new GradientDrawable();
            gd.setStroke(1, 0xFF000000);
            editText.setBackground(gd);

            InputFilter[] FilterArray = new InputFilter[1];
            FilterArray[0] = new InputFilter.LengthFilter(1);
            editText.setFilters(FilterArray);


        } else {
            editText = (EditText) convertView;
        }

        editText.setText(String.valueOf(grid[position]));


        return editText;
    }

}

