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

enum Direction {
        UP, DOWN, FORWARD, BACKWARD;
}

public class Main {
    ArrayList<String> maze = new ArrayList<String>();
    Direction d = Direction.FORWARD;

    private static final Logger logger = LogManager.getLogger();

    private void fileProcessor(String filename){ //reads file and assigns to maze variable 
        String line;
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            while ((line = reader.readLine()) != null) {
                maze.add(line);
            }  
            
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
            System.out.println("PATH NOT COMPUTED"); //info
        }
    }

    private String makeMove(char right_tile, char next_tile){
        if (right_tile == '#'){
            if (next_tile == ' '){
                return "F";
            }

            else {
                return "L";
            }
        }

        else {
            return "RF";
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
     * **/

    private String pathFinderr(int row_num){
        String path = "F";
        String row = maze.get(row_num);
        String row_above;
        String row_below;
        String move;
        int len = row.length();
        int j = 0;
        int direction = 1; //1 = forward, 2 = up, 3 = down, 4 back

        while (j<len-1){
            row = maze.get(row_num);
            row_below = maze.get(row_num+1);
            row_above = maze.get(row_num-1);

            if (direction == 1){
                move = this.makeMove(row_below.charAt(j), row.charAt(j+1));
                path = path + move;
                if (move.equals("F")){
                    j++;
                    logger.info("**** moving forward");
                }

                else if (move.charAt(0) == 'R'){
                    direction = 3;
                    row_num++;
                    logger.info("**** turning and moving down");
                }

                else {
                    direction = 2;
                    logger.info("**** turning up");
                }
            }

            else if (direction == 2){
                move = this.makeMove(row.charAt(j+1), row_above.charAt(j));
                path = path + move;
                if (move.equals("F")){
                    row_num--;
                    logger.info("**** moving up");
                }

                else if (move.charAt(0) == 'R'){
                    direction = 1;
                    j++;
                    logger.info("**** turning and moving right");
                }

                else {
                    direction = 4;
                    logger.info("**** turning left");
                }
            }

            else if (direction == 3){
                move = this.makeMove(row.charAt(j-1), row_below.charAt(j));
                path = path + move;
                if (move.equals("F")){
                    row_num++;
                    logger.info("**** moving down");   
                }

                else if (move.charAt(0) == 'R'){
                    direction = 4;
                    j--;
                    logger.info("**** turning and moving left");
                }

                else {
                    direction = 1;
                    logger.info("**** turning right");
                }
            }

            else if (direction == 4){
                move = this.makeMove(row_above.charAt(j), row.charAt(j-1));
                path = path + move;
                if (move.equals("F")){
                    j--;
                    logger.info("**** moving left");
                }
                
                else if (move.charAt(0) == 'R'){
                    direction = 2;
                    row_num--;
                    logger.info("**** turning and moving up");
                }

                else {
                    direction = 3;
                    logger.info("**** turning down");
                }
            }
        }

        return (path+'F');
    }

    private boolean pathVerifyy(String path, int row_num){
        String row = maze.get(row_num);
        String row_above;
        String row_below;
        char move;
        int len = row.length();
        int direction = 1; //1 = forward, 2 = up, 3 = down, 4 back
        int current_col = 0;

        for (int j=0; j<path.length(); j++){
            move = path.charAt(j);
            row = maze.get(row_num);
            row_below = maze.get(row_num+1);
            row_above = maze.get(row_num-1);

            if (move != 'F' && move != 'R' && move != 'L'){
                return false;
            }

            if (current_col == len-1){
                return true;
            }

            else if (current_col < 0){
                return false;
            }

            if (direction == 1){
                if (move == 'F'){
                    if (row.charAt(current_col+1) == ' '){
                        current_col++;
                        logger.info("**** moving forward");
                    }
                }

                else if (move == 'R'){
                    direction = 3;
                    logger.info("**** turning down");
                }

                else if (move == 'L'){
                    direction = 2;
                    logger.info("**** turning up");
                }
            }

            else if (direction == 2){
                if (move == 'F'){
                    if (row_above.charAt(current_col) == ' '){
                        row_num--;
                        logger.info("**** moving up");
                    }
                }

                else if (move == 'R'){
                    direction = 1;
                    logger.info("**** turning right");
                }

                else if (move == 'L'){
                    direction = 4;
                    logger.info("**** turning left");
                }
            }

            else if (direction == 3){
                if (move == 'F'){
                    if (row_below.charAt(current_col) == ' '){
                        row_num++;
                        logger.info("**** moving down");
                    }
                }

                else if (move == 'R'){
                    direction = 4;
                    logger.info("**** turning left");
                }

                else if (move == 'L'){
                    direction = 1;
                    logger.info("**** turning right");
                }
            }

            else if (direction == 4){
                if (move == 'F'){
                    if (row.charAt(current_col-1) == ' '){
                        current_col--;
                        logger.info("**** moving backwards");
                    }
                }

                else if (move == 'R'){
                    direction = 2;
                    logger.info("**** turning up");
                }

                else if (move == 'L'){
                    direction = 3;
                    logger.info("**** turning down");
                }
            }
        }    

        return false;
    }

    private boolean pathVerify(String path, int entry_line){ //processes the maze with given path and returns a boolean representing whether or not path is valid
        String row = maze.get(entry_line);
        boolean valid_path = false;
        int lines = maze.size();
        int len = row.length();
        int current_line = entry_line;
        int current_col = 0;

        char move;
        int direction = 1;
        
        //while (current_col < len && current_line < lines){
    
        for (int j=0; j<path.length(); j++){
            move = path.charAt(j);
            if (move == 'F'){
                if (row.charAt(current_col) == ' '){
                    current_col++;
                }
            }
            else if (move == 'R'){

            }
        }      
        //}
        
        if (current_col == len){
            valid_path = true;
        }

        return valid_path;
    }

    private int findEntrance(){
        int lines = maze.size();
        int len = 0;
        int entry_line = 0;
        String row = "";

        for (int i=0; i<lines; i++){
            row = maze.get(i);
            len = row.length();

            if (row.charAt(0) == '#'){
                logger.info("**** no entrance, pass");
            }

            else{
                logger.info("**** entrance found!");
                entry_line = i;
                i=lines;
            }
        }

        return entry_line;
    }

    public static void main(String[] args) { //process args and provide UI statements
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

                if (cmd.hasOption("p")){
                    boolean valid = m.pathVerifyy(args[3], entry_line);
                    System.out.println(valid);
                }
                    
                else {
                    logger.info("**** Computing path"); //info
                    String path = m.pathFinderr(entry_line);
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
