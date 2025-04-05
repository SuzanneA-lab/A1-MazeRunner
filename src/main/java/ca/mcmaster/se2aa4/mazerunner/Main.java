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
    North N = North.getInstance();
    South S = South.getInstance();
    East E = East.getInstance();
    West W = West.getInstance();
    Direction current;

    //logger object is created at this stage
    private static final Logger logger = LogManager.getLogger();

    //the fileProcessor method reads in all lines from the input file, and assigns each line to an index in the maze ArrayList
    private ArrayList<String> fileProcessor(String filename){
        String line;
        ArrayList<String> maze = new ArrayList<String>(); 

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            while ((line = reader.readLine()) != null) {
                maze.add(line);
            }  
            
        } catch(Exception e) { //if file does not exist, expection is thrown
            logger.error("/!\\ An error has occured /!\\");
            System.out.println("PATH NOT COMPUTED"); 
        }

        return maze;
    }

    //pathFinder method uses direction classes to navigate through the maze using the right hand rule
    public String pathFinder(int row_num, int exit_line, ArrayList<String> maze){
        String path = "";
        String row = maze.get(row_num);
        String row_above = maze.get(row_num-1);
        String row_below = maze.get(row_num+1);
        int len = row.length();
        int col_num = 0;
        int[] newcoords = {row_num,0};
        int j=0;

        current = E;

        while (col_num != len-1 || row_num != exit_line){
            row = maze.get(row_num);
            row_above = maze.get(row_num-1);
            row_below = maze.get(row_num+1);

            current.check_case(row, row_above, row_below, col_num, row_num);
            path = path + current.get_path();
            current.clear_path();

            newcoords = current.getnewcoords();
            row_num = newcoords[0];
            col_num = newcoords[1];

            this.updateDirection();
        }

        return this.factorizePath(path);
    }

    //takes output from get_direction method uses it to switch current direction object
    private void updateDirection(){
        if (current.get_direction().equals("N")){
            current = N;
        }

        else if (current.get_direction().equals("S")){
            current = S;
        }

        else if (current.get_direction().equals("E")){
            current = E;
        }

        else if (current.get_direction().equals("W")){
            current = W;
        }
    }

    //checks if a given path works by using direction classes to navigate through the maze using it
    public String pathVerify(String path, int entry_line, int exit_line, ArrayList<String> maze){
        path = this.canonizePath(path);

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

            if (this.outofBounds(len, col_num, row_num, row)){
                return "Invalid path entered";
            }
            
            row = maze.get(row_num);
            row_above = maze.get(row_num-1);
            row_below = maze.get(row_num+1); 
            
            move = path.charAt(i);
            current.updateInfo(row, row_above, row_below, col_num, row_num);

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

    //checks if path given to pathverify method has taken us out of the allowable bounds for the maze
    public Boolean outofBounds(int length, int col_num, int row_num, String row){
        if (col_num < 0 || col_num >= length){
            return true;
        }

        else if (row_num < 0 || row_num >= length){
            return true;
        }

        else if (row.charAt(col_num) == '#'){
            return true;
        }

        else {
            return false;
        }
    }

    //the findEntrance method looks for the entrance to the maze by iterating through the maze object, returning its index
    public int findEntrance(ArrayList<String> maze){
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

    //finds the exit of the maze by iterating through the maze, returns its index
    public int findExit(ArrayList<String> maze){
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
    public String factorizePath(String canon_path){
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

    //takes in the factorized path through a maze and returns the canonical form
    public String canonizePath(String factor_path){
        int len = factor_path.length();
        String canon_path = "";
        char move;
        String num_duplicates = "";

        for (int i=0; i<len; i++){
            move = factor_path.charAt(i);

            if (move == 'F' || move == 'L' || move == 'R'){
                if (num_duplicates.equals("")){
                    canon_path = canon_path + move;
                }

                else {
                    for (int j=0; j < Integer.parseInt(num_duplicates); j++){
                        canon_path = canon_path + move;
                    }
                }

                num_duplicates = "";
            }

            else {
                num_duplicates = num_duplicates + move;
            }
        }
    
        return canon_path;
    }

    //main method processes args, calls methods when appropriate and provides UI statements
    public static void main(String[] args) { 
        Tests t = new Tests();
        t.runAllTests();

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
                ArrayList<String> maze = m.fileProcessor(args[1]);

                int entry_line = m.findEntrance(maze); 
                int exit_line = m.findExit(maze);

                if (cmd.hasOption("p")){
                    String valid = m.pathVerify(args[3], entry_line, exit_line, maze);
                    System.out.println(valid);
                }
                    
               else {
                    logger.info("**** Computing path"); //info
                    String path = m.pathFinder(entry_line, exit_line, maze);
                    System.out.println(path);
                } 
            }


        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
            System.out.println("PATH NOT COMPUTED"); //info
        }

        logger.info("** End of MazeRunner"); //info
    }
}
