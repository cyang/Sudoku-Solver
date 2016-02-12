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

public class TextAdapter extends BaseAdapter {
    private Context mContext;

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


        } else {
            editText = (EditText) convertView;
        }

//        setEasyPuzzle();
        editText.setText(String.valueOf(position%10));


        return editText;
    }

//    public void setEasyPuzzle(){
//        textView.setText()
//    }
}

