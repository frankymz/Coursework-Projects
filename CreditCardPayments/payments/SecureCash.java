package payments;

import entities.Item;
import entities.Logger;
import entities.Person;
import java.text.SimpleDateFormat;
import java.util.Date;
import reports.Reporter;
import transactions.SecureTransaction;


public class SecureCash extends Cash implements Reporter, SecureTransaction{
    

    // class variables 
    // this is used to create a Pin later
    private static final String[] letterIndex = { " ", "a", "b", "c", "d", "e", "f",
        "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
        "w", "x", "y", "z" };
    
    // Constructor
    public SecureCash(Person person, double amount) {
        super(person, amount);
    }
    
    // instance method
    public void makePurchase(Item item, String pin){
        
        if(item.getPrice() <= super.getAmount()){
            substractCash(item.getPrice());
            
            String pattern = "MM-dd-YYYY|HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String dateStr = simpleDateFormat.format(new Date());
            
            String sender = "SECURECASH-" + super.getPerson().getLastName()+ "-" + pin;
            String message = "<" + dateStr + ">" + "  Purchased " + item.getName() +
                    " for $" + item.getPrice();
            
            Logger.output(sender, message);
            
            Cash.setCashTransactionCount(Cash.getCashTransactionCount()+1);
            
        }else{
            String pattern = "MM-dd-YYYY|HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String dateStr = simpleDateFormat.format(new Date());
            
            String sender = "SECURECASH-" + super.getPerson().getLastName() + "-" + pin;
            String message = "<" + dateStr + ">" + "  Do not enough cash to buy " +
                    item.getName() + " for $" + item.getPrice();
            Logger.output(sender, message);
        }
        
    }
    
    // Interface Methods
    // Reporter Interface
     public void runReports(){
         
     }
     
     
    // SecureTransaction Interface
    public String  generateTransactionSecret(){
         
System.out.println("");
System.out.println("//////////////////////////////////////////////");     
System.out.println("Section 9");       
System.out.println("//////////////////////////////////////////////");  
        
        // Explanation: first four letters of the last name 
        // Index:  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 ..... 25 26
        // Letter: a b c d e f g h i j  k  l  m  n  o  ....  y  z       
        // Example:
        // W   h   i   t   first four letters
        // 23  8   9   20  index to alphabet location
        // 3   8   9   0   %10 mod
        // 3890 <- final pin
        
         String pin = "";
         String firstName = this.getPerson().getFirstName();
         firstName = firstName.substring(0, 4);
         firstName = firstName.toLowerCase();
         
         for(int i = 0; i<firstName.length(); i++){
             String letter = firstName.substring(i, i+1); // creates string with indexes
             for(int j = 1; j < letterIndex.length; j++){
                 if(letter.equals(letterIndex[j])){
                     int temp = j%10;  // mod letter index by 10 to generate pin
                     pin = pin + temp;
                 }
             }
         }
           
        //debugging code
        //System.out.println("Pin:\t" + pin);
        return pin;
     }
    
    public void secureTransaction(Item item){
        String pin = generateTransactionSecret();
        makePurchase(item, pin);
    }
    
}