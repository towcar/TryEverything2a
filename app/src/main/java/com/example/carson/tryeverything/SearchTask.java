package com.example.carson.tryeverything;

/**
 * Created by Carson on 2017-03-31.
 */

public class SearchTask {


    //have a big data list
    //using an algorithm to compare lang/long to find the closest location
    //using the position of the item in the list, pass data to map
    //map adds and sets accordingly.
    public Object locate(String string, Double lat, Double lon){
        //object to carry through new data
        Object[] outputArray = new Object[]{"",0,0};
        Object[][] sortArray;
        int recordLocation = 0;

        double currentRecord = 10000;

        //sort an object based on chosen interest
        switch(string) {
            case "brewery": sortArray = BreweryObject();
                break;
            default: sortArray = BreweryObject();
        }

        //loop to the end of the array
        //if proven to be to slow then change search algorithm
        for(int i = 0; i < sortArray.length; i++){
            //pull out data from array to compare
            double x = (double) sortArray[i][1];
            double y = (double) sortArray[i][2];

            //pythagorem theorm
            double otherC = Math.sqrt((x*x)+(y*y));

            double myC = Math.sqrt((lat*lat)+(lon*lon));
            double currentDistance = otherC - myC;
            //alter to not be a negative
            if (currentDistance < 0) {currentDistance *= -1;}

            //find closest location, loop to next
            if (currentDistance < currentRecord) {
                currentRecord = currentDistance;
                recordLocation = i;
            }

        }

        //put data into the output Array
        for(int i = 0; i < 4; i++) {
            outputArray[i] = sortArray[recordLocation][i];
        }

        //return the output array
        return outputArray;
    }

    public Object[][] BreweryObject() {
        Object[][] brewArray = new Object[][]{
            {"Mission Springs", 12.0938384, -104.85849},
            {"Sistos", 12.0938384, -104.85849}
        };



        return brewArray;
    }
}