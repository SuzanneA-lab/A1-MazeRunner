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

public class Main {
    //this array list is used to store the maze read in from the file
    private ArrayList<String> maze = new ArrayList<String>(); 

    Direction N = new North();
    Direction S = new South();
    Direction E = new East();
    Direction W = new West();
    Direction current;

    //logger object is created at this stage
    private static final Logger logger = LogManager.getLogger();

    //the fileProcessor method reads in all lines from the input file, and assigns each line to an index in the maze ArrayList
    private void fileProcessor(String filename){
        String line;
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            while ((line = reader.readLine()) != null) {
                maze.add(line);
            }  
            
        } catch(Exception e) { //if file does not exist, expection is thrown
            logger.error("/!\\ An error has occured /!\\");
            System.out.println("PATH NOT COMPUTED"); 
        }
    }

    //the makeMove method uses the right-hand rule to determine the next move the user should make, depending on the right and front tiles
    //accepts the right tile and next tile as char arguments to evaluate moves
    private String makeMove(char right_tile, char next_tile){
        if (right_tile == '#'){ 
            if (next_tile == ' '){ //if the right tile is a wall, and the next tile is not a wall, move forward
                return "F";
            }

            else { //if the right tile is a wall, and the next tile is also a wall, turn left
                return "L"; 
            }
        }

        else { //if the right tile is not a wall, turn right and move forward by one 
            return "RF";
        }
    }

    private String pathFinder(int row_num, int exit_line){
        String path = "";
        String row = maze.get(row_num);
        String row_above = maze.get(row_num-1);
        String row_below = maze.get(row_num+1);
        int len = row.length();
        int col_num = 0;
        int[] newcoords = {row_num,0};
        int j=0;

        current = E;

        System.out.printf("exit line: %d\n",exit_line);

        /*if (row_num == exit_line){
            for (int i=0; i < len-1 ;i++){
                path = path + "F";
            }
            return path;
        }*/

        while (col_num != len-1 || row_num != exit_line/*j<20 && col_num<=(len-2) && row_num<=(len-2)*/){
            //j++;
            row = maze.get(row_num);
            row_above = maze.get(row_num-1);
            row_below = maze.get(row_num+1);
            //System.out.println(row_above);
            System.out.println(row);

            current.check_case(row, row_above, row_below, col_num, row_num);
            path = path + current.get_path();
            current.clear_path();

            newcoords = current.getnewcoords();
            row_num = newcoords[0];
            col_num = newcoords[1];

            this.updateDirection();

            //System.out.printf("%d %d \n",row_num, col_num);
            //System.out.println(path);

        }

        return path;

    }

    private void updateDirection(){
        if (current.get_direction().equals("N")){
            current = N;
            System.out.println("face north");
        }

        else if (current.get_direction().equals("S")){
            current = S;
            System.out.println("face south");
        }

        else if (current.get_direction().equals("E")){
            current = E;
            System.out.println("face east");
        }

        else if (current.get_direction().equals("W")){
            current = W;
            System.out.println("face west");
        }
    }

    //the pathfinder method uses the makeMove method to find a path through the maze
    //accepts the entry row number as an argument
    /*
    private String pathFinder(int row_num){
        String path = "F"; //the path always starts with F, to account for indexing issues
        
        String row = maze.get(row_num); //row value is assigned to the index of the starting row

        String row_above; //represents the line directly above row

        String row_below; //represents the line directly below row

        String move; //holds the move returned from the makeMove method

        int len = row.length(); //represents the length of all rows in the maze

        int j = 0; //represents the column we're currently on in the array

        int direction = 1; //1 = forward/right, 2 = up, 3 = down, 4 = back/left

        //program loops until reaching the end of the maze
        while (j<len-1){
            //row values are updated on each run of the loop as we move through the maze
            row = maze.get(row_num);
            row_below = maze.get(row_num+1);
            row_above = maze.get(row_num-1);

            if (direction == 1){ //if currently facing forward
                move = this.makeMove(row_below.charAt(j), row.charAt(j+1)); //determine move using makeMove method
                path = path + move; //add move to path string

                if (move.equals("F")){
                    j++; //current position moves to the right by one
                }

                else if (move.charAt(0) == 'R'){ //right is always accomponied by forward direction, so we change direction and move down
                    direction = 3;
                    row_num++;
                }

                else { //if char is 'L', turn upwards
                    direction = 2;
                }
            }

            else if (direction == 2){ //if currently facing up
                move = this.makeMove(row.charAt(j+1), row_above.charAt(j));
                path = path + move;

                if (move.equals("F")){
                    row_num--; //current position moves one row up
                }

                else if (move.charAt(0) == 'R'){
                    direction = 1;
                    j++; //turn right and move to the right by one
                }

                else {
                    direction = 4; //turn to the left
                }
            }

            else if (direction == 3){ //if currently facing down
                move = this.makeMove(row.charAt(j-1), row_below.charAt(j));
                path = path + move;

                if (move.equals("F")){
                    row_num++; //current position moves down by one row 
                }

                else if (move.charAt(0) == 'R'){
                    direction = 4;
                    j--; //turn left and move to the left by one
                }

                else {
                    direction = 1; //turn to the right
                }
            }

            else if (direction == 4){ //if currently facing backwards
                move = this.makeMove(row_above.charAt(j), row.charAt(j-1));
                path = path + move;

                if (move.equals("F")){
                    j--; //current position moves moves to the left by one
                }
                
                else if (move.charAt(0) == 'R'){
                    direction = 2;
                    row_num--; //turn up and move up one row
                }

                else {
                    direction = 3; //turn downwards
                }
            }
        }

        return path;
    }
    */

    private String pathVerify(String path, int entry_line, int exit_line){
        String row = maze.get(entry_line);
        String row_above;
        String row_below;
        char move;
        int len = row.length();
        int row_num = entry_line;
        int col_num = 0;
        int[] newcoords = {row_num,0};

        int i = 0;
        int path_len = path.length();

        current = E;

        while (row_num != exit_line || col_num != len-1){
            if (i == path_len){
                return "Invalid path entered";
            }   
            
            row = maze.get(row_num);
            row_above = maze.get(row_num-1);
            row_below = maze.get(row_num+1); 
            
            move = path.charAt(i);
            current.path_check(row, row_above, row_below, col_num, row_num);

            if (move == 'F'){
                current.moveForward();
            }
            
            else if (move == 'L'){
                current.turnLeft();
            }

            else if (move == 'R'){
                current.OnlyturnRight();
            }

            else {
                return "Invalid path entered";
            }

            newcoords = current.getnewcoords();
            row_num = newcoords[0];
            col_num = newcoords[1];
            
            this.updateDirection();
            i++;
        }

        return "Valid path entered";
    
    }


    //pathVerify method takes in the entry line and a path given by the user and returns a boolean representing if the path is legit or not
    private boolean pathVerify(String path, int row_num){
        //see pathFinder method for in-depth descriptions of variables
        String row = maze.get(row_num);
        String row_above;
        String row_below;
        char move;
        int len = row.length();
        int direction = 1; //1 = forward, 2 = up, 3 = down, 4 back
        int current_col = 0; //equivalent to 'j' in pathFinder

        //for loop indexes through all chars in the given path
        for (int j=0; j<path.length(); j++){
            //all variables are updated
            move = path.charAt(j); //current move is char at index j
            row = maze.get(row_num);
            row_below = maze.get(row_num+1);
            row_above = maze.get(row_num-1);

            //if incompatible characters are found in the path it is automatically deemed invalid, return false
            if (move != 'F' && move != 'R' && move != 'L' && move != ' '){
                return false;
            }

            //if we can make it to the end of the maze using the path, it is valid, return true
            if (current_col == len-2){
                return true;
            }

            //if we exit through the start of the maze, path is invalid
            else if (current_col < 0){
                return false;
            }

            /**each if statement block checks for F, R, and L chars,  
            - spaces are ignored
            - directions are followed until loop is broken
            **/

            if (direction == 1){
                if (move == 'F'){
                    if (row.charAt(current_col+1) == ' '){
                        current_col++;
                    }
                }

                else if (move == 'R'){
                    direction = 3;
                }

                else if (move == 'L'){
                    direction = 2;
                }
            }

            else if (direction == 2){
                if (move == 'F'){
                    if (row_above.charAt(current_col) == ' '){
                        row_num--;
                    }
                }

                else if (move == 'R'){
                    direction = 1;
                }

                else if (move == 'L'){
                    direction = 4;
                }
            }

            else if (direction == 3){
                if (move == 'F'){
                    if (row_below.charAt(current_col) == ' '){
                        row_num++;
                    }
                }

                else if (move == 'R'){
                    direction = 4;
                }

                else if (move == 'L'){
                    direction = 1;
                }
            }

            else if (direction == 4){
                if (move == 'F'){
                    if (row.charAt(current_col-1) == ' '){
                        current_col--;
                    }
                }

                else if (move == 'R'){
                    direction = 2;
                }

                else if (move == 'L'){
                    direction = 3;
                }
            }
        }    

        return false; //if loop ends without finding the exit or hitting an edge case, path is invalid
    }


    //the findEntrance method looks for the entrance to the maze by iterating through the maze object, returning its index
    private int findEntrance(){
        int lines = maze.size();
        int len = 0;
        int entry_line = 0;
        String row = "";

        for (int i=0; i<lines-1; i++){
            row = maze.get(i);
            len = row.length();

            if (row.charAt(0) == ' '){
                entry_line = i;
                i=lines;
            }
        }

        return entry_line;
    }

    private int findExit(){
        int lines = maze.size();
        int len = 0;
        int exit_line = 0;
        String row = "";

        for (int i=0; i<lines-1; i++){
            row = maze.get(i);
            len = row.length();

            if (row.charAt(len-1) == ' '){
                exit_line = i;
                i=lines;
            }
        }

        return exit_line;
    }

    //the factorizePath method takes in the canonical path generated by the pathFinder method and returns the factorized form of it
    private String factorizePath(String canon_path){
        int len = canon_path.length();
        String factor_path = "";
        int num_duplicates = 1;

        for (int i=1; i<len; i++){
            //if statement checks if the current index is the same as the previous
            if (canon_path.charAt(i) == canon_path.charAt(i-1)){
                num_duplicates++; //if true, increases the num_duplicates variable
            }

            else { //if the previous and current index are not the same
                if (num_duplicates != 1){ //if there are duplicates, add the duplicate number to the string and reset it back to one
                    factor_path = factor_path + num_duplicates;
                    num_duplicates = 1;
                }

                factor_path = factor_path + canon_path.charAt(i-1); //add the previous character to the string
            }
        }

        //checks if the string ends in duplicates, and if so, adds them to the string
        if (num_duplicates != 1){
            factor_path = factor_path + num_duplicates;
            factor_path = factor_path + canon_path.charAt(len-1);
        }

        else{
            factor_path = factor_path + canon_path.charAt(len-1);
        }

        //return the new path
        return factor_path;
    }

    //main method processes args, calls methods when appropriate and provides UI statements
    public static void main(String[] args) { 
        Options options = new Options();
        options.addOption("i", true,"Traverse the maze");
        options.addOption("p", true,"Verify a path through a maze is valid");
        CommandLineParser parser = new DefaultParser();
        Main m = new Main();

        logger.info("** Starting Maze Runner");

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")){
                logger.info("**** Reading the maze from file", args[1]);
                m.fileProcessor(args[1]);

                int entry_line = m.findEntrance(); 
                int exit_line = m.findExit();

                if (cmd.hasOption("p")){
                    String valid = m.pathVerify(args[3], entry_line, exit_line);
                    System.out.println(valid);
                }
                    
               else {
                    logger.info("**** Computing path"); //info
                    String path = m.pathFinder(entry_line, exit_line);
                    String factorized_path = m.factorizePath(path);
                    System.out.println(factorized_path);
                } 
            }


        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
            System.out.println("PATH NOT COMPUTED"); //info
        }
        

        

        logger.info("** End of MazeRunner"); //info
    }
}
