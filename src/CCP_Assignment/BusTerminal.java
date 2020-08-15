package CCP_Assignment;

import java.util.Random;

public class BusTerminal {

    int maxCapacity;
    int customerCounter = 0;
    boolean max = false;
    String busTerminalName;

    public BusTerminal(String busTerminalName){
        this.busTerminalName = busTerminalName;
        this.maxCapacity = 20;
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
            if(!max){
                try{
                    Thread.sleep((new Random().nextInt(4) + 1) * 1000);
                } catch(Exception e){}
                System.out.println("(" + java.time.LocalTime.now().withNano(0) + " - " + getBusEntrance() + ")\t" + "Customer " + customer.getName() + " reached the terminal entrance");
                try{
                    Thread.sleep(1000);
                } catch(Exception e){}
                customer.entered = true;
                customer.busTerminal.increaseCustomerCounter();
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
