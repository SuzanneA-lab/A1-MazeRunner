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

    //test 1
        public boolean rightTurnTest(){
            String row;
            String row_above;
            String row_below;
            int row_num = 1;
            int col_num = 2;

            System.out.println("\n\nTEST 1: Running right turn test...");

            //NORTH
            row = "##  ##";
            row_above = "######";
            row_below = "######";

            N.check_case(row, row_above, row_below, col_num, row_num);
            boolean a1 = this.moveTestRunner(N, "E", 1, 3, "North", 1);

            //EAST
            row = "## ###";
            row_above = "######";
            row_below = "## ###";

            E.check_case(row, row_above, row_below, col_num, row_num);
            boolean a2 = this.moveTestRunner(E, "S", 2, 2, "East", 2);

            //SOUTH
            row = "#  ###";
            row_above = "######";
            row_below = "######";

            S.check_case(row, row_above, row_below, col_num, row_num);
            boolean a3 = this.moveTestRunner(S, "W", 1, 1, "South", 3);

            //WEST        
            row = "## ###";
            row_above = "## ###";
            row_below = "######";

            W.check_case(row, row_above, row_below, col_num, row_num);
            boolean a4 = this.moveTestRunner(W, "N", 0, 2, "West", 4);

            if (a1 == a2 && a2 == a3 && a3 == a4 && a1 == true){
                return true;
            }

            else {
                return false;
            }
        }

    //test 2
        public boolean leftTurnTest(){
            String row;
            String row_above;
            String row_below;
            int row_num = 1;
            int col_num = 2;

            System.out.println("\n\nTEST 2: Running left turn test...");

            //NORTH
            row = "## ###";
            row_above = "######";
            row_below = "######";

            N.check_case(row, row_above, row_below, col_num, row_num);
            boolean a1 = this.moveTestRunner(N, "W", 1, 2, "North", 1);

            //EAST
            row = "## ###";
            row_above = "######";
            row_below = "######";

            E.check_case(row, row_above, row_below, col_num, row_num);
            boolean a2 = this.moveTestRunner(E, "N", 1, 2, "East", 2);

            //SOUTH
            row = "## ###";
            row_above = "######";
            row_below = "######";

            S.check_case(row, row_above, row_below, col_num, row_num);
            boolean a3 = this.moveTestRunner(S, "E", 1, 2, "South", 3);

            //WEST        
            row = "## ###";
            row_above = "######";
            row_below = "######";

            W.check_case(row, row_above, row_below, col_num, row_num);
            boolean a4 = this.moveTestRunner(W, "S", 1, 2, "West", 4);

            if (a1 == a2 && a2 == a3 && a3 == a4 && a1 == true){
                return true;
            }

            else {
                return false;
            }
        }

    //test 3
        public boolean moveForwardTest(){
            String row;
            String row_above;
            String row_below;
            int row_num = 1;
            int col_num = 2;

            System.out.println("\n\nTEST 3: Running move forward test...");

            //NORTH
            row = "## ###";
            row_above = "## ###";
            row_below = "######";

            N.check_case(row, row_above, row_below, col_num, row_num);
            boolean a1 = this.moveTestRunner(N, "N", 0, 2, "North", 1);

            //EAST
            row = "##  ##";
            row_above = "######";
            row_below = "######";

            E.check_case(row, row_above, row_below, col_num, row_num);
            boolean a2 = this.moveTestRunner(E, "E", 1, 3, "East", 2);

            //SOUTH
            row = "## ###";
            row_above = "######";
            row_below = "## ###";

            S.check_case(row, row_above, row_below, col_num, row_num);
            boolean a3 = this.moveTestRunner(S, "S", 2, 2, "South", 3);

            //WEST        
            row = "#  ###";
            row_above = "######";
            row_below = "######";

            W.check_case(row, row_above, row_below, col_num, row_num);
            boolean a4 = this.moveTestRunner(W, "W", 1, 1, "West", 4);

            if (a1 == a2 && a2 == a3 && a3 == a4 && a1 == true){
                return true;
            }

            else {
                return false;
            }
        }

        private boolean moveTestRunner(Direction current, String right_direction, int right_row_num, int right_col_num, String direction, int stage){
            System.out.printf("\nStage %d: %s\n", stage, direction);

            int[] Coords = current.getnewcoords();
            int row_num = Coords[0];
            int col_num = Coords[1];
            String Direction = current.get_direction();

            if (Direction.equals(right_direction) && row_num == right_row_num && col_num == right_col_num){
                System.out.printf("Test PASS! Expected direction: %s, Actual direction: %s\n", right_direction, Direction);
                System.out.printf("           Expected row number: %d, Actual row number: %d\n", right_row_num, row_num);
                System.out.printf("           Expected column number: %d, Actual column number: %d\n", right_col_num, col_num);
                return true;
            }

            else{
                System.out.printf("Test FAIL! Expected direction %s, Actual direction %s\n", right_direction, Direction);
                System.out.printf("           Expected row number: %d, Actual row number: %d\n", right_row_num, row_num);
                System.out.printf("           Expected column number: %d, Actual column number: %d\n", right_col_num, col_num);
                return false;
            }
        }

    //test 4
        public boolean factorizePathTest(){
            String sample_path1 = "FFFRRRLFF";
            String sample_answer1 = "3F3RL2F";

            String sample_path2 = "RLF";
            String sample_answer2 = "RLF";
            
            String sample_path3 = "LLRRF";
            String sample_answer3 = "2L2RF";

            String sample_path4 = "LRRRRRRRRRRRF";
            String sample_answer4 = "L11RF";

            System.out.println("\n\nTEST 4: Running factorize path test...");

            boolean a1 = this.factorizePathTestRunner(sample_path1, sample_answer1, 1);
            boolean a2 = this.factorizePathTestRunner(sample_path2, sample_answer2, 2);
            boolean a3 = this.factorizePathTestRunner(sample_path3, sample_answer3, 3);
            boolean a4 = this.factorizePathTestRunner(sample_path4, sample_answer4, 4);

            if (a1 == a2 && a2 == a3 && a3 == a4 && a1 == true){
                return true;
            }

            else {
                return false;
            }
        }

        private boolean factorizePathTestRunner(String sample_path, String sample_answer, int stage){
            System.out.printf("\nStage %d - %s\n", stage, sample_path);
            String actual_answer = main.factorizePath(sample_path);

            if (actual_answer.equals(sample_answer)){
                System.out.printf("Test PASS! Expected result: %s, Actual result %s\n", sample_answer, actual_answer);
                return true;
            }

            else{
                System.out.printf("Test FAIL! Expected result: %s, Actual result %s\n", sample_answer, actual_answer);
                return false;
            }
        }

    //test 5
        public boolean canonizePathTest(){
            String sample_answer1 = "FFFRRRLFF";
            String sample_path1 = "3F3RL2F";

            String sample_answer2 = "RLF";
            String sample_path2 = "RLF";
            
            String sample_answer3 = "LLRRF";
            String sample_path3 = "2L2RF";

            String sample_answer4 = "LRRRRRRRRRRRF";
            String sample_path4 = "L11RF";

            System.out.println("\n\nTEST 5: Running canonize path test...");

            boolean a1 = this.canonizePathTestRunner(sample_path1, sample_answer1, 1);
            boolean a2 = this.canonizePathTestRunner(sample_path2, sample_answer2, 2);
            boolean a3 = this.canonizePathTestRunner(sample_path3, sample_answer3, 3);
            boolean a4 = this.canonizePathTestRunner(sample_path4, sample_answer4, 4);

            if (a1 == a2 && a2 == a3 && a3 == a4 && a1 == true){
                return true;
            }

            else {
                return false;
            }
        }

        private boolean canonizePathTestRunner(String sample_path, String sample_answer, int stage){
            System.out.printf("\nStage %d - %s\n", stage, sample_path);
            String actual_answer = main.canonizePath(sample_path);

            if (actual_answer.equals(sample_answer)){
                System.out.printf("Test PASS! Expected result: %s, Actual result %s\n", sample_answer, actual_answer);
                return true;
            }

            else{
                System.out.printf("Test FAIL! Expected result: %s, Actual result %s\n", sample_answer, actual_answer);
                return false;
            }
        }

    //test 6
        public boolean pathFinderTest(){
            ArrayList<String> maze1 = new ArrayList<String>();
            ArrayList<String> maze2 = new ArrayList<String>();
            ArrayList<String> maze3 = new ArrayList<String>();
            ArrayList<String> maze4 = new ArrayList<String>();
            String correct_path1 = "5F2L2FR2FR2F2L2FR2FR3F";
            String correct_path2 = "4F";
            String correct_path3 = "4F2L2FR2FR4F";
            String correct_path4 = "FR2FL3FRFLFRFL2F";

            maze1.add("#######");
            maze1.add("#      ");
            maze1.add("### ###");
            maze1.add("#     #");
            maze1.add("### ###");
            maze1.add("      #");
            maze1.add("#######");

            maze2.add("#####");
            maze2.add("#####");
            maze2.add("     ");
            maze2.add("#####");
            maze2.add("#####");

            maze3.add("#######");
            maze3.add("#######");
            maze3.add("##     ");
            maze3.add("## ####");
            maze3.add("     ##");
            maze3.add("#######");
            maze3.add("#######");

            maze4.add("########");
            maze4.add("  ######");
            maze4.add("# ######");
            maze4.add("#     ##");
            maze4.add("####  ##");
            maze4.add("#####   ");
            maze4.add("########");

            System.out.println("\n\nTEST 6: Running path finder test...");
            
            String real_path1 = main.pathFinder(5, 1, maze1);
            String real_path2 = main.pathFinder(2, 2, maze2);
            String real_path3 = main.pathFinder(4, 2, maze3);
            String real_path4 = main.pathFinder(1, 5, maze4);

            boolean a1 = this.pathFinderTestRunner(correct_path1, real_path1, 1, "tiny.maz");
            boolean a2 = this.pathFinderTestRunner(correct_path2, real_path2, 2, "straight.maz");
            boolean a3 = this.pathFinderTestRunner(correct_path3, real_path3, 3, "test.maz");
            boolean a4 = this.pathFinderTestRunner(correct_path4, real_path4, 4, "direct.maz");

            if (a1 == a2 && a2 == a3 && a3 == a4 && a1 == true){
                return true;
            }

            else {
                return false;
            }
        }

        private boolean pathFinderTestRunner(String correct_path, String real_path, int stage, String maze_name){
            System.out.printf("\nStage %d - %s\n", stage, maze_name);

            if (real_path.equals(correct_path)){
                System.out.printf("Test PASS! Expected path: %s, Actual path %s\n", correct_path, real_path);
                return true;
            }

            else{
                System.out.printf("Test FAIL! Expected path: %s, Actual path %s\n", correct_path, real_path);
                return false;
            }
        }

    //test 7
        public boolean pathVerifyTest(){
            ArrayList<String> maze = new ArrayList<String>();
            String correct_path1 = "4F";
            String correct_path2 = "FFFF";
            String incorrect_path1 = "RRFFFRFR";
            String incorrect_path2 = "FFLFFFFLRF";

            maze.add("#####");
            maze.add("#####");
            maze.add("     ");
            maze.add("#####");
            maze.add("#####");

            System.out.println("\n\nTEST 7: Running path verification test...");
            
            String response_1 = main.pathVerify(correct_path1, 2, 2, maze);
            String response_2 = main.pathVerify(correct_path2, 2, 2, maze);
            String response_3 = main.pathVerify(incorrect_path1, 2, 2, maze);
            String response_4 = main.pathVerify(incorrect_path2, 2, 2, maze);

            boolean a1 = this.pathVerifyTestRunner("Valid path entered", response_1, 1);
            boolean a2 = this.pathVerifyTestRunner("Valid path entered", response_2, 2);
            boolean a3 = this.pathVerifyTestRunner("Invalid path entered", response_3, 3);
            boolean a4 = this.pathVerifyTestRunner("Invalid path entered", response_4, 4);      

            if (a1 == a2 && a2 == a3 && a3 == a4 && a1 == true){
                return true;
            }

            else {
                return false;
            }
        }

        private boolean pathVerifyTestRunner(String expected_response, String response, int stage){
            System.out.printf("\nStage %d - straight.maz\n", stage);

            if (expected_response.equals(response)){
                System.out.printf("Test PASS! Expected response: %s, Actual response: %s\n", expected_response, response);
                return true;
            }

            else{
                System.out.printf("Test FAIL! Expected response: %s, Actual response: %s\n", expected_response, response);
                return false;
            }
        }

    //test 8
        public boolean outofBoundsTest(){
            String row1 = "######";
            String row2 = " #####";

            int row_num1 = 0;
            int row_num2 = -1;
            int row_num3 = 100;

            int col_num1 = 0;
            int col_num2 = -3;
            int col_num3 = 100;

            int length = 10;

            System.out.println("\n\nTEST 8: Running out of bounds test...");

            //check if being inside wall counts as being out of bounds
            Boolean response1 = main.outofBounds(length, col_num1, row_num1, row1); //out of bounds

            //check with various row indices
            Boolean response2 = main.outofBounds(length, col_num1, row_num1, row2); //should be false - not out of bounds
            Boolean response3 = main.outofBounds(length, col_num1, row_num2, row2); //out of bounds
            Boolean response4 = main.outofBounds(length, col_num1, row_num3, row2); //out of bounds
            
            //check with various column indices
            Boolean response5 = main.outofBounds(length, col_num2, row_num1, row2); //out of bounds
            Boolean response6 = main.outofBounds(length, col_num3, row_num1, row2); //out of bounds

            boolean a1 = this.outofBoundsTestRunner(response1, true, 1, "inside wall");
            boolean a2 = this.outofBoundsTestRunner(response2, false, 2, "within bounds");
            boolean a3 = this.outofBoundsTestRunner(response3, true, 3, "row number too low");
            boolean a4 = this.outofBoundsTestRunner(response4, true, 4, "row number too high");
            boolean a5 = this.outofBoundsTestRunner(response5, true, 5, "column number too low");
            boolean a6 = this.outofBoundsTestRunner(response6, true, 6, "column number too high");

            if (a1 == a2 && a2 == a3 && a3 == a4 && a4 == a5 && a5 == a6 && a1 == true){
                return true;
            }

            else {
                return false;
            }

        }

        private boolean outofBoundsTestRunner(Boolean expected_response, Boolean response, int stage, String condition){
            System.out.printf("\nStage %d - %s\n", stage, condition);

            if (expected_response == response){
                System.out.printf("Test PASS! Expected response: %s, Actual response: %s\n", expected_response, response);
                return true;
            }

            else{
                System.out.printf("Test FAIL! Expected response: %s, Actual response: %s\n", expected_response, response);
                return false;
            }   
        }

    //test 9
        public boolean findEntranceTest(){
            ArrayList<String> maze1 = new ArrayList<String>();
            ArrayList<String> maze2 = new ArrayList<String>();
            ArrayList<String> maze3 = new ArrayList<String>();
            int correct_entrance1 = 5;
            int correct_entrance2 = 2;
            int correct_entrance3 = 1;

            maze1.add("#######");
            maze1.add("#      ");
            maze1.add("### ###");
            maze1.add("#     #");
            maze1.add("### ###");
            maze1.add("      #");
            maze1.add("#######");

            maze2.add("#####");
            maze2.add("#####");
            maze2.add("     ");
            maze2.add("#####");
            maze2.add("#####");

            maze3.add("########");
            maze3.add("  ######");
            maze3.add("# ######");
            maze3.add("#     ##");
            maze3.add("####  ##");
            maze3.add("#####   ");
            maze3.add("########");

            System.out.println("\n\nTEST 9: Running find entrance test...");

            int given_entrance1 = main.findEntrance(maze1);
            int given_entrance2 = main.findEntrance(maze2);
            int given_entrance3 = main.findEntrance(maze3);

            boolean a1 = this.findEntranceOrExitTestRunner(correct_entrance1, given_entrance1, 1, "tiny.maz");
            boolean a2 = this.findEntranceOrExitTestRunner(correct_entrance2, given_entrance2, 2, "straight.maz");
            boolean a3 = this.findEntranceOrExitTestRunner(correct_entrance3, given_entrance3, 3, "direct.maz");

            if (a1 == a2 && a2 == a3 && a1 == true){
                return true;
            }

            else {
                return false;
            }
        }

    //test 10
        public boolean findExitTest(){
            ArrayList<String> maze1 = new ArrayList<String>();
            ArrayList<String> maze2 = new ArrayList<String>();
            ArrayList<String> maze3 = new ArrayList<String>();
            int correct_exit1 = 1;
            int correct_exit2 = 2;
            int correct_exit3 = 5;

            maze1.add("#######");
            maze1.add("#      ");
            maze1.add("### ###");
            maze1.add("#     #");
            maze1.add("### ###");
            maze1.add("      #");
            maze1.add("#######");

            maze2.add("#####");
            maze2.add("#####");
            maze2.add("     ");
            maze2.add("#####");
            maze2.add("#####");

            maze3.add("########");
            maze3.add("  ######");
            maze3.add("# ######");
            maze3.add("#     ##");
            maze3.add("####  ##");
            maze3.add("#####   ");
            maze3.add("########");

            System.out.println("\n\nTEST 10: Running find exit test...");

            int given_exit1 = main.findExit(maze1);
            int given_exit2 = main.findExit(maze2);
            int given_exit3 = main.findExit(maze3);

            boolean a1 = this.findEntranceOrExitTestRunner(correct_exit1, given_exit1, 1, "tiny.maz");
            boolean a2 = this.findEntranceOrExitTestRunner(correct_exit2, given_exit2, 2, "straight.maz");
            boolean a3 = this.findEntranceOrExitTestRunner(correct_exit3, given_exit3, 3, "direct.maz");

            if (a1 == a2 && a2 == a3 && a1 == true){
                return true;
            }

            else {
                return false;
            }
        }

        private boolean findEntranceOrExitTestRunner(int correct_index, int given_index, int stage, String maze_name){
            System.out.printf("\nStage %d - %s\n", stage, maze_name);

            if (correct_index == given_index){
                System.out.printf("Test PASS! Expected response: %d, Actual response: %d\n", correct_index, given_index);
                return true;
            }
        
            else{
                System.out.printf("Test FAIL! Expected response: %d, Actual response: %d\n", correct_index, given_index);
                return false;
            }
        }

    //run all tests at once
        public void runAllTests(){
            boolean t1 = this.rightTurnTest();
            boolean t2 = this.leftTurnTest();
            boolean t3 = this.moveForwardTest();
            boolean t4 = this.factorizePathTest();
            boolean t5 = this.canonizePathTest();
            boolean t6 = this.pathFinderTest();
            boolean t7 = this.pathVerifyTest();
            boolean t8 = this.outofBoundsTest();
            boolean t9 = this.findEntranceTest();
            boolean t10 = this.findExitTest();
            boolean failure = false;

            boolean[] testResults = {t1,t2,t3,t4,t5,t6,t7,t8,t9,t10};
            for (int i=0; i<10; i++){
                if (testResults[i] == false){
                    System.out.printf("\nTest failure detected: test %d", i+1);
                    failure = true;
                }
            }

            if (!failure){
                System.out.println("\nAll Tests passed!\n");
            }
        }
}