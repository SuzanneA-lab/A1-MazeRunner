package ca.mcmaster.se2aa4.mazerunner;

public class North extends Direction {  
    private static North instance = new North();

    private North(){
        newdirection = "N";
    }

    public static North getInstance(){
        return instance;
    }

    protected void setTiles(){
        right_tile = row.charAt(col_num+1);
        front_tile = row_above.charAt(col_num); 
    }

    protected void moveForward(){
        newdirection = "N";
        newcoords[0] = row_num-1;
        newcoords[1] = col_num;
        path = "F";
    }

    protected void turnLeft(){
        newdirection = "W";
        newcoords[0] = row_num;
        newcoords[1] = col_num;
        path = "L";
    }

    protected void OnlyturnRight(){
        newcoords[0] = row_num;
        newcoords[1] = col_num;
        newdirection = "E";
        path = "R";
    }

    protected void turnRight(){
        newcoords[0] = row_num;
        newcoords[1] = col_num+1;
        newdirection = "E";
        path = "RF";
    }

}