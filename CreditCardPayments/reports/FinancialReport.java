package reports;

import java.util.ArrayList;
import java.util.Map;

public class FinancialReport extends PersonReport {
    
    // constructor 

    public FinancialReport(ArrayList<String> header, Map<String, Object> reportData) {

        super(header, reportData);
        System.out.println("");
    }

}