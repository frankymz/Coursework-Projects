// Stack, Map, TreeMap, List Iterator, Fibonacci Sequence, Output Formatting, 
// ArrayList Retrieval, Stop watch
package app;

import analyze.DataAnalyzer;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import util.StopWatch;
import java.util.ListIterator;

public class Controller {
   
    public static void main(String[] args) {
         
        DataAnalyzer dataAnalyzer = new DataAnalyzer();
 
        for(int n=1; n<=35; n++){
            
            Stack<String> stack = new Stack<>(); 
            StopWatch stopWatch = new StopWatch();
            
            stopWatch.start(); // starting the stop watch
            
            // fibonacci index at n and save the value in fibValue
            // passing the stack object so it can record the recursive method calls
            int fibValue = fib(n, stack);
            
                          
            Map<String, Integer> map = new TreeMap(); // constr. with TreeMap constructor
                       
            // figures out how many times a fib(n) was called and stores this 
            // information in the map with fib(m) as the key and the times it was called 
            // as the vlaue for this key

             for(int i=1; i<=n; i++){ 
            
                 String mapString = "fib(" + i + ")"; 
                 ListIterator <String> stackIterator = stack.listIterator();
                 int counter = 0;
                                   
                 while(stackIterator.hasNext()){
                   if (stackIterator.next().equals(mapString)){ 
                       counter++;
                   } 
                 }       
                 map.put(mapString, counter);               
             }
                
            int fibIndex = n;  
            
            stopWatch.stop(); // stop the stopWatch object
                                         
            ExecutionInfo executionInfo = new ExecutionInfo(map, fibIndex, fibValue,
                    stopWatch.getElapsedTime()); // inputs for constructor
            
            // adding the executionInfo object to the dataAnalyzer arraylist
            dataAnalyzer.getData().add(executionInfo);
        } // ends the for-loop
        
        //--------------------------------------------------------------------
        
        // running the displayInfo method on each executionInfo object
        // within the dataAnalyzer arraylist using a for-loop
        
        dataAnalyzer.getData().forEach((executionInfo) -> {
            executionInfo.displayInfo();
        } ); // the hint changed it to this, not sure about this syntax but it works
             
        dataAnalyzer.findGrowthRate(); 

    }
    
    public static int fib(int n, Stack stack){
        
        // update the stack with the needed information
        stack.push("fib(" + n + ")");
        
        if(n==1) {
            return 1;
        }
        if(n==2) {
            return 2;
        }
       
        return fib(n-1,stack) + fib(n-2,stack);
    } 
}