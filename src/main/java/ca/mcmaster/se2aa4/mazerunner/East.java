package ca.mcmaster.se2aa4.mazerunner;

public class East extends Direction {
    private static East instance = new East();

    private East(){
        newdirection = "E";
    }

    public static East getInstance(){
        return instance;
    }
    
    protected void setTiles(){
        right_tile = row_below.charAt(col_num);
        front_tile = row.charAt(col_num+1); 
    }

    protected void moveForward(){
        newdirection = "E";
        newcoords[0] = row_num;
        newcoords[1] = col_num+1;
        path = "F";
    }

    protected void turnLeft(){
        newdirection = "N";
        newcoords[0] = row_num;
        newcoords[1] = col_num;
        path = "L";
    }

    protected void OnlyturnRight(){
        newcoords[0] = row_num;
        newcoords[1] = col_num;
        newdirection = "S";
        path = "R";
    }

    protected void turnRight(){
        newcoords[0] = row_num+1;
        newcoords[1] = col_num;
        newdirection = "S";
        path = "RF";
    }
}
