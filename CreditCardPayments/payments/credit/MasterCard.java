package payments.credit;

import entities.Item;
import entities.Logger;
import entities.Person;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import reports.CreditCardReport;
import reports.Reporter;
import transactions.SecureTransaction;


public class MasterCard  extends CreditCard implements Reporter, SecureTransaction {
    
    // class variables 
    private static ArrayList<String> issuedNumbers = new ArrayList<>(); 
    
    // instance variables
    private double interestRate;
    protected String cardNumber; 
    protected double totalFees;
    
    // bad design here
    protected ArrayList<Item> purchases;
    protected ArrayList<Date> transactionTime;
    
    
    // constructor 
    public MasterCard(Person cardHolder, double creditLimit) {
        
        super(cardHolder, creditLimit, 0);
        
        type = "MasterCard";
        totalFees = 0;
        
System.out.println("");
System.out.println("//////////////////////////////////////////////");    
System.out.println("Section 2.1");       
System.out.println("//////////////////////////////////////////////");  

        // code that gives a card an interest rate depending on the
        // credit score of the cardholder
        if(cardHolder.getCreditScore() >= 740){
            interestRate = 10.99;
        }else if (cardHolder.getCreditScore() >= 670){
            interestRate = 12.50;
        }else{
            interestRate = 14.99;
        }
          
        generateCardNumber();  
        purchases = new ArrayList<Item>();
        transactionTime = new ArrayList<Date>();
    
    }
    
    // instance method  
    private void generateCardNumber(){
         
        boolean hasBeenIssued = false;   
        Random renGen = new Random();
        cardNumber = "";      // string     
         
        // generate card number
        // creates a random create number that has not been
        // issued in the past
        
        // 8234 7456 6307 7049 
        // in the end you get a string that looks like this
        
        do {
           for(int i=1; i<20; i++){
                if(i%5==0){
                    cardNumber = cardNumber + " "; // adds a space for card number
                }else{
                    int rnd = renGen.nextInt(10); 
                    cardNumber += rnd; // appends the rnd# to card number
                }
                for(String str: issuedNumbers){ // Checking all issued card numbers
                    if(str.equals(cardNumber)){
                        hasBeenIssued = true;
                    }
                }
           } 
        } while(hasBeenIssued);  
        issuedNumbers.add(cardNumber);
    }
    
    protected  void fees(){
        
        double fee =0;
       
        if(purchases.size() > 0 && purchases.size()%3 == 0){ // math for fees
            fee = balance * interestRate/500;
            totalFees +=fee;
            
            String pattern = "MM-dd-YYYY|HH:mm:ss"; // date formatting
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String dateStr = simpleDateFormat.format(new Date()); 
            
            
            String sender = "MC-" + cardHolder.getLastName(); // Fee output
            String message ="<" + dateStr + ">" + String.format("  $%-5.2f fee charged",
                    fee);
            Logger.output(sender, message);
            
        }
       
        
    }
    
    public void makePurchase(Item item){
        
        if(item.getPrice() <= (creditLimit - balance)){ // simple balance/purchase output
            
            Date date = new Date();
            
            balance += item.getPrice();
            purchases.add(item);
            transactionTime.add(date); 
            
            String pattern = "MM-dd-YYYY|HH:mm:ss"; // date formatting
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String dateStr = simpleDateFormat.format(new Date());
            
            String sender = "MC-" + cardHolder.getLastName(); // fee formatting
            String message = "<" + dateStr + ">" + "  Charged " + item.getName() +
                    " for $" + item.getPrice();
            
            Logger.output(sender, message);
            
            fees();
            
        }else{ // credit limit
            String pattern = "MM-dd-YYYY|HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String dateStr = simpleDateFormat.format(new Date());
            
            String sender = "MC-" + cardHolder.getLastName();
            String message = "<" + dateStr + ">" +
                    " Charge declined due to credit limits";
            Logger.output(sender, message);
            
        }
    }
    
    
        public void makePurchase(Item item, String pin){
        
        if(item.getPrice() <= (creditLimit - balance)){ // now with pin
            
            Date date = new Date();
            
            balance += item.getPrice();
            purchases.add(item);
            transactionTime.add(date);
              
            String pattern = "MM-dd-YYYY|HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String dateStr = simpleDateFormat.format(new Date());
            
            String sender = "MC:SECURE-" + cardHolder.getLastName()+ "-" + pin;;
            String message = "<" + dateStr + ">" + "  Charged " + item.getName() +
                    " for $" + item.getPrice();
            
            Logger.output(sender, message);
            
            fees();
            
        }else{
            String pattern = "MM-dd-YYYY|HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String dateStr = simpleDateFormat.format(new Date());
            
            String sender = "MC:SECURE-" + cardHolder.getLastName();
            String message = "<" + dateStr + ">" +
                    " Charge declined due to credit limits";
            Logger.output(sender, message);
        }
    }
    

    public void infoReport(){
        
        Map<String, Object> map = new LinkedHashMap<>();
        
        ArrayList<String> header = new ArrayList<>();
        
        header.add("");
        header.add("=======================================");
        header.add(type + " Info Report");
        header.add("=======================================");
  
        map.put("Card Holder", cardHolder.getFirstName() + " " +
                cardHolder.getLastName());
        map.put("Number", "\t"+ cardNumber);
        map.put("Interest Rate", String.format("%-4.2f%%",interestRate));
        map.put("Credit Limit", String.format("%-10.2f", creditLimit));
        map.put("Balance", String.format("\t%-10.2f",  balance));
        map.put("Remaining Credit", String.format("%-10.2f", (creditLimit - balance)) );
        map.put("Fees", String.format("\t%-10.2f", totalFees ));
        map.put("Transaction Count", transactionTime.size());
        
       CreditCardReport creditCardReport = new CreditCardReport(header, map);
       creditCardReport.display();
        
    }

    public double getInterestRate() {
        return interestRate;
    }
    
    
    
    //==============================================
    // reporter interface methods
    //==============================================
    public void runReports(){
        infoReport();
    }
    
    // ------------------------------------------------------
    // SecureTransaction Interface
    // ------------------------------------------------------


    public void secureTransaction(Item item) {
         String pin = generateTransactionSecret();
         makePurchase(item, pin);
    }
    
    
        public String  generateTransactionSecret(){
            
System.out.println("");
System.out.println("//////////////////////////////////////////////");     
System.out.println("Section 10");       
System.out.println("//////////////////////////////////////////////");  
                 
        // for creditcard types    
        // 2341 4833 9332 9933   <- card number
        // 2    4    9    9      <- selected number
        // pin is 2499           <- generated pin

        String pin = ""; 
        
        for(int i=0; i<20; i++){ 
            if(i%5==0){
                pin += cardNumber.charAt(i);
            }
        }
        
        // debugging code
        // System.out.println("Pin:\t" + pin);
       
        return pin;
     }
    
}//end class