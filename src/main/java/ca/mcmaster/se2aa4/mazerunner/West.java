package ca.mcmaster.se2aa4.mazerunner;

public class West extends Direction {
    private static West instance = new West();

    private West(){
        newdirection = "W";
    }

    public static West getInstance(){
        return instance;
    }

    protected void setTiles(){
        right_tile = row_above.charAt(col_num);
        front_tile = row.charAt(col_num-1); 
    }
 
    protected void moveForward(){
        newdirection = "W";
        newcoords[0] = row_num;
        newcoords[1] = col_num-1;
        path = "F";
    }

    protected void turnLeft(){
        newdirection = "S";
        newcoords[0] = row_num;
        newcoords[1] = col_num;
        path = "L";
    }

    protected void OnlyturnRight(){
        newcoords[0] = row_num;
        newcoords[1] = col_num;
        newdirection = "N";
        path = "R";
    }

    protected void turnRight(){
        newcoords[0] = row_num-1;
        newcoords[1] = col_num;
        newdirection = "N";
        path = "RF";
    }
}