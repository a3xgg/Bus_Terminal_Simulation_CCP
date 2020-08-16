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

    public synchronized void enterBus(Customer customer){
        System.out.println("("+java.time.LocalTime.now().withNano(0) + " - " + getName() + ")\t" + " Customer " + customer.getName() + " has entered " + getName() + " (Current Bus Capacity: " + (busCounter + 1)+")");
    }

    public synchronized void incrementBusCounter(){
        busCounter += 1;
    }

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(50000);
                System.out.println(getName()  + " has arrived!");
                arrived = true;
                if(arrived){
                    while(!isFull){
                        isFull = busCounter == maxCapacity ? true : false;
                        synchronized (this){
                            this.notifyAll();
                        }
                    }
                }
                Thread.sleep(1000);
                System.out.println(getName()+" has left");
                busCounter = 0;
                isFull = false;
                arrived = false;
            } catch(Exception e){}
        }
    }
}
