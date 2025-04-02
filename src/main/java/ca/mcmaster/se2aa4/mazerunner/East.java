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

public class East extends Direction {

    public East(int num){
        super(num);
        newdirection = "E";
    }
    
    public void check_case(String row, String row_above, String row_below, int col_num, int row_num){
        this.row = row;
        this.row_above = row_above;
        this.row_below = row_below;
        this.col_num = col_num;
        this.row_num = row_num;
        System.out.printf("%d %d \n",row_num, col_num);

        if (row_below.charAt(col_num) == ' '){
             newcoords[0] = row_num+1;
             newcoords[1] = col_num;
             newdirection = "S";
             path = "RF";
         }
 
         else {
 
             if (row.charAt(col_num+1) == '#'){
                 newdirection = "N";
                 newcoords[0] = row_num;
                 newcoords[1] = col_num;
                 path = "L";
             }
 
             else {
                 newdirection = "E";
                 newcoords[0] = row_num;
                 newcoords[1] = col_num+1;
                 path = "F";
             }
         }
     }
 
     /*

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
    */

    protected void dead_end(){
        newcoords[0] = row_num;
        newcoords[1] = col_num;
        newdirection = "W";
        path = "LL";

    }

    protected void two_paths(){
        newcoords[0] = row_num+1;
        newcoords[1] = col_num;
        newdirection = "S";
        path = "RF";
    }

    protected void one_path(){
        if (row_above.charAt(col_num) == ' '){
            newcoords[0] = row_num-1;
            newcoords[1] = col_num;
            newdirection = "N";
            path = "LF";
        }

        else{
            newcoords[0] = row_num+1;
            newcoords[1] = col_num;
            newdirection = "S";
            path = "RF";
        }
        
    }

    protected void move_forward(){
        newcoords[0] = row_num;
        newcoords[1] = col_num+1;
        path = "F";
        System.out.println("forward");
    }

    protected void follow_wall(){
        newcoords[0] = row_num+1;
        newcoords[1] = col_num;
        newdirection = "S";
        path = "RF";
        System.out.println("follow wall");
    }
}
