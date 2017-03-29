package com.example.carson.tryeverything;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static com.example.carson.tryeverything.R.id.container;

public class MainActivity extends AppCompatActivity {


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentStatePagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private static ViewPager mViewPager;
    private static TabLayout tabLayout;

    //declaration
    /*static String[] array = {"Brewery Tour", "Bungee Jumping", "Cave Diving", "Camping", "Cliff Jumping", "Drive In Theater", "Escape Room Challenge", "Golfing", "Helicopter Tour", "Horseback Riding",
            "Kayaking", "Maze Challenge", "Mountain Biking", "Paintball", "Ride A Roller Coaster", "River Rapid Riding", "Sand Surfing", "Scuba Diving", "Shark Cage Diving", "Skydiving",
            "Snowboarding", "Surfing", "Volcano Trekking" ,"Whale Watching", "Wine Tasting", "Yachting", "Zip Lining" };
    static Integer[] arrayInt = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};*/

    private List<Fragment> mFragments = new Vector<>();

    //Arraylists
    static ArrayList<String> mylist = new ArrayList<>();
    static ArrayList<String> arrayPage = new ArrayList<>();

    //private static final String ARG_SECTION_NUMBER = "section_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(0);

        //more

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setScrollPosition(0, 0f, true);


        //Floating Action Bar for Clicking
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Contact us to add ideas!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

       /* tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int pageIndex = tabLayout.getSelectedTabPosition();
                mViewPager.setCurrentItem(pageIndex);
                tabLayout.setupWithViewPager(mViewPager);


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    /*public static class PlaceholderFragment extends Fragment {
        *//**
     * The fragment argument representing the section number for this
     * fragment.
     *//*
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        *//**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    /*
        public static PlaceholderFragment newInstance(int sectionNumber) {
            TabFragment2 fragment = new TabFragment2();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            final ListView listView = (ListView) rootView.findViewById(R.id.section_label);
*//*
            //reset the clear
            arrayPage.clear();
            //sets up the data based on the opage selected
            for (int i = 0; i < Global.array.length; i++) {
                arrayPage.add(Global.array[i]);
            }
            //method set array commented out for reseting
            switch (position4) {
                case 0:
                    arrayPage = calculateArrayFull(Global.array, Global.arrayInt);
                    break;
                case 1:
                    arrayPage = calculateArrayUnfinished(Global.array, Global.arrayInt);
                    break;
                case 2:
                    arrayPage = calculateArrayCompleted(Global.array, Global.arrayInt);
                    break;
            }*//*

            MyAdapter adapter = new MyAdapter(getContext(), arrayPage);

            //set adapter according to page number
            listView.setAdapter(adapter);

            //gives position of current page selection
            Log.e("Position", "" + position4);


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

            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
            //listView.setOnItemClickListener(new AdapterView.OnItem

        }

        private ArrayList<String> calculateArrayFull(String[] array, Integer[] integer) {
            mylist.clear();

            //if the value in the integer array = "2" meaning completed. Add the word to the list.
            for (int i = 0; i < integer.length; i++) {
                mylist.add(array[i]);
            }
            Log.e("Log", "App Resumed");
            return mylist;
        }

        private ArrayList<String> calculateArrayCompleted(String[] array, Integer[] integer) {
            int g = 0;
            mylist.clear();

            //if the value in the integer array = "2" meaning completed. Add to word to list.
            for (int i = 0; i < integer.length; i++) {
                if (integer[i] == 2) {
                    mylist.add(array[i]);
                    g++;
                }
            }
            Log.e("Log", "App Resumed");
            return mylist;
        }

        private ArrayList<String> calculateArrayUnfinished(String[] array, Integer[] integer) {
            //resest list
            mylist.clear();
            int g = 0;

            //if the value in the integer array = "1" meaning uncompleted. Add to word to list.
            for (int i = 0; i < integer.length; i++) {
                if (integer[i] == 1) {
                    mylist.add(array[i]);
                    g++;
                }
            }
            //Leaves it blank
            return mylist;
        }

    }
*/
    /*
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
        int number = position;
            switch (number) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return TabFragment1.newInstance();
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return TabFragment2.newInstance();
                case 2: // Fragment # 1 - This will show SecondFragment
                    return TabFragment3.newInstance();
                default:
                    return null;
            }

           // return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "All";
                case 1:
                    return "Unfinished";
                case 2:
                    return "Completed";
            }
            return null;
        }
    }
*/
}






