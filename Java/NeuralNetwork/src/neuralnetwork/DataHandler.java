/*
 *
 */

package neuralnetwork;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Fuad Ikhlasul Amal
 */
public class DataHandler 
{
    private ANNData data;
    
    private BufferedReader reader;
    
    private String errorMessage = "Error in class DataHandler!!! ";
    
    private int nInput;
    private int nOutput;

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getnInput() {
        return nInput;
    }

    public void setnInput(int nInput) {
        this.nInput = nInput;
    }

    public int getnOutput() {
        return nOutput;
    }

    public void setnOutput(int nOutput) {
        this.nOutput = nOutput;
    }
    
    private boolean isNotSetInputOutput()
    {
        return ((nInput == 0) && (nOutput == 0));
    }
    
    private boolean isValidInputOutput(int nElmt)
    {
        return !isNotSetInputOutput() && (nInput + nOutput <= nElmt) && nInput > 0 && nOutput > 0;
    }
    
    public void loadData(String filename)
    {
        String type = this.getFileType(filename);
        File f = new File(filename);
        data = new ANNData();
        
        try
        {
            if(type.equals("txt"))
            {
                reader = new BufferedReader(new FileReader(f));
                
                ArrayList<ArrayList<Double>> dx = new ArrayList<>();
                ArrayList<ArrayList<Double>> dy = new ArrayList<>();
                
                String line;
                
                while((line = reader.readLine()) != null)
                {
                    String[] elm = line.split(" ");
                    
                    if( !isValidInputOutput(elm.length) )
                    {
                        nOutput = 1;
                        nInput = elm.length - nOutput;
                    }
                    
                    ArrayList<Double> x = new ArrayList<>();
                    ArrayList<Double> y = new ArrayList<>();
                    
                    for (int i = 0; i < elm.length; i++) {
                        Double d = Double.parseDouble(elm[i]);
                        if(i < nInput)
                        {
                            x.add(d);
                        }
                        else
                        {
                            y.add(d);
                        }
                    }
                    
                    dx.add(x);
                    dy.add(y);
                }
                
                data.setX(dx);
                data.setY(dy);
            }
        } 
        catch (FileNotFoundException ex) 
        {
            errorMessage += "File not found!!!";
        } 
        catch (IOException ex) 
        {
            errorMessage += "IO failure!!!";
        }
    }
    
    public void loadData(File f)
    {
        String filename = f.getName();
        String type = this.getFileType(filename);
    }
    
    private String getFileType(String filename)
    {
        return filename.substring(filename.lastIndexOf(".") + 1, filename.length());
    }
    
    public void normalize()
    {
        
    }
    
    public ANNData getData()
    {
        return data;
    }
}
