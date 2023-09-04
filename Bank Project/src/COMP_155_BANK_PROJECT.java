/*
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import java.util.Scanner;
import java.util.ArrayList;


/**
 *
 * @author Carson
 * @author Braiden
 * 
 */
public class COMP_155_BANK_PROJECT {

   
    public static void main(String[] args) {
        
        
        Customer myCustomer = new Customer("John","Doe",334456);
     
        myCustomer.Basic_Account_ArrayList.add(new Basic_Account(1000.0, 0));
        myCustomer.CD_Account_ArrayList.add(new CD_Account(1000.0, 0, 0.05));
        myCustomer.Money_Market_Account_ArrayList.add(new Money_Market_Account(1000.0, 0));
        
        System.out.println("========== BANK TRANSFER SYSTEM ==========");
        int YesNo = 1;
        while(YesNo == 1) {
        

        Scanner accChoice = new Scanner(System.in);
        System.out.println("PLEASE SELECT ACCOUNT TO WITHDRAW FROM: " + "\n [1] BASIC ACCOUNT" + "\n [2] CD ACCOUNT" + "\n [3] MONEY MARKET ACCOUNT\n");
        
        int account = accChoice.nextInt();

        

        if (account == 1){

            System.out.println("BASIC ACCOUNT SELECTED\n====================");
            
            
            
            System.out.println("CURRENT BALANCE : " + myCustomer.Basic_Account_ArrayList.get(0).getBalance() + "\n ACCOUNT NO. : " + myCustomer.Basic_Account_ArrayList.get(0).getAccountNumber() );
            
            Scanner choiceTwo = new Scanner(System.in);
            System.out.println("PLEASE SELECT DEPOSIT ACCOUNT: " + "\n [1] CD ACCOUNT" + "\n [2] MONEY MARKET ACCOUNT");
            int accountTwo = choiceTwo.nextInt();

            if (accountTwo == 1){
                
             
                
                Scanner transferAmount = new Scanner(System.in);
                System.out.println("CD ACCOUNT SELECTED\n====================" + "\nENTER TRANSFER AMOUNT: ");
                double i = transferAmount.nextInt();

                myCustomer.Basic_Account_ArrayList.get(0).transferFunds_CD(myCustomer.Basic_Account_ArrayList.get(0), myCustomer.CD_Account_ArrayList.get(0), i);
                System.out.println("FOR BASIC ACCOUNT: \n====================");

                myCustomer.Basic_Account_ArrayList.get(0).displayInfo();
                System.out.println("FOR CD ACCOUNT: \n====================");

                myCustomer.CD_Account_ArrayList.get(0).displayInfo();
                
                
                


            }            

            if (accountTwo == 2){

                
             
                Scanner transferAmount = new Scanner(System.in);
                
                System.out.println("MONEY MARKET ACCOUNT SELECTED\n====================\nENTER TRANSFER AMOUNT: ");
                
                double i = transferAmount.nextInt();
                myCustomer.Basic_Account_ArrayList.get(0).transferFunds_MM(myCustomer.Basic_Account_ArrayList.get(0), myCustomer.Money_Market_Account_ArrayList.get(0), i);
                System.out.println("FOR BASIC ACCOUNT: \n====================");
                myCustomer.Basic_Account_ArrayList.get(0).displayInfo();
                
                System.out.println("FOR MONEY MARKET ACCOUNT: \n====================");
                myCustomer.Money_Market_Account_ArrayList.get(0).displayInfo();

                
            }
            

        }

        if (account == 2){
            
            System.out.println("CD ACCOUNT SELECTED\n====================");
            System.out.println("CURRENT BALANCE: " + myCustomer.CD_Account_ArrayList.get(0).getBalance()  + "\n ACCOUNT NO. : " +myCustomer.CD_Account_ArrayList.get(0).getAccountNumber());
                                    
            Scanner choiceTwo = new Scanner(System.in);
            
            System.out.println("PLEASE SELECT DEPOSIT ACCOUNT" + "\n [1] BASIC ACCOUNT" + "\n [2] MONEY MARKET ACCOUNT");
            int accountTwo = choiceTwo.nextInt();          

            if (accountTwo== 1){

                
                Scanner transferAmount = new Scanner(System.in);
                
                System.out.println("BASIC ACCOUNT SELECTED\n====================" + "\nENTER TRANSFER AMOUNT: ");
                double i = transferAmount.nextInt();
                
                myCustomer.CD_Account_ArrayList.get(0).transferFunds_BA(myCustomer.CD_Account_ArrayList.get(0),myCustomer.Basic_Account_ArrayList.get(0) ,i);
                System.out.println("FOR CD ACCOUNT: \n====================");
                myCustomer.CD_Account_ArrayList.get(0).displayInfo();
                System.out.println("FOR BASIC ACCOUNT: \n====================");
                myCustomer.Basic_Account_ArrayList.get(0).displayInfo();
               

                                
                


            }            

            if (accountTwo == 2){
                
                Scanner transferAmount = new Scanner(System.in);
                
                System.out.println("MONEY MARKET ACCOUNT SELECTED\n====================" + "\nENTER TRANSFER AMOUNT: ");
                
                double i = transferAmount.nextInt();
                
                myCustomer.CD_Account_ArrayList.get(0).transferFunds_MM(myCustomer.CD_Account_ArrayList.get(0),myCustomer.Money_Market_Account_ArrayList.get(0) ,i);
                System.out.println("FOR CD ACCOUNT: \n====================");
                myCustomer.CD_Account_ArrayList.get(0).displayInfo();
                System.out.println("FOR MONEY MARKET ACCOUNT: \n====================");
                myCustomer.Money_Market_Account_ArrayList.get(0).displayInfo();
             
            }



        }

        if (account == 3){

            System.out.println("MONEY MARKET ACCOUNT SELECTED\n====================");

            
            
            System.out.println("CURRENT BALANCE: " + myCustomer.Money_Market_Account_ArrayList.get(0).getBalance()  + "\n ACCOUNT NO. : " +myCustomer.Money_Market_Account_ArrayList.get(0).getAccountNumber());
            
            Scanner choiceTwo = new Scanner(System.in);
            System.out.println("PLEASE SELECT DEPOSIT ACCOUNT: " + "\n [1] BASIC ACCOUNT" + "\n [2] CD ACCOUNT");
            int accountTwo = choiceTwo.nextInt();

            if (accountTwo== 1){

                
                 
                    System.out.println("BASIC ACCOUNT SELECTED\n====================" + "\nENTER TRANSFER AMOUNT: ");
                    
                      Scanner transferAmount = new Scanner(System.in);
                      double i = transferAmount.nextInt();
                myCustomer.Money_Market_Account_ArrayList.get(0).transferFunds_BA(myCustomer.Money_Market_Account_ArrayList.get(0), myCustomer.Basic_Account_ArrayList.get(0), i);
                System.out.println("FOR MONEY MARKET ACCOUNT: \n====================");
                myCustomer.Money_Market_Account_ArrayList.get(0).displayInfo();
                System.out.println("FOR BASIC ACCOUNT: \n====================");
                myCustomer.Basic_Account_ArrayList.get(0).displayInfo();
                
              
                }



                       

            if (accountTwo== 2){

       

                    Scanner transferAmount = new Scanner(System.in);
                    System.out.println("CD ACCOUNT SELECTED\n===================="+ "\nENTER TRANSFER AMOUNT: ");
                  double i = transferAmount.nextInt();

                        myCustomer.Money_Market_Account_ArrayList.get(0).transferFunds_CD(myCustomer.Money_Market_Account_ArrayList.get(0),myCustomer.CD_Account_ArrayList.get(0), i);
                        System.out.println("FOR MONEY MARKET ACCOUNT: \n====================");
                        myCustomer.Money_Market_Account_ArrayList.get(0).displayInfo();
                        System.out.println("FOR CD ACCOUNT: \n====================");
                        myCustomer.CD_Account_ArrayList.get(0).displayInfo();
                    }
                
        

        }
        
        
            System.out.println("SELECT AN OPTION: \n[1] PERFORM ANOTHER TRANSFER\n[2]EXIT PROGRAM" );
            
            Scanner input = new Scanner(System.in);
            int i = input.nextInt();
            if(i == 2) {
                
                System.out.println("HAVE A NICE DAY.\n====================");
                
                
            YesNo = i;
            
            
    }
        
    }
    
}  

}    
