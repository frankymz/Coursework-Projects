// instanceof, casting types, interfaces, overriding methods, Map & HashMap, output
// formatting
package app;

import entities.Item;
import entities.Logger;
import entities.Person;
import payments.Cash;
import payments.SecureCash;
import payments.credit.CreditCard;
import payments.credit.MasterCard;
import payments.credit.MasterRewards;
import reports.Reporter;
import transactions.SecureTransaction;

public class Controller {

    public static void main(String[] args) {
        
System.out.println("//////////////////////////////////////////////");    
System.out.println("Section 1");       
System.out.println("//////////////////////////////////////////////");  
    
    
Item item1 = new Item("Mitutoyo 513-403-10E",
        "DIAL TEST INDICATOR, BASIC SET, STANDARD, .008 IN, . 0001 IN GRAD,"
                + " WHITE DIAL ", 139.16);

Item item2 = new Item("Garmin Forerunner 935",
        "Iconic clog that started a comfort revolution around the world", 284.49);

Item item3 = new Item("CROC Classic Clog",
        "Iconic clog that started a comfort revolution around the world", 40.99);

Item item4 = new Item("Adidas Ultra Boost PB",
        "Offers a plush, yet bouncy ride with an updated upper is built for speed.", 
        105.95);

Item item5 = new Item("Macbook Pro 13",
        "1.4GHz quad-core 8th-generation Intel Core i5 processor", 1399.00);
      
  
Person person1 = new Person("Ava", "Davis", "704 Brickell Ave", "Superman", 760);

Person person2 = new Person("John", "Smith", "340 S.W. 13 Street", "myPassword123", 680);

Person person3 = new Person("Bill", "Johnson", "100 Ocean Dr. S.W. ", "Mom12345", 530);
   

System.out.println("");
System.out.println("//////////////////////////////////////////////");    
System.out.println("Section 2");       
System.out.println("//////////////////////////////////////////////");  
        
       Cash AvaCash = new Cash(person1, 400); // Ava's wallet
      
       // Master Card instance for Ava; person1 with credit limit of $3500
       MasterCard AvaMasterCard = new MasterCard(person1, 3500);
       
       // adding the cash and master card instance to Ava's wallet
       person1.getWallet().add(AvaCash);
       person1.getWallet().add(AvaMasterCard);
       
       person1.financialReport();
       
       Cash JohnCash = new Cash(person2, 400); // Setting up John's wallet
       MasterCard JohnMasterCard = new MasterCard(person2, 500);
       MasterRewards JohnMasterRewards = new MasterRewards(person2, 1500);
       
       // adding cash, master card and master reward instance to John's wallet
       person2.getWallet().add(JohnCash);
       person2.getWallet().add(JohnMasterCard);
       person2.getWallet().add(JohnMasterRewards);
      
       person2.financialReport();
       
       SecureCash BillSecureCash = new SecureCash(person3, 1000); // Bill's wallet
       MasterCard BillMasterCard = new MasterCard(person3, 1000);
       MasterRewards BillMasterRewards = new MasterRewards(person3, 1500);
            
       // adding the secure cash, master card and master reward instance to Bill's wallet
       person3.getWallet().add(BillSecureCash);
       person3.getWallet().add(BillMasterCard);
       person3.getWallet().add(BillMasterRewards);
       
       person3.financialReport();
       
System.out.println("");
System.out.println("//////////////////////////////////////////////");    
System.out.println("Section 3.2");       
System.out.println("//////////////////////////////////////////////");  
        
            AvaMasterCard.infoReport(); // calling reports on cards
                      
            JohnMasterCard.infoReport();
            
            BillMasterRewards.infoReport();
            
System.out.println("");
System.out.println("//////////////////////////////////////////////");     
System.out.println("Section 4");       
System.out.println("//////////////////////////////////////////////");  


        //=========================================================
        // people buying stuff
        //=========================================================
        
        System.out.println("\n");
        System.out.println("---------------------------------------------");
        System.out.println(person1.getFirstName() + " " + person1.getLastName() +
                " buying");
        System.out.println("---------------------------------------------");
     
        AvaMasterCard.makePurchase(item1);  // Using Ava's master card to buy item1
        AvaMasterCard.makePurchase(item2); 
        AvaCash.makePurchase(item2); 
  
        // Ava buying item5 using her master card
        ((MasterCard)person1.getWallet().get(1)).makePurchase(item5);
        
         person1.financialReport();
         
        System.out.println("\n");
        System.out.println("---------------------------------------------");
        System.out.println(person2.getFirstName() + " " + person2.getLastName() +
                " buying");
        System.out.println("---------------------------------------------");
       
        JohnMasterCard.makePurchase(item1);  // Using John's master card to buy items
        JohnMasterCard.makePurchase(item2);
        JohnMasterCard.makePurchase(item3);
        JohnMasterCard.makePurchase(item4);
        
        JohnCash.makePurchase(item2);
        
        JohnMasterRewards.makePurchase(item1);  // Use John's master rewards to buy items
        JohnMasterRewards.makePurchase(item2);
        JohnMasterRewards.makePurchase(item3);
        JohnMasterRewards.makePurchase(item4);
        JohnMasterRewards.makePurchase(item5);

        // John buying item1 using master rewards
        ((MasterRewards)person2.getWallet().get(2)).makePurchase(item1);
       
         person2.financialReport();
         
        System.out.println("\n");
        System.out.println("---------------------------------------------");
        System.out.println(person3.getFirstName() + " " + person3.getLastName() +
                " buying");
        System.out.println("---------------------------------------------");
        
        BillSecureCash.makePurchase(item1); // Bill buying stuff
        BillSecureCash.makePurchase(item2);
        BillSecureCash.makePurchase(item5);
        
        BillMasterCard.makePurchase(item4);

        BillMasterRewards.makePurchase(item3);       
       
        person3.financialReport();

System.out.println("");
System.out.println("//////////////////////////////////////////////");     
System.out.println("Section 5");       
System.out.println("//////////////////////////////////////////////");          

        System.out.println("");  // Class Static Info
        System.out.println("---------------------------------------------------");
        System.out.println("Class Statics");
        System.out.println("---------------------------------------------------");
           
        // methods were added to classes for this to work, code was also added to 
        // constructors
        System.out.println("Number of Person in System:\t\t\t" +
                Person.getPersonCount());
        System.out.println("Number of all Cash type transactions:\t\t" + 
                Cash.getCashTransactionCount());
        System.out.println("Number of all Credit Card type transactions:\t" + 
                CreditCard.getCreditTransactionCount());
        System.out.println("---------------------------------------------------");
 
        
System.out.println("");
System.out.println("//////////////////////////////////////////////");     
System.out.println("Section 6");       
System.out.println("//////////////////////////////////////////////");  

        System.out.println("");
        System.out.println("---------------------------------------------------");
        System.out.println("Logger Data");
        System.out.println("---------------------------------------------------");
        
        Logger messages = new Logger(); // See all the messages stored in the logger 
        messages.runReports();

        System.out.println("---------------------------------------------------");   

System.out.println("");
System.out.println("//////////////////////////////////////////////");     
System.out.println("Section 7");       
System.out.println("//////////////////////////////////////////////");    

        System.out.println(""); // Interfaces
        System.out.println("---------------------------------------------------");
        System.out.println("REPORTER INTERFACE");
        System.out.println("---------------------------------------------------");
        
        // Objects can be of various types, UML Diagrams helped
        processReports(person1);   // processReport is in this class
        processReports(messages);
        processReports(BillSecureCash);
        processReports(AvaMasterCard);
        
System.out.println("");
System.out.println("//////////////////////////////////////////////");     
System.out.println("Section 8");       
System.out.println("//////////////////////////////////////////////");          

        System.out.println("\n\n\n");
        System.out.println("---------------------------------------------------");
        System.out.println("SECURE INTERFACE");
        System.out.println("---------------------------------------------------");
        
        processSecureTransaction(AvaMasterCard); // method is in this class
        processSecureTransaction(BillSecureCash);   
        
    }// end main()

