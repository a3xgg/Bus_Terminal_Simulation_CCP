package CCP_Assignment;

import java.util.Random;

public class Foyer{

    int foyerCount;     // number of people in the foyer
    String foyerName;

    // CONSTRUCTOR
    public Foyer (String foyerName){
        this.foyerName = foyerName;
    }

    public void enterFoyer(Customer customer){
        synchronized (this){
            try {
                Thread.sleep(1500);
                foyerCount+=1;
                System.out.println("Customer " + customer.getName() + " is waiting at the Foyer" + " (Foyer: " + foyerCount +")");
            } catch(Exception e){}
        }
        if(!customer.enteredWaitingArea){
            synchronized (customer.waitingArea){
                try{
                    customer.waitingArea.wait();        // waits for the notification from the waiting area on whether the waiting area is full or not
                } catch(Exception e){}
                foyerCount -= 1;
                customer.waitingArea.enterWaitingArea(customer);    // enters the waiting area after waiting area max cap is decreased
            }
        }
    }
}
