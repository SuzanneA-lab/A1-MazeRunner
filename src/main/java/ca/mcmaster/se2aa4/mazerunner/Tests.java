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
        int row_num = 1;
        int col_num = 2;

        System.out.println("\nRunning right turn test...");

        //NORTH
        row = "##  ##";
        row_above = "######";
        row_below = "######";

        N.check_case(row, row_above, row_below, col_num, row_num);
        this.TurnTestRunner(N, "E", 1, 3, "North", 1);

        //EAST
        row = "## ###";
        row_above = "######";
        row_below = "## ###";

        E.check_case(row, row_above, row_below, col_num, row_num);
        this.TurnTestRunner(E, "S", 2, 2, "East", 2);

        //SOUTH
        row = "#  ###";
        row_above = "######";
        row_below = "######";

        S.check_case(row, row_above, row_below, col_num, row_num);
        this.TurnTestRunner(S, "W", 1, 1, "South", 3);

        //WEST        
        row = "## ###";
        row_above = "## ###";
        row_below = "######";

        W.check_case(row, row_above, row_below, col_num, row_num);
        this.TurnTestRunner(W, "N", 0, 2, "West", 4);
    }

    private void TurnTestRunner(Direction current, String right_direction, int right_row_num, int right_col_num, String direction, int stage){
        System.out.printf("\nStage %d: %s\n", stage, direction);

        int[] Coords = current.getnewcoords();
        int row_num = Coords[0];
        int col_num = Coords[1];
        String Direction = current.get_direction();

        if (Direction.equals(right_direction) && row_num == right_row_num && col_num == right_col_num){
            System.out.printf("Test PASS! Expected direction: %s, Actual direction: %s\n", right_direction, Direction);
            System.out.printf("           Expected row number: %d, Actual row number: %d\n", right_row_num, row_num);
            System.out.printf("           Expected column number: %d, Actual column number: %d\n", right_col_num, col_num);
        }

        else{
            System.out.printf("Test FAIL! Expected result %s, Actual result %s\n", right_direction, Direction);
            System.out.printf("           Expected row number: %d, Actual row number: %d\n", right_row_num, row_num);
            System.out.printf("           Expected column number: %d, Actual column number: %d\n", right_col_num, col_num);
        }
    }

    public void factorizePathTest(){
        String sample_path1 = "FFFRRRLFF";
        String sample_answer1 = "3F3RL2F";

        String sample_path2 = "RLF";
        String sample_answer2 = "RLF";
        
        String sample_path3 = "LLRRF";
        String sample_answer3 = "2L2RF";

        System.out.println("\nRunning factorize path test...");

        this.factorizePathTestRunner(sample_path1, sample_answer1, 1);
        this.factorizePathTestRunner(sample_path2, sample_answer2, 2);
        this.factorizePathTestRunner(sample_path3, sample_answer3, 3);
    }

    private void factorizePathTestRunner(String sample_path, String sample_answer, int stage){
        System.out.printf("\nStage %d - %s\n", stage, sample_path);
        String actual_answer = main.factorizePath(sample_path);

        if (actual_answer.equals(sample_answer)){
            System.out.printf("Test PASS! Expected result %s, Actual result %s\n", sample_answer, actual_answer);
        }

        else{
            System.out.printf("Test FAIL! Expected result %s, Actual result %s\n", sample_answer, actual_answer);
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