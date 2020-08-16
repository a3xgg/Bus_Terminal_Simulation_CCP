package CCP_Assignment;

import java.util.Random;

public class Bus extends Thread{

    int maxCapacity;
    int busCounter = 0;
    boolean isFull = false;
    boolean arrived = false;

    public Bus(String busName){
        setName(busName);
        this.maxCapacity = 12;
    }

    public void enterBus(Customer customer){
        synchronized (this){
            try{
                Thread.sleep(1500);
            } catch(Exception e){}
            System.out.println("("+java.time.LocalTime.now().withNano(0) + " - " + getName() + ")\t" + " Customer " + customer.getName() + " has entered " + getName() + " (Current Bus Capacity: " + (busCounter + 1)+")");
            incrementBusCounter();
        }
    }

    public synchronized void incrementBusCounter(){
        busCounter += 1;
    }

    @Override
    public void run(){
        while(true){
//            try{
//                Thread.sleep(50000);
//            } catch(Exception e){}
//            System.out.println(getName() + " has arrived! Onboard the Bus!");
//            arrived = true;
//            isFull = busCounter == maxCapacity ? true : false;
//            while(arrived && !isFull){
//                synchronized (this){
//                    this.notify();
//                }
//            }
//
//            try{
//                Thread.sleep(1000);
//            } catch(Exception e){}
//            System.out.println(getName() + " has left");
//            arrived = false;
//            busCounter = 0;
            try{
                Thread.sleep(15000);
                System.out.println(getName()  + " has arrived!");
                arrived = true;
                if(arrived){
                    while(!isFull){
                        isFull = busCounter == maxCapacity ? true : false;
                        synchronized (this){
                            this.notify();
                        }
                    }
                }
                Thread.sleep(1000);
                System.out.println(getName()+" has left");
                busCounter = 0;
                isFull = arrived = false;
            } catch(Exception e){}
        }
    }
}
