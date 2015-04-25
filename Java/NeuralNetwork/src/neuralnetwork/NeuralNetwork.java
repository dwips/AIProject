package neuralnetwork;

/**
 * @author Fuad Ikhlasul Amal
 */
public class NeuralNetwork {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        DataHandler datahandler = new DataHandler();
        datahandler.loadData("D:/data.xls");
        
        ANNData trainData = datahandler.getData();
        
        ANNTraining ann = new ANNTraining();
        ann.setData(trainData);
        
        ann.epoch = 1;
        ann.learningRate = 0.1;
        ann.nHidNeuron = 3;
        ann.nHiddenLayer = 1;
        
        ann.optimize();
        
    }
    
}
