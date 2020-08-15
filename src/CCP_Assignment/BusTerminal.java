package CCP_Assignment;

import java.util.Random;

public class BusEntrance {

    int maxCapacity;
    int customerCounter = 0;
    boolean max = false;
    String busTerminalName;

    public BusEntrance(String busTerminalName){
        this.busTerminalName = busTerminalName;
        this.maxCapacity = 10;
    }

    public String getBusEntrance(){
        return this.busTerminalName;
    }

    public synchronized void increaseCustomerCounter(){
        customerCounter += 1;
    }

    public void enterBusTerminal(Customer customer){
        synchronized (this){
            max = (customerCounter == maxCapacity) ? true : false;
            try{
                Thread.sleep((new Random().nextInt(4) + 1) * 1000);
            } catch(Exception e){}
            System.out.println("(" + java.time.LocalTime.now().withNano(0) + " - " + getBusEntrance() + ")\t" + "Customer " + customer.getName() + " reached the terminal entrance");
            if(!max){

                synchronized (this){
                    customer.busEntrance.increaseCustomerCounter();
                }

                try{
                    Thread.sleep(1000);
                } catch(Exception e){}
                customer.entered = true;
                System.out.println("(" + java.time.LocalTime.now().withNano(0) + " - " + getBusEntrance() + ")\t" + "Customer " + customer.getName() + " has entered the bus terminal" + "(Current Capacity: " + customerCounter+")");
            } else {
                System.out.println("(" + java.time.LocalTime.now().withNano(0) + " - " + getBusEntrance() + ")\t" + "Customer " + customer.getName() + " is currently waiting at the entrance");
                try{
                    Thread.sleep(5000);
                } catch(Exception e){}
            }
        }
        if(customer.entered && customer.ticket == false){
            customer.ticketCounter.sellTicket(customer);
        }
    }
}
