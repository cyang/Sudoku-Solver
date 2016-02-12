package com.sudoku;

import android.app.Activity;
import android.content.Context;
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

import java.lang.reflect.Array;
import java.util.HashMap;

public class TextAdapter extends BaseAdapter {
    private Context mContext;
    private HashMap<Integer, Integer> puzzle1 = new HashMap<>();
    private Integer[] puzzle1Positions = {1, 3 ,4, 6, 11, 12, 13, 19, 20, 22, 23, 28, 29, 30, 32, 33, 39, 50, 51, 52, 54, 55, 62, 63, 65, 67, 71, 73, 76, 79};
    private Integer[] puzzle1Given = {9, 5, 6, 7, 4, 3, 9, 5, 2, 8, 7, 1, 7, 6, 4, 5, 2, 8, 4, 7, 3, 1, 9, 9, 6, 2, 3, 8, 1, 2};

    public TextAdapter(Context c) {
        mContext = c;
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
            editText.setPadding(8, 8, 8, 8);
            editText.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);

            GradientDrawable gd = new GradientDrawable();
            gd.setColor(0xFFFFFFFF); // Changes this drawbale to use a single color instead of a gradient
            gd.setCornerRadius(5);
            gd.setStroke(1, 0xFF000000);
            editText.setBackground(gd);

            InputFilter[] FilterArray = new InputFilter[1];
            FilterArray[0] = new InputFilter.LengthFilter(1);
            editText.setFilters(FilterArray);


        } else {
            editText = (EditText) convertView;
        }

        createPuzzle1();
        if(puzzle1.containsKey(position)) {
            editText.setText(String.valueOf(puzzle1.get(position)));
        } else {
            editText.setText("");
        }

        return editText;
    }

    public void createPuzzle1(){
        for (int i = 0; i < puzzle1Positions.length; i++) {
            puzzle1.put(puzzle1Positions[i], puzzle1Given[i]);
        }

    }

//    public void setEasyPuzzle(){
//        textView.setText()
//    }
}

