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
    }

    @Override
    public void run(){
        while(true){
            try{
                //Thread.sleep(150000);
                Thread.sleep((new Random().nextInt(5) + 15) * 1000);
            } catch(Exception e){}
            System.out.println(getName()  + " has arrived!");
            arrived = true;
            while(arrived){
                synchronized (this){
                    this.notify();
                }
                if(busCounter == maxCapacity){
                    break;
                }
            }
            //try{Thread.sleep(1000);} catch(Exception e){}
            System.out.println(getName()+" has left");
            busCounter = 0;
            isFull = arrived = false;
        }
    }
}
