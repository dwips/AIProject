/*
 * 
 */

package neuralnetwork;

/**
 * @author Fuad Ikhlasul Amal
 */
public class ANNTraining 
{   
    private int nHiddenLayer;
    private int nInpNeuron;
    private int nOutNeuron;
    
    private double learningRate;
    
    private double[] optimumWeight;
            
    private ANNData trainData;
    
    public ANNTraining() {}
    
    public ANNTraining(ANNData d) {
        this.trainData = d;
    }

    public void setData(ANNData data) {
        this.trainData = data;
    }
    
    public void optimize()
    {
        System.out.println(trainData.getX().toString());
        System.out.println(trainData.getY().toString());
    }
            
    public void setnHiddenLayer(int nHiddenLayer) {
        this.nHiddenLayer = nHiddenLayer;
    }
    
}
