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

public abstract class Observer {
    public abstract void update();

}
/*

class NorthObserver extends Observer{
    public NorthObserver(){
        protected North N = North.getInstance();
    }

    public void update(){

    }
}

class SouthObserver extends Observer{
    public SouthObserver(){
        protected South S = South.getInstance();
    }

    public void update(){
        
    }
}

class EastObserver extends Observer{
    public EastObserver(){
        protected East E = East.getInstance();
    }

    public void update(){
        
    }
}

class WestObserver extends Observer{
    public WestObserver(){
        protected West W = West.getInstance();
    }

    public void update(){
        
    }
}
*/