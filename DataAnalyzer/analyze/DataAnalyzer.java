package analyze;

import app.ExecutionInfo;
import java.util.ArrayList;

public class DataAnalyzer {
    
    private ArrayList<ExecutionInfo> data; 

    public DataAnalyzer() {
        data = new ArrayList<>();
    }

    public ArrayList<ExecutionInfo> getData() {
        return data;
    }
    
    public void findGrowthRate(){
        
         System.out.println("");
         System.out.println("-------------------------------------------------");
         System.out.println("Data Analyzer Growth Rate");
         System.out.println("-------------------------------------------------");
         
         for(int i = 0; i <data.size(); i++){ // output
     System.out.printf("Index: %-3s FibValue: %-10d Calls: %-10.0f Exponent: %-10.3f\n",
                data.get(i).getFibIndex(),
                data.get(i).getFibValue(),
                data.get(i).getTotalMethodCalls(),
                Math.log(data.get(i).getTotalMethodCalls())); 
         } // for
    }
}