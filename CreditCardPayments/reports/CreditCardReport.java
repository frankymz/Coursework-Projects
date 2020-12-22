package reports;

import java.util.ArrayList;
import java.util.Map;

public class CreditCardReport extends Report { 

    // constructor 
    public CreditCardReport(ArrayList<String> header, Map<String, Object> reportData) {
        super(header, reportData); // keyword super
    }
    
    // instance method 
    public void display() {
        
        for(String str:header){
            System.out.println(str);
        }//end for
        
        for (Map.Entry<String, Object> entry : reportData.entrySet()) { // output
            
            if(entry.getKey().equals("Remaining Credit") || 
                    entry.getKey().equals("Transaction Count") ){ // card / rewards
               System.out.println(entry.getKey() + ":\t" + entry.getValue().toString());
            }else{
                System.out.println(entry.getKey() + ":\t\t" +
                    entry.getValue().toString());
            }//end if else
        }//end for
        
        System.out.println("");
        
    }
    
}//end class