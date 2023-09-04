/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Carson
 */
public class Basic_Account {
    
   protected double balance;
   protected int accountNumber;
   
   public Basic_Account(double initialBalance, int initialAccountNumber) {
       this.setBalance(initialBalance);
       this.setAccountNumber(initialAccountNumber);
       
       
   }
      
   public double getBalance() {
       return this.balance;
   }
   public void setBalance(double newBalance) {
       this.balance = newBalance;
   }
   
   public int getAccountNumber() {
       return this.accountNumber;
   }
   public void setAccountNumber(int newAccountNumber) {
       this.accountNumber = newAccountNumber;
   }
   
  public void withdrawal(double withdrawalAmount) {
        
        if(withdrawalAmount > this.balance) {
            
            System.out.println("Insufficient Funds for withdrawal. Transfer required.");
                       
        } else {
            this.setBalance(this.getBalance() - withdrawalAmount);
        }
        
    }
  public void displayInfo() {
      System.out.println("CURRENT BALANCE: " + this.getBalance() +"\nACCOUNT NO. : " +this.getAccountNumber());
   }

   
   public void deposit(double depositAmount) {
       this.setBalance(this.getBalance() + depositAmount);
   }
   
   final public void transferFunds_MM(Basic_Account A, Money_Market_Account B, double transferAmount) {
       A.withdrawal(transferAmount);
       B.deposit(transferAmount); 
   }
   final public void transferFunds_CD(Basic_Account A, CD_Account B, double transferAmount) {
       A.withdrawal(transferAmount);
       B.deposit(transferAmount);
   }
}
