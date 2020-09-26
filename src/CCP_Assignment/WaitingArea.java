package CCP_Assignment;

import java.util.Random;

public class WaitingArea extends Thread{

    boolean isFull;                         // Checks whether the waiting area is full
    int maxCapacity;                        // Counter to be used in initializing a random maxCapacity value at runtime
    int waitingAreaCustomerCounter = 0;     // Counter for the number of Passengers currently waiting in the Waiting Area

    // CONSTRUCTOR
    public WaitingArea(String waitingAreaName){
        setName(waitingAreaName);
        this.maxCapacity = 10;
    }


    public void enterWaitingArea(Customer customer){
        synchronized (this){
            isFull = (waitingAreaCustomerCounter == maxCapacity) ? true : false;
            if(!isFull){
                try{
                    Thread.sleep((new Random().nextInt(2) + 1) * 1000);
                    System.out.println("(" + java.time.LocalTime.now().withNano(0) + " - " + getName() + ")\t" + "Customer " + customer.getName() + " has REACHED " + getName());
                    Thread.sleep(1000);
                    waitingAreaCustomerCounter += 1;    // Increase the counter every time a passenger succesfully enters the waiting area
                    customer.enteredWaitingArea = true; // Variable in customer class to determine whether a customer has entered the waiting area
                    System.out.println("(" + java.time.LocalTime.now().withNano(0) + " - " + getName() + ")\t" + "Customer " + customer.getName() + " has ENTERED " + getName() + "\t(Current Capacity: " + waitingAreaCustomerCounter + ")");
                } catch(Exception e){}

            } else{
                customer.foyer.enterFoyer(customer);    // If the isFull variable is true, passengers will enter the foyer.
            }
        }
        if(!customer.enteredBus){
            synchronized (customer.bus){
                try{
                    customer.bus.wait();    // If a customer is inside the waiting area, they will need to wait for the bus notification through this function
                } catch(Exception e){}
                customer.bus.enterBus(customer);    // Enters the bus once it arrives
            }
        }
    }

    //OVERRIDE FUNCTION from Thread class
    @Override
    public void run(){
        while(true){        // Waiting Area Thread will keep running
            isFull = waitingAreaCustomerCounter == maxCapacity ? true: false;   // Set the value of isFull variable to respective boolean values depending whether the waiting area has reached max capacity
            if(!isFull){
                try{
                    synchronized (this){ this.notify();}    // If the waiting area is not full, it will notify a single thread
                }catch(Exception e){}
            } else {
                System.out.println(getName() + " is FULL please proceed to the Foyer"); // If it is full then it will stop notifying and display a message every 10s
                try{
                    Thread.sleep(10000);
                } catch(Exception e){}
            }
        }
    }
}
