/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



/**
 *
 * @author Carson
 */

public class CD_Account extends Basic_Account {
    
    private double interest;
    
    public double getInterest() {
        
        return this.interest;
    }
    public void setInterest(double newInterest) {
        this.interest = newInterest;
    }
    
   public CD_Account(double initialBalance, int initialAccountNumber, double initialInterest) {

    

        super(initialBalance, initialAccountNumber);

        this.setBalance(initialBalance);

        this.setAccountNumber(initialAccountNumber);

        this.setInterest(initialInterest);

       
        
        

       

   }
   
    @Override
    public void displayInfo() {
        System.out.println("CURRENT BALANCE: " + this.getBalance() +"\nACCOUNT NO. : " +this.getAccountNumber() + "\nCURRENT INTEREST RATE: " + (this.getInterest()*100) + "%");
    }
   
    final public void transferFunds_BA(CD_Account A, Basic_Account B, double transferAmount) {
        A.withdrawal(transferAmount);
        B.deposit(transferAmount);
    }
    final public void transferFunds_MM(CD_Account A, Money_Market_Account B, double transferAmount) {
        A.withdrawal(transferAmount);
        B.deposit(transferAmount);
    }
    
    
    
    
    
}
