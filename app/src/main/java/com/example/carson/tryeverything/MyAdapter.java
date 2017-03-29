package com.example.carson.tryeverything;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.carson.tryeverything.Global.drawableArray;

/**
 * Created by Carson on 2016-12-28.
 */

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> data;
    private static LayoutInflater inflater = null;

    //accepts data
    public MyAdapter(Context context, ArrayList<String> data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {

        //return data.get(position);
        switch (position) {
            case 0:
                TabFragment1 tab1 = new TabFragment1();
                return tab1;
            default:
                return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null) {
            vi = inflater.inflate(R.layout.activity_listview, parent, false);
        }
        TextView text = (TextView) vi.findViewById(R.id.textName);
        text.setText(data.get(position));

        //set item image
        ImageView image = (ImageView) vi.findViewById(R.id.itemImage);
        Integer imageId = drawableArray[position];
        Drawable drawable = ContextCompat.getDrawable(context,imageId);
        image.setImageDrawable(drawable);
        //vi.setBackgroundResource(drawableArray[position]);


        return vi;
    }

}

