package reports;

import java.util.ArrayList;
import java.util.Map;


public class LoggerReport extends Report{
    
    // constructor  
    public LoggerReport(ArrayList<String> header, Map<String, Object> reportData) {
        super(header, reportData);
    }
    
    // instance method 
     public void display() { // output
        
        for(String str:header){
            System.out.println(str);
        }
        
        for (Map.Entry<String, Object> entry : reportData.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
        
        System.out.println("");
    }
    
}//end class