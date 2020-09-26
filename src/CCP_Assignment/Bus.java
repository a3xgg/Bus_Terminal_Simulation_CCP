package CCP_Assignment;

import java.util.Random;

public class Bus extends Thread{

    int maxCapacity;            // Variable to set the max capacity - max capacity of bus is 12
    int busCounter = 0;         // The number of passengers that has entered the bus
    boolean isFull = false;     // Checks whether the bus is full
    boolean arrived = false;    // Checks whether the bus has arrived

    // Constructor
    public Bus(String busName){
        setName(busName);
        this.maxCapacity = 12;
    }

    public void enterBus(Customer customer){    // Function that allows a customer to enter the bus
        synchronized (this){
            if(!isFull){
                try{
                    Thread.sleep(1500);
                } catch(Exception e){}
                System.out.println("("+java.time.LocalTime.now().withNano(0) + " - " + getName() + ")\t" + " Customer " + customer.getName() + " has entered " + getName() + " (Current Bus Capacity: " + (busCounter + 1)+")");
                incrementBusCounter();
                customer.enteredBus = true;
                customer.busTerminal.customerCounter -= 1;
                customer.waitingArea.waitingAreaCustomerCounter -= 1;
                System.out.println("BusTerminal Count: " + customer.busTerminal.customerCounter + "\t" + "WaitingArea Count: " + customer.waitingArea.waitingAreaCustomerCounter);
                isFull = busCounter == maxCapacity ? true : false;
            } else {
                customer.waitingArea.enterWaitingArea(customer);
            }
        }
    }

    public synchronized void incrementBusCounter(){
        busCounter += 1;
    }   // Function that increments the bus counter by 1


    // Override function from Thread class
    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep((new Random().nextInt(5) + 15) * 1000);    // To simulate a bus arrival random time from 10 - 20s
            } catch(Exception e){}
            System.out.println(getName()  + " has arrived!");
            arrived = true;
            while(arrived){
                synchronized (this){
                    this.notify();      // While arrived variable is true, keep notifying passengers who are waiting in the waiting area
                }
                if(busCounter == maxCapacity){  // once the bus is full, break the loop
                    break;
                }
            }
            System.out.println(getName()+" has left");
            busCounter = 0;             // reset the bus counter
            isFull = arrived = false;   // reset both isFull and arrived to false for the next bus
        }
    }
}
