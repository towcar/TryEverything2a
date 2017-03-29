package com.example.carson.tryeverything;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import static com.example.carson.tryeverything.Global.array;
import static com.example.carson.tryeverything.Global.arrayInt;
import static com.example.carson.tryeverything.R.id.container;

/**
 * Created by Carson on 2017-02-25.
 */

public class TabFragment2 extends android.support.v4.app.Fragment {

    public ArrayList<String> mylist2 = new ArrayList<>();

    ListView listView;
    MyAdapter adapter;
    View rootView1;
    LayoutInflater inflater1;
    ViewGroup container1;
    int g = 0;

    // newInstance constructor for creating fragment with arguments
    public static TabFragment2 newInstance() {
        TabFragment2 fragmentFirst = new TabFragment2();
        Bundle args = new Bundle();
        //pass a list or something?
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //listView = (ListView) rootView.findViewById(R.id.text_label);

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container1 = container;
        inflater1 = inflater;
        rootView1 = inflater1.inflate(R.layout.fragment_main, container, false);
        Log.e("Frag2", "Refresh Test");
        mylist2.clear();
        for (int i = 0; i < arrayInt.length; i++) {
            if (arrayInt[i] == 1) {
                mylist2.add(array[i]);
            }
        }
        final ListView listView = (ListView) rootView1.findViewById(R.id.text_label);

        MyAdapter adapter = new MyAdapter(getContext(), mylist2);

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
                String textGet = listView.getItemAtPosition(position).toString();
                //put text into a bundle and add to intent
                intent.putExtra("text", textGet);

                //get position to carry integer
                intent.putExtra("position", position);

                intent.putExtras(b);

                //begin other activity
                startActivity(intent);
            }
        });

        return rootView1;
    }

    public void onResume(){
        super.onResume();

       // adapter.notifyDataSetChanged();

       // Log.e("Frag2", "Refresh Test");

        // a check so it doesnt run first time
        if (g != 0) {
            updateData();
        }
        g = 1;


    }

    private void updateData(){
        Log.e("Frag2", "Refresh Test");
        this.mylist2.clear();
        for (int i = 0; i < arrayInt.length; i++) {
            if (arrayInt[i] == 1) {
                this.mylist2.add(array[i]);
            }
        }


       // rootView1 = inflater1.inflate(R.layout.fragment_main, container1, false);
        //listView = (ListView) rootView1.findViewById(R.id.text_label);
        adapter = new MyAdapter(getContext(), this.mylist2);
        //listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}