/*
 *
 */

package neuralnetwork;

import java.util.ArrayList;

/**
 * @author Fuad Ikhlasul Amal
 */
public class ANNData {
    
    private ArrayList<ArrayList<Double>> x;
    private ArrayList<ArrayList<Double>> y;

    public ArrayList<ArrayList<Double>> getX() {
        return x;
    }

    public void setX(ArrayList<ArrayList<Double>> x) {
        this.x = x;
    }

    public ArrayList<ArrayList<Double>> getY() {
        return y;
    }

    public void setY(ArrayList<ArrayList<Double>> y) {
        this.y = y;
    }

}
