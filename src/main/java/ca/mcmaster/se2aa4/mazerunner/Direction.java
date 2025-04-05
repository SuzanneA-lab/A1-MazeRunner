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

public abstract class Direction {
    int row_num;
    int col_num;
    String row;
    String row_above;
    String row_below;
    char right_tile;
    char front_tile;
    String path;
    String newdirection = "";
    int[] newcoords = {0,0};

    public void makeDecision(char right_tile, char front_tile){
        if (right_tile == ' '){
                this.turnRight();
            }
    
        else {
            if (front_tile == '#'){
                this.turnLeft();
            }

            else {
                this.moveForward();
            }
        }
    } 

    //updates instance variables with new location information
    public void updateInfo(String row, String row_above, String row_below, int col_num, int row_num){
        this.row = row;
        this.row_above = row_above;
        this.row_below = row_below;
        this.col_num = col_num;
        this.row_num = row_num;
    }

    public String get_path(){
        return path;
    }

    public String get_direction(){
        return newdirection; 
    }

    public void clear_path(){
        path = "";
    }

    public int[] getnewcoords(){
        return newcoords;
    }

    //template check_case method updates location information, allows subclasses to implement their own setTiles methods for right and forward tiles, and uses a set make_decision method to decide which action to take 
    public void check_case(String row, String row_above, String row_below, int col_num, int row_num){
        this.updateInfo(row, row_above, row_below, col_num, row_num); //fixed method
        this.setTiles(); //no default method
        this.makeDecision(right_tile, front_tile); //fixed method
    }

    abstract protected void setTiles();
    abstract protected void moveForward();
    abstract protected void turnLeft();
    abstract protected void turnRight();
    abstract protected void OnlyturnRight();
    
}