/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
/**
 *
 * @author Carson
 */
public class Customer {
  private String firstName;
  private String lastName;
    
  private int SSN;
  
  public Customer(String fName, String lName, int n) {
      this.setFirstName(fName);
      this.setLastName(lName);
      this.setSSN(n);
  }
 
  
  public ArrayList <Basic_Account> Basic_Account_ArrayList = new ArrayList <Basic_Account>();
  public ArrayList <Money_Market_Account> Money_Market_Account_ArrayList = new ArrayList <Money_Market_Account>();
  public ArrayList <CD_Account> CD_Account_ArrayList = new ArrayList <CD_Account>();

  public String getFirstName() {
      return firstName;
  }
  public String getLastName() {
      return lastName;
  }  
  
  public void setFirstName(String newFirstName) {
      this.firstName = newFirstName;
  }
  public void setLastName(String newLastName) {
      this.lastName = newLastName;
  }
  
  public int getSSN() {
      return this.SSN;
  }
  public void setSSN(int newSSN) {
      this.SSN = newSSN;
  }
    
}
