package com.example.carson.tryeverything;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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
        //this.mNumOfTabs = NumOfTabs;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        //return mNumOfTabs;
        return data.size();
    }

    @Override
    public Object getItem(int position) {

        //return ArrayListFragment.newInstance(position);

        //old system, switch is updated way of showing tabfragments
        //return data.get(position);
        switch (position) {
            case 0:
                TabFragment1 tab1 = new TabFragment1();
                return tab1;
            case 1:
                TabFragment2 tab2 = new TabFragment2();
                return tab2;
            case 2:
                TabFragment3 tab3 = new TabFragment3();
                return tab3;
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

        return vi;
    }
}

