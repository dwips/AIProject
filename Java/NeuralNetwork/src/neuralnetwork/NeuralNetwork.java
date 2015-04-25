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
        datahandler.setnInput(3);
        datahandler.setnOutput(2);
        datahandler.loadData("D:/data.txt");
        
        ANNData trainData = datahandler.getData();
        
        ANNTraining train = new ANNTraining();
        train.setData(trainData);
        train.optimize();
        
    }
    
}
