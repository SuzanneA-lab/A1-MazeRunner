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
    String newdirection = "S";
    
    public South(int num){
        super(num);
    }
    
    public void check_case(String row, String row_above, String row_below, int col_num){
        this.row = row;
        this.row_above = row_above;
        this.row_below = row_below;
        this.col_num = col_num;
        
        //blockade in front
        if (row_below.charAt(col_num) == '#'){

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
            if (row.charAt(col_num-1) == ' '){
                this.follow_wall();
            }

            else {
                this.move_forward(); 
            }
        }
    }

    protected void dead_end(){
        newcoords = {row_num,col_num};
        newdirection = "N";
        path = "LL";

    }

    protected void two_paths(){
        newcoords = {row_num,col_num-1};
        newdirection = "W";
        path = "RF";
    }

    protected void one_path(){
        if (row.charAt(col_num+1) == ' '){
            newcoords = {row_num,col_num+1};
            newdirection = "E";
            path = "LF";
        }

        else{
            newcoords = {row_num,col_num-1};
            newdirection = "W";
            path = "RF";
        }
        
    }

    protected void move_forward(){
        newcoords = {row_num-1,col_num};
        path = "F";
    }

    protected void follow_wall(){
        newdirection = "W";
        newcoords = {row_num,col_num-1};
        path = "RF";
    }
}