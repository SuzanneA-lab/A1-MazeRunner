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
    ArrayList<String> maze = new ArrayList<String>();

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
            //System.err.println("/!\\ An error has occured /!\\"); //error
        }
    }

    private String pathFinder(int entry_line){ //processes string and returns its canonical path
        String path = "";
        String row = maze.get(entry_line);
        int lines = maze.size();
        int len = row.length();
        int line_num = entry_line;
        
        for (int j=0; j<len; j++){
            row = maze.get(line_num);
            if (row.charAt(j) == ' '){
                path = path + "F";
            }
            //add else statement accounting for turns 
        }
        
        return path;

    }

    private boolean pathVerify(String path, int entry_line){ //processes the maze with given path and returns a boolean representing whether or not path is valid
        String row = maze.get(entry_line);
        boolean valid_path = false;
        int lines = maze.size();
        int len = row.length();
        int current_line = entry_line;
        int current_col = 0;
        
        //while (current_col < len && current_line < lines){
    
        for (int j=0; j<path.length(); j++){
            if (path.charAt(j) == 'F'){
                if (row.charAt(current_col) == ' '){
                    current_col++;
                }
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
        //System.out.println("** Starting Maze Runner"); //info

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")){
                logger.info("**** Reading the maze from file", args[1]);
                m.fileProcessor(args[1]);
                int entry_line = m.findEntrance(); 

                if (cmd.hasOption("p")){
                    boolean valid = m.pathVerify(args[3], entry_line);
                    System.out.println(valid);
                }
                    
                else {
                    logger.info("**** Computing path"); //info
                    String path = m.pathFinder(entry_line);
                    System.out.println(path);
                }

                //System.out.println("**** Reading the maze from file " + args[1]); //info
                
                /**
                BufferedReader reader = new BufferedReader(new FileReader(args[1]));
                String line;
                while ((line = reader.readLine()) != null) {
                    for (int idx = 0; idx < line.length(); idx++) {
                        if (line.charAt(idx) == '#') {
                            System.out.print("WALL "); //trace
                        } else if (line.charAt(idx) == ' ') {
                            System.out.print("PASS "); //trace
                        }
                    }
                    System.out.print(System.lineSeparator());
                }
                **/
                
            }

        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
            System.out.println("PATH NOT COMPUTED"); //info
            //System.err.println("/!\\ An error has occured /!\\"); //error
        }

        logger.info("** End of MazeRunner"); //info
    }
}
