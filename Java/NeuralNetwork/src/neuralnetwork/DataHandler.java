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
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * @author Fuad Ikhlasul Amal
 */
public class DataHandler 
{
    private ANNData data;
    
    private BufferedReader reader;
    
    private String errorMessage = "Error DataHandler - ";
    
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
            ArrayList<ArrayList<Double>> dx = new ArrayList<>();
            ArrayList<ArrayList<Double>> dy = new ArrayList<>();
            
            if(type.equals("txt"))
            {
                reader = new BufferedReader(new FileReader(f));
                
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
                    
                    for (int i = 0; i < elm.length; i++) 
                    {
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
            }
            else if(type.equals("xls"))
            {
                Workbook wb = Workbook.getWorkbook(f);
                Sheet sheet = wb.getSheet(0);
                int nAttr = sheet.getColumns();
                int nData = sheet.getRows();
                
                if( !isValidInputOutput(nAttr) )
                {
                    nOutput = 1;
                    nInput = nAttr - nOutput;
                }
                
                for (int i = 0; i < nData; i++) 
                {
                    ArrayList<Double> x = new ArrayList<>();
                    ArrayList<Double> y = new ArrayList<>();
                    
                    for (int j = 0; j < nAttr; j++) 
                    {   
                        Double d = Double.parseDouble(sheet.getCell(j, i).getContents());
                        
                        if(j < nInput)
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
            }
            else
            {
                errorMessage += "File data source is not allowed.";
                System.exit(0);
            }
            
            data.setX(dx);
            data.setY(dy);
        } 
        catch (FileNotFoundException ex)
        {
            errorMessage += "File not found!!!";
        } 
        catch (IOException ex) 
        {
            errorMessage += "IO failure!!!";
        }
        catch (BiffException ex)
        {
            errorMessage += "Error while try to open Excel file!!!";
        }
        finally
        {
            System.out.println("No error in DataHandler.");
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
