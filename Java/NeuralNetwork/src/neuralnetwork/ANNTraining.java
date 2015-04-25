/*
 * 
 */

package neuralnetwork;

import java.util.ArrayList;

/**
 * @author Fuad Ikhlasul Amal
 */
public class ANNTraining 
{   
    private int nHiddenLayer;
    private int nInpNeuron;
    private int nOutNeuron;
    
    public double learningRate;
    public double epoch;
    
    private double[] optimumWeight;
            
    private ANNData trainData;
    
    public ANNTraining() {}
    
    public ANNTraining(ANNData d) {
        this.trainData = d;
    }

    public void setData(ANNData data) {
        this.trainData = data;
    }
    
    private void initWeight()
    {
        
    }
    
    public void optimize()
    {   
        initWeight();
        for (int i = 0; i < epoch; i++) {
            int nData = trainData.getX().size();
            
            ArrayList<ArrayList<Double>> x = trainData.getX();
            ArrayList<ArrayList<Double>> y = trainData.getY();
            
            for (int j = 0; j < nData; j++) {
                for (int k = 0; k < x.get(j).size(); k++) {
                    
                }
            }
        }
    }
            
    public void setnHiddenLayer(int nHiddenLayer) {
        this.nHiddenLayer = nHiddenLayer;
    }
}
