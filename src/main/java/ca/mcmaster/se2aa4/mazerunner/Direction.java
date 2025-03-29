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
    String newdirection;
    int[] newcoords;

    public Direction(int num){
        row_num = num;
    }

    abstract public void check_case(String row, String row_above, String row_below, int col_num);
    
    //these methods return the new coordinate positions for each action, and are used within check_case
    abstract protected void dead_end();
    abstract protected void two_paths();
    abstract protected void one_path();
    abstract protected void move_forward();
    abstract protected void follow_wall();

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

public class North extends Direction {
    newdirection = "N";
    
    public North(int num){
        super(num);
    }
    
    public void check_case(String row, String row_above, String row_below, int col_num){
        this.row = row;
        this.row_above = row_above;
        this.row_below = row_below;
        this.col_num = col_num;
        
        //blockade in front
        if (row_above.charAt(col_num) == '#'){

            //if we've hit a dead end 
            if (row.charAt(col_num+1) == '#' && row.charAt(col_num-1) == '#'){
                this.dead_end();
            }

            else if (row.charAt(col_num+1) == ' ' && row.charAt(col_num-1) == ' '){
                this.two_paths();
            }

            else if (row.charAt(col_num+1) == ' ' || row.charAt(col_num-1) == ' '){
                this.one_path();
            }
        }

        else {
            if (row.charAt(col_num+1) == ' '){
                this.follow_wall();
            }

            else {
                this.move_forward(); 
            }
        }
    }

    protected void dead_end(){
        newcoords = {row_num,col_num};
        newdirection = "S";
        path = "LL";

    }

    protected void two_paths(){
        newcoords = {row_num,col_num};
        newdirection = "E";
        path = "R";
    }

    protected void one_path(){
        newcoords = {row_num,col_num};

        if (row.charAt(col_num+1) == ' '){
            newdirection = "E";
            path = "R";
        }

        else{
            newdirection = "W";
            path = "L";
        }
        
    }

    protected void move_forward(){
        newcoords = {row_num+1,col_num};
        path = "F";
    }

    protected void follow_wall(){
        newdirection = "E";
        newcoords = {row_num,col_num};
        path = "R";
    }
}

public class East extends Direction {
    newdirection = "E";
    
    public East(int num){
        super(num);
    }
    
    public void check_case(String row, String row_above, String row_below, int col_num){
        this.row = row;
        this.row_above = row_above;
        this.row_below = row_below;
        this.col_num = col_num;
        
        //blockade in front
        if (row.charAt(col_num+1) == '#'){

            //if we've hit a dead end 
            if (row_above.charAt(col_num) == '#' && row_below.charAt(col_num) == '#'){
                this.dead_end();
            }

            else if (row_above.charAt(col_num) == ' ' && row_below.charAt(col_num) == ' '){
                this.two_paths();
            }

            else if (row_above.charAt(col_num) == '#' || row_below.charAt(col_num) == '#'){
                this.one_path();
            }
        }

        else {
            if (row_below.charAt(col_num) == ' '){
                this.follow_wall();
            }

            else {
                this.move_forward(); 
            }
        }
    }

    protected void dead_end(){
        newcoords = {row_num,col_num};
        newdirection = "W";
        path = "LL";

    }

    protected void two_paths(){
        newcoords = {row_num,col_num};
        newdirection = "S";
        path = "R";
    }

    protected void one_path(){
        newcoords = {row_num,col_num};

        if (row_above.charAt(col_num) == ' '){
            newdirection = "N";
            path = "L";
        }

        else{
            newdirection = "S";
            path = "R";
        }
        
    }

    protected void move_forward(){
        newcoords = {row_num,col_num+1};
        path = "F";
    }

    protected void follow_wall(){
        newdirection = "S";
        newcoords = {row_num,col_num};
        path = "R";
    }
}
