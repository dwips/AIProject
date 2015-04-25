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
    private ArrayList<ArrayList<Double>> who;
    private double[] bh;
    private double[] bo;
    private double[] optimumWeight;
            
    private ANNData trainData;
    
    public ANNTraining() {}
    
    public ANNTraining(ANNData d) {
        trainData = d;
    }

    public void setData(ANNData data) {
        trainData = data;
    }
    
    private void setInOutNeuron()
    {
        nOutNeuron = trainData.getY().get(0).size();
        nInpNeuron = trainData.getX().get(0).size();
    }
    
    private void initWeight()
    {
        wxh = new ArrayList<>();
        who = new ArrayList<>();

        if(nHiddenLayer == 1)
        {
            bh = new double[nHidNeuron];
            bo = new double[nOutNeuron];

            for (int j = 0; j < nHidNeuron; j++)
            {
                ArrayList<Double> tmpWin = new ArrayList<>();
                double w;
                
                for (int i = 0; i < nInpNeuron; i++)
                {
                    w = randomInitWeight ? (2 * Math.random() - 1) : 0.0;
                    tmpWin.add(w);
                }
                
                double bias = randomInitWeight ? (2 * Math.random() - 1) : 0.0;
                bh[j] = bias;
                wxh.add(tmpWin);
                
                ArrayList<Double> tmpWout = new ArrayList<>();
                
                for (int i = 0; i < nOutNeuron; i++)
                {
                    bias = randomInitWeight ? (2 * Math.random() - 1) : 0.0;
                    bo[i] = bias;
                    w = randomInitWeight ? (2 * Math.random() - 1) : 0.0;
                    tmpWout.add(w);
                }
                
                who.add(tmpWout);
            }
        }
        else
        {
            
        }
    }
    
    public void optimize()
    {   
        setInOutNeuron();
        initWeight();
        
        for (int i = 0; i < epoch; i++)
        {
            int nData = trainData.getX().size();
            
            ArrayList<ArrayList<Double>> dx = trainData.getX();
            ArrayList<ArrayList<Double>> dy = trainData.getY();
            
            for (int j = 0; j < nData; j++) 
            {
                System.out.println("");
                System.out.println("Fetching data row-" + (j + 1) + "...");
                
                ArrayList<Double> x = dx.get(j);
                
                if(nHiddenLayer == 1)
                {
                    double[] yh = new double[nHidNeuron];
                    double[] yo = new double[nOutNeuron];
                    
                    double sum = 0.0;
                    
                    for (int k = 0; k < nHidNeuron; k++)
                    {
                        sum = 0.0;
                        
                        System.out.println("Calculate data at hidden neuron-" + (k + 1));
                        
                        for (int l = 0; l < nInpNeuron; l++)
                        {   
                            System.out.print("sum = x(" +(l + 1)+ ") * w(" +(k + 1)+ "," +(l + 1)+ ") + ");
                            
                            sum += wxh.get(k).get(l) * x.get(l);
                        }
                        
                        System.out.print(" + bias ");
                        yh[k] = 1 / (1 + Math.exp(-sum)) + bh[k];
                        System.out.print(" = " + sum);
                        System.out.println("");
                        System.out.println("1 / (1 + exp(-sum)) = " + yh[k]);
                    }
                    
                    for (int k = 0; k < nOutNeuron; k++)
                    {
                        sum = 0.0;
                        
                        for (int l = 0; l < nHidNeuron; l++) 
                        {
                            sum += yh[l] * who.get(l).get(k);
                        }
                        
                        yo[k] = 1 / (1 + Math.exp(-sum)) + bo[k];
                    }
                }
                else
                {
                    System.out.println("The function with more than one hidden layer is under construction... Please wait...");
                    System.exit(0);
                }
            }
        }
    }
            
    public void setnHiddenLayer(int nHiddenLayer) {
        this.nHiddenLayer = nHiddenLayer;
    }
}
