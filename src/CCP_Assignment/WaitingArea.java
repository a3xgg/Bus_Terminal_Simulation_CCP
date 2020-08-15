package CCP_Assignment;

import java.util.Random;

public class WaitingArea {

    String waitingAreaName;
    boolean isFull;
    int waitingAreaCustomerCounter = 0;

    public WaitingArea(String waitingAreaName){
        this.waitingAreaName = waitingAreaName;
    }

    public String getWaitingAreaName(){
        return this.waitingAreaName;
    }

    public synchronized int incrementCounter(){
        return waitingAreaCustomerCounter += 1;
    }

    public synchronized void enterWaitingArea(Customer customer){
        isFull = waitingAreaCustomerCounter == 10 ? true : false;
        if(!isFull){
            try{
                Thread.sleep((new Random().nextInt(2) + 1) * 1000);
                System.out.println("(" + java.time.LocalTime.now().withNano(0) + " - " + getWaitingAreaName() + ")\t" + "Customer " + customer.getName() + " has REACHED " + getWaitingAreaName());
                Thread.sleep(1000);
                incrementCounter();
                System.out.println("(" + java.time.LocalTime.now().withNano(0) + " - " + getWaitingAreaName() + ")\t" + "Customer " + customer.getName() + " has ENTERED " + getWaitingAreaName() + "\t(Current Capacity: " + waitingAreaCustomerCounter + ")");
            } catch(Exception e){}
        } else{

        }
    }
}
