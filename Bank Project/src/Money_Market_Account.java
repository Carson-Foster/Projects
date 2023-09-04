/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Carson
 */
    public class Money_Market_Account extends Basic_Account {
        
    private int withdrawalCount = 0;
    
    public void setWithdrawalCount(int n) {
        this.withdrawalCount = n;
    }
    public int getWithdrawalCount() {
        return this.withdrawalCount;
    }
    
    public Money_Market_Account(double initialBalance, int initialAccountNumber) { 

        super(initialBalance, initialAccountNumber);

        this.setBalance(initialBalance);

        this.setAccountNumber(initialAccountNumber);

    
    }
    
    @Override
    public void withdrawal(double withdrawalAmount) {
        
       if(this.withdrawalCount > 2) {
           
       super.withdrawal(withdrawalAmount + 1.5);
       System.out.println("A WITHDRAWAL FEE OF 1.50$ HAS BEEN DEDUCTED FROM THIS ACCOUNT.");
       this.setWithdrawalCount(this.getWithdrawalCount()+ 1);
   } else {
        super.withdrawal(withdrawalAmount);
        this.setWithdrawalCount(this.getWithdrawalCount()+ 1);
     }
       
       
   }   
    
   final public void transferFunds_BA(Money_Market_Account A, Basic_Account B, double transferAmount) {
       A.withdrawal(transferAmount);
       B.deposit(transferAmount); 
   }
   final public void transferFunds_CD(Money_Market_Account A, CD_Account B, double transferAmount) {
       A.withdrawal(transferAmount);
       B.deposit(transferAmount);
   }
    
    
    
    
}
