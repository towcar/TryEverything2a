package com.example.carson.tryeverything;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.carson.tryeverything.Global.array;
import static com.example.carson.tryeverything.Global.arrayInt;

/**
 * Created by Carson on 2017-02-25.
 */

public class TabFragment1 extends android.support.v4.app.Fragment {

    public ArrayList<String> mylist1 = new ArrayList<>();

    // newInstance constructor for creating fragment with arguments
    public static TabFragment1 newInstance() {
        TabFragment1 fragmentFirst = new TabFragment1();
        Bundle args = new Bundle();
        //pass a list or something?
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mylist1.clear();
        for (int i = 0; i < arrayInt.length; i++) {

                mylist1.add(array[i]);

        }


        final ListView listView = (ListView) rootView.findViewById(R.id.text_label);

        MyAdapter adapter = new MyAdapter(getContext(), mylist1);

        listView.setAdapter(adapter);

        //Click on Item
        //selecting items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //goes to new activity passing the item name
                Intent intent = new Intent(view.getContext(), MapsActivity.class);
                Bundle b = new Bundle();

                //get text for current item
                //String textGet = listView.getItemAtPosition(position).toString();
                String textGet = mylist1.get(position);
                //put text into a bundle and add to intent
                intent.putExtra("text", textGet);

                //get position to carry integer
                intent.putExtra("position", position);

                intent.putExtras(b);

                //begin other activity
                startActivity(intent);
            }
        });

        return rootView;
    }
}