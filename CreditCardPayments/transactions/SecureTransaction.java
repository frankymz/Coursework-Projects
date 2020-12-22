package transactions;

import entities.Item;

public interface SecureTransaction {
    
    // interface can have static constants
    // this is static final public
    final double VERSION = 0.9;
    
    // interface can have variable
    // can not do this why
    // int numberOfSecureTransaction = 0;
    // double totalTransactionAmount = 0;
    
    
    // this is public method
    // public not needed
    String generateTransactionSecret();
    
    public void secureTransaction(Item item);
    
  
    // Java interface can jave static methods
    // public static method
    
    static double version(){
        return VERSION;
    }

}//end class