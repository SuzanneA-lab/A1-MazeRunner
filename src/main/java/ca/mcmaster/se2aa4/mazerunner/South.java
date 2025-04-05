package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLineParser;

public class South extends Direction {
    public South(){
        newdirection = "S";
    }
 
    protected void setTiles(){
        right_tile = row.charAt(col_num-1);
        front_tile = row_below.charAt(col_num); 
    }

    protected void moveForward(){
        newdirection = "S";
        newcoords[0] = row_num+1;
        newcoords[1] = col_num;
        path = "F";
    }

    protected void turnLeft(){
        newdirection = "E";
        newcoords[0] = row_num;
        newcoords[1] = col_num;
        path = "L";
    }

    protected void OnlyturnRight(){
        newcoords[0] = row_num;
        newcoords[1] = col_num;
        newdirection = "W";
        path = "R";
    }

    protected void turnRight(){
        newcoords[0] = row_num;
        newcoords[1] = col_num-1;
        newdirection = "W";
        path = "RF";
    }

}