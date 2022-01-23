package models;

public class Tile {
    private char state = ' ';

    public char getState() {
        return state;
    }

    public boolean changeState(char token) {
        if(token == 'X' || token == 'O') { // check if valid token
            if (state == ' ') { // check if current tile state is not set
                state = token;

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
