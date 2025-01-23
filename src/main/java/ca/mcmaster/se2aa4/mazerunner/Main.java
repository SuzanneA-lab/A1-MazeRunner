package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLineParser;

public class Main {
    String maze;

    private static final Logger logger = LogManager.getLogger();

    private void fileProcessor(){ //reads file and assigns to maze variable

    }

    private String pathFinder(){ //processes string and returns its canonical path
        return "placeholder";
    }

    private boolean pathVerify(String path){ //processes the maze with given path and returns a boolean representing whether or not path is valid
        return true;
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
                logger.info("**** Reading the maze from file ", args[1]);
                m.fileProcessor();

                if (cmd.hasOption("p")){
                    boolean valid = m.pathVerify(args[3]);
                }
                    
                else {
                    String path = m.pathFinder();
                }

                //System.out.println("**** Reading the maze from file " + args[1]); //info

                /**BufferedReader reader = new BufferedReader(new FileReader(args[1]));
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
            //System.err.println("/!\\ An error has occured /!\\"); //error
        }
        logger.info("**** Computing path"); //info
        System.out.println("PATH NOT COMPUTED"); //info
        logger.info("** End of MazeRunner"); //info
    }
}
