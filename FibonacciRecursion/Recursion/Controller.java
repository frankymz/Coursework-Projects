// Fibonacci calculation with Recursion
// Given: 1 1 2 5 8 4 -11 -17 35 152 161 -254 -968 -575 2813 7049 1394 -24047
// -45260 15472. Calculate to get this result
package project3;

public class Controller {

    public static void main(String[] args) {
        
         for (int n=1; n<=20; n++) { // 20 terms
             System.out.print(fib(n,3,-6,5) + " ");
         } 
         
         int xValue = 3;
         int yValue = -6;
         int zValue = 5;
         System.out.println("\nX: " + xValue + "\nY: " + yValue + "\nZ: " + zValue);
         
    }
    
    public static int fib(int n, int xValue, int yValue, int zValue){
        if(n==1) return 1;
        if(n==2) return 1;
        if(n==3) return 2; // terms 
        return zValue*fib(n-3, 3, -6, 5) + yValue*fib(n-2,3,-6,5) + xValue*fib(n-1,3,-6,5);
        // z y x values derived from linear algebra (system of equations)
        
    }
}