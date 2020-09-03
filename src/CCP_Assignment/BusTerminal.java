package CCP_Assignment;

import java.util.Random;

public class BusTerminal {

    int maxCapacity;
    int customerCounter = 0;
    boolean max = false;
    String busTerminalName;

    public BusTerminal(String busTerminalName){
        this.busTerminalName = busTerminalName;
        this.maxCapacity = 100;
    }

    public String getBusEntrance(){
        return this.busTerminalName;
    }

    public synchronized void increaseCustomerCounter(){
        customerCounter += 1;
    }

    public void enterBusTerminal(Customer customer){
        synchronized (this){

            if(!max) {
                try {
                    Thread.sleep((new Random().nextInt(4) + 1) * 1000);
                } catch (Exception e) {
                }
                System.out.println("(" + java.time.LocalTime.now().withNano(0) + " - Terminal" + ")\t" + "Customer " + customer.getName() + " reached the terminal entrance");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                customer.entered = true;
                increaseCustomerCounter();
                System.out.println("(" + java.time.LocalTime.now().withNano(0) + " - Terminal" + ")\t" + "Customer " + customer.getName() + " has entered the bus terminal" + " (Bus Terminal Cap: " + this.customerCounter + ")");
                max = (customerCounter == maxCapacity) ? true : false;
            } else {
                if(customerCounter < (int)(0.7*maxCapacity)){
                    max = false;
                    customer.busTerminal.enterBusTerminal(customer);
                } else {
                    try{
                        Thread.sleep(5000);
                    }catch(Exception e){}
                    System.out.println("CUSTOMER " + customer.getName() + " IS WAITING AT ENTRANCE");
                }
            }
        }
        if(customer.entered && customer.ticket == false){
            if(customer.ticketMachine != null){
                customer.ticketMachine.sellTicket(customer);
            } else {
                customer.ticketCounter.sellTicket(customer);
            }
        }
    }
}
