package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.lang.String;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLineParser;

public class North extends Direction {
    
    public North(int num){
        super(num);
        newdirection = "N";
    }
    
    public void check_case(String row, String row_above, String row_below, int col_num, int row_num){
        this.row = row;
        this.row_above = row_above;
        this.row_below = row_below;
        this.col_num = col_num;
        this.row_num = row_num;

        char right_tile = row.charAt(col_num+1);
        char front_tile = row_above.charAt(col_num); 

        this.make_decision(right_tile, front_tile);
    }

/*
    public void check_case(String row, String row_above, String row_below, int col_num, int row_num){
        this.row = row;
        this.row_above = row_above;
        this.row_below = row_below;
        this.col_num = col_num;
        this.row_num = row_num;

        char right_tile = row.charAt(col_num+1);
        char front_tile = row_above.charAt(col_num); 

        
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


/*
    protected char makeMove(char move){
        if (move == 'F'){
            newcoords[0] = row_num-1;
            newcoords[1] = col_num;
        }

        else if (move == 'R'){

        }
    }
    */

    protected void moveForward(){
        newdirection = "N";
        newcoords[0] = row_num-1;
        newcoords[1] = col_num;
        path = "F";
    }

    protected void turnLeft(){
        newdirection = "W";
        newcoords[0] = row_num;
        newcoords[1] = col_num;
        path = "L";
    }

    protected void turnRight(){
        newcoords[0] = row_num;
        newcoords[1] = col_num+1;
        newdirection = "E";
        path = "RF";
    }

         /*

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

    */

    protected void dead_end(){
        newcoords[0] = row_num;
        newcoords[1] = col_num;
        newdirection = "S";
        path = "LL";

    }

    protected void two_paths(){
        newcoords[0] = row_num;
        newcoords[1] = col_num+1;
        newdirection = "E";
        path = "RF";
        System.out.println("turn and go forward");
    }

    protected void one_path(){
        if (row.charAt(col_num+1) == ' '){
            newcoords[0] = row_num;
            newcoords[1] = col_num+1;
            newdirection = "E";
            path = "RF";
            System.out.println("turn and go forward");
        }

        else{
            newcoords[0] = row_num;
            newcoords[1] = col_num-1; 
            newdirection = "W";
            path = "LF";
        }
        
    }

    protected void move_forward(){
        newcoords[0] = row_num-1;
        newcoords[1] = col_num;
        path = "F";
        System.out.println("go up");
    }

    protected void follow_wall(){
        newcoords[0] = row_num;
        newcoords[1] = col_num+1;
        newdirection = "E";
        path = "RF";
    }
}