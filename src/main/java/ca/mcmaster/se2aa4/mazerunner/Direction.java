/**package ca.mcmaster.se2aa4.mazerunner;

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
    //abstract String turnRight();
    //abstract String moveForward();
    //abstract String turnLeft();
    abstract String makemove(char right_tile, char next_tile);

    public static void main(String[] args) { 
    }
}

/**algorithm notes:
 *  1) we need to have an empty space in front of us to move forward in all cases
 *  2) we need a wall on our right to move forward at all times, except when turning a corner
 *      (just turned and no wall in front of us)
 * 
 * ideas:
 * - need cases: move forward, turn around - could be if else statements in a method accepting relevant test condition variables
 *  
 * 

class Up extends Direction {
    String makemove(char right_tile, char next_tile){
        if (right_tile)
    }

}
**/