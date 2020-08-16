package CCP_Assignment;

import java.util.Random;

public class WaitingArea extends Thread{

    boolean isFull;
    int maxCapacity;
    int waitingAreaCustomerCounter = 0;

    public WaitingArea(String waitingAreaName){
        setName(waitingAreaName);
        this.maxCapacity = 10;
    }

    public synchronized int incrementCounter(){
        return waitingAreaCustomerCounter += 1;
    }

    public synchronized int decrementCounter(){
        return waitingAreaCustomerCounter -= 1;
    }

    public synchronized void enterWaitingArea(Customer customer){
        isFull = waitingAreaCustomerCounter == maxCapacity ? true : false;
        if(!isFull){
            try{
                Thread.sleep((new Random().nextInt(2) + 1) * 1000);
                System.out.println("(" + java.time.LocalTime.now().withNano(0) + " - " + getName() + ")\t" + "Customer " + customer.getName() + " has REACHED " + getName());
                Thread.sleep(1000);
                incrementCounter();
                System.out.println("(" + java.time.LocalTime.now().withNano(0) + " - " + getName() + ")\t" + "Customer " + customer.getName() + " has ENTERED " + getName() + "\t(Current Capacity: " + waitingAreaCustomerCounter + ")");
            } catch(Exception e){}
//            synchronized (customer.bus){
//                try{
//                    customer.bus.wait();
//                } catch(Exception e){}
//            }
//            customer.bus.enterBus(customer);
//            customer.bus.incrementBusCounter();
        } else{
            customer.foyer.enterFoyer(customer);
        }
    }

    @Override
    public void run(){
        while(true){
            if(!isFull){
                try{
                    synchronized (this){ this.notify();}
                }catch(Exception e){}
            } else {
                System.out.println(getName() + " is FULL please proceed to the Foyer");
                try{
                    Thread.sleep(5000);
                } catch(Exception e){}
            }
        }
    }
}
