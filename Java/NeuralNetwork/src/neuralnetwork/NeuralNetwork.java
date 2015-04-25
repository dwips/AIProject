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
        datahandler.loadData("D:/data.txt");
        
        ANNData trainData = datahandler.getData();
        
        ANNTraining ann = new ANNTraining();
        ann.setData(trainData);
        ann.epoch = 10;
        ann.optimize();
        
    }
    
}
