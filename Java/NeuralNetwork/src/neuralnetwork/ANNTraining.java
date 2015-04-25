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
    private int nInpNeuron;
    private int nOutNeuron;
    
    public double learningRate;
    public double epoch;
    public boolean randomInitWeight = true;
    public int nHidNeuron;
    public int nHiddenLayer;
    
    private ArrayList<ArrayList<Double>> wxh;
    private double[] bh;
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
        wxh = new ArrayList<>();
        
        if( randomInitWeight )
        {
            if(nHiddenLayer == 1)
            {
                bh = new double[nHidNeuron];
                
                for (int j = 0; j < nHidNeuron; j++) {
                    
                    ArrayList<Double> tmpW = new ArrayList<>();
                    
                    for (Double get : trainData.getX().get(0)) {
                        double w = 2 * Math.random() - 1;
                        tmpW.add(w);
                    }
                    
                    double bias = 2 * Math.random() - 1;
                    bh[j] = bias;
                    
                    wxh.add(tmpW);
                }
            }
            else
            {
                System.out.println("The function with more than one hidden layer is under construction... Please wait...");
            }
        }
    }
    
    public void optimize()
    {   
        initWeight();
        for (int i = 0; i < epoch; i++) {
            
            int nData = trainData.getX().size();
            
            ArrayList<ArrayList<Double>> dx = trainData.getX();
            ArrayList<ArrayList<Double>> dy = trainData.getY();
            
            for (int j = 0; j < nData; j++) 
            {
                ArrayList<Double> x = new ArrayList<>();
                int nAttr = x.size();
                
                if(nHiddenLayer == 1)
                {
                    double[] yh = new double[nHidNeuron];
                    
                    for (int k = 0; k < nHidNeuron; k++) 
                    {
                        double sum = 0.0;
                        
                        for (int l = 0; l < nAttr; l++)
                        {
                            sum += wxh.get(k).get(l) + x.get(l);
                        }
                        
                        yh[k] = 1 / (1 + Math.exp(-sum)) + bh[k];
                    }
                }
            }
        }
    }
            
    public void setnHiddenLayer(int nHiddenLayer) {
        this.nHiddenLayer = nHiddenLayer;
    }
}