    public Controller() {
    }
    
    //----------------------------------------
    // class methods 
    //----------------------------------------  
    
        // To remember understanding of these methods: 
        // which object type is excuting this?
        // which code is running?
    public static void processReports(Reporter reporter){    
        reporter.runReports();    
    }
    
    public static void processSecureTransaction(SecureTransaction secureTransaction){
        
        String pin = secureTransaction.generateTransactionSecret();
        
        if(secureTransaction instanceof CreditCard){ // Output Name with Pin
            String firstName =
                    ((CreditCard)secureTransaction).getCardHolder().getFirstName(); 
            String lastName =
                    ((CreditCard)secureTransaction).getCardHolder().getLastName();
            
            System.out.println(firstName + " " + lastName +
                    " (MASTER CARD)  secure transaction pin:"  + pin);
            
        }else if(secureTransaction instanceof SecureCash){
            String firstName =
                    ((SecureCash)secureTransaction).getPerson().getFirstName();  
            String lastName =
                    ((SecureCash)secureTransaction).getPerson().getLastName();
            
            System.out.println(firstName + " " + lastName +
                    " (SECURE CASH) secure  transaction pin:"  + pin);
        }//end if-else   
    }
       
    public static void makePurchase(CreditCard creditCard, Item item){
        
            creditCard.makePurchase(item);  
    }
    
    public static void makePurchase(Cash cash, Item item){
        
            cash.makePurchase(item);  
    }
    
     public static void makePurchase(Object obj, Item item){
        
            if(obj instanceof CreditCard){
                ((CreditCard)obj).makePurchase(item); 
            }else if (obj instanceof Cash){
                ((Cash)obj).makePurchase(item);
            }       
    }

}//end class