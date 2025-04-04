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

public class Tests {
    Main main = new Main();
    Direction N = new North();
    Direction S = new South();
    Direction E = new East();
    Direction W = new West();

    public void rightTurnTest(){
        String row;
        String row_above;
        String row_below;
        int col_num = 2;
        int row_num = 1;

        //NORTH
        System.out.println("Running right turn test...");
        System.out.println("Stage 1: north");

        row = "##  ##";
        row_above = "######";
        row_below = "######";


        N.check_case(row, row_above, row_below, col_num, row_num);
        this.rightTurnTestRunner(N, "E");

        //EAST
        System.out.println("Stage 2: east");

        row = "## ###";
        row_above = "######";
        row_below = "## ###";

        E.check_case(row, row_above, row_below, col_num, row_num);
        this.rightTurnTestRunner(E, "S");

        //SOUTH
        System.out.println("Stage 3: south");

        row = "#  ###";
        row_above = "######";
        row_below = "######";

        S.check_case(row, row_above, row_below, col_num, row_num);
        this.rightTurnTestRunner(S, "W");

        //WEST        
        System.out.println("Stage 4: west");

        row = "## ###";
        row_above = "## ###";
        row_below = "######";

        W.check_case(row, row_above, row_below, col_num, row_num);
        this.rightTurnTestRunner(W, "N");
    }

    private void rightTurnTestRunner(Direction current, String right_direction){
        int[] Coords = current.getnewcoords();
        String Direction = current.get_direction();

        if (Direction.equals(right_direction)){
            System.out.printf("Test PASS! Expected result %s, Actual result %s\n", right_direction, Direction);
        }

        else{
            System.out.printf("Test FAIL! Expected result %s, Actual result %s\n", right_direction, Direction);
        }
    }

    //private void horizontalTest(){

    //}

/*    public static void main(String[] args) {
        Tests t = new Tests();
        t.rightTurnTest();
    }
    */
}