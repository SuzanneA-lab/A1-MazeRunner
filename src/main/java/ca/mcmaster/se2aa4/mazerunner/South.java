package ca.mcmaster.se2aa4.mazerunner;

public class South extends Direction {
    private static South instance = new South();

    private South(){
        newdirection = "S";
    }

    public static South getInstance(){
        return instance;
    }
 
    protected void setTiles(){
        right_tile = row.charAt(col_num-1);
        front_tile = row_below.charAt(col_num); 
    }

    protected void moveForward(){
        newdirection = "S";
        newcoords[0] = row_num+1;
        newcoords[1] = col_num;
        path = "F";
    }

    protected void turnLeft(){
        newdirection = "E";
        newcoords[0] = row_num;
        newcoords[1] = col_num;
        path = "L";
    }

    protected void OnlyturnRight(){
        newcoords[0] = row_num;
        newcoords[1] = col_num;
        newdirection = "W";
        path = "R";
    }

    protected void turnRight(){
        newcoords[0] = row_num;
        newcoords[1] = col_num-1;
        newdirection = "W";
        path = "RF";
    }

}