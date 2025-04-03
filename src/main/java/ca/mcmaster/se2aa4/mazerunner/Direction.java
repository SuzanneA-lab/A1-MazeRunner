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
    String path;
    String newdirection = "";
    int[] newcoords = {0,0};

    public Direction(int num){
        row_num = num;
    }

    
    public void make_decision(char right_tile, char front_tile){
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
    
    //these methods return the new coordinate positions for each action, and are used within check_case
    abstract protected void check_case(String row, String row_above, String row_below, int col_num, int row_num);

    abstract protected void dead_end();
    abstract protected void two_paths();
    abstract protected void one_path();
    abstract protected void move_forward();
    abstract protected void follow_wall();

    abstract protected void moveForward();
    abstract protected void turnLeft();
    abstract protected void turnRight();
    //abstract protected char makeMove(char move);

    //returns the update to the path
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

}