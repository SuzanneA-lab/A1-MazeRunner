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

public class West extends Direction {
    
    public West(){
        newdirection = "W";
    }

/*   
    public void check_case(String row, String row_above, String row_below, int col_num, int row_num){
        this.row = row;
        this.row_above = row_above;
        this.row_below = row_below;
        this.col_num = col_num;
        this.row_num = row_num;

        char right_tile = row_above.charAt(col_num);
        char front_tile = row.charAt(col_num-1); 

        this.make_decision(right_tile, front_tile);
    }
*/

    protected void setTiles(){
        right_tile = row_above.charAt(col_num);
        front_tile = row.charAt(col_num-1); 
    }
 
    protected void moveForward(){
        newdirection = "W";
        newcoords[0] = row_num;
        newcoords[1] = col_num-1;
        path = "F";
    }

    protected void turnLeft(){
        newdirection = "S";
        newcoords[0] = row_num;
        newcoords[1] = col_num;
        path = "L";
    }

    protected void OnlyturnRight(){
        newcoords[0] = row_num;
        newcoords[1] = col_num;
        newdirection = "N";
        path = "R";
    }

    protected void turnRight(){
        newcoords[0] = row_num-1;
        newcoords[1] = col_num;
        newdirection = "N";
        path = "RF";
    }
}