package com.example.carson.tryeverything;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ItemActionActivity extends AppCompatActivity {

    String picture;
    int pictureID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_action);

        //get intent data
        Bundle b = this.getIntent().getExtras();
        String textName = b.getString("text");
        Log.e("Text", textName);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(textName);


        //sets the background image
        LinearLayout layout =(LinearLayout)findViewById(R.id.activity_item_action);
        Log.e("Test","Code Test");
        pictureID = setBackground(textName);
        layout.setBackgroundResource(R.drawable.snowboardlist);


    }

    private int setBackground(String text){

        //set the background picture based on the text
        switch (text) {
            case "snowboarding":
                picture = "R.drawable.snowboarding";
                break;
            case "surfing":
                picture = "R.drawable.surfing";
                break;
            default: picture = "R.drawable.snowboarding";

        }
        pictureID = getResources().getIdentifier(picture , "drawable", getPackageName());
    Log.e("ID number", "" + pictureID);
    return pictureID;
    }
}

